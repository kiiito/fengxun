<template>
  <div
      id="teamCardList"
  >
    <van-card
        v-for="team in props.teamList"
        :desc="team.description"
        :thumb="girl"
        :title="`${team.name} `"
    >
      <template #tags>
        <van-tag plain type="danger" style="margin-right: 8px; margin-top: 8px" >
          {{
            teamStatusEnum[team.status]
          }}
        </van-tag>
      </template>
      <template #bottom>
        <div>
          {{`队伍人数:${team.hasJoinNum}/${team.maxNum}`}}
        </div>
        <div>
          {{'过期时间' + team.expireTime}}
        </div>
        <div>
          {{'创建时间' + team.createTime}}
        </div>
      </template>
      <template #footer>
        <van-button size="mini" plain type="primary" @click="preJoinTeam(team)"
                    v-if="team.userId !== currentUser?.id && !team.hasJoin">
          加入队伍
        </van-button>
        <van-button v-if="team.userId === currentUser?.id" size="mini" plain  @click="doUpdateTeam(team.id)">
          更新队伍
        </van-button>
        <van-button  size="mini" plain  @click="doQuitTeam(team.id)"
                     v-else-if="team.userId !== currentUser?.id && team.hasJoin">
          退出队伍
        </van-button>
        <van-button v-if="team.userId === currentUser?.id" size="mini" type="danger" plain @click="doDeleteTeam(team.id)">
          解散队伍
        </van-button>
      </template>
    </van-card>
    <van-dialog v-model:show="showPasswordDialog" title="请输入密码"
                show-cancel-button @confirm="doJoinTeam" @cancel="doJoinCancel">
      <van-field v-model="password" placeholder="请输入密码"/>
    </van-dialog>
  </div>
</template>

<script setup lang="ts">
import {TeamType} from "../models/team";
import {teamStatusEnum} from "../constants/team";
import myAxios from "../plugins/myAxios";
import girl from "../assets/1.png";
import {defineProps, onMounted, withDefaults,ref} from "vue";

import 'vant/lib/index.css';
import {showSuccessToast, showFailToast, Dialog} from 'vant';
import {getCurrentUserState} from "../states/user";
import {getCurrentUser} from "../services/user";
import {useRouter} from "vue-router";

interface TeamCardListProps{
  teamList: TeamType[];
}
const props = withDefaults(defineProps<TeamCardListProps>(), {
  teamList: () => [] as TeamType[]
});
const router = useRouter();
const currentUser = ref();
const password = ref('');
const joinTeamId = ref(0);
const showPasswordDialog = ref(false);
onMounted(async () =>{
  currentUser.value = await getCurrentUser();
})
//队伍列表加入队伍
const doJoinTeam = async() =>{
  if (!joinTeamId.value){
    return;
  }
  const res = await myAxios.post("/team/join",{
    teamId:joinTeamId.value,
    password:password.value,
  });
  if (res?.code === 0){
    showSuccessToast('加入成功');
    doJoinCancel()
  }else{
    showFailToast('加入失败');
  }
}
const preJoinTeam = (team: TeamType) =>{
  joinTeamId.value = team.id;
  console.log(team.status)
  if (team.status === 0){
    doJoinTeam()
  }else {
    console.log(1)
    showPasswordDialog.value = true;
  }
}

const doJoinCancel =()=>{
  joinTeamId.value = 0;
  password.value= '';
}

/**
 * 更新队伍跳转到更新页面
 * @param id 队伍id
 */
const doUpdateTeam = (id:number) =>{
  router.push({
    path:'/team/update',
    query:{
      id,
    }
  })
}
/**
 * 退出队伍
 * @param id 队伍id
 */
const doQuitTeam = async (id: number) => {
  const res = await myAxios.post("/team/quit", {
    teamId: id
  });
  if (res?.code === 0) {
    showSuccessToast('操作成功');
  } else {
    showFailToast('操作失败');
  }
}
/**
 * 解散队伍
 * @param id 队伍id
 */
const doDeleteTeam = async (id: number) => {
  const res = await myAxios.post("/team/delete", {
    id: id
  });
  if (res?.code === 0) {
    showSuccessToast('操作成功');
  } else {
    showFailToast('操作失败');
  }
}

</script>

<style scoped>
#teamCardList :deep(.van-image__img){
  height: 128px;
  object-fit: unset;
}
</style>