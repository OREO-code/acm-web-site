<template>
  <div>


      <el-container>
        <el-aside width="20%">
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
                  style="width: 53%;"
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
                  style="width: 16%;"
                  :src="imgurl1"
                  :preview-src-list="srcList">
              </el-image>
              <div style="margin: auto;width: 60%;text-align: left">
                NEUQ-ACM暑期新生编程训练营始于2014年，自NEUQ-ACM俱乐部成立之初即发起开办。最初创办训练营的目的即是让热爱编程的新生充分利用被录取后到开学期间的时间，学习编程基础，提前适应大学学习生活。
              </div>
            </div>
          </el-card>
          <el-card class="box-card_2" shadow="hover">
            <div class="demo-image__preview">
              <div style="margin: auto;width: 60%;text-align: left">
                2017年，由NEUQ-ACM俱乐部潘骏同学担任法人代表的秦皇岛牛客科技有限公司入驻学校孵化基地，新生训练营被纳入牛客公益项目，作为NEUQ-ACM俱乐部成员的暑期社会实践活动，继续在每年暑期面向所有的对编程感兴趣的爱好者开放。同时随着训练营规模的进一步扩大与俱乐部的逐渐发展，也有来自全国不同地区、不同年龄、不同职业和身份的营员加入到训练营中来，也获得了各方的一致好评。训练营已然承担了推动NEUQ-ACM俱乐部在全国范围内进一步发展的重要任务。
              </div>
              <el-image
                  style="width: 16%;"
                  :src="imgurl2"
                  :preview-src-list="srcList">
              </el-image>
            </div>
          </el-card>
          <el-card class="box-card_3" shadow="hover">
            <div class="demo-image__preview">
              <el-image
                  style="width: 20%;"
                  :src="imgurl3"
                  :preview-src-list="srcList">
              </el-image>
              <div style="margin: auto;width: 60%;text-align: left">
                在这其中，部分积极进取的训练营成员在进入大学后迅速成长为NEUQ-ACM的骨干成员，在经过进一步的集训后，在程序设计类的校赛、省赛、国赛斩获多个大奖。现如今，新生编程训练营已经成为俱乐部发现人才和培养人才的重要手段。在新的发展时期，新生编程训练营顺应俱乐部发展需要和人才培养需要，已经成为了NEUQ-ACM俱乐部一张重要的金色名片。
              </div>
            </div>
          </el-card>
        </el-main>
      </el-container>

  </div>
</template>

<script>
import jpg1 from '@/assets/4.png'
import jpg2 from '@/assets/5.png'
import jpg3 from '@/assets/6.png'
import jpg4 from '@/assets/7.png'

const http = 'http://101.43.16.42:8082/'
const delFile = 'delFile/'
const downFile = 'download/'
export default {
  name: "Camp",
  data() {
    return {
      imgurl1: jpg1,
      imgurl2: jpg2,
      imgurl3: jpg3,
      imgurl4: jpg4,
      http: 'http://101.43.16.42:8082/',
      downFile: 'download/',
      activeIndex: '4',
      url: '../../public/1.png',
      srcList: [
        jpg1,
        jpg2,
        jpg3,
        jpg4,
      ]
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    getFileList() {
      this.$axios
          .get('http://101.43.16.42:8082/file', {
            headers: {
              "Authorization": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0MTMwNTA4MiwiZXhwIjoxNjQxMzkxNDgyfQ.iZ1Bcrq0eae6aVQ4ojcvT2ymdJ3szbKg6S-qNYRbF0Wizwv5Gwquvum-vv9gpYQs"
            }
          })
          .then((data) => {
            this.tableData = data.data.data.fileList
            console.log(data)
          })
          .catch((error) => {
            console.log(error)
          })
    },
  }
}
</script>

<style scoped>
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
  text-align: center;
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