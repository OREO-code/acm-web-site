<template>
  <div id="app">
	  	<div id="nav">
	  		<el-menu
						router="true"
	  		          :default-active="1"
	  		          class="el-menu-demo"
	  		          mode="horizontal"

	  		          >


          <el-menu-item><div> <img src="./assets/acmlogo.jpg" style="width: 3.875rem;height: 3.125rem;"></div></el-menu-item>
              <el-menu-item index="1" route="/">
          <div ><i class="el-icon-house"></i>首页</div>
					</el-menu-item>

          <el-menu-item index="2" route="/introduce"><div><i class="el-icon-position"></i>资讯</div></el-menu-item>
          <el-menu-item index="3" route="/member"><div><i class="el-icon-medal-1"></i>成员风采</div></el-menu-item>
          <el-menu-item index="4" route="/tulin"><div><i class="el-icon-data-line"></i>暑期编程训练营</div></el-menu-item>
          <el-menu-item index="5" route="/tulin"><div><i class="el-icon-trophy"></i>图灵杯</div></el-menu-item>
          <el-menu-item index="6" route="/time"><div ><i class="el-icon-time"></i> 时间线</div></el-menu-item>
          <el-menu-item><a href="http://newoj.acmclub.cn/home" style="text-decoration: none;color: #A6ADB4;" target="_blank" ><div><i class="el-icon-link"></i>NEUQ OJ</div></a>
          </el-menu-item>
          <el-menu-item index="7" route="/login" ><div><i class="el-icon-setting"></i>管理员登录</div></el-menu-item>
	  		      </el-menu>
	  	</div>
		<router-view></router-view>
	  
  </div>
</template>

<style>

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
			        // console.log(key, keyPath);
			      }
		}
	}
</script>