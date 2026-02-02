<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入搜索的标签"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>
  <van-divider content-position="left">已选标签</van-divider>
  <div v-if="activeIds.length === 0">请选择标签</div>
  <van-row gutter="16" style="padding: 0 16px">
    <van-col v-for="tag in activeIds">
      <van-tag closeable size="small" type="primary" @close="doClose(tag)">
        {{ tag }}
      </van-tag>
    </van-col>

  </van-row>

  <van-divider content-position="left">选择标签</van-divider>
  <van-tree-select
      v-model:active-id="activeIds"
      v-model:main-active-index="activeIndex"
      :items="tagList"
  />
  <div style="padding: 16px">
    <van-button block type="primary" @click="doSearchResult">搜索</van-button>
  </div>

</template>

<script setup lang="ts">
import {ref} from 'vue';
import {showToast} from 'vant';
import {useRouter} from "vue-router";


// const value = ref('');
const searchText = ref('');
const router = useRouter();
const originTagList = [
  {
    text: '性别',
    children: [
      {text: '男', id: '男'},
      {text: '女', id: '女'},
    ],
  },
  {
    text: '年级',
    children: [
      {text: '大一', id: '大一'},
      {text: '大二', id: '大二'},
      {text: '大三', id: '大三'},
    ],
  },
]

//标签列表
let tagList = ref(originTagList);
// const onSearch = (val) => showToast(val);
const onSearch = (val) => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildren = [...parentTag.children];
    const tempParentTag = {...parentTag};
    tempParentTag.children = tempChildren.filter(item => item.text.includes(searchText.value));
    return tempParentTag;
  });
}
const activeIds = ref([]);
const activeIndex = ref(0);

// const onCancel = () => showToast('取消')
const onCancel = () => {
  searchText.value = '';
  tagList.value = originTagList;
}

// 移除标签
const doClose = (tag) => {
  // activeIds.value 是一个响应式数组（可能是 ref 或 reactive）
  // 使用 filter 方法创建一个新数组，只保留不等于 tag 的元素
  activeIds.value = activeIds.value.filter(item => {
    // 对于数组中的每个元素，检查是否不等于要移除的 tag
    // 如果不等，则保留该元素；如果相等，则过滤掉
    return item !== tag;
  })
}
/**
 * 执行搜索跳转
 */
const doSearchResult = () => {
  router.push({
    path: '/user/list',
    query: {
      tags: activeIds.value
    }
  })
}
</script>

<style scoped>

</style>