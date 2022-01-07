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
          <el-aside width="20%"  >
            <div  style="width: 98.95%;height: 200px;">
              <el-card class="box-card">
                <h3 style="height: 10px">公示文件</h3>
                <el-card class="box-card">
                  <el-table
                      height="660"
                      :header-cell-style="{'text-align':'center'}"
                      :cell-style="{'text-align':'center'}"
                      max-height="100%"
                      :data="fileList"
                      style="width: 100%;line-height: normal;"
                      highlight-current-row="true">

                    <el-table-column
                        label="文件名"
                        prop="fileName">
                    </el-table-column>

                    <el-table-column
                        label="上传时间"
                        prop="created">
                    </el-table-column>
                  </el-table>
                </el-card>
              </el-card>
            </div>

          </el-aside>
          <el-main >
            <el-carousel :interval="4000" type="card" height="200px">
              <el-carousel-item v-for="item in showImageList" :key="item">
                <el-image :src="item.url" style="position:absolute;top:0;bottom:0;left:0;right:0;width:100%;margin:auto;" fit="contain"></el-image>
              </el-carousel-item>
            </el-carousel>
          </el-main>
          <el-aside width="20%" >
			<div class="box1" style="width: 98.95%;height: 100%;"></div>
          </el-aside>
        </el-container>
        <el-container>
          <el-aside width="50%">
			<div class="box2father">
					<div class="box2" style="width: 95%;height: 95%;"></div>
			</div>
		  </el-aside>
          <el-main>
            <h1 style="text-align: left">最新资讯</h1>
            <div>
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
	.box2{
		background-color: white;
		padding-top: 2.42rem;
	}
.box1{
	background-color: white;
}
.el-main{
  line-height: normal !important;
}
.el-header, .el-footer{
  background-color: #58B7FF !important;
}
.el-aside{
	background-color: white !important;
}

.box2father{
	width: 100%;
	height: 100%;
}
</style>
<script>
	import * as echarts from 'echarts';
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
					this.formdraw()
					this.awardDraw()
					// this.formdraw()
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
				
			},
			awardDraw(){
				let myChart = this.$echarts.init(document.querySelector('.box1') )
				        // 绘制图表
				        // myChart.setOption({
				        //     title: { text: '在Vue中使用echarts' },
				        //     tooltip: {},
				        //     xAxis: {
				        //         data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
				        //     },
				        //     yAxis: {},
				        //     series: [{
				        //         name: '销量',
				        //         type: 'bar',
				        //         data: [5, 20, 36, 10, 10, 20]
				        //     }]
				        // });
						//图表绘制
						// prettier-ignore
						let dataAxis = ['国一', '国二', '国三'];
						// prettier-ignore
						let data = [3,20,26];
						let yMax = 500;
						let dataShadow = [];
						for (let i = 0; i < data.length; i++) {
						  dataShadow.push(yMax);
						}
						var option;
						 option = {
							 // tooltip: {
							 //     trigger: 'axis',
							 //     axisPointer: {
							 //         type: 'cross',
							 //         crossStyle: {
							 //             color: '#999'
							 //         }
							 //     },
							 //     formatter: function (params) {
							 //         console.log(params);
								// 	 console.log(params[0].value)
							 //         return params[0].value;
							 //     }
							 // },
							 tooltip : {
							         trigger: 'item',
							         formatter: "{b} : {c} "
							     },
						  title: {
						    text: '国奖获奖数量',
						    subtext: ''
						  },
						  xAxis: {
						    data: dataAxis,
						    axisLabel: {
						      inside: true,
						      color: '#191F47'
						    },
						    axisTick: {
						      show: false
						    },
						    axisLine: {
						      show: false
						    },
						    z: 10
						  },
						  yAxis: {
						    axisLine: {
						      show: false
						    },
						    axisTick: {
						      show: false
						    },
						    axisLabel: {
						      color: '#999'
						    }
						  },
						  dataZoom: [
						    {
						      type: 'inside'
						    }
						  ],
						  series: [
						    {
						      type: 'bar',
						      showBackground: true,
						      itemStyle: {
						        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
						          { offset: 0, color: '#83bff6' },
						          { offset: 0.5, color: '#188df0' },
						          { offset: 1, color: '#188df0' }
						        ])
						      },
						      emphasis: {
						        itemStyle: {
						          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
						            { offset: 0, color: '#2378f7' },
						            { offset: 0.7, color: '#2378f7' },
						            { offset: 1, color: '#83bff6' }
						          ])
						        }
						      },
						      data: data
						    }
						  ]
						};
						// Enable data zoom when user click bar.
						const zoomSize = 6;
						myChart.on('click', function (params) {
						  console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
						  myChart.dispatchAction({
						    type: 'dataZoom',
						    startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
						    endValue:
						      dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
						  });
						});
						option && myChart.setOption(option);
						// window.addEventListener("resize", () => { 
						//     this.myChart.resize();  
						// 	console.log('执行适应1')
						// });
						window.onresize = function () {
									console.log('执行更改')
									height= $(window).height();//浏览器的高度 
									myChart.getDom().style.height = this.height+ "px";
									myChart.resize();
								}
			},
			formdraw(){
				let myChart2 = this.$echarts.init(document.querySelector('.box2'),null, {
										width: 730,
										height: 814
  })
				       var option;
				       
				       var data = [
				         {
				           name: 'NEUQ-ACM',
						    itemStyle: {
						                       color: '#58B7FF',
						    },
				           children: [
				             {
				               name: '预备队',
				               value: 10,
							   itemStyle: {
							                      color: '#64CBCC'
							   },
				               children: [
				                 {
				                   name: '程序设计创新实验班',
								   itemStyle: {
								                      color: '#64CBCC'
								   },
				                   value: 5
				                 },
				                 {
				                   name: '工程实践实训实验班',
								   itemStyle: {
								                      color: '#64CBCC'
								   },
				                   value: 5
				                 }
				               ]
				             },
							 {
							   name: '竞赛队',
							   value: 10,
							   itemStyle: {
							                      color: '#F8C82E'
							   },
							 },
							 {
							   name: '技术部',
							   value: 10,
							   itemStyle: {
							                      color: '#F69899'
							   },
							 },{
							   name: '宣传部',
							   value: 10,
							   itemStyle: {
							                      color: '#E6FDD3'
							   },
							 },
							 {
							   name: '人事部',
							   value: 10,
							   itemStyle: {
							                      color: '#64CBCC'
							   },
							 },
							 {
							   name: '秘书部',
							   value: 10,
							   itemStyle: {
							                      color: '#8FC9FB'
							   },
							 },
							 {
							   name: '财务部',
							   value: 10,
							   itemStyle: {
							                      color: '#364D79'
							   },
							 }
							 
				           ]
				         }
				       ];
				       option = {
						   
						   title: {
						     text: '组织架构',
						     subtext: ''
						   },
						   textStyle: {
						   	color: '#fff',
						   							
						   },
						   grid:{
							   top:100,
						   },
						   tooltip : {
						           trigger: 'item',
						           formatter: "{b} "
						       },
				         series: {
				           type: 'sunburst',
				           // emphasis: {
				           //     focus: 'ancestor'
				           // },
				           data: data,
				           radius: [0, '90%'],
				           label: {
				             rotate: 'radial'
				           }
				         }
				       };
				       
				       option && myChart2.setOption(option);
					   window.onresize = function () {
								console.log('执行更改')
					   			height= $(window).height();//浏览器的高度 
					   			myChart2.getDom().style.height = this.height+ "px";
					   			myChart2.resize();
					   		}
			}
		}
	}
</script>