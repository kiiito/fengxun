<template>
  <div id="pictureManagePage">
    <a-flex justify="space-between">
      <h2>图片管理</h2>
      <a-space>
        <a-button type="primary" href="/add_picture"target="_blank">+ 创建图片</a-button>
        <a-button type="primary" href="/add_picture/batch"target="_blank" ghost>+ 批量创建图片</a-button>
      </a-space>
    </a-flex>

    <!-- 搜索栏 --->
    <a-form layout="inline" :model="searchParams" @finish="doSearch">
      <a-form-item label="关键词">
        <a-input v-model:value="searchParams.searchText" placeholder="从名称和简介搜索" allow-clear/>
      </a-form-item>
      <a-form-item label="类型">
        <a-input v-model:value="searchParams.category" placeholder="请输入类型" allow-clear/>
      </a-form-item>
      <a-form-item label="标签">
        <a-select v-model:value="searchParams.tags"
                  mode="tags"
                  placeholder="请输入标签"
                  style="min-width: 180px"
                  allow-clear/>
      </a-form-item>
      <a-form-item label="审核状态" name="reviewStatus">
        <a-select
          v-model:value="searchParams.reviewStatus"
          :options="PIC_REVIEW_STATUS_OPTIONS"
          placeholder="请输入审核状态"
          style="min-width: 180px"
          allow-clear
        />
      </a-form-item>
      <a-form-item>
        <a-space>
          <a-button type="primary" html-type="submit">搜索</a-button>
          <a-button type="primary" danger @click="onCheckAll">是否展示所有图片</a-button>
        </a-space>

      </a-form-item>
    </a-form>
    <div style="margin-bottom: 16px"/>
    <!-- 表格 --->
    <a-table :columns="columns"
             :data-source="dataList"
             :pagination="pagination"
             @change="doTableChange"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'url'">
          <a-image :src="record.url" width="64px"/>
        </template>
        <template v-if="column.dataIndex === 'tags'">
          <a-space wrap>
            <a-tag v-for="tag in JSON.parse(record.tags || '[]')" :key="tag">
              {{ tag }}
            </a-tag>
          </a-space>
        </template>
        <template v-if="column.dataIndex === 'picInfo'">
          <div>格式:{{ record.picFormat }}</div>
          <div>宽度:{{ record.picWidth }}</div>
          <div>高度:{{ record.picHeight }}</div>
          <div>高宽比:{{ record.picScale }}</div>
          <div>大小:{{ (record.picSize / 1024 / 1024).toFixed(2) }}MB</div>
        </template>

        <template v-if="column.dataIndex === 'reviewMessage'">
          <div>审核状态:{{
              PIC_REVIEW_STATUS_MAP[record.reviewStatus]
            }}
          </div>
          <div>审核信息:{{ record.reviewMessage }}</div>
          <div>审核人:{{ record.reviewerId }}</div>
          <div v-if="record.reviewTime">
            审核时间:{{ dayjs(record.reviewTime).format('YYYY-MM-DD HH:mm:ss') }}
          </div>
        </template>
        <template v-if="column.dataIndex === 'createTime'">
          {{
            dayjs(record.createTime).format('YYYY-MM-DD HH:mm:ss')
          }}
        </template>
        <template v-if="column.dataIndex === 'editTime'">
          {{
            dayjs(record.editTime).format('YYYY-MM-DD HH:mm:ss')
          }}
        </template>
        <template v-else-if="column.key === 'action'">
          <a-space wrap>
            <a-popconfirm title="是否通过?" @confirm="confirm(record, PIC_REVIEW_STATUS_ENUM.PASS)" @cancel="cancel">
              <a-button
                v-if="record.reviewStatus !== PIC_REVIEW_STATUS_ENUM.PASS"
                type="link"
              >
                通过
              </a-button>
            </a-popconfirm>
            <a-popconfirm title="是否拒绝?" @confirm="confirm(record, PIC_REVIEW_STATUS_ENUM.PASS)" @cancel="cancel">
              <a-button
                v-if="record.reviewStatus !== PIC_REVIEW_STATUS_ENUM.REJECT"
                type="link"
                danger
              >
                拒绝
              </a-button>
            </a-popconfirm>
            <a-button type="link" :href="`/add_picture?id=${record.id}`" target="_blank"
            >编辑
            </a-button>
            <a-popconfirm title="是否删除?" @confirm="doDelete(record.id)" @cancel="cancel">
              <a-button type="link" danger>删除</a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </template>
    </a-table>
  </div>
</template>
<script lang="ts" setup>
import {SmileOutlined, DownOutlined} from '@ant-design/icons-vue';
import {computed, onMounted, reactive, ref} from "vue";
import {
  deletePictureUsingPost, doPictureReviewUsingPost,
  listPictureByPageUsingPost,
  listPictureVoByPageUsingPost
} from "@/api/pictureController";
import {message} from "ant-design-vue";
import dayjs from "dayjs";
import {PIC_REVIEW_STATUS_MAP} from "@/constants/picture";
import {PIC_REVIEW_STATUS_ENUM} from "@/constants/picture";
import {PIC_REVIEW_STATUS_OPTIONS} from "@/constants/picture";

const columns = [
  {
    title: 'id',
    dataIndex: 'id',
    width: 80,
  },
  {
    title: '图片',
    dataIndex: 'url',
  },
  {
    title: '名称',
    dataIndex: 'name',
  },
  {
    title: '简介',
    dataIndex: 'introduction',
    ellipsis: true,
  },
  {
    title: '类型',
    dataIndex: 'category',
  },
  {
    title: '标签',
    dataIndex: 'tags',
  },
  {
    title: '图片信息',
    dataIndex: 'picInfo',
  },
  {
    title: '空间id',
    dataIndex: 'spaceId',
  },
  {
    title: '用户 id',
    dataIndex: 'userId',
    width: 80,
  },
  {
    title: '审核信息',
    dataIndex: 'reviewMessage',
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
  },
  {
    title: '编辑时间',
    dataIndex: 'editTime',
  },
  {
    title: '操作',
    key: 'action',
  },
]
const isAll = ref<Boolean>( false)

/**
 * reactive 更适合存放对象，因为他单个数据更改就会更新数据 ref更适合存放数组这种，他需要一个对象都做修改才会更新数据
 */

//定义数据
const dataList = ref<API.Picture[]>([])
const total = ref(0)
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
const pagination = computed(() => {
  return {
    current: searchParams.current,
    pageSize: searchParams.pageSize,
    total: total.value,
    showSizeChanger: true,
    showTotal: (total) => `共 ${total} 条`
  }
})
//表格变换后重新获取数据
const doTableChange = (page: any) => {
  searchParams.current = page.current
  searchParams.pageSize = page.pageSize
  fetchData()
}

const onCheckAll = () => {
  if (isAll.value == true){
    isAll.value = false
    fetchData()
  }
  isAll.value = true
  fetchData()
}
//获取数据
const fetchData = async () => {
  let res;
  if (isAll.value){
    res = await listPictureByPageUsingPost({
      ...searchParams,
    })
  }else {
    res = await listPictureByPageUsingPost({
      ...searchParams,
      nullSpaceId:true
    })
  }

  if (res.data.code === 0 && res.data.data) {
    // ??代表赋值，如果dataList.value为空，则赋值为[]
    dataList.value = res.data.data.records ?? []
    total.value = res.data.data.total ?? 0
  } else {
    message.error("获取数据失败" + res.data.message)
  }
}

//删除数据
const doDelete = async (id: string) => {
  if (!id) {
    return
  }
  const res = await deletePictureUsingPost({id})
  if (res.data.code === 0) {
    message.success("删除成功")
    fetchData()
  } else {
    message.error("删除失败" + res.data.message)
  }
}

/**
 * 审核图片
 * @param record
 * @param reviewStatus
 */
const confirm = async (record: API.Picture, reviewStatus: number) => {
  const reviewMessage = reviewStatus === PIC_REVIEW_STATUS_ENUM.PASS ? '管理员操作通过' : '管理员操作拒绝'
  const res = await doPictureReviewUsingPost({
    id: record.id,
    reviewStatus,
    reviewMessage,
  })
  if (res.data.code === 0) {
    message.success('审核操作成功')
    // 重新获取列表
    fetchData()
  } else {
    message.error('审核操作失败，' + res.data.message)
  }
}

const cancel = (e: MouseEvent) => {
  console.log(e);
  message.error('已取消');
};
//搜索数据
const doSearch = () => {
  //重置页码，否则他不是从第一页开始查询
  searchParams.current = 1
  fetchData()
}
//页面加载时获取数据，请求一次
onMounted(() => {
  fetchData()
})

</script>


