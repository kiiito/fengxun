<template>
  <van-skeleton title avatar :row="3" :loading="props.loading" v-for="user in userList">
  <van-card
      :desc="user.profile"
      :title="`${user.username}(${user.plantCode})`"
      :thumb="user.avatarUrl"
  >
    <template #tags>
      <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px;margin-top: 8px">
        {{ tag }}
      </van-tag>
    </template>
    <template #footer>
      <van-button size="mini">联系我</van-button>
    </template>
  </van-card>
  </van-skeleton>
</template>

<script setup lang="ts">
import type {userType} from "../models/user.d.ts";
import {withDefaults,defineProps}from 'vue';
// interface UserCardListProps{
//   loading?:boolean;
//   userList?:userType[];
// }
 // const props = withDefaults(defineProps<UserCardListProps>(),{
 //   loading:true,
 //   userList:()=>[] as userType[],
 // })
// const props = defineProps<{
//   loading?: boolean;
//   userList?: userType[];
// }>().withDefaults({
//   loading: true,
//   userList: () => [] as userType[]
// });


// 使用这个语法，兼容性最好
const _props = defineProps<{
  loading?: boolean;
  userList?: userType[];
}>();
// 手动处理默认值
import { computed } from 'vue';

const props = {
  loading: computed(() => _props.loading ?? true),
  userList: computed(() => _props.userList ?? [])
};

</script>

<style scoped>

</style>