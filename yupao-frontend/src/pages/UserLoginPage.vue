<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="账号"
          label="账号"
          placeholder="请输入用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="userPassword"
          type="password"
          name="userPassword"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>

</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {ref} from 'vue';
import myAxios from "../plugins/myAxios";
import {showToast} from "vant";
const router = useRouter();
const route = useRoute();
const userAccount = ref('');
const userPassword = ref('');
const onSubmit = async () => {
  const res = await myAxios.post('/user/login',{
   userAccount:userAccount.value,
   userPassword:userPassword.value,
 })
  console.log(res,'用户登录');
  if(res.code === 0 && res.data){
    showToast({
      message: '登陆成功',
      type: 'success'
    });
    const redirectUrl = route.query?.redirect as string ?? '/';
    window.location.href= redirectUrl;
  }else {
    showToast({
      message: '登陆失败',
      type: 'fail'
    });
  }
};
</script>

<style scoped>

</style>