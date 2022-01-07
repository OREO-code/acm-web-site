<template>
  <div>
    <el-container class="container">
        <el-aside width="22%">

          <el-card class="box-card_1" shadow="hover">
            <div>
              <span style="float: left;padding-bottom: 20px">历年真题</span>
              <a href="http://newoj.acmclub.cn/home" target="_blank" class="oj">
                <el-button size="medium" type="primary"
                           style="line-height: normal;float: right;"
                           round
                >
                  NEUQ OJ
                </el-button>
              </a>
            </div>
          </el-card>
          <el-card class="box-card_2" shadow="hover" >
            <div slot="header" class="clearfix" style="text-align: left">
              <span>历年题解文档</span>
            </div>
            <div class="text item" style="height: 200px">
              <el-link type="primary" :href="downUrl+item.fileName" target="_blank"
                           v-for="item in tableData" v-if="matchType(item.fileName)">
                <h3>{{ item.fileName }}</h3>
              </el-link>
            </div>
          </el-card>
          <el-card class="box-card_3" shadow="hover" style="padding: 0">
            <div slot="header" class="clearfix" style="text-align: left">
              <span>历年真题讲解视频</span>
            </div>
            <div class="text item">
              <el-table
                  height="200"
                  :show-header="false"
                  :data="videoData"
                  style="width: 100%;line-height: normal;">
                <el-table-column width="400" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <a :href="downUrl+scope.row.fileName" style="text-decoration: none;padding: 0">
                      {{ scope.row[1].fileName }}
                    </a>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>
          <el-card class="box-card_3">
            <div slot="header" class="clearfix" style="text-align: left">
              <span>关注公众号沉思广场 立即报名</span>
            </div>
            <div class="text item">
              <img
                  src="https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzA4NjYzMzE0Ng==&mid=2665512771&idx=1&sn=32a79cfc0b3f10f14180b978615aea79&send_time="
                  alt="" style="width: 60%">
            </div>
          </el-card>
          <el-card class="box-card_3">
            <div slot="header" class="clearfix" style="text-align: left">
              <span>关注NEUQ ACM俱乐部QQ公众号</span>
            </div>
            <div class="text item">
              <el-image
                  style="width: 54.5%;"
                  :src="imgurl4"
                  :preview-src-list="srcList">
              </el-image>
            </div>
          </el-card>
        </el-aside>
        <el-main>
          <el-card class="box-card_1" shadow="hover">
            <div class="demo-image__preview">
              <el-image
                  style="width: 20%;"
                  :src="imgurl2"
                  :preview-src-list="srcList">
              </el-image>
              <div style="margin: auto;width: 60%;text-align: left">
                NEUQ-ACM“图灵杯”程序设计竞赛源于2013年。第一届“图灵杯”程序设计竞赛也宣告了NEUQ-ACM俱乐部的诞生，是俱乐部秉承“自强不息，知行合一”的校训精神，为在全校范围内弘扬程序设计精神，增进程序设计的浓厚氛围而带头发起。
              </div>
            </div>
          </el-card>
          <el-card class="box-card_2" shadow="hover">
            <div class="demo-image__preview">
              <div style="margin: auto;width: 60%;text-align: left">
                “图灵杯”程序设计竞赛从开始办赛至今，从第一届的几十人到第三届的上百人，再到2021年初第八届“图灵杯”校内参赛人数已达500人，通过网络连线参与线上同步赛的更是超过5000人。参赛人员也从大学生推广到了全国所有对程序设计竞赛有热忱之心的人，包括中学生和已经就职的IT从业人员。参赛人数的逐渐增加也给了“图灵杯”走出校门走向全国的机会，带着NEUQ-ACM俱乐部“Coding
                for fun!”的初心，让我们结交了许多在程序设计竞赛方面有突出成绩的兄弟院校。
              </div>
              <el-image
                  style="width: 20%;"
                  :src="imgurl2"
                  :preview-src-list="srcList">
              </el-image>
            </div>
          </el-card>
          <el-card class="box-card_3" shadow="hover">
            <div class="demo-image__preview">
              <el-image
                  style="width: 20%;"
                  :src="imgurl1"
                  :preview-src-list="srcList">
              </el-image>
              <div style="margin: auto;width: 60%;text-align: left">
                随着俱乐部的发展壮大以及大学生程序设计竞赛氛围在国内逐渐浓厚，“图灵杯”程序设计竞赛也顺应潮流，逐步发展，现如今已经成为在程序设计竞赛领域具有相当影响力的比赛。回顾“图灵杯”程序设计竞赛多年的历史，发现我们正是顺应了21世纪高素质信息人才的发展需求，激发了同学们在程序设计基础道路上探索的不竭动力。NEUQ-ACM“图灵杯”程序设计竞赛接下来会站在新的起点，以更高的标准和规范继往开来，带给同学们独一无二的参赛体验。
              </div>
            </div>
          </el-card>
        </el-main>
      </el-container>

  </div>
</template>

<script>
import jpg1 from '@/assets/1.png'
import jpg2 from '@/assets/2.png'
import jpg3 from '@/assets/3.png'
import jpg4 from '@/assets/7.png'

const http = 'http://101.43.16.42:8082/'
const downFile = 'download/'
export default {
  name: "viewport",
  data() {
    return {
      imgurl1: jpg1,
      imgurl2: jpg2,
      imgurl3: jpg3,//有问题 没用 3.png
      imgurl4: jpg4,
      downUrl: http + downFile,
      activeIndex: '3',
      srcList: [
        jpg1,
        jpg2,
        jpg2,
      ],
      tableData: [],
      videoData: [],
    };
  },
  methods: {
    fullVideo() {
      let temp = 0
      for (let i = 0; i < this.tableData.length; i++) {
        if (!this.matchType(this.tableData[i].fileName)) {
          this.videoData[temp++] = this.tableData[i]
        }
      }
      console.log(this.tableData)
      console.log(this.videoData.length)
      console.log(this.videoData)
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    getFileList() {
      this.$axios
          .get('http://101.43.16.42:8082/file')
          .then((data) => {
            this.tableData = data.data.data.fileList
            this.fullVideo()
            console.log(data)
          })
          .catch((error) => {
            console.log(error)
          })
    },
    matchType(fileName) {
      // 后缀获取
      let suffix = '';
      // 获取类型结果
      let result = '';
      try {
        let flieArr = fileName.split('.');
        suffix = flieArr[flieArr.length - 1];
      } catch (err) {
        suffix = '';
      }
      // fileName无后缀返回 false
      if (!suffix) {
        result = false;
        return result;
      }
      // 匹配 pdf
      const pdflist = ['pdf'];
      result = pdflist.some(function (item) {
        return item === suffix;
      });
      if (result) {
        result = 'pdf';
        return true;
      }
      // 匹配 视频
      const videolist = ['mp4'];
      result = videolist.some(function (item) {
        return item === suffix;
      });
      if (result) {
        result = 'video';
        return false;
      }
    },
  },
  mounted() {
    this.getFileList();
  }
}
</script>

<style>
.el-table__body-wrapper::-webkit-scrollbar {
  width: 8px;
}

.el-table__body-wrapper::-webkit-scrollbar-thumb {
  background-color: #EAECF1;
  border-radius: 5px;
}
</style>

<style scoped>
.oj {
  position: relative;
  bottom: 8px;
}

.item[data-v-400a73c0] {
  padding: 0;
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-thumb {
  background-color: #eaecf1;
  border-radius: 3px;
}

body .el-scrollbar__wrap {
  overflow-x: hidden;
}

.demo-image__preview {
  display: flex;
  flex-direction: row;
  align-items: center;
  text-align: center;
}

.box-card_1, .box-card_2, .box-card_3 {
  margin: 2%;
}

.el-card {
  line-height: normal;
  overflow-y: auto;
}

.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  /*text-align: center;*/
  line-height: 200px;
}

.el-main {
  display: block;
  background-color: #E9EEF3;
  color: #333;
  /*text-align: center;*/
  height: 100%;
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

.el-menu-demo {
  padding: 0;
  margin: 0;
}

.index_7 {
  display: none;
}


@media (max-width: 476px) {
  .index_2, .index_3, .index_4, .index_5, .index_6 {
    display: none;
  }

  .index_7 {
    display: block;
    position: absolute;
    right: 2%;
  }
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}
</style>