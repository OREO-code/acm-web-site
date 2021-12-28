<template>
  <div id="app">
	  	<div id="nav">
	  		<el-menu
						router="true"
	  		          :default-active="activeIndex2"
	  		          class="el-menu-demo"
	  		          mode="horizontal"
	  		          @select="handleSelect"
	  		          >
	  		        <el-menu-item index="1" route="/"><div >首页</div></el-menu-item>
	  				<el-menu-item index="2" route="/time"><div >时间线</div></el-menu-item>
	  				<!-- <el-menu-item index="3" route="/form">组织架构</el-menu-item> -->
	  				<el-menu-item index="4" route="/member"><div>成员风采</div></el-menu-item>
	  		        <!-- <el-submenu index="2">
	  		          <template slot="title">组织架构</template>
	  		          <el-menu-item index="2-1">竞赛队</el-menu-item>
	  		          <el-menu-item index="2-2">技术部</el-menu-item>
	  		          <el-menu-item index="2-3">预备队</el-menu-item>
	  		        </el-submenu> -->
	  		        <el-menu-item index="5" route="/tulin"><div>图灵杯</div></el-menu-item>
	  		        <el-menu-item index="6"><a href="http://newoj.acmclub.cn/home" style="text-decoration: none;color: #A6ADB4;" target="_blank" ><div>NEUQ OJ</div></a>
	  		        </el-menu-item>
					<el-menu-item index="7" route="/login"><div>管理员登录</div></el-menu-item>
	  		        <!--        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
	  		      </el-menu>
	  	</div>
		<router-view></router-view>
	  
  </div>
</template>

<style>
#app {
	margin-top: -1%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
/* 菜单栏 */
/* .el-menu-item :hover{
	color: white;
} */
.top{
	width: 100%;
	height: auto;
}
.topitem :hover{
	background-color: #1890FF;
}
#nav {
	width: 100%;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
.el-carousel__container{
	width: 60%;
	margin-left: 20%;
	margin-top: 3.125rem;
}
.el-carousel__item{
	width: 100%;
}
.player{
	width: 100%;
	height: 3.125rem;
	background-color: #FFEBCD;
	filter: Alpha(Opacity=50); /* 针对IE*/
	background-color: rgba(0, 0, 0, 0.5); /*针对其他浏览器*/
	top: 0%;
	line-height: 3.125rem;
}
.el-carousel__item h1 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #D4DFE7;
  }
  .el-carousel__item:nth-child(2n+1) {
    background-color: #FFFAE8;
  }
  .bannerImg{
      width: 100%;
      height: inherit;
      min-height: 360px;
      min-width: 1400px;
    }
	body{
		width: 100%;
		background-color: #fff;
	}
	.showintro{
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
		width: 80%;
		margin-left: 10%;
		height: 500px;
		background-color: #FFFFFF;
		margin-top: 3%;
	}
	.notice{
		width: 100%;
		height: 3.125rem;
		line-height: 3.125rem;
		text-align: left;
	}
	@-webkit-keyframes horse {
		0% {
			left: 0%;
			top: 0px;
			background-color: lavenderblush;
		}
	 
		47% {
			left: 105%;
			top: 0px;
		}
	 
		48% {
			left: 105%;
			top: 100px;
		}
	 
		49% {
			left: -105%;
			top: 100px;
		}
	 
		50% {
			left: -100%;
			top: 0px;
			background-color: lightpink;
		}
	 
		100% {
			left: 0%;
			top: 0px;
			background-color: #FFB6C1;
		}
	}
		
	.showImage{
		width: 100%;
		height: 100%;
	}
</style>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
	export default {
	    data() {
	      return {
	        activeIndex: '1',
	        activeIndex2: '1',
			showImageList:[],
			notice:'',
	      };
	    },
		methods:{
			getInfo(){
				console.log('执行')
				axios.get('http://101.43.16.42:8082/notice')
				  .then(function (response) {
				    console.log(response);
					console.log('已执行')
				  })
				  .catch(function (error) {
				    console.log(error);
				  });
			},
			handleSelect(key, keyPath) {
			        console.log(key, keyPath);
			      }
		}
	}
</script>