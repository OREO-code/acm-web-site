<template>
  <div id="app">

    <el-container>
      <el-header>
        <marquee class="player"  style="color: ivory;" >
          公告:{{notice}}
        </marquee>
      </el-header>
      <el-main>
        <el-carousel :interval="5000" arrow="always" height="35vw" >
          <el-carousel-item v-for="item in showImageList" :key="item">
            <img :src="item.url" width="100%" height="100%">
          </el-carousel-item>
        </el-carousel>
        <div class="showintro">
          <router-view/>
        </div>
      </el-main>
      <el-footer>


      </el-footer>
    </el-container>



  </div>
</template>

<style>
	a {font-size:16px}
	a:link {color: blue; text-decoration:none;}
	a:active:{color: red; }
	a:visited {color:purple;text-decoration:none;}
	a:hover {color: red; text-decoration:underline;}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  margin: 0;
  padding: 0;
}
.top{
	width: 100%;
	height: auto;
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
.shownotice{
	height: 17rem;
	width: 40%;
	background-color: #0969DA;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;

  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  img{
    /*设置图片宽度和浏览器宽度一致*/
    width: 100%;
    height: inherit;
  }
.el-carousel{
	overflow: hidden;
		height: 60%;
}
.player{
	width: 100%;
	height: 3.125rem;
	filter: Alpha(Opacity=50); /* 针对IE*/
	background-color: #003A79;
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
		margin-left: 0;
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
	.line{
		width: 100%;
		height: 1px;
		background-color: #9c9c9c;
		margin-top: 3%;
		margin-bottom: 3%;
		margin-left: -3%;
	}
	.bottom{
		width: 100%;
		display: flex;
		justify-content: space-around;
		background-color: #003A79;
		height: 35vh;
		align-items: center;
	}
	.bottomitem{
		width: 30%;
		height: 30vh;
	}
	.bottomitem img{
		margin-top: 10%;
		width: 6.875rem;
		height: 6.875rem;
		height: auto;
		background-color: white;
	}
	.itemtitle{
		text-align: center;
		font-weight: 300;
		color:white;
		width: 100%;
		line-height: 3.125rem;
		margin-top: 1%;
		z-index: 99;
	}
	.qrcodeshow{
		width: ;
	}

</style>
<script>
	export default {
	    data() {
	      return {
	        activeIndex: '1',
	        activeIndex2: '1',
			showImageList:[],
			notice:'',
			sum:'',
			fileList:[],
			imgHeight: "",
			// 图片父容器高度
			bannerHeight :1000,
			// 浏览器宽度
			screenWidth :0,
			token:''
	      };
	    },
		mounted() {

		},
		created() {
			var _that=this
			this.$axios.get('http://101.43.16.42:8082/notice').then((response)=>{
					_that.notice=response.data.data.notice
			        console.log(response);
			      }).catch((response)=>{
			        console.log(response);
			      })
			this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
					_that.showImageList=response.data.data.urlList
			        console.log(response);
			      }).catch((response)=>{
					console.log('err')
			        console.log(response);
			      })
			this.$axios.get('http://101.43.16.42:8082/introduce/file').then((response)=>{
					_that.fileList=response.data.data.fileList
			        console.log(response);
			      }).catch((response)=>{
					  console.log('err')
			        console.log(response);
			      })
		},
		 mounted() {
		          // 首次加载时,需要调用一次
		          this.screenWidth =  window.innerWidth;
		          this.setSize();
		          // 窗口大小发生改变时,调用一次
		          window.onresize = () =>{
		          this.screenWidth =  window.innerWidth;
		          this.setSize();
		        }
			},
		methods:{
			imgLoad() {
			            this.$nextTick(function() {
			                // 获取窗口宽度*图片的比例，定义页面初始的轮播图高度
			                this.imgHeight = document.body.clientWidth*1/4
							console.log(this.imgHeight)
			            });
			        },
			getInfo(){
				console.log('执行')
				this.$axios.get('http://101.43.16.42:8082/notice')
				  .then(function (response) {
					  this.imgLoad()
				    console.log(response);
					console.log('已执行')
				  })
				  .catch(function (error) {
				    console.log(error);
				  });
			},
			canvasbg(){
				
			},
			setSize:function () {
			            // 通过浏览器宽度(图片宽度)计算高度
			            this.bannerHeight = 400 / 1920 * this.screenWidth;
			          },
			linebackground:function(){
				
			}
		}
	}
</script>