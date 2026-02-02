<template>
  <div id="app">
    <a-config-provider :locale="locale">
      <BasicLayout />
    </a-config-provider>
  </div>
</template>
<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue"
import {healthCheckUsingGet} from "@/api/mainController";
import {useLoginUserStore} from "@/stores/useLoginUserStore";
import zhCN from 'ant-design-vue/es/locale/zh_CN'
import { ref } from 'vue'
const locale = ref(zhCN)
//已经改为权限控制中获取用户信息了
// const loginUserStore =  useLoginUserStore()
// loginUserStore.fetchLoginUser()
// healthCheckUsingGet().then(res => {
//   console.log(res)
// })
</script>

<style scoped>

</style>
