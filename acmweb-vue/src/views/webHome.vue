<template>
  <div id="app">


    <el-container >
      <el-header>
        <marquee class="player"  style="color: ivory;" >
          公告:{{notice}}
        </marquee>
      </el-header>
      <el-main>
        <el-container>
          <el-aside width="20%" style="text-align: center">
            文件下载
          </el-aside>
          <el-main >

            <el-carousel :interval="4000" type="card" height="200px">
              <el-carousel-item v-for="item in showImageList" :key="item">
                <el-image :src="item.url" style="position:absolute;top:0;bottom:0;left:0;right:0;width:100%;margin:auto;" fit="contain"></el-image>
              </el-carousel-item>
            </el-carousel>

          </el-main>
          <el-aside width="20%" >
            Echarts
          </el-aside>
        </el-container>
        <el-container>
          <el-aside width="50%">组织架构图</el-aside>
          <el-main>
            <h1 style="text-align: left">最新资讯</h1>
            <div >
              <router-view/>
            </div>
          </el-main>
        </el-container>



      </el-main>
      <el-footer>
        <el-row>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple-light"></div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
      </el-footer>
    </el-container>



  </div>
</template>

<style>

.el-main{
  line-height: normal !important;
}
.el-header, .el-footer{
  background-color: #58B7FF !important;
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