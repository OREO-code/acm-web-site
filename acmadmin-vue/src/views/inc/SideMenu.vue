<template>
	<el-menu
			:default-active="this.$store.state.menus.editableTabsValue"
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

      <router-link to="/sys/notice" >
        <el-menu-item index="1-1">
          <template slot="title">
            <i class="el-icon-message-solid"></i>
            <span slot="title">公告</span>
          </template>
        </el-menu-item>
      </router-link>

      <router-link  to="/sys/rotation" >
        <el-menu-item index="1-2">
          <template slot="title">
            <i class="el-icon-picture"></i>
            <span slot="title">轮播图</span>
          </template>
        </el-menu-item>
      </router-link>

      <router-link to="/sys/intro" >
        <el-menu-item  index="1-3" >
          <template slot="title">
            <i class="el-icon-s-order"></i>
            <span slot="title">介绍文档</span>
          </template>
        </el-menu-item>
      </router-link>

    </el-submenu>

		<router-link to="/sys/time" >
			<el-menu-item index="2" >
				<template slot="title">
					<i class="el-icon-time"></i>
					<span slot="title">时间线</span>
				</template>
			</el-menu-item>
		</router-link>

		<router-link to="/sys/member" >
			<el-menu-item index="3">
				<template slot="title">
					<i class="el-icon-medal-1"></i>
					<span slot="title">成员风采</span>
				</template>
			</el-menu-item>
		</router-link>

		<router-link to="/sys/users" >
			<el-menu-item index="4">
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
			menuList: {
				get() {
					return this.$store.state.menus.menuList
				}
			}
		},
		methods: {
			selectMenu(item) {
				this.$store.commit("addTab", item)
			}
		}
	}
</script>

<style scoped>
	.el-menu-vertical-demo {
		height: 100%;
	}
</style>