import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'


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

				component: Index
			},
			{
				path: '/userCenter',
				name: 'UserCenter',

				component: () => import('@/views/UserCenter.vue')
			},

			{
				path: '/sys/rotation',
				name: 'Sysrotation',
				component: Rotation,

			},
			{
				path: '/sys/intro/:introduceId/edit',
				name: 'IntroduceEdit',
				component: IntroEdit,

			},
			{
				path: '/sys/intro/edit',
				name: 'IntroduceEdit',
				component: IntroEdit,

			},
			{
				path: '/sys/intro',
				name: 'Sysintro',
				component: Intro,

			},
			{
				path: '/sys/time',
				name: 'Systime',
				component: Time,

			},
			{
				path: '/sys/member',
				name: 'Sysmember',
				component: Member,

			},
			{
				path: '/sys/tulin',
				name: 'Systulin',
				component: Tulin,

			},

			{
				path: '/sys/manage',
				name: 'Sysmanage',
				component: Manage,

			},
			{
				path: '/sys/notice',
				name: 'Sysnotice',
				component: Notice,

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
