import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import User from '../views/sys/User.vue'
import Role from '../views/sys/Role.vue'
import Menu from '../views/sys/Menu.vue'
import Rotation from '../views/sys/Rotation.vue'
import Member from '../views/sys/Member.vue'
import Intro from '../views/sys/Intro.vue'
import Time from '../views/sys/Time.vue'
import Tulin from '../views/sys/Tulin.vue'
import Qrcode from '../views/sys/Qrcode.vue'
import Manage from '../views/sys/Manage.vue'
import Notice from '../views/sys/Notice.vue'
import axios from "../axios";
import store from "../store"
import el from "element-ui/src/locale/lang/el";
import IntroEdit from "../views/sys/IntroEdit";

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'Home',
		component: Home,
		children: [
			{
				path: '/index',
				name: 'Index',
				meta: {
					title: "首页"
				},
				component: Index
			},
			{
				path: '/userCenter',
				name: 'UserCenter',
				meta: {
					title: "个人中心"
				},
				component: () => import('@/views/UserCenter.vue')
			},
			{
				path: '/sys/users',
				name: 'SysUser',
				meta: {
					title: "管理"
				},
				component: User
			},
			{
				path: '/sys/roles',
				name: 'SysRole',
				component: Role
			},
			{
				path: '/sys/menus',
				name: 'SysMenu',
				component: Menu
			},
			{
				path: '/sys/rotation',
				name: 'Sysrotation',
				component: Rotation,
				meta: {
					title: "修改轮播图"
				},
			},
			{
				path: '/sys/intro/:introduceId/edit',
				name: 'IntroduceEdit',
				component: IntroEdit
			},
			{
				path: '/sys/intro',
				name: 'Sysintro',
				component: Intro,
				meta: {
					title: "修改介绍文档"
				},
			},
			{
				path: '/sys/time',
				name: 'Systime',
				component: Time,
				meta: {
					title: "修改时间线"
				},
			},
			{
				path: '/sys/member',
				name: 'Sysmember',
				component: Member,
				meta: {
					title: "修改成员风采"
				},
			},
			{
				path: '/sys/tulin',
				name: 'Systulin',
				component: Tulin,
				meta: {
					title: "图灵杯"
				},
			},
			{
				path: '/sys/qrcode',
				name: 'Sysqrcode',
				component: Qrcode,
				meta: {
					title: "二维码"
				},
			},
			{
				path: '/sys/manage',
				name: 'Sysmanage',
				component: Manage,
				meta: {
					title: "权限管理"
				},
			},
			{
				path: '/sys/notice',
				name: 'Sysnotice',
				component: Notice,
				meta: {
					title: "公告管理"
				},
			},
		]
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('@/views/Login.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {

	let hasRoute = store.state.menus.hasRoutes

	let token = localStorage.getItem("token")

	if (to.path == '/login') {
		next()

	} else if (!token) {
		next({path: '/login'})


	} else if(token && !hasRoute) {
		axios.get("/sys/menu/nav", {
			headers: {
				Authorization: localStorage.getItem("token")
			}
		}).then(res => {

			console.log(res.data.data)

			// 拿到menuList
			store.commit("setMenuList", res.data.data.nav)

			// 拿到用户权限
			store.commit("setPermList", res.data.data.authoritys)

			console.log(store.state.menus.menuList)

			// 动态绑定路由
			let newRoutes = router.options.routes

			res.data.data.nav.forEach(menu => {
				if (menu.children) {
					menu.children.forEach(e => {

						// 转成路由
						let route = menuToRoute(e)

						// 吧路由添加到路由管理中
						if (route) {
							newRoutes[0].children.push(route)
						}

					})
				}
			})

			console.log("newRoutes")
			console.log(newRoutes)
			router.addRoutes(newRoutes)

			hasRoute = true
			store.commit("changeRouteStatus", hasRoute)
		})
	}



	next()
})


// 导航转成路由
const menuToRoute = (menu) => {

	if (!menu.component) {
		return null
	}

	let route = {
		name: menu.name,
		path: menu.path,
		meta: {
			icon: menu.icon,
			title: menu.title
		}
	}
	route.component = () => import('@/views/' + menu.component +'.vue')

	return route
}

export default router
