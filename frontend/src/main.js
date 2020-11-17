import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import i18n from './lang' // Internationalization
import '@/icons' // icon
import '@/permission' // permission control
import webSocketService from '@/config/websocket/index'

Vue.use(ElementUI, {
  size: Cookies.get('size') || 'medium', // set element-ui default size,
  i18n: (key, value) => i18n.t(key, value),
  locale
})

Vue.config.productionTip = false

Vue.use(webSocketService, {
  store
})

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
