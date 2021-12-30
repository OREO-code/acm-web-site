<template>
<div class="total">
  <h3><span>成</span><span>员</span><span>风</span><span>采</span></h3>
  <div class="groups">
    <div class="group">
      <div class="row row-first">
        <div class="head">
          <h4><span class="title" title="年级">年级</span><span>></span></h4>
          <h4><span class="title" title="学院">学院</span><span>></span></h4>
        </div>

          <div class="body">
            <el-checkbox-group v-model="from.yearList" @change="getValue()">
            <div class="year row">
            <el-checkbox  v-for="item in getYear"  :label="item"  :checked="false"  class="year-inner J_Ajax">
            </el-checkbox>
              </div>

        </el-checkbox-group>
            <el-checkbox-group v-model="from.collegeList" @change="getValue()">
            <div class="college row collegec">
              <el-checkbox  v-for="item in getCollege"  :label="item"  :checked="false"  class="college-inner J_Ajax">
              </el-checkbox>
              </div>
            </el-checkbox-group>
          </div>
      </div>
    </div>
  </div>

  <div class="people"  v-for="item in memberList" :key="item" width="100%">
    <img class="picture"  src="item.url"></img>
    <div class="introduction1" ><span>姓名：</span>{{item.name}}</div>
    <div class="introduction2" ><span>年级：</span>{{item.year}}</div>
    <div class="introduction3"><span>学院：</span>{{item.college}}</div>
    <div class="introduction4"><span>荣誉：</span>{{item.honor}}</div>
<!--    <img class="picture" src="../assets/images/1.png" width="100%"></img>-->
<!--    <div class="introduction">东北大学悉尼智能科技学院</div>-->
  </div>
</div>
</template>

<script>
var flag=0;

var img=document.querySelector('img');
var div=document.getElementsByClassName('introduction')
export default {
  name: "Show",
  data(){
    return {
      from:{
        yearList: [],
        collegeList: [],
      },
      memberList: [],
      getYear:[],
      getCollege:[]

    }

  },

  mounted(){
    document.querySelector('body').setAttribute('style',  'background:-webkit-linear-gradient(left,#6190E8,#A7BFE8)');
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  created() {
    const _this=this;
    _this.$axios.get("http://101.43.16.42:8082/getYear").then((response)=>{
      // console.log(response);
      _this.getYear=response.data.data;
      //console.log(response.data.data)
    })
    _this.$axios.get("http://101.43.16.42:8082/getCollege").then((response)=>{
      // console.log(response);
      _this.getCollege=response.data.data;

      //console.log(response.data.data)
    })

  },
  methods:{
    getValue(){
      const _this= this;
      // console.log(this.from)
      // console.log(123456)
      _this.$axios.post('http://101.43.16.42:8082/members',this.from).then((response)=>{
        //console.log(response);
        console.log(_this.from)
        _this.memberList=response.data.data;
        console.log(response)
        console.log(_this.memberList)
      })

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
  margin-top: -38px;
  box-shadow:0 10px 50px 0px #ADA996 ;
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
   border:  1px solid rgba(173, 171, 171, 0.5);
   border-radius: 7px;
   width: 978px;
   height: 166px;
   left: -9px;
   /*background: -webkit-linear-gradient(left,#6190E8,#021B79);*/
   background: rgba(164, 167, 164, 0.4);
   color: rgb(0,0,0);
 }
 img{
   height: 148px;
   width: 118px;
   float: left;
   padding-top: 9px;
   padding-left: 12px;
 }
 .introduction1,.introduction2,.introduction3,.introduction4{
   width: 800px;
   height:3px;
   position: relative;
   padding: 18px;
   left: 121px;
 }
 h3{
   font-size: 3em;
   font-weight: 500;
   color: #222;
   letter-spacing: 5px;
   text-align: center;
   cursor:pointer;
   font-family: 'Poppons';
   margin-top: 2px;
 }
 h3 span{
   transition: 0.5s;
 }
 h3:hover span:nth-child(1){
   margin-right: 10px;
 }
h3:hover span:nth-child(3){
  margin-left: 40px;
}
h3:hover span{
  color: #fff;
  text-shadow: 0 0 10px #733ebf;
}
.group{
  background: url("../assets/images/9.png");
  color: #FFFFFF;
}
h4 span{
  color: #FFFFFF;
}
.people {
  transition: 0.5s;
}
.people:hover{
  box-shadow:  0 0 20px #FFFFFF;
  margin-left: 40px;
}
</style>