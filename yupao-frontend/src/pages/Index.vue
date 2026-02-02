<template>
  <van-cell center title="心动模式">
    <template #right-icon>
      <van-switch v-model="isMatchMode" />
    </template>
  </van-cell>

  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!userList || userList.length < 1" description="数据为空"/>
</template>

<script setup lang="ts">
import {ref, onMounted, watchEffect} from 'vue';
import {useRoute} from "vue-router";
import myAxios from "../plugins/myAxios.ts";
import {showFailToast, showSuccessToast} from 'vant';
import UserCardList from "../components/UserCardList.vue";
import {userType} from "../models/user.d.ts";
//定义两种不同类型的模式
// type ModeType = "default" | "match";

//接收路由传参
const route = useRoute();
const userList = ref([]);
//可切换模式 默认为列表模式
// const mode = ref<ModeType>("default");
const isMatchMode = ref<Boolean>(false);
const loading = ref(true);

onMounted(async () => {

})
/**
 * 匹配用户
 */
const loadDate = async () =>{
  let userListData;
  loading.value = true;
  //心动模式
  if (isMatchMode.value){
    const  num = 10;
    userListData = await myAxios.get('user/match',{
      params: {
        num,
      },
    })
        .then(function (response) {
          console.log('/user/match succeed',response);
          showSuccessToast('请求成功');
          return response?.data;
        })
        .catch(function (error) {
          console.log('/user/match error',error);
          showFailToast('请求失败');
        });
  }else {
    //普通用户使用分页查询
    userListData = await  myAxios.get('/user/recommend',{
      params: {
        pageSize: 8,
        pageNum: 1,
      },
    })
        .then(function (response) {
          console.log('/user/recommend succeed', response);
          showSuccessToast('请求成功');
          return response?.data?.records;
        })
        .catch(function (error) {
          console.log('/user/recommends error',error);
          showFailToast('请求失败');
        });

  }
  if (userListData){
    userListData.forEach((user: userType) =>{
      if (user.tags){
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
  loading.value = false;
}

//监控变量变化
watchEffect(() =>{
  loadDate()
})
</script>

<style scoped>

</style>>