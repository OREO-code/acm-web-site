<template>


  <el-container>


    <el-dialog
        title="修改时间线"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="id" prop="id">
          <el-input v-model="ruleForm.id"></el-input>
        </el-form-item>

        <el-form-item label="时间" prop="createdTime">
          <el-date-picker v-model="ruleForm.time" value-format="yyyy-MM-ddTHH:mm:ss" type="datetime" placeholder="Please pick a date" />
        </el-form-item>



        <el-form-item label="内容" prop="content">
        <el-input v-model="ruleForm.content"></el-input>
        </el-form-item>

        <el-form-item style="text-align: center">
          <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>

    <el-dialog
        title="增加时间线"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="时间" prop="createdTime">
          <el-date-picker v-model="ruleForm.time" value-format="yyyy-MM-ddTHH:mm:ss" type="datetime" placeholder="Please pick a date" />
        </el-form-item>

        <el-form-item label="内容" prop="content">
          <el-input v-model="ruleForm.content"></el-input>
        </el-form-item>

        <el-form-item style="text-align: center">
          <el-button type="primary" @click="addFormmethods('ruleForm')">立即新增</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
    <el-main style="text-align: center">

      <div>
        <el-table
            height="660"
            :data="times"
            style="width: 100%">
          <el-table-column
              align="center"
              label="ID"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column
              align="center"
              label="日期"
              column-key="date"
              width="180">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span >{{ scope.row.time }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="内容"
              width="300">
            <template slot-scope="scope">
<!--              <el-popover trigger="hover" placement="top">-->

                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">{{ scope.row.content }}</el-tag>
                </div>
<!--              </el-popover>-->
            </template>
          </el-table-column>

          <el-table-column align="center" style="width: 80px">
            <template slot="header" slot-scope="scope">
              <el-button type="primary" @click="open_add">新增</el-button>
            </template>
            <template slot-scope="scope">
              <el-button

                  @click="open(scope.row)">编辑</el-button>
              <el-button

                  type="danger"
                  @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>

    </el-main>
  </el-container>

</template>
<script>
export default {
  name: 'Time',
  data(){
    return{
      times :[],
      dialogVisible: false,
      dialogVisible_add:false,
      ruleForm: {
        id:"",
        time:"",
        content:"...",
      },
      rules: {
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        time: [
          { required: true, message: '请选择日期', trigger: 'blur' }
        ],
      }

    }

  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogVisible = false
          const _this = this;
          console.log(_this.ruleForm)
          this.$axios.post("/updateTime",_this.ruleForm).then(res=>{
            this.page()
            this.$message({
              message: '修改成功',
              type: 'success'
            });
          }).catch(reason => {
            this.$message.error(reason);
          })

        } else {
          this.$message.error('error submit!!');
          return false;
        }
      });
    },
    addFormmethods(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogVisible_add = false
          const _this = this;
          this.$axios.post("/addTime",_this.ruleForm).then(res=>{
            _this.ruleForm.time = ''
            _this.ruleForm.content = ''
            this.page()
            this.$message({
              message: '增加成功',
              type: 'success'
            });
          }).catch(reason => {
            this.$message.error(reason);
          })

        } else {
          this.$message.error('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    open(time){
      this.dialogVisible = true;
      this.ruleForm.id = time.id;
      this.ruleForm.time = time.time
      this.ruleForm.content = time.content;
    },
    open_add(){
      this.dialogVisible_add = true;
    },

    page(){
      const _this = this
      _this.$axios.get("/time").then(res=>{
        _this.times = res.data.data.timeList;
        for(var i=0;i<_this.times.length;i++){
          var ipods=_this.times[i].time.indexOf("T")
          _this.times[i].time=_this.times[i].time.substring(0,ipods)
        }
      })
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    handleDelete(id) {
      console.log(id);
      this.$confirm('此操作将永久删除该文档, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const _this = this
        _this.$axios.get("/delTime?id="+id).then(res=>{
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.page();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  created() {
    this.page();
  }


}

</script>

<style>

</style>