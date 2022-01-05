<template>


  <el-container>
    <el-header style="text-align: left">
      <el-button type="primary" @click="open_add" round>新增</el-button>
    </el-header>

    <el-dialog
        title="修改成员信息"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="id" prop="id">
          <el-input v-model="ruleForm.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="year">
          <el-input v-model="ruleForm.year"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="college">
          <el-input v-model="ruleForm.college"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="honor">
          <el-input v-model="ruleForm.honor"></el-input>
        </el-form-item>
        <el-form-item label="照片" prop="url">
          <el-upload
              action="http://101.43.16.42:8082/addImg"
              :headers="myHeaders"
              list-type="picture-card"
              :limit="1"
              :file-list="dialogImageUrl"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-success="GetUrl">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisibleImage">
            <img width="100%" :src="dialogImageUrl[0].url" alt="">
          </el-dialog>
        </el-form-item>


        <el-form-item style="text-align: center">
          <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>


    <el-main style="text-align: center">

      <div>
        <el-table
            :data="members"
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
              label="姓名"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.name }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="年级"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.year }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="学院"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.college }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="简介"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.honor }}</span>
            </template>
          </el-table-column>


          <el-table-column
              align="center"
              label="照片"
              width="180">
            <template slot-scope="scope">
              <el-image :src="scope.row.url"></el-image>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" style="width: 80px">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  @click="open(scope.row)">编辑</el-button>
              <el-button
                  size="mini"
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

      myHeaders:{
        'Authorization' : localStorage.getItem("token")
      },

      members :[],
      dialogImageUrl: [
        {url:""}
      ],
      dialogVisibleImage: false,
      dialogVisible: false,
      dialogVisible_add:false,

      ruleForm: {
        id:"",
        name:"",
        year:"",
        college:"",
        honor:"",
        url:"",
      },
      getUrlPath:"",
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        year: [
          { required: true, message: '请输入年级', trigger: 'blur' }
        ],
        college: [
          { required: true, message: '请输入年级', trigger: 'blur' }
        ],
        honor: [
          { required: true, message: '请输入年级', trigger: 'blur' }
        ],
      }

    }

  },
  methods:{
    GetUrl(response, file, fileList){
      this.ruleForm.url = response.data
      // this.getUrlPath = response.data
      // console.log(this.getUrlPath);
      // console.log("=============")
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);

    },
    handlePictureCardPreview(file) {
      console.log(file.url)
      //this.dialogImageUrl = file.url;
      this.dialogVisibleImage = true;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // this.dialogVisible = false
          // console.log(this.getUrlPath);
          //
          // this.ruleForm.url = this.getUrlPath;
          console.log(this.ruleForm)
          const _this = this
          this.$axios.post("/updateMember",_this.ruleForm).then(res=>{
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
      this.ruleForm.name = time.name;
      this.ruleForm.year = time.year;
      this.ruleForm.college = time.college;
      this.ruleForm.honor = time.honor;
      this.ruleForm.url = time.url;
      this.dialogImageUrl[0].url = time.url;



    },
    open_add(){
      this.ruleForm.time=''
      this.ruleForm.id=''
      this.ruleForm.content=''
      this.dialogVisible_add = true;
    },

    page(){
      const _this = this
      _this.$axios.post("/getAll").then(res=>{
        _this.members = res.data.data;
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