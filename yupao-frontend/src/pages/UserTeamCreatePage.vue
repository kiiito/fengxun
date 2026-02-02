<template>
<div id="teamPage">
  <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch" />
  <van-button type="primary" @click="doJoinTeam">创建队伍</van-button>
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

const listTeam = async (val = '') =>{
  //get请求 需要传值params
  const res = await myAxios.get("/team/list/my/create",{
    params:{
      searchText: val,
      pageNum: 1,
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
</script>

<style scoped>
#teamPage{

}
</style>