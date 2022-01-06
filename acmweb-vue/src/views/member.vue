<template>
  <div class="total">
    <div class="group">
      <div class="body">
        <div class="fxd">
          <h4><span class="title" title="年级">年级</span><span>></span></h4>

          <el-checkbox-group v-model="from.yearList" @change="getValue()">
            <div class="year row">
              <el-checkbox
                  v-for="item in getYear"
                  :label="item"
                  :checked="false"
                  class="year-inner J_Ajax"
              >
              </el-checkbox>
            </div>
          </el-checkbox-group>
        </div>
        <div class="fxd">
          <h4><span class="title" title="学院">学院</span><span>></span></h4>

          <el-checkbox-group v-model="from.collegeList" @change="getValue()">
            <div class="college row collegec">
              <el-checkbox
                  v-for="item in getCollege"
                  :label="item"
                  :checked="false"
                  class="college-inner J_Ajax"
              >
              </el-checkbox>
            </div>
          </el-checkbox-group>
        </div>
      </div>
    </div>


    <div class="people" v-for="item in memberList" :key="item" width="100%">
      <el-container>
        <el-aside width="200px">
          <div class="ima">
            <div class="block" v-for="fit in fits" :key="fit">
              <el-image
                  class="picture"
                  :src="item.url"
                  :fit="fit"></el-image>
            </div>
          </div>
        </el-aside>
        <el-main>
          <div class="intro">
            <div class="introduction1"><span>姓名：</span>{{ item.name }}</div>
            <div class="introduction2"><span>年级：</span>{{ item.year }}</div>
            <div class="introduction3"><span>学院：</span>{{ item.college }}</div>
            <div class="introduction4"><span>简介：</span>{{ item.honor }}</div>
          </div>
        </el-main>
      </el-container>
    </div>


    <!--    <div class="people" v-for="item in memberList" :key="item" width="100%">-->

    <!--        <div class="ima">-->
    <!--          <div class="block" v-for="fit in fits" :key="fit">-->
    <!--            <el-image-->
    <!--                class="picture"-->
    <!--                :src="item.url"-->
    <!--                :fit="fit"></el-image>-->
    <!--          </div>-->
    <!--        </div>-->

    <!--      <div class="intro">-->
    <!--      <div class="introduction1"><span>姓名：</span>{{ item.name }}</div>-->
    <!--      <div class="introduction2"><span>年级：</span>{{ item.year }}</div>-->
    <!--      <div class="introduction3"><span>学院：</span>{{ item.college }}</div>-->
    <!--      <div class="introduction4"><span>简介：</span>{{ item.honor }}</div>-->
    <!--      </div>-->
    <!--      </div>-->


  </div>
</template>

<script>
var flag = 0;

// var img=document.querySelector('img');
// var div=document.getElementsByClassName('introduction')
export default {
  name: "Show",
  data() {
    return {
      from: {
        yearList: [],
        collegeList: [],
      },
      memberList: [],
      getYear: [],
      getCollege: [],
      fits: ["contain"],
    };
  },

  // mounted(){
  //   document.querySelector('body').setAttribute('style',  'background:-webkit-linear-gradient(left,#6190E8,#A7BFE8)');
  // },
  // beforeDestroy() {
  //   document.querySelector('body').removeAttribute('style')
  // },
  created() {
    const _this = this;
    _this.$axios.get("http://101.43.16.42:8082/getYear").then((response) => {
      // console.log(response);
      _this.getYear = response.data.data;
      //console.log(response.data.data)
    });
    _this.$axios.get("http://101.43.16.42:8082/getCollege").then((response) => {
      // console.log(response);
      _this.getCollege = response.data.data
      ;

      //console.log(response.data.data)
    });
  },
  methods: {
    getValue() {
      const _this = this;
      _this.$axios.post('http://101.43.16.42:8082/members', this.from).then((response) => {
        _this.memberList = response.data.data;
        // this.form.id=response.data.data[0].id;
        console.log(response)
        // console.log(_this.memberList)

      })
    },
  },
};
</script>

<style scoped>
.group {
  border: 1px solid #e8e8e8;
  border-radius: 7px;
  margin-top: 98px;
  margin-left: 45px;
  padding-left: 23px;
  box-shadow: 0 2px 5px 0px #ada996;
  position: absolute;
  left: 0;
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  justify-content: flex-start;
}

.group .head {
  color: black;
}
.group .head .title {
  max-width: 80px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.year {
  display: flex;
  flex-direction: column;
}
.collegec {
  width: 281px;
}
.fxd {
  display: flex;
}

.head h4 {
  font-weight: 400;
  font-size: 100%;
}
h4 {
  font-size: 100%;
}

.group .body .btn {
  display: none;
  text-align: center;
}
.group .body .year .college {
  margin: 9px 40px 9px 0;
  height: 18px;
  line-height: 18px;
  color: black;
  text-decoration: none;
}
.college {
  float: left;
  margin: -5px 41px 0px 48px;
  color: black;
  text-decoration: none;
}

.year-inner {
  text-decoration: none;
  color: #403131;
  margin: 22px 85px 9px 46px;
  height: 18px;
  text-decoration: none;
}
.college-inner {
  text-decoration: none;
  color: #403131;
  margin: 21px 40px 9px 0;
  height: 18px;
  text-decoration: none;
}

.people {
  position: relative;
  margin: 12px;
  border: 1px solid rgba(173, 171, 171, 0.5);
  border-radius: 7px;
  width: 937px;
  /*height: 166px;*/
  left: 245px;
  top: 97px;
  /*background: -webkit-linear-gradient(left,#6190E8,#021B79);*/
  /*background: rgba(164, 167, 164, 0.4);*/
  color: rgb(0, 0, 0);
  box-shadow: 0 2px 5px 0px #ada996;
}
.ima {
  /*height: 148px;*/
  width: 150px;
  float: left;
  padding-top: 36px;
  padding-left: 15px;
  /*display: flex;*/
}

/*.people{*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*  !*flex-wrap:wrap;*!*/
/*  !*align-items: flex-start;*!*/
/*  !*justify-content: flex-start;*!*/
/*}*/
.intro{
  display: flex;
  flex-direction: column;
  flex-wrap:wrap;
  align-items: flex-start;
  justify-content: flex-start;
  margin-left: -213px;
  overflow-x: hidden;
}

.introduction1,
.introduction2,
.introduction3,
.introduction4 {
  width: 800px;
  height: 3px;
  position: relative;
  padding: 11px;
  left: 121px;
  padding-left: 133px;
  flex: 1;
}
.introduction4{
  width: 560px;
}
/*.intro{*/
/*  flex: 1;*/
/*}*/

h4 span {
  color: black;
}
.people {
  transition: 0.5s;
}
.people:hover {
  box-shadow: 0 0 20px #ffffff;
  margin-left: 40px;
}
</style>