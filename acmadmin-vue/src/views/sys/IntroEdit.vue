<template>

  <div style="text-align: center">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要" prop="description">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <mavon-editor v-model="ruleForm.content"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "IntroduceEdit",
  data() {
    return {
      ruleForm: {
        id:'',
        title: '',
        description: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入摘要', trigger: 'blur'}
        ],
        content: [
          { required: true,message: '请输入内容',trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          this.$axios.post("/introduce/edit",_this.ruleForm).then(res=>{
            console.log(res)

            this.$alert("操作成功", '提示', {
              confirmButtonText: '确定',
              callback: action => {
                _this.$router.push("/")

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
  },
  created() {
    const id = this.$route.params.introduceId;
    const _this = this;
    if(id){
      this.$axios.get("/introduce/"+id).then(res=>{
        const introduce = res.data.data;
        _this.ruleForm.id = introduce.id;
        _this.ruleForm.title = introduce.title;
        _this.ruleForm.description = introduce.description;
        _this.ruleForm.content = introduce.content;
      })
    }
  }
}
</script>

<style scoped>

</style>