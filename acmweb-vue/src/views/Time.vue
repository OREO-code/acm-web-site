<template>
	<div id="app">
		  	<div class="block">
		  	  <el-timeline>
		  	    <el-timeline-item v-for="item in timeList" :timestamp="item.year" placement="top">
		  	      <el-card>
		  	        <p>{{item.content}}</p>
		  	      </el-card>
		  	    </el-timeline-item>
		  	  </el-timeline>
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
	        timeList:[]
	      };
	    },
		created() {
			var _that=this
			this.$axios.post('http://101.43.16.42:8082/time').then((response)=>{
					_that.notice=response.data.data.notice
			        console.log(response);
			      }).catch((response)=>{
			        console.log(response);
			      })
		}
	  };	
</script>

<style>
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
	.item {
	  margin-left: 30px; /*用左边margin显示时间线*/
	  width: calc(100% - 30px); /*因为左边部分用于显示时间线，所以右边部分减去30px*/
	  height: auto; /*高度由内容决定*/
	  position: relative;
	  margin-bottom: 10px;
	  cursor: pointer;
	}
	.item::before {
	  content: "";
	  width: 11px;
	  height: 11px;
	  border-radius: 100%;
	  background-color: #91c2fc;
	  position: absolute;
	  left: -15px;
	}
	.block{
		margin-top: 5%;
	}

</style>
