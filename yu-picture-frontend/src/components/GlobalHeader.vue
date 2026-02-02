<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="200px">
        <router-link to="/">
          <div class="title-bar">
            <img class="logo" src="../assets/logo.png" alt="logo"/>
            <div class="title">方源云图库</div>
          </div>
        </router-link>
      </a-col>
      <a-col flex="auto">
        <a-menu v-model:selectedKeys="current" mode="horizontal" :items="items" @click="onMenuClick"/>
      </a-col>
      <!-- 用户信息展示栏-->
      <a-col flex="120px">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar"/>
                {{ loginUserStore.loginUser.userName ?? '毛名5671' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item >
                    <router-link to="/my_space">
                      <UserOutlined/>
                      我的空间
                    </router-link>
                  </a-menu-item>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined/>
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>

  </div>

</template>
<script lang="ts" setup>
import {computed, h, ref} from 'vue'
import {HomeOutlined, LogoutOutlined,UserOutlined} from '@ant-design/icons-vue'
import {MenuProps, message} from 'ant-design-vue'
import {useRouter} from "vue-router";
import * as path from "path";
import {useLoginUserStore} from "@/stores/useLoginUserStore";
import {userLogoutUsingPost} from "@/api/userController";

const loginUserStore = useLoginUserStore()

//当前要高亮的菜单项
const current = ref<string[]>(['home'])
const router = useRouter()
//监听路由变化 更新高亮菜单项
router.afterEach((to, from, next) => {
  current.value = [to.path]
})
//未经过过滤的原始用户菜单栏
const originItems = [{
  key: '/',
  icon: () => h(HomeOutlined),
  label: '主页',
  title: '主页',
},
  {
    key: '/admin/userManage',
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: '/admin/pictureManage',
    label: '图片管理',
    title: '图片管理',
  },
  {
    key: '/admin/spaceManage',
    label: '空间管理',
    title: '空间管理',
  },
  {
    key: '/add_picture',
    label: '创建图片',
    title: '创建图片',
  },
  {
    key: 'others',
    label: h('a', {href: 'https://www.codefather.cn', target: '_blank'}, '编程导航'),
    title: '编程导航',
  },]
const items = computed(() => {
 return  filterMenus(originItems);
})

//根据权限过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    //只有管理员才能看到以/admin开头的菜单项
    if (menu?.key?.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}
const onMenuClick = ({key}) => {
  router.push({
      path: key
    }
  )
}
/**
 * 用户注销
 */
const doLogout = async () => {
  const res = await userLogoutUsingPost()
  if (res.data.code === 0 && res.data.data) {
    //移除登录态
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success("退出登录成功")
    await router.push({
      path: '/user/login'
    })
  } else {
    message.error("退出登录失败")
  }
}
</script>
<style scoped>
#globalHeader .title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  font-size: 18px;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>


