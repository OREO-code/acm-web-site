<template>
	<el-menu
			:default-active="this.$store.state.menus.editableTabsValue"
			class="el-menu-vertical-demo"
			background-color="#545c64"
			text-color="#fff"
			active-text-color="#ffd04b"
			router>
<!-- @click="selectMenu({name: 'Index', title: '首页'})" -->
		<router-link to="/index">
			<el-menu-item index="1"  >
				<template slot="title">
					<i class="el-icon-s-home"></i>
					<span slot="title">首页</span>
				</template>
			</el-menu-item>
		</router-link>
<!-- @click="selectMenu({name: 'Rotation', title: '修改轮播图'})" -->
		<router-link  to="/sys/rotation" >
			<el-menu-item index="2">
				<template slot="title">
					<i class="el-icon-picture"></i>
					<span slot="title">修改轮播图</span>
				</template>
			</el-menu-item>
		</router-link>
<!-- @click="selectMenu({name: 'Intro', title: '修改介绍文档'})" -->
		<router-link to="/sys/intro" >
			<el-menu-item  index="3" >
				<template slot="title">
					<i class="el-icon-s-order"></i>
					<span slot="title">修改介绍文档</span>
				</template>
			</el-menu-item>
		</router-link>
<!-- @click="selectMenu({name: 'Time', title: '修改时间线'})" -->
		<router-link to="/sys/time" >
			<el-menu-item index="4" >
				<template slot="title">
					<i class="el-icon-time"></i>
					<span slot="title">修改时间线</span>
				</template>
			</el-menu-item>
		</router-link>
<!-- @click="selectMenu({name: 'Member', title: '修改成员风采'})" -->
		<router-link to="/sys/member" >
			<el-menu-item index="5">
				<template slot="title">
					<i class="el-icon-medal-1"></i>
					<span slot="title">修改成员风采</span>
				</template>
			</el-menu-item>
		</router-link>
<!-- @click="selectMenu({name: 'Tulin', title: '图灵杯'})" -->
		<router-link to="/sys/tulin" >
			<el-menu-item index="6">
				<template slot="title">
					<i class="el-icon-trophy"></i>
					<span slot="title">修改图灵杯</span>
				</template>
			</el-menu-item>
		</router-link>
		<!-- @click="selectMenu({name: 'User', title: '管理员'})" -->
		<router-link to="/sys/users" >
			<el-menu-item index="7">
				<template slot="title">
					<i class="el-icon-s-custom"></i>
					<span slot="title">权限管理</span>
				</template>
			</el-menu-item>
		</router-link>
		<!-- @click="selectMenu({name: 'Qrcode', title: '二维码'})" -->
		<router-link to="/sys/qrcode" >
			<el-menu-item index="8">
				<template slot="title">
					<i class="el-icon-s-grid"></i>
					<span slot="title">修改二维码</span>
				</template>
			</el-menu-item>
		</router-link>
		<!-- @click="selectMenu({name: 'Notice', title: '修改公告'})" -->
		<router-link to="/sys/notice" >
			<el-menu-item index="9">
				<template slot="title">
					<i class="el-icon-message-solid"></i>
					<span slot="title">修改公告</span>
				</template>
			</el-menu-item>
		</router-link>
		<!-- <router-link to="/sys/roles" @click="selectMenu({name: 'Role', title: '权限'})">
			<el-menu-item index="9">
				<template slot="title">
					<i class="el-icon-s-custom"></i>
					<span slot="title">权限</span>
				</template>
			</el-menu-item>
		</router-link> -->
		<!-- <router-link to="/sys/users" @click="selectMenu({name: 'User', title: '用户'})">
			<el-menu-item index="10">
				<template slot="title">
					<i class="el-icon-s-custom"></i>
					<span slot="title">用户</span>
				</template>
			</el-menu-item>
		</router-link> -->
		<!-- <router-link to="/index">
			<el-menu-item index="Index"  @click="selectMenu({name: 'Index', title: '首页'})">
				<template slot="title">
					<i class="el-icon-s-home"></i>
					<span slot="title">首页</span>
				</template>
			</el-menu-item>
		</router-link> -->
		<!-- <el-submenu :index="menu.name" v-for="menu in menuList">
			<template slot="title">
				<i :class="menu.icon"></i>
				<span>{{menu.title}}</span>
			</template>

			<router-link :to="item.path" v-for="item in menu.children">
				<el-menu-item :index="item.name" @click="selectMenu(item)">
					<template slot="title">
						<i :class="item.icon"></i>
						<span slot="title">{{item.title}}</span>
					</template>
				</el-menu-item>
			</router-link> -->

		</el-submenu>

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