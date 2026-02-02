<template>
<user-card-list :user-list="userList"/>
  <van-empty v-if="!userList || userList.length < 1" description="搜索结果为空"/>
</template>

<script setup >
import {ref,onMounted} from 'vue';
import {useRoute} from "vue-router";
import myAxios from "../plugins/myAxios.ts";
import { showToast } from 'vant';
import qs from 'qs';
import UserCardList from "../components/UserCardList.vue";
//接收路由传参
const route = useRoute();

const userList = ref([]);



onMounted( async() =>{
  // 为给定 ID 的 user 创建请求
  const userListData = await myAxios.get('/user/search/tags',{
    withCredentials: false,
    params: {
      tagNameList: tags
    },
    //用鱼皮的这个我的头像不会显示。
    // paramsSerializer: params =>{
    //   return qs.stringify(params,{indices: false})
    // }

    //序列化
    paramsSerializer: {
      serialize: params => qs.stringify(params, { indices: false}),
    }

  })
      .then(function (response) {
        console.log('/user/search/tags succeed',response);
        showToast({
          message: '请求成功',
          type: 'success'
        });
        return response?.data;
      })
      .catch(function (error) {
        console.log('/user/search/tags error',error);
        showToast({
          message: '请求失败',
          type: 'fail'
        });
      });
  if (userListData){
    userListData.forEach(user =>{
      if (user.tags){
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
})

//定义tags
const {tags} = route.query;
console.log(tags)
// const mockUser = {
//   id: 12345,
//   username: '李佳怡',
//   userAccount: 'lijiayi',
//   avatarUrl: 'https://pic.code-nav.cn/user_avatar/1659139613182193665/thumbnail/J6wYaVYo-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230518181932.jpg',
//   gender: '0',
//   profile: '我是帅哥',
//   phone: '12345678912',
//   email: '123@qq.com',
//   userRole: 0,
//   planetCode: '1234',
//   tags: ['java', 'emo', '打工仔'],
//   createTime: new Date(),
// }

</script>

<style scoped>

</style>