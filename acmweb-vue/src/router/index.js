import Vue from 'vue'
import VueRouter from 'vue-router'
import WebHome from '../views/webHome.vue'
import Form from '../views/Form.vue'
import Tulin from '../views/Tulin.vue'
import Member from '../views/member.vue'
import Time from '../views/Time.vue'
import Login from '../views/Login.vue'
import Introduce from "../views/Introduce";
import IntroduceDetail from "../views/IntroduceDetail";
import Camp from "../views/Camp";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'WebHome',
    component: WebHome,
    children: [
      {
        path: '/',
        name: 'Introduce',
        meta: {
          title: "首页"
        },
        component: Introduce
      }
    ]
  },
  {
    path: '/introduce',
    name: 'Introduce',
    component: Introduce
  },
  {
    path: '/introduce/:introduceId',
    name: 'IntroduceDetail',
    component: IntroduceDetail
  },
  {
    path: '/form',
    name: 'Form',
    component: Form
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/tulin',
    name: 'Tulin',
    component: Tulin
  },
  {
    path: '/camp',
    name: 'Camp',
    component: Camp
  },
  {
    path: '/member',
    name: 'Member',
    component: Member
  },
  {
    path: '/time',
    name: 'Time',
    component: Time
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
