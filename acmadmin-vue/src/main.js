import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import mavonEditor from 'mavon-editor'

import Element from "element-ui"
import "element-ui/lib/theme-chalk/index.css"

import axios from './axios'



import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)
Vue.prototype.$axios = axios //
Vue.config.productionTip = false

// require("./mock.js")

Vue.use(Element)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
