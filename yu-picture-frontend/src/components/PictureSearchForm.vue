<template>
  <div class="picture-search-form">
    <!-- 主搜索表单 -->
    <a-form layout="inline" :model="searchParams" @finish="doSearch">

      <a-form-item label="关键词" name="searchText">
        <a-input
          v-model:value="searchParams.searchText"
          placeholder="从名称和简介搜索"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete
          v-model:value="searchParams.category"
          style="min-width: 180px"
          :options="categoryOptions"
          placeholder="请输入分类"
          allowClear
        />
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select
          v-model:value="searchParams.tags"
          style="min-width: 180px"
          :options="tagOptions"
          mode="tags"
          placeholder="请输入标签"
          allowClear
        />
      </a-form-item>
      <!-- 筛选器图标按钮（靠右） -->
      <a-form-item>
        <a-button
          type="link"
          @click="toggleAdvanced"
          style="padding: 0; margin-left: 8px; color: #1890ff"
        >
          <template #icon>
            <filter-outlined />
          </template>
          <span>筛选器</span>
        </a-button>
      </a-form-item>
    </a-form>

    <!-- 折叠的高级搜索条件（用 a-collapse） -->
    <a-collapse v-model:activeKey="activeKey" ghost style="margin-top: 1px" >
      <a-collapse-panel key="advanced" header="" collapsible="disabled" :showArrow="false">
        <!-- 注意：header 为空，因为我们用外部按钮控制 -->
        <a-form-item label="日期" name="">
          <a-range-picker
            style="width: 400px"
            show-time
            v-model:value="dateRange"
            :placeholder="['编辑开始日期', '编辑结束时间']"
            format="YYYY/MM/DD HH:mm:ss"
            :presets="rangePresets"
            @change="onRangeChange"
          />
        </a-form-item>
        <a-form layout="inline" :model="searchParams">
          <a-form-item label="名称" name="name">
            <a-input v-model:value="searchParams.name" placeholder="请输入名称" allow-clear />
          </a-form-item>
          <a-form-item label="简介" name="introduction">
            <a-input v-model:value="searchParams.introduction" placeholder="请输入简介" allow-clear />
          </a-form-item>
          <a-form-item label="宽度" name="picWidth">
            <a-input-number v-model:value="searchParams.picWidth" placeholder="像素" />
          </a-form-item>
          <a-form-item label="高度" name="picHeight">
            <a-input-number v-model:value="searchParams.picHeight" placeholder="像素" />
          </a-form-item>
          <a-form-item label="格式" name="picFormat">
            <a-input v-model:value="searchParams.picFormat" placeholder="如 jpg, png" allow-clear />
          </a-form-item>
        </a-form>
      </a-collapse-panel>
    </a-collapse>
    <!-- 操作按钮 -->
    <div class="search-actions" style="margin-top: 16px">
      <a-space>
        <a-button type="primary" @click="doSearch" style="width: 96px">搜索</a-button>
        <a-button @click="doClear">重置</a-button>
      </a-space>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import dayjs from "dayjs";
import { listPictureTagCategoryUsingGet } from "@/api/classificationController";
import { message } from "ant-design-vue";
import { FilterOutlined } from "@ant-design/icons-vue"; // 图标

import {searchPictureByColorUsingPost} from "@/api/pictureController";

interface Props {
  onSearch?: (searchParams: API.PictureQueryRequest) => void;
}

const props = defineProps<Props>();

// 控制折叠面板是否展开
const activeKey = ref<string[]>([]);

// 切换高级搜索面板
const toggleAdvanced = () => {
  if (activeKey.value.includes("advanced")) {
    activeKey.value = [];
  } else {
    activeKey.value = ["advanced"];
  }
};

// 其他逻辑保持不变...
const dateRange = ref<any[]>([]);
const onRangeChange = (dates: any[], dateStrings: string[]) => {
  if (dates.length < 2) {
    searchParams.startEditTime = undefined;
    searchParams.endEditTime = undefined;
  } else {
    searchParams.startEditTime = dates[0].toDate();
    searchParams.endEditTime = dates[1].toDate();
  }
};

const rangePresets = ref([
  { label: "过去 7 天", value: [dayjs().add(-7, "d"), dayjs()] },
  { label: "过去 14 天", value: [dayjs().add(-14, "d"), dayjs()] },
  { label: "过去 30 天", value: [dayjs().add(-30, "d"), dayjs()] },
  { label: "过去 90 天", value: [dayjs().add(-90, "d"), dayjs()] },
]);
const categoryOptions = ref<{ value: string; label: string }[]>([]);
const tagOptions = ref<{ value: string; label: string }[]>([]);

const getTagCategoryOptions = async () => {
  const res = await listPictureTagCategoryUsingGet();
  if (res.data.code === 0 && res.data.data) {
    tagOptions.value = (res.data.data.tagList ?? []).map((tag: string) => ({
      value: tag,
      label: tag,
    }));
    categoryOptions.value = (res.data.data.categoryList ?? []).map((cat: string) => ({
      value: cat,
      label: cat,
    }));
  } else {
    message.error("加载选项失败：" + res.data.message);
  }
};

onMounted(() => {
  getTagCategoryOptions();
});

const searchParams = reactive<API.PictureQueryRequest>({});

const doSearch = () => {
  props.onSearch?.(searchParams);
};

const doClear = () => {
  Object.keys(searchParams).forEach((key) => {
    searchParams[key] = undefined;
  });
  dateRange.value = [];
  props.onSearch?.(searchParams);
};

</script>

<style scoped>
/* 控制折叠面板 header 高度 */
:deep(.ant-collapse-header) {
  height: 1px !important;
  padding: 0 8px !important;
  line-height: 32px !important;
}

/* 控制展开箭头间距 */
:deep(.ant-collapse-arrow) {
  margin-right: 4px !important;
}

/* 控制内容区 padding */
:deep(.ant-collapse-content-box) {
  padding: 8px 0 !important;
}

/* 控制表单项间距（确保在折叠面板内也生效） */
:deep(.ant-form-item) {
  margin-bottom: 8px !important;
}

/* 主表单样式保持不变 */
.picture-search-form .ant-form-item {
  margin-top: 16px;
}
.search-actions {
  text-align: left;
}

/* 让筛选器按钮靠右 */
.picture-search-form .ant-form-inline {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}
.picture-search-form .ant-form-inline > .ant-form-item:last-child {
  margin-left: auto;
}
/* 让整个表单不换行 */
.picture-search-form .ant-form-inline {
  display: flex;
  flex-wrap: nowrap; /* 关键：禁止换行 */
  align-items: center;
  gap: 8px; /* 控制间距 */
  overflow-x: auto; /* 如果太长可以横向滚动 */
}

/* 确保每个 form-item 不换行 */
.picture-search-form .ant-form-item {
  margin-bottom: 0 !important; /* 去掉默认底部间距 */
}

/* 筛选器按钮样式 */
.picture-search-form .ant-form-item:last-child {
  margin-left: 0; /* 避免被推到右边 */
}
</style>
