<template>
	<div>
		<el-carousel :interval="5000" arrow="always" height="20vw" type="card" autoplay="{{}}">
		    <el-carousel-item v-for="item in showImageList" :key="item">
		      <img :src="item.url" width="100%" height="100%">
			  <!-- <div>
			  	<p>序号：{{item.id}}</p>
				<p>url：{{item.url}}</p>
				<el-button>修改</el-button>
				<el-button>删除</el-button>
			  </div> -->
		    </el-carousel-item>
		  </el-carousel>
		  <!-- <div class="operate">
			  <div>
				  <el-upload
				    class="upload-demo"
				    drag
				    action="https://jsonplaceholder.typicode.com/posts/"
				    multiple>
				    <i class="el-icon-upload"></i>
				    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
				    <div class="el-upload__tip" slot="tip">请注意图片尺寸</div>
				  </el-upload>
			  </div>
		  </div> -->
		  <div class="imgshow">
			  <div v-for="item in showImageList" class="imgbtn">
					  <img :src="item.url" width="100%" height="100%">
					<div class="deletebtn">
						{{item.id}}
						<el-button type="danger" icon="el-icon-delete" circle @click="getvalue(item.id)" ></el-button>
					</div>
			  </div>
		  </div>
	</div>
</template>

<script>
	export default {
	    data() {
	      return {
			
			showImageList:[],
			sum:'',
			imgHeight: '',
			// 图片父容器高度
			bannerHeight :1000,
			// 浏览器宽度
			screenWidth :0,
			token:'',
			fileList: []
	      };
	    },
		mounted() {
		    
		},
		created() {
			var _that=this
			
			this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
					_that.showImageList=response.data.data.urlList
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
			getvalue(value){
				console.log(value)
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
				         this.$axios.get('http://101.43.16.42:8082/rotation',{params:{id:value}}).then((response)=>{
				         		_that.showImageList=response.data.data.urlList
				                 console.log(response);
				               }).catch((response)=>{
				         		console.log('err')
				                 console.log(response);
				               })
				       }).catch(() => {
				         this.$message({
				           type: 'info',
				           message: '已取消删除'
				         });          
				       });
			},
			getInfo(){
				console.log('执行')
				this.$axios.get('http://101.43.16.42:8082/notice')
				  .then(function (response) {
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
				
			},
			addimg:function(){
				console.log('添加图片')
				// this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
				// 		_that.showImageList=response.data.data.urlList
				//         console.log(response);
				//       }).catch((response)=>{
				// 		console.log('err')
				//         console.log(response);
				//       })
			},
				
			delete(value){
				 this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
				          this.$axios.get('http://101.43.16.42:8082/rotation',{params:{id:value}}).then((response)=>{
				          		_that.showImageList=response.data.data.urlList
				                  console.log(response);
				                }).catch((response)=>{
				          		console.log('err')
				                  console.log(response);
				                })
				        }).catch(() => {
				          this.$message({
				            type: 'info',
				            message: '已取消删除'
				          });          
				        });
				
			},
			handleRemove(file, fileList) {
			        console.log(file, fileList);
			      },
			      handlePreview(file) {
			        console.log(file);
			      },
			      // handleExceed(files, fileList) {
			      //   this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
			      // },
			      beforeRemove(file, fileList) {
			        return this.$confirm(`确定移除 ${ file.name }？`);
			      }
			    
			
		}
	}
</script>
<style>
	.operate{
		width: 80%;
		margin-left: 10%;
		height: auto;
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
		margin-top: 3%;
		margin-bottom: 3%;
		align-items: center;
		display: flex;
		text-align: center;
		align-items: center;
		justify-content: center;
		padding: 1.875rem;
	}
	.imgshow{
		width: 80%;
		margin-left: 10%;
		margin-top: 1%;
		display: grid;
		grid-template-columns: repeat(3, 1fr);
		grid-auto-rows: 200px;
		grid-gap: 25px;
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
		margin-bottom: 5%;
		padding: 3%;
	}
	
	.imgbtn{
	display: flex;
	align-items: center;
	justify-content: space-around;
	text-align: center;
	border: #D3DCE6 0.0625rem solid;
	padding: 1.25rem;
	border-radius: 0.625rem;
	box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
	}
	.deletebtn{
		margin-left: 0.625rem;
	}
</style>
