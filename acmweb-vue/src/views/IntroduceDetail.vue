<template>

  <div class="introduce" >
    <div style="font-size: 1.875rem;margin-top: 3%;padding-top: 3%;">
      {{introduce.title}}
    </div>
    <div style="margin-top: 2%;margin-bottom: 1%;">
      {{introduce.description}}
    </div>
    <el-divider><i class="el-icon-mobile-phone" ></i></el-divider>
    <div style="width: 80%;margin-left: 10%;margin-top: 3%;">
      <div class="markdown-body" v-html="introduce.content"></div>
    </div>
  </div>
</template>

<script>
import "github-markdown-css/github-markdown.css"

export default {
  name: "IntroduceDetail",
  data() {
    return {
      introduce: {
        id: '6',
        title: 'title',
        description: 'description',
        content: 'content'
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
  width: 80%;
  margin-left: 10%;
  min-height: 700px;
  margin-bottom: 3%;
}
</style>