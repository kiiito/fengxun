<template>
  <div class="avatar-upload-page">
    <h2 class="page-title">修改头像</h2>

    <!-- 头像预览区域 -->
    <div class="avatar-preview-section">
      <img :src="avatarPreview" class="avatar-preview" alt="头像预览">
      <div class="preview-label">预览效果</div>
    </div>

    <!-- 上传区域 -->
    <div class="upload-section">
      <van-uploader
          v-model="fileList"
          :after-read="afterRead"
          :before-read="beforeRead"
          :max-size="50 * 1024 * 1024"
          @oversize="onOversize"
          accept="image/jpeg,image/png,image/jpg,image/gif,image/webp"
          :max-count="1"
          result-type="file"
      >
        <van-button icon="plus" type="primary" block>选择图片</van-button>
      </van-uploader>

      <p class="upload-tips">支持 JPG、PNG、GIF、WEBP 格式，大小不超过 5MB</p>
    </div>

    <!-- 上传状态显示 -->
    <div class="upload-status" :class="uploadStatusClass" v-if="uploadStatus">
      {{ uploadStatus }}
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <van-button
          type="primary"
          size="large"
          :disabled="!selectedFile || uploading"
          :loading="uploading"
          loading-text="上传中..."
          @click="uploadAvatar"
          block
      >
        确认上传
      </van-button>
      <van-button
          type="default"
          size="large"
          @click="resetUpload"
          :disabled="uploading"
          block
      >
        取消
      </van-button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { showToast, showLoadingToast, closeToast } from 'vant';
import { useRouter } from 'vue-router';

const router = useRouter();

// 响应式数据
const avatarPreview = ref('/default-avatar.png');
const fileList = ref([]);
const selectedFile = ref(null);
const uploading = ref(false);
const uploadStatus = ref('');
const uploadStatusClass = ref('');

// 文件选择前的验证
const beforeRead = (file) => {
  const allowedTypes = ['image/jpeg', 'image/png', 'image/jpg', 'image/gif', 'image/webp'];
  if (!allowedTypes.includes(file.type)) {
    showToast('请上传图片格式文件（JPG/PNG/GIF/WEBP）');
    return false;
  }
  return true;
};

// 文件选择后的处理
const afterRead = (file) => {
  const fileObj = Array.isArray(file) ? file[0] : file;
  selectedFile.value = fileObj;

  if (fileObj.file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      avatarPreview.value = e.target.result;
    };
    reader.readAsDataURL(fileObj.file);
  }

  uploadStatus.value = '';
};

// 文件大小超出限制
const onOversize = () => {
  showToast('文件大小不能超过50MB');
};

// 上传头像到服务器
const uploadAvatar = async () => {
  if (!selectedFile.value) {
    showToast('请先选择图片');
    return;
  }

  uploading.value = true;
  uploadStatus.value = '正在上传...';
  uploadStatusClass.value = '';

  try {
    const formData = new FormData();
    formData.append('file', selectedFile.value.file);

    // 添加用户ID（实际项目中从登录状态获取）
    const userId = localStorage.getItem('userId') || '1';
    formData.append('userId', userId);

    showLoadingToast({
      message: '上传中...',
      forbidClick: true,
      duration: 0
    });

    const response = await fetch('/api/user/avatar/upload', {
      method: 'POST',
      body: formData,
    });

    closeToast();

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const result = await response.json();

    if (result.code === 200) {
      uploadStatus.value = '头像上传成功！';
      uploadStatusClass.value = 'success-status';
      showToast('头像上传成功');

      avatarPreview.value = result.data;

      // 清空文件列表
      setTimeout(() => {
        fileList.value = [];
        selectedFile.value = null;
      }, 1500);
    } else {
      throw new Error(result.message || '上传失败');
    }
  } catch (error) {
    console.error('上传错误:', error);
    uploadStatus.value = '上传失败，请重试';
    uploadStatusClass.value = 'error-status';
    showToast('上传失败，请重试');
  } finally {
    uploading.value = false;
  }
};

// 重置上传
const resetUpload = () => {
  fileList.value = [];
  selectedFile.value = null;
  avatarPreview.value = '/default-avatar.png';
  uploadStatus.value = '';
  uploadStatusClass.value = '';
};
</script>

<style scoped>
.avatar-upload-page {
  padding: 20px;
  min-height: 100vh;
  background-color: #f7f8fa;
}

.page-title {
  text-align: center;
  margin-bottom: 30px;
  font-size: 18px;
  font-weight: bold;
  color: #323233;
}

.avatar-preview-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 30px;
}

.avatar-preview {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  border: 3px solid #fff;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.preview-label {
  margin-top: 10px;
  font-size: 14px;
  color: #969799;
}

.upload-section {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.upload-tips {
  font-size: 13px;
  color: #969799;
  text-align: center;
  margin-top: 10px;
}

.action-buttons {
  display: flex;
  gap: 12px;
  margin-top: 30px;
}

.upload-status {
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
}

.success-status {
  color: #07c160;
}

.error-status {
  color: #ee0a24;
}
</style>