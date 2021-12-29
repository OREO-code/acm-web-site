import Vue from 'vue'
import VueRouter from 'vue-router'
import WebHome from '../views/webHome.vue'
import Form from '../views/Form.vue'
import Tulin from '../views/Tulin.vue'
import Member from '../views/member.vue'
import Time from '../views/Time.vue'
import Login from '../views/Login.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'WebHome',
    component: WebHome
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
    path: '/member',
    name: 'Member',
    component: Member
  },
  {
    path: '/time',
    name: 'Time',
    component: Time
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
