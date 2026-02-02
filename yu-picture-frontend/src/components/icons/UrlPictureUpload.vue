<template>
  <div class="url-picture-upload">
    <a-input-group compact>
      <a-input v-model:value="fileUrl" style="width: calc(100% - 120px)" plaaceholder="请输入图片URL" />
      <a-button type="primary" style="width: 120px" @click="handleUpload">提交</a-button>
    </a-input-group>
    <div class="img-wrapper">
      <img v-if="picture?.url" :src="picture?.url" alt="avatar"/>
    </div>
  </div>
</template>
<script lang="ts" setup>
import {defineProps, ref} from 'vue';
import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import type { UploadChangeParam, UploadProps } from 'ant-design-vue';
import {uploadPictureByUrlUsingPost, uploadPictureUsingPost} from "@/api/pictureController";


interface Props{
  picture?:API.PictureVO
  spaceId?:number
  onSuccess?:(newPicture:API.PictureVO) => void
}
const props = defineProps<Props>()
const loading = ref<boolean>(false);
const fileUrl = ref<string>()
/**
 * 上传图片
 */
const handleUpload = async () =>{
  loading.value = true;
  try {
    const params :API.PictureUploadRequest = {
      fileUrl : fileUrl.value
    }
    params.spaceId = props.spaceId
    if (props.picture){
      params.id = props.picture.id
    }
    const res = await uploadPictureByUrlUsingPost(params)
    if (res.data.code === 0 && res.data.data){
      message.success("图片上传成功")
      props.onSuccess?.(res.data.data)
    }else {
      message.error("图片上传失败" + res.data.message)
    }
  }catch (error){
    console.log("图片上传失败" ,error)
    message.error("图片上传失败" + error.message)
  }
  loading.value = false;
}

</script>
<style scoped>
.url-picture-upload {
 margin-bottom: 16px;
}

.url-picture-upload img {
  max-width: 100%;
  max-height: 480px;
}
.url-picture-upload .img-wrapper {
  text-align: center;
  margin-top: 16px;
}


</style>
