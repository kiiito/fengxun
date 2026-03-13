<template>
  <div id="addSpacePage">
    <h2 style="margin-bottom: 16px">
      {{ route.query?.id ? '修改' : '创建' }}{{ SPACE_TYPE_MAP[spaceType] }}
    </h2>
    <!--    空间信息表单-->
    <a-form layout="vertical" name="spaceForm" :model="spaceForm" @finish="handleSubmit">
      <a-form-item name="name" label="空间名称">
        <a-input v-model:value="spaceForm.spaceName" placeholder="请输入空间名称" allow-clear/>
      </a-form-item>
      <a-form-item label="空间级别" name="spaceLevel">
        <a-select
          v-model:value="spaceForm.spaceLevel"
          :options="SPACE_LEVEL_OPTIONS"
          placeholder="请输入空间级别"
          style="min-width: 180px"
          allow-clear
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" :loading="loading" style="width: 100%;">提交</a-button>
      </a-form-item>
    </a-form>
    <a-card title="空间级别介绍">
      <a-typography-paragraph>
        * 目前仅支持开通普通版，如需升级空间，请联系
        <a href="https://www.douyin.com/user/self?from_tab_name=main&showSubTab=compilation&showTab=favorite_collection"
           target="_blank">老胡</a>
      </a-typography-paragraph>
      <a-typography-paragraph v-for="spaceLevel in spaceLevelList">
        {{ spaceLevel.text }}： 大小 {{ formatSize(spaceLevel.maxSize) }}， 数量
        {{ spaceLevel.maxCount }}
      </a-typography-paragraph>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
import {computed, onMounted, reactive, ref} from "vue";
import {SPACE_TYPE_MAP} from "@/constants/space";
import {message} from "ant-design-vue";
import {
  addSpaceUsingPost,
  getSpaceVoByIdUsingGet,
  listSpaceLevelUsingGet, updateSpaceUsingPost
} from "@/api/spaceController";
import {useRoute, useRouter} from "vue-router";
import {SPACE_LEVEL_OPTIONS, SPACE_TYPE_ENUM} from "@/constants/space";
import {formatSize} from "@/utils/index";

const router = useRouter()
const space = ref<API.SpaceVO>()
const spaceForm = reactive<API.SpaceAddRequest | API.SpaceEditRequest>({})
const loading = ref(false)
const spaceLevelList = ref<API.SpaceLevel[]>([])
const route = useRoute()
// 空间类别
const spaceType = computed(() => {
  if (route.query?.type) {
    return Number(route.query.type)
  }
  return SPACE_TYPE_ENUM.PRIVATE
})

// 获取空间级别
const fetchSpaceLevelList = async () => {
  const res = await listSpaceLevelUsingGet()
  if (res.data.code === 0 && res.data.data) {
    spaceLevelList.value = res.data.data
  } else {
    message.error('加载空间级别失败，' + res.data.message)
  }
}
onMounted(() => {
  fetchSpaceLevelList()
})
const handleSubmit = async (values: any) => {
  loading.value = true;
  const spaceId = space.value?.id
  let res;
  if (spaceId) {
    res = await updateSpaceUsingPost({
      id: spaceId,
      ...spaceForm,
    })
  } else {
    //创建空间
    res = await addSpaceUsingPost({
      ...spaceForm,
      spaceType: spaceType.value
    })
  }
  console.log(res)
  if (res.data.code === 0 && res.data.data) {
    console.log("1212")
    message.success("操作成功")
    //跳转到空间详情页
    await router.push({
      path: `/space/${res.data.data}`
    })
  } else {
    message.error("操作失败" + res.data.message)
  }
  loading.value = false;
};

/**
 * 获取老数据
 */

const getOldSpace = async () => {
  const id = route.query?.id
  if (id) {
    const res = await getSpaceVoByIdUsingGet({id})
    if (res.data.code === 0 && res.data.data) {
      const data = res.data.data
      space.value = data
      spaceForm.spaceName = data.spaceName
      spaceForm.spaceLevel = data.spaceLevel
    }
  }

}
onMounted(() => {
  getOldSpace()
})
</script>

<style scoped>
#addSpacePage {
  max-width: 720px;
  margin: 0 auto;
}
</style>
