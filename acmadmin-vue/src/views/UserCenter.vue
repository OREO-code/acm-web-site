<template>
  <el-container>
    <el-header style="text-align: center">
      <h2>你好！{{ userInfo.username }} 同学</h2>
    </el-header>

    <el-card class="box-card" style="text-align: center">

      <el-row>
        <el-col :span="12"><div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 90%;">
            <h2>个人信息</h2>
            <el-descriptions class="margin-top" :column="1" style="text-align: center" >
              <el-descriptions-item label="ID" style="text-align: center !important;">{{userInfo.id}}</el-descriptions-item>
              <el-descriptions-item label="用户名">{{userInfo.username}}</el-descriptions-item>
              <el-descriptions-item label="邮箱">{{userInfo.email}}</el-descriptions-item>
              <el-descriptions-item label="手机号">{{userInfo.phone}}</el-descriptions-item>
              <el-descriptions-item label="备注">{{userInfo.remarks}}</el-descriptions-item>
              <el-descriptions-item label="操作"> <el-button
                  type="success"
                  @click="open">编辑</el-button></el-descriptions-item>

            </el-descriptions>
          </el-card>
        </div></el-col>
        <el-col :span="12"><div class="grid-content bg-purple-light">
          <el-card class="box-card" style="width: 90%">
            <h2>修改密码</h2>
            <el-form :model="passForm" status-icon :rules="passRules" ref="passForm" label-width="100px">
              <el-form-item label="旧密码" prop="currentPass">
                <el-input type="password" v-model="passForm.oldPassword" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="password">
                <el-input type="password" v-model="passForm.newPassword1" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="passForm.newPassword2" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitPasswordForm('passForm')">提交</el-button>
                <el-button @click="resetForm('passForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div></el-col>
      </el-row>


    </el-card>

    <el-dialog
        title="修改管理员信息"
        :visible.sync="dialogVisible"
        width="30%">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="content">
          <el-input v-model="ruleForm.username"></el-input>
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
  </el-container>
</template>

<script>

	export default {
		name: "Login",
		data() {
			var validatePass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请再次输入密码'));
				} else if (value !== this.passForm.password) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			return {
        dialogVisible: false,
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
        },
				userInfo: {

				},
				passForm: {
          username:'',
          newPassword1: '',
          newPassword2: '',
          oldPassword: ''
				},
				passRules: {
          newPassword1: [
						{ required: true, message: '请输入新密码', trigger: 'blur' },
						{ min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
					],
          newPassword2: [
						{ required: true, validator: validatePass, trigger: 'blur' }
					],
          oldPassword: [
						{ required: true, message: '请输入当前密码', trigger: 'blur' },
					]
				}
			}
		},
		created() {
			this.getUserInfo()
		},
		methods: {

			getUserInfo() {
				this.$axios.get("/currentUser",localStorage.token).then(res =>{
					this.userInfo=res.data.data
          this.ruleForm.id = res.data.data.id;
          this.ruleForm.username = res.data.data.username;
          this.passForm.username = res.data.data.username;
          this.ruleForm.email = res.data.data.email;
          this.ruleForm.phone = res.data.data.phone;
          this.ruleForm.remarks = res.data.data.remarks;
				})

			},
      open(){
        this.dialogVisible = true

      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.dialogVisible = false
            const _this = this;
            this.$axios.post("/updateUser",_this.ruleForm).then(res=>{
              this.getUserInfo()
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
			submitPasswordForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {

						const _this = this
            console.log(_this.passForm)
						this.$axios.post('/updatePassword', _this.passForm).then(res => {

							_this.$alert(res.data.msg, '提示', {
								confirmButtonText: '确定',
								callback: action => {
									this.$refs[formName].resetFields();
								}
							});
						})

					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>

<style scoped>
.el-form {
	width: 420px;
	margin: 50px auto;
}


</style>