<template>

  <div class="introduce" >
    <h2 style="text-align: center">{{introduce.title}}</h2>
    <el-divider><i class="el-icon-mobile-phone" ></i></el-divider>
    <div>{{introduce.description}}</div>
    <div class="markdown-body" v-html="introduce.content"></div>
  </div>
</template>

<script>
import "github-markdown-css/github-markdown.css"

export default {
  name: "IntroduceDetail",
  data() {
    return {
      introduce: {
        id: '',
        title: '',
        description: '',
        content: ''
      }
    }
  },
  created() {
    const id = this.$route.params.introduceId;
    const _this = this;
    this.$axios.get("http://101.43.16.42:8082/introduce/"+id).then(res=>{
      const introduce = res.data.data;
      _this.introduce.id = introduce.id;
      _this.introduce.title = introduce.title;
      _this.introduce.description = introduce.description;
      const MarkdownId = require("markdown-it");
      const md = new MarkdownId();
      _this.introduce.content = md.render(introduce.content);
    })
  }
}
</script>

<style scoped>
.introduce{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  background: lightgrey;
}
</style>