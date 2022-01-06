<template>
	<el-menu
			:default-active="index"
			class="el-menu-vertical-demo"
			background-color="#545c64"
			text-color="#fff"
			active-text-color="#ffd04b"
			router>

    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </template>
    </el-submenu>

    <router-link to="/sys/notice" style="text-decoration: none">
      <el-menu-item index="2">
        <template slot="title">
          <i class="el-icon-message-solid"></i>
          <span slot="title">公告</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link  to="/sys/rotation" style="text-decoration: none">
      <el-menu-item index="3">
        <template slot="title">
          <i class="el-icon-picture"></i>
          <span slot="title">轮播图</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/intro" style="text-decoration: none">
      <el-menu-item  index="4" >
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span slot="title">介绍文档</span>
        </template>
      </el-menu-item>
    </router-link>

		<router-link to="/sys/time" style="text-decoration: none">
			<el-menu-item index="5" >
				<template slot="title">
					<i class="el-icon-time"></i>
					<span slot="title">时间线</span>
				</template>
			</el-menu-item>
		</router-link>

		<router-link to="/sys/member" style="text-decoration: none">
			<el-menu-item index="6">
				<template slot="title">
					<i class="el-icon-medal-1"></i>
					<span slot="title">成员风采</span>
				</template>
			</el-menu-item>
		</router-link>

    <router-link to="/sys/tulin" style="text-decoration: none">
      <el-menu-item index="7">
        <template slot="title">
          <i class="el-icon-medal-1"></i>
          <span slot="title">文件管理</span>
        </template>
      </el-menu-item>
    </router-link>

		<router-link to="/sys/users" style="text-decoration: none">
			<el-menu-item index="8">
				<template slot="title">
					<i class="el-icon-s-custom"></i>
					<span slot="title">权限管理</span>
				</template>
			</el-menu-item>
		</router-link>


	</el-menu>
</template>

<script>
	export default {
		name: "SideMenu",
		data() {
			return {
				
			}
		},
		computed:  {

		},
		methods: {

		}
	}
</script>

<style scoped>
	.el-menu-vertical-demo {
		height: 100%;
	}

</style>