import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import User from '../views/sys/User.vue'

import Rotation from '../views/sys/Rotation.vue'
import Member from '../views/sys/Member.vue'
import Intro from '../views/sys/Intro.vue'
import Time from '../views/sys/Time.vue'
import Tulin from '../views/sys/Tulin.vue'

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
				component: IntroEdit,
				meta: {
					title: "编辑介绍文档"
				},
			},
			{
				path: '/sys/intro/edit',
				name: 'IntroduceEdit',
				component: IntroEdit,
				meta: {
					title: "新建介绍文档"
				},
			},
			{
				path: '/sys/intro',
				name: 'Sysintro',
				component: Intro,
				meta: {
					title: "查看介绍文档"
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


	let token = localStorage.getItem("token")

	if (to.path == '/login') {
		next()

	} else if (!token) {
		next({path: '/login'})

	}



	next()
})




export default router
