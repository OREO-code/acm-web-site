<template>


  <el-container>


    <el-dialog
        title="修改管理员信息"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="content">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password" placeholder="不修改密码忽视即可">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="ruleForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="ruleForm.remarks"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center;margin-left: -100px!important;">
          <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>

    <el-dialog
        title="增加管理员"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="content">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="ruleForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="ruleForm.remarks"></el-input>
        </el-form-item>

        <el-form-item style="text-align: center;margin-left: -100px!important;">
          <el-button type="primary" @click="addFormmethods('ruleForm')">立即新增</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
    <el-main style="text-align: center">

      <div>
        <el-table
            height="660"
            :data="users"
            style="width: 100%">
          <el-table-column
              align="center"
              label="ID"
              width="150%">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column
              align="center"
              label="用户名"
              column-key="date">
            <template slot-scope="scope">

              <el-tag >{{ scope.row.username }}</el-tag>

            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="邮箱"
              column-key="date">
            <template slot-scope="scope">
              <span >{{ scope.row.email }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="手机号"
              column-key="date">
            <template slot-scope="scope">
              <span >{{ scope.row.phone }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="备注"
              column-key="date">
            <template slot-scope="scope">
              <span >{{ scope.row.remarks }}</span>
            </template>
          </el-table-column>

          <el-table-column align="center" style="width: 80px" v-if='adminShow'>
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
      currentUser:[],
      adminShow:"",

      users :[],
      dialogVisible: false,
      dialogVisible_add:false,
      ruleForm: {
        id:"",
        username:"",
        password:"",
        email:"",
        phone:"",
        remarks:""
      },
      rules: {
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ],
        remarks: [
          { required: true, message: '请输入备注', trigger: 'blur' }
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
          this.$axios.post("/updateUser",_this.ruleForm).then(res=>{
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
          console.log(_this.ruleForm)
          this.$axios.post("/addUser",_this.ruleForm).then(res=>{
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
      this.ruleForm.username = item.username;
      this.ruleForm.email = item.email;
      this.ruleForm.phone = item.phone;
      this.ruleForm.remarks = item.remarks;
    },
    open_add(){
      this.dialogVisible_add = true;
      this.ruleForm.id = "";
      this.ruleForm.username = "";
      this.ruleForm.password = "";
      this.ruleForm.email = "";
      this.ruleForm.phone = "";
      this.ruleForm.remarks = "";
    },

    page(){
      const _this = this
      _this.$axios.post("/getAllUser").then(res=>{
        _this.users = res.data.data;
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
        _this.$axios.get("/delUser?id="+id).then(res=>{
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
    const _this = this
    _this.$axios.get("/currentUser").then(res=>{
      _this.adminShow = res.data.data.username === 'admin'
      console.log(_this.adminShow)
    })


  }


}

</script>

<style>

</style>