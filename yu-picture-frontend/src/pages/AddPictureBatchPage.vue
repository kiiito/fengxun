<template>
  <div id="addPictureBatchPage">
    <h2 style="margin-bottom: 16px">批量创建图片</h2>
    <a-form layout="vertical" :model="formData" @finish="handleSubmit">
      <a-form-item label="关键词" name="searchText">
        <a-input v-model:value="formData.searchText" placeholder="请输入关键词"/>
      </a-form-item>
      <a-form-item label="抓取数量" name="count">
        <a-input-number
          v-model:value="formData.count"
          placeholder="请输入数量"
          style="min-width: 180px"
          :min="1"
          :max="30"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="名称前缀" name="namePrefix">
        <a-input v-model:value="formData.namePrefix" placeholder="请输入名称前缀，会自动补充序号"/>
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete
          v-model:value="formData.category"
          placeholder="请输入分类"
          :options="categoryPotion"
          allow-clear/>
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select
          v-model:value="formData.tags"
          mode="tags"
          placeholder="请输入标签"
          :options="tagPotion"
          allow-clear
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%" :loading="loading">
          执行任务
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";
import {message} from "ant-design-vue";
import {uploadPictureByBatchUsingPost} from "@/api/pictureController";
import {useRouter} from "vue-router";
import {listPictureTagCategoryUsingGet} from "@/api/classificationController";
import {onMounted} from "vue";


const router = useRouter()
const formData = reactive<API.PictureUploadByBatchRequest>({
  count: 10,
})
const loading = ref(false)
const handleSubmit = async (values: any) => {
  loading.value = true;
  const res = await uploadPictureByBatchUsingPost({
    ...formData,
  })
  if (res.data.code === 0 && res.data.data) {
    message.success(`创建成功，共 ${res.data.data} 条`)
    router.push({
      path: '/',
    })
  } else {
    message.error('创建失败，' + res.data.message)
  }
  loading.value = false;
}
const categoryPotion = ref<string[]>([])
const tagPotion = ref<string[]>([])
const getTagCategoryPotion = async () => {
  const res = await listPictureTagCategoryUsingGet()
  if (res.data.code === 0 && res.data.data) {
    tagPotion.value = (res.data.data.tagList ?? []).map((data: string) => {
      return {
        value: data,
        label: data
      }
    })
    categoryPotion.value = (res.data.data.categoryList ?? []).map((data: string) => {
      return {
        value: data,
        label: data
      }
    })
  } else {
    message.error("获取标签列表分类失败" + res.data.message)
  }
};
onMounted(() => {
  getTagCategoryPotion()
})
</script>

<style scoped>
#addPictureBatchPage {
  max-width: 720px;
  margin: 0 auto;
}
</style>
