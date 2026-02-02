<template>
  <template v-if="user">
    <van-cell title="用户名" is-link to="/user/edit" :value="user.username" @click="toEdit('username','昵称',user.gender)"/>
    <van-cell title="账号" :value="user.userAccount"/>
    <van-cell title="头像" is-link to="/user/edit">
      <img style="height: 40px" :src="user.avatarUrl">
    </van-cell>
  <van-cell title="修改信息" is-link to="/user/update" />
  <van-cell title="我创建的队伍" is-link to="/user/team/create" />
  <van-cell title="我加入的队伍" is-link to="/user/team/join" />
</template>
</template>
<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from 'vue';
import myAxios from "../plugins/myAxios";
import {getCurrentUser} from "../services/user";
// const user = {
//   id: 1,
//   username: '黄腾辉',
//   userAccount: 'huang',
//   avatarUrl: 'https://pic.code-nav.cn/user_avatar/1659139613182193665/thumbnail/J6wYaVYo-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230518181932.jpg',
//   gender: '男',
//   phone: '12345678912',
//   email: '123@qq.com',
//   planetCode: '1234',
//   createTime: new Date(),
// }
const router = useRouter();
const user = ref();
onMounted(async () => {
  user.value = await getCurrentUser();
  console.log("i1", typeof user.value.plantCode)
  console.log("i2", user.value.plantCode)
})
const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit',
    query: {
      editKey,
      editName,
      currentValue
    }
  })
}
</script>

<style scoped>

</style>