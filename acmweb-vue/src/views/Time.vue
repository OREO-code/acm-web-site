<template>
	<div id="app">
		  	<div class="block">
				<el-timeline :reverse="reverse">
				    <el-timeline-item
				      v-for="(item, index) in timeList"
				      :key="index"
					  :class="index%2 !=0?'el-timeline-item-live-out':''"
				      :timestamp="item.time">
				      {{item.content}}
				    </el-timeline-item>
				  </el-timeline>
		  	 <!-- <el-timeline>
		  	    <el-timeline-item v-for="(item,index) in timeList" :timestamp="item.year" placement="top" :class="index%2 !=0?'el-timeline-item-live-out':''">
		  	      <el-card>
		  	        <p>{{item.content}}</p>
		  	      </el-card>
		  	    </el-timeline-item>
		  	  </el-timeline> -->
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
					_that.timeList=response.data.data.timeList
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
	.el-timeline-item__timestamp.is-bottom{
		text-align: center !important;
	}
	.el-timeline{
		margin-top: 10% !important;
		margin-left: 45% !important;
	}
	/* .el-timeline-item__wrapper {
	position: relative;
	padding-left: -280px !important;
	top: -3px;} */
	.el-timeline-item__wrapper{
		position: relative;
		margin-left: -20%;
	}
	.el-timeline-item-live-out .el-timeline-item__wrapper {
    position: relative;
    padding-left: -160px !important;
    left: -80%;
	}
	.el-timeline-item__node {
	    position: absolute;
	    background-color: #537EAF !important;
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
	//时间线
	
	.block{
		margin-top: 5%;
		display: flex;
		align-items: center;
	}

</style>
