<template>
  <div id="addPicturePage">
    <h2 style="margin-bottom: 16px">
      {{ route.query?.id ? "编辑图片" : "创建图片"}}
    </h2>
    <a-typography-paragraph v-if="spaceId" type="secondary">
      保存至空间：<a :href="`/space/${spaceId}`" target="_blank">{{ spaceId }}</a>
    </a-typography-paragraph>
    <a-tabs v-model:activeKey="uploadType">
      <a-tab-pane key="file" tab="文件上传">
        <!--  图片上传组件-->
        <PictureUpload :picture="picture" :spaceId="spaceId" :onSuccess="onSuccess "/>
      </a-tab-pane>
      <a-tab-pane key="url" tab="URL上传">
        <!--    url图片上传组件-->
        <UrlPictureUpload :picture="picture" :spaceId="spaceId"  :onSuccess="onSuccess "/>
      </a-tab-pane>
    </a-tabs>
    <div v-if="picture" class="edit-bar">
      <a-space size="middle">
        <a-button :icon="h(EditOutlined)" @click="doEditPicture">编辑图片</a-button>
        <a-tooltip>
          <template #title>使用AI次数剩余:{{loginUser.aiUseCount}}</template>
          <a-button type="primary" ghost :icon="h(FullscreenOutlined)" @click="doImagePainting">
            AI 扩图
          </a-button>
        </a-tooltip>
      </a-space>
      <ImageOutPainting
        ref="imageOutPaintingRef"
        :picture="picture"
        :spaceId="spaceId"
        :onSuccess="onImageOutPaintingSuccess"
      />
      <ImageCropper
        ref="imageCropperRef"
        :imageUrl="picture?.url"
        :picture="picture"
        :spaceId="spaceId"
        :space="space"
        :onSuccess="onCropSuccess"
      />
    </div>
    <!--    图片信息表单-->
    <a-form v-if="picture" layout="vertical" name="pictureForm" :model="pictureForm" @finish="handleSubmit">
      <a-form-item name="name" label="名称">
        <a-input v-model:value="pictureForm.name" placeholder="请输入图片名称" allow-clear/>
      </a-form-item>
      <a-form-item label="简介" name="introduction">
        <a-textarea v-model:value="pictureForm.introduction"
                    placeholder="请输入简介"
                    :auto-size="{minLines: 2,maxLines: 5}"
                    allow-clear/>
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete
          v-model:value="pictureForm.category"
          placeholder="请输入分类"
          :options="categoryPotion"
          allow-clear/>
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select
          v-model:value="pictureForm.tags"
          mode="tags"
          placeholder="请输入标签"
          :options="tagPotion"
          allow-clear
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%;">创建</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import PictureUpload from "@/components/PictureUpload.vue";
import {computed, onMounted, reactive, ref, watchEffect} from "vue";
import {message} from "ant-design-vue";
import {editPictureUsingPost, getPictureVoByIdUsingGet} from "@/api/pictureController";
import {useRoute, useRouter} from "vue-router";
import {listPictureTagCategoryUsingGet} from "@/api/classificationController";
import UrlPictureUpload from "@/components/icons/UrlPictureUpload.vue";
import ImageCropper from "@/components/ImageCropper.vue";
import {EditOutlined,FullscreenOutlined} from "@ant-design/icons-vue";
import {h} from "vue";
import ImageOutPainting from "@/components/ImageOutPainting.vue";
import {getLoginUserUsingGet} from "@/api/userController";
import {getSpaceVoByIdUsingGet} from "@/api/spaceController";
// 空间 id
const spaceId = computed(() => {
  return route.query?.spaceId
})

const router = useRouter()
const picture = ref<API.PictureVO>()
const onSuccess = (newPicture: API.PictureVO) => {
  picture.value = newPicture
  pictureForm.name = newPicture.name
}
const pictureForm = reactive<API.PictureEditRequest>({})

const uploadType = ref<'file' |'url'>('file')

const loginUser = ref<API.LoginUserVO>()

//获取当前登录用户信息
const fetchLoginUser = async () => {
  const res = await getLoginUserUsingGet()
  if (res.data.code === 0 && res.data.data) {
    loginUser.value = res.data.data
  }else {
    message.error('获取当前登录用户信息失败')
    await router.push({
      path: '/user/login'
    })
  }
}

onMounted(() => {
  fetchLoginUser()
})
const handleSubmit = async (values: any) => {
  const pictureId = picture.value.id
  if (!pictureId) {
    return
  }
  const res = await editPictureUsingPost({
    id: pictureId,
    spaceId: spaceId.value,
    ...values,
  })
  if (res.data.code === 0 && res.data.data) {
    message.success("创建成功")
    //跳转到图片详情页
    router.push({
      path: `/picture/${pictureId}`
    })
  } else {
    message.error("创建失败" + res.data.message)
  }
};

// 图片编辑弹窗引用
const imageCropperRef = ref()

// 编辑图片
const doEditPicture = () => {
  if (imageCropperRef.value) {
    imageCropperRef.value.openModal()
  }
}

// 编辑成功事件
const onCropSuccess = (newPicture: API.PictureVO) => {
  picture.value = newPicture
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

// AI 扩图弹窗引用
const imageOutPaintingRef = ref()

// AI 扩图
const doImagePainting = () => {
  if (imageOutPaintingRef.value) {
    imageOutPaintingRef.value.openModal()
  }
}

// 编辑成功事件
const onImageOutPaintingSuccess = (newPicture: API.PictureVO) => {
  picture.value = newPicture
}

/**
 * 获取老数据
 */
const route = useRoute()
const getOldPicture = async()=>{
  const id = route.query?.id
  if (id){
    const res = await getPictureVoByIdUsingGet({id})
    if (res.data.code === 0 && res.data.data){
      const data = res.data.data
      picture.value = data
      pictureForm.name = data.name
      pictureForm.introduction = data.introduction
      pictureForm.category = data.category
      pictureForm.tags = data.tags
    }
  }

}
onMounted(() => {
  getOldPicture()
})

const space = ref<API.SpaceVO>()

// 获取空间信息
const fetchSpace = async () => {
  // 获取数据
  if (spaceId.value) {
    const res = await getSpaceVoByIdUsingGet({
      id: spaceId.value,
    })
    if (res.data.code === 0 && res.data.data) {
      space.value = res.data.data
    }
  }
}

watchEffect(() => {
  fetchSpace()
})

</script>

<style scoped>
#addPicturePage {
  max-width: 720px;
  margin: 0 auto;
}
#addPicturePage .edit-bar {
  text-align: center;
  margin: 16px 0;
}

</style>
