import { createApp } from 'vue'
import App from './App.vue'
import "vant/lib/index.css"
import { Button, Icon, NavBar, Tabbar, TabbarItem } from "vant"
import { createWebHistory, createRouter } from 'vue-router'
import routes from "./config/routes"
import Vant from 'vant'
import '../global.css'
const app = createApp(App)

// 注册 Vant 组件
// app.use(Button)
// app.use(NavBar)
// app.use(Icon)
// app.use(Tabbar)
// app.use(TabbarItem)

app.use(Vant)
// 创建路由 ts类型错误 可忽略
const router = createRouter({
    history: createWebHistory(),
    routes,
})

app.use(router)
app.mount('#app')