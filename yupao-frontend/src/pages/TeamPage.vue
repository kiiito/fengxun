<template>
<div id="teamPage">
  <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch" />
  <van-tabs v-model:active="active" @change="onTabChange">
    <van-tab title="公开" name="public"/>
    <van-tab title="加密" name="private"/>
  </van-tabs>
  <div style="margin-bottom: 16px"/>
  <van-button class="add-button" type="primary" icon="plus"  @click="doJoinTeam"></van-button>
  <team-card-list :teamList="teamList"/>
  <van-empty v-if="teamList?.length < 1" description="数据为空"/>
</div>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import TeamCardList from "../components/TeamCardList.vue";
import myAxios from "../plugins/myAxios.js";
import {showToast} from "vant";
import {onMounted,ref} from "vue";

const active = ref('public');

const router = useRouter();
const searchText = ref('');
//跳转到加入队伍页
const doJoinTeam = () => {
  router.push({
    path: "/team/add"
  })
}

//定义一个空数组
const teamList = ref([]);

const listTeam = async (val = '',status = 0) =>{
  //get请求 需要传值params
  const res = await myAxios.get("/team/list",{
    params:{
      searchText: val,
      pageNum: 1,
      status,
    }
  });
  console.log(res);
  if (res?.code === 0){
    teamList.value = res.data;
  }else {
    showToast({
      message: '队伍加载失败，请刷新重试',
      type: 'fail'
    });
  }
}
//页面加载时只加载一次
onMounted( () =>{
listTeam();
})
const onSearch = (val) => {
  listTeam(val);
};

//标签切换触发
const onTabChange =(name) =>{
  //查公开
  if (name === 'public'){
    listTeam(searchText.value,0)
  }else {
    //查加密
    listTeam(searchText.value,2)
  }
}

//定义一个空数组
const myJoinTeamList = ref([]);
const myJoinListTeam = async (val = '') =>{
  //get请求 需要传值params
  const res = await myAxios.get("/team/list/my/join",{
    params:{
      searchText: val,
      pageNum: 1,
    }
  });
  if (res?.code === 0){
    myJoinTeamList.value = res.data;
  }else {
    showToast({
      message: '队伍加载失败，请刷新重试',
      type: 'fail'
    });
  }
}
</script>

<style scoped>
#teamPage{

}
</style>