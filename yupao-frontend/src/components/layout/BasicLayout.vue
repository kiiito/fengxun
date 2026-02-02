<template>
  <van-nav-bar
      :title="title"
      right-text="按钮"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
  >
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>
<!-- 各个模块的内容选项 需要点击导航栏进行切换-->
 <div id="content">
<!--   <template v-if="active === 'index'">-->
<!--     <Index />-->
<!--   </template>-->
<!--   <template v-if="active === 'team'">-->
<!--     <Team />-->
<!--   </template>-->
<!--   更改成路由切换-->
   <router-view/>
 </div>
  <van-tabbar route @change="onChange">
    <van-tabbar-item to="/" icon="home-o" name="index">主页</van-tabbar-item>
    <van-tabbar-item to="/team" icon="search" name="team">队伍</van-tabbar-item>
    <van-tabbar-item to="/user" icon="friends-o" name="user">个人</van-tabbar-item>
  </van-tabbar>

</template>

<script setup lang="ts">
import { ref } from 'vue';
import {showToast} from "vant";
import {useRoute, useRouter} from "vue-router";
import routes from "../../config/routes.js";
const router = useRouter();
const DEFAULT_TITLE = '伙伴匹配'
const title = ref(DEFAULT_TITLE);

//切换路径更改标题
router.beforeEach((to,from) =>{
  const toPath = to.path;
  const route = routes.find((route) =>{
    return toPath == route.path;
  })
title.value = route?.title ?? DEFAULT_TITLE;
})

// 引进router对象

const onClickLeft = () => {
  router.back();
};
const onClickRight = () =>{
  router.push('/search')
};
// 更换高亮（初始化的高亮）
const active = ref('index');
const onChange = (index) => showToast(`标签 ${index}`);
</script>

<style scoped>
#content{
  padding-bottom: 50px;
}
</style>