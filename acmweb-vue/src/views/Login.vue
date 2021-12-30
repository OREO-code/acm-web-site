<template>
	<div id="app">
		<el-container>
		  <el-header></el-header>
		  <el-main>
			  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
			    <el-form-item label="用户名" prop="username">
			      <el-input v-model="ruleForm.username"></el-input>
			    </el-form-item>
			    <el-form-item label="密码" prop="password">
			      <el-input type="password" v-model="ruleForm.password"></el-input>
			    </el-form-item>
			    <el-form-item>
			      <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
			      <el-button @click="resetForm('ruleForm')">重置</el-button>
			    </el-form-item>
			  </el-form>
		  </el-main>
		</el-container>
	</div>
</template>

<script>
	 export default {
	    data() {
	      return {
			  code:'',
			  token:'',
			  msg:'',
	        ruleForm: {
	          username: '',
	          password: '',
	        },
	        rules: {
				username: [
				  { required: true, message: '请输入用户名', trigger: 'change' }
				],
	          password: [
	            { required: true, message: '请输入密码', trigger: 'change' }
	          ]
	        }
	      };
	    },
	    methods: {
			
	      submitForm(formName) {
	        this.$refs[formName].validate((valid) => {
				var _that=this
				this.$axios.post('http://101.43.16.42:8082/login',this.ruleForm).then((response)=>{
					console.log(response)
					this.msg=response.data.msg
					if(response.data.status==200){
						this.code=response.data.code
						this.token=response.data.data.token
						const token =response.data.data.token
						console.log(token)
						_that.open2()
					}
					else{
						_that.open4()
					}
				        console.log(response);
				      }).catch((response)=>{
				        console.log(response);
						_that.open4()
				      })
	        });
	      },
	      resetForm(formName) {
	        this.$refs[formName].resetFields();
	      },
		  open1() {
		          this.$message('这是一条消息提示');
		        },
		        open2() {
		          this.$message({
		            message: '登录成功',
		            type: 'success'
		          });
		        },
		        open3() {
		          this.$message({
		            message: '警告哦，这是一条警告消息',
		            type: 'warning'
		          });
		        },
		        open4() {
		          this.$message.error('登录失败，用户名或密码错误');
		        },
				open5() {
				  this.$message.error('登录异常');
				}
	    }
	  }
</script>

<style>
	.el-header, .el-footer {
	    background-color: #B3C0D1;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
	  }
	  
	  .el-aside {
	    background-color: #D3DCE6;
	    color: #333;
	    text-align: center;
	    line-height: 200px;
	  }
	  
	  .el-main {
	    color: #333;
	    text-align: center;
	    line-height: 160px;
	  }
	  
	  body > .el-container {
	    margin-bottom: 40px;
	  }
	  
	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }
	  
	  .el-container:nth-child(7) .el-aside {
	    line-height: 320px;
	  }
	  .demo-ruleForm{
		  width: 500px;
		  margin: 0 auto;
	  }
</style>
