<template>
	<div id="app">
		  	<div class="block">
				<div class="title">
					道路仍在延续
					
				</div>
				<div class="time">
					<el-timeline :reverse="reverse">
					    <el-timeline-item
					      v-for="(item, index) in timeList"
					      :key="index"
						  :class="index%2 !=0?'el-timeline-item-live-out':''"
					      :timestamp="item.time">
					      <div class="info">
							  {{item.content}}
						  </div>
					    </el-timeline-item>
					  </el-timeline>
				</div>
				<div class="bottomtitle">
					脚步从未停止
				</div>
		  	</div>
	</div>
</template>
<!-- </el-row> -->
<script>
	export default {
	    data() {
	      return {
	        reverse: true,
			sum:0,
	        timeList:[
				
			]
	      };
	    },
		created() {
			var _that=this
			this.$axios.get('http://101.43.16.42:8082/time').then((response)=>{
					_that.timeList=response.data.data.timeList.reverse()
					for(var i=0;i<_that.timeList.length;i++){
						var ipods=_that.timeList[i].time.indexOf("T")
						_that.timeList[i].time=_that.timeList[i].time.substring(0,ipods)
					}
			        console.log(response);
			      }).catch((response)=>{
			        console.log(response);
			      })
		}
	  };	
</script>

<style>
	.el-timeline.is-reverse{
		width: 50% !important;
		margin-left: 45% !important;
	}
	.el-timeline-item__timestamp.is-bottom{
		text-align: center !important;
		width: 50% !important;
		margin-left: 25%;
		margin-top: 0px !important;
		border-bottom: 0.03125rem darkgray solid !important;
		border-bottom-right-radius: 20px;
		border-bottom-left-radius: 20px;
		padding-top: 1.25rem !important;
		padding-bottom: 1.25rem;
		background: #1FA2FF;  /* fallback for old browsers */
		background: -webkit-linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF);  /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		color: white !important;
	}
	.el-timeline{
		position: relative;
		margin-top: 10% !important;
		
	}
	.info{
		/* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
		/* border-radius: 1.875rem; */
		padding-top: 1.25rem;
		font-weight: 600 !important;
		width: 50% !important;
		margin-left: 25%;
		border-top: 0.03125rem darkgray solid !important;
		border-top-right-radius: 20px;
		border-top-left-radius: 20px;
		color: #409EFF !important;
		background: #1FA2FF;  /* fallback for old browsers */
		background: -webkit-linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF);  /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		color: white !important;
	}
	/* .el-timeline-item__wrapper {
	position: relative;
	padding-left: -280px !important;
	top: -3px;} */
	.el-timeline-item__wrapper{
		position: relative;
		
/* 		margin-left: -40%; */
		
	}
	.el-timeline-item-live-out .el-timeline-item__wrapper {
    position: relative;
	   padding-left: -160px !important;
		left: -100%;
	}
	.el-timeline-item__node {
	    position: absolute;
	    background-color: #58B7FF !important;
	    border-radius: 50%;
	    display: flex;
	    justify-content: center;
	    align-items: center;
		
	}
	.el-timeline-item__tail{
		background-color: #22B2E4 !important;
		
	}
	/* el-timeline-item__timestamp is-bottom */
	/* .el-card_body{
		color: white !important;
		height: 6.25rem !important;
	} */
	#app {
	  font-family: Avenir, Helvetica, Arial, sans-serif;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	  text-align: center;
	  color: #2c3e50;
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
	.block{
		padding-top: 5%;
		width: 100%;
		/* background: #FFEFBA;
		background: -webkit-linear-gradient(to bottom, #FFFFFF, #FFEFBA); 
		background: linear-gradient(to bottom, #FFFFFF, #FFEFBA); 
		*/
	   background-image: url(../assets/bg2.jpg);
	   background-repeat:no-repeat;
	   background-size: auto 135%;
	}
	.timebox{
		width: 80%;
		margin-left: 10%;
		margin-top: 3%;
		margin-bottom: 3%;
	}
	.title{
		width: 80%;
		margin-left: 10%;
		padding-top: 3%;
		padding-bottom: 3%;
		font-size: 3.125rem;
		font-weight: bold;
		color: white;
	}
	.bottomtitle{
		width: 80%;
		margin-left: 10%;
		padding-top: 3%;
		padding-bottom: 3%;
		font-size: 3.125rem;
		font-weight: bold;
		color: #58B7FF;
	}
	.time{
		width: 60%;
		margin-left: 20%;
	}
</style>
