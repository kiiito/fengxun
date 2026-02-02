<template>
  <div id="homeView">
    <!--    搜索框-->
    <div class="search-bare">
      <a-input-search
        v-model:value="searchParams.searchText"
        placeholder="从海量图片中搜索"
        enter-button="搜索"
        size="large"
        @search="doSearch"
      />
    </div>
    <!-- 分类 + 标签 -->
    <a-tabs v-model:activeKey="selectedCategory" @change="doSearch">
      <a-tab-pane key="all" tab="全部"/>
      <a-tab-pane v-for="category in categoryList" :key="category" :tab="category"/>
    </a-tabs>
    <div class="tag-bar">
      <span style="margin-right: 8px">标签：</span>
      <a-space :size="[0, 8]" wrap>
        <a-checkable-tag
          v-for="(tag, index) in tagList"
          :key="tag"
          v-model:checked="selectedTagList[index]"
          @change="doSearch"
        >
          {{ tag }}
        </a-checkable-tag>
      </a-space>
    </div>
    <!--    图片列表-->
    <!-- 图片列表 -->
    <PictureList :dataList="dataList" :loading="loading"/>
    <a-pagination
      style="text-align: right"
      v-model:current="searchParams.current"
      v-model:pageSize="searchParams.pageSize"
      :total="total"
      @change="onPageChange"
    />

  </div>
</template>

<script setup lang="ts">
import {computed, onMounted, reactive, ref} from "vue";
import {
  listPictureByPageUsingPost,
  listPictureVoByPageUsingPost
} from "@/api/pictureController";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import {listPictureTagCategoryUsingGet} from "@/api/classificationController";
import PictureList from "@/pages/PictureList.vue";

const dataList = ref<API.PictureVO[]>([])
const total = ref(0)
const loading = ref(true)
const router = useRouter()

//搜索条件
const searchParams = reactive<API.PictureQueryRequest>({
  current: 1,
  pageSize: 10,
  sortField: 'createTime',
  sortOrder: 'descend',
})
//分页参数
/**
 * pagination 对象需要根据当前页码、
 * 每页大小和总数量动态生成分页配置，
 * 使用 computed 可以确保当这些参数变化时自动更新分页设置
 */
const onPageChange = (page, pageSize) => {
  searchParams.current = page;
  searchParams.pageSize = pageSize;
  fetchData();
}

const fetchData = async () => {
  loading.value = true
  // 转换搜索参数
  const params = {
    ...searchParams,
    tags: [],
  }
  if (selectedCategory.value !== 'all') {
    params.category = selectedCategory.value
  }
  selectedTagList.value.forEach((useTag, index) => {
    if (useTag) {
      params.tags.push(tagList.value[index])
    }
  })
  const res = await listPictureVoByPageUsingPost(params)
  if (res.data.data) {
    dataList.value = res.data.data.records ?? []
    total.value = res.data.data.total ?? 0
  } else {
    message.error('获取数据失败，' + res.data.message)
  }
  loading.value = false
}

const doSearch = () => {
  searchParams.current = 1
  fetchData()
}
onMounted(() => {
  fetchData()
})
/**
 * 标签和分类列表
 */
const categoryList = ref<string[]>([])
const selectedCategory = ref<string>('all')
const tagList = ref<string[]>([])
const selectedTagList = ref<string[]>([])

// 获取标签和分类选项
const getTagCategoryOptions = async () => {
  const res = await listPictureTagCategoryUsingGet()
  if (res.data.code === 0 && res.data.data) {
    // 转换成下拉选项组件接受的格式
    categoryList.value = res.data.data.categoryList ?? []
    tagList.value = res.data.data.tagList ?? []
  } else {
    message.error('加载分类标签失败，' + res.data.message)
  }
}

onMounted(() => {
  getTagCategoryOptions()
})

</script>
<style scoped>
#homeView .search-bare {
  max-width: 480px;
  margin: 0 auto 16px;
}

#homeView .tag-bar {
  margin-bottom: 16px;
}
</style>
