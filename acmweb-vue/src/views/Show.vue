<template>
<div class="total">
  <div class="groups">
    <div class="group">
      <div class="row row-first">
        <div class="head">
          <h4><span class="title" title="年级">年级</span>></h4>
          <h4><span class="title" title="学院">学院</span>></h4>
        </div>

          <div class="body">
            <el-checkbox-group v-model="year1" @change="getValue()">
            <div class="year row">
            <el-checkbox  v-for="(item,i) in year" :key="i" :label="item.content" :disabled="item.disabled" :checked="item.checked"  class="year-inner J_Ajax"></el-checkbox>
              </div>
        </el-checkbox-group>
            <el-checkbox-group v-model="college1" @change="getValue()">
            <div class="college row collegec">
              <el-checkbox  v-for="(item,i) in college" :key="i" :label="item.content" :disabled="item.disabled" :checked="item.checked"  class="college-inner J_Ajax"></el-checkbox>
              </div>
            </el-checkbox-group>
          </div>
      </div>
    </div>
  </div>

  <div class="people">
<!--    <img class="picture" src="{{item.src}}" width="100%"></img>-->
<!--    <div class="introduction">{{item.context}}</div>-->
    <img class="picture" src="../assets/images/1.png" width="100%"></img>
    <div class="introduction">东北大学悉尼智能科技学院</div>
  </div>
</div>
</template>

<script>

var img=document.querySelector('img');
var div=document.getElementsByClassName('introduction')
export default {
  name: "Show",
  data(){
    return{
      year1:[],
      year:[
        {id:0,content:"2020",disabled:false,checked:false},
        {id:1,content:"2019",disabled: false,checked:false},
        {id:2,content:"2018",disabled: false,checked:false},
        {id:3,content:"2017",disabled: false,checked:false}
      ],
      college1:[],
      college:[
        {id:0,content:"经济学院",disabled:false,checked:false},
        {id:1,content:"管理学院",disabled: false,checked:false},
        {id:2,content:"计算机与通信工程学院",disabled: false,checked:false},
        {id:3,content:"控制工程学院",disabled: false,checked:false},
        {id:4,content:"外国语言文化学院",disabled:false,checked:false},
        {id:5,content:"数学与统计学院",disabled: false,checked:false},
        {id:6,content:"资源与材料学院",disabled: false,checked:false},
        {id:7,content:"东北大学悉尼智能科技学院",disabled: false,checked:false}
      ],

    }
  },
  mounted(){
    document.querySelector('body').setAttribute('style','background:-webkit-linear-gradient(left,#7474BF,#348AC7)')
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },

  methods:{
    getValue(){
      const _this =this;
      console.log(_this.content)
      // _this.axios({
      //   method:'post',
      //   url:'http://101.43.16.42:8082/members',
      //   params:{
      //       year:[_this.content],
      //       college:[_this.content]
      //   },
      //   headers:{
      //     "Authorization":'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0MDYxMTM1NiwiZXhwIjoxNjQwNjk3NzU2fQ.Zqwgz3GQFYyL95lmYyvMUpEEqdLp8PTXKXGhF9ViQYUTvpeRT1MBoOT5yfAqqVFd',
      //   },
      // }).then(response=>{
      //   // if(response.status==200){
      //   //   return
      //   // }
      // })
    }
  }

}










</script>

<style scoped>

div{
  display: block;
}

.group{
  border: 1px solid #e8e8e8;
  border-radius: 7px;
  margin-top: -1px;
}

.group .row{
  position: relative;
  margin: 0 8px;
}
.group .row-first, .group .row:first-child {
  border-top: none;
}
.group .head{
  position: absolute;
  left: 11px;
  top: 9px;
  color: black
}
.collegec{
  position: absolute !important;
  top:54px;
  left:124px;
  width:500px;
}
.group .year{
  position:absolute;
  left:123px;
  bottom: 189px;
}
.head h4{
  font-weight: 400;
  font-size: 100%;
}
h4{
  font-size: 100%;
}
.group .head .title{
  float: left;
  max-width: 80px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.group .body{
  height: 36px;
  width:500px;
  overflow: hidden;
  padding:0 116px 210px ;
}

.group .body .btn{
  display: none;
  text-align: center;
}
.group .body .year .college {
  float: left;
  margin: 9px 40px 9px 0;
  height: 18px;
  color: black;
  text-decoration: none;
}
.college{
  float: left;
  margin: 9px 40px 9px 0;
  height: 18px;
  color: black;
  text-decoration: none;
}

 .year-inner{
   text-decoration: none;
   color: #403131;
   float: left;
   margin: 28px 85px 9px 0;
   height: 18px;
   text-decoration: none;

 }
 .college-inner{
   text-decoration: none;
   color: #403131;
   float: left;
   margin: 21px 40px 9px 0;
   height: 18px;
   text-decoration: none;
 }

 .people{
   position: relative;
   margin: 10px;
   border:  1px solid #e8e8e8;
   border-radius: 7px;
   width: 978px;
   height: 166px;
   left: -9px;
 }
 img{
   height: 124px;
   width: 96px;
   float: left;
   padding-top: 20px;
   padding-left: 20px;
 }
 .introduction{
   width: 800px;
   height:160px;
   position: relative;
   padding: 18px;
   left: 121px;
 }
.group{
  background:-webkit-linear-gradient(left,#ECE9E6,#FFFFFF);
  opacity: 0.9;
}
.people{
  background: azure;
  opacity: 0.9;
}

</style>