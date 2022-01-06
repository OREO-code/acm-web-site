<template>


  <el-container>


    <el-dialog
        title="增加成员信息"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name" >
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
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>


        <el-form-item style="text-align: center">
          <el-button type="primary" @click="addFormmethods('ruleForm')">立即增加</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>

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
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-success="GetUrl">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisibleImage">
            <img width="100%" :src="dialogImageUrl" alt="">
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
            height="660"
            max-height="100%"
            :data="members"
            style="width: 100%;line-height: normal;"
            width="150%">
          <el-table-column
              align="center"
              label="ID">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.id }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="姓名">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top">
                <p>年级: {{ scope.row.year }}</p>
                <p>学院: {{ scope.row.college }}</p>
                <p>简介: {{ scope.row.honor }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
              </el-popover>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="年级">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.year }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="学院">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.college }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="简介">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.honor }}</span>
            </template>
          </el-table-column>


          <el-table-column
              align="center"
              label="照片">
            <template slot-scope="scope">
              <el-image :src="scope.row.url"></el-image>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" style="width: 80px">
            <template slot="header" slot-scope="scope">
              <el-button type="primary" @click="open_add">新增</el-button>
            </template>

            <template slot-scope="scope">
              <el-button
                  type="success"

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

      myHeaders:{
        'Authorization' : localStorage.getItem("token")
      },

      members :[],
      dialogImageUrl: "",
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
          this.$axios.post("/addMember",_this.ruleForm).then(res=>{
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
    open(item){
      this.dialogVisible = true;
      this.ruleForm.id = item.id;
      this.ruleForm.name = item.name;
      this.ruleForm.year = item.year;
      this.ruleForm.college = item.college;
      this.ruleForm.honor = item.honor;
      this.ruleForm.url = item.url;
      this.dialogImageUrl = item.url;
    },
    open_add(){
      this.dialogVisible_add = true
      this.ruleForm.id = "";
      this.ruleForm.name = "";
      this.ruleForm.year = "";
      this.ruleForm.college = "";
      this.ruleForm.honor = "";
      this.ruleForm.url = "";
      this.dialogImageUrl = "";
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
        _this.$axios.get("/delMember?id="+id).then(res=>{
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