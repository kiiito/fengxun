<template>
  <h1>队伍添加页面</h1>
  <div id="teamAppPage">
    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field
            v-model="addTeamData.name"
            name="name"
            label="队伍名"
            placeholder="请输入队伍名"
            :rules="[{ required: true, message: '请填写队伍名' }]"
        />
        <van-field
            v-model="addTeamData.description"
            rows="4"
            autosize
            label="队伍描述"
            type="textarea"
            placeholder="请输入队伍描述"
        />
        <van-field
            is-link
            readonly
            name="datePicker"
            label="时间选择"
            :placeholder=" addTeamData.expireTime ??'点击选择时间'"
            @click="showPicker = true"
        />
        <van-popup v-model:show="showPicker" destroy-on-close position="bottom">
          <van-date-picker
              :model-value="pickerValue"
              @confirm="onDateConfirm"
              :min-date="minDate"
              @cancel="showPicker = false"
          />
        </van-popup>

        <van-field name="stepper" label="最大人数">
          <template #input>
            <van-stepper name="stepper" v-model="addTeamData.maxNum" max="10"/>
          </template>
        </van-field>
        <van-field name="radio" label="队伍状态">
          <template #input>
            <van-radio-group v-model="addTeamData.status" style="display: flex;">
              <van-radio name="0" style="margin-right: 10px">公开</van-radio>
              <van-radio name="1" style="margin-right: 10px">私有</van-radio>
              <van-radio name="2" style="margin-right: 10px">加密</van-radio>
            </van-radio-group>
          </template>
        </van-field>
        <van-field
            v-if="Number(addTeamData.status) === 2"
            v-model="addTeamData.password"
            type="password"
            name="userPassword"
            label="密码"
            placeholder="请输入队伍密码"
            :rules="[{ required: true, message: '请填写密码' }]"
        />
      </van-cell-group>
      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit">
          提交
        </van-button>
      </div>
    </van-form>
  </div>
</template>
<script setup lang="ts">
import {useRouter} from "vue-router";
import {ref} from "vue";
import axios from "axios";
import {showToast} from "vant";
import myAxios from "../plugins/myAxios.ts"

const router = useRouter();
//展示日期选择器
const showPicker = ref(false);
const minDate = new Date();
const pickerValue = ref([]);
const initFormData = {
  "name": "",
  "description": null,
  "maxNum": 3,
  "expireTime": "",
  "status": 0,
  "password": ""
}
//需要用户填写表单数据
const addTeamData = ref({...initFormData})
// 日期确认事件 - 转换为标准时间字符串
const onDateConfirm = ({ selectedValues }) => {
  if (selectedValues && selectedValues.length === 3) {
    const [year, month, day] = selectedValues;
    // 格式1: 标准ISO字符串 "2025-10-22T00:00:00.000Z"
    const isoString = new Date(year, month - 1, day).toISOString();

    // 格式2: 简单日期字符串 "2025-10-22"
    const simpleString = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;

    // 格式3: 带时间的字符串 "2025-10-22 23:59:59"
    const withTimeString = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')} 23:59:59`;

    // 根据后端需求选择一种格式
    addTeamData.value.expireTime = simpleString; // 推荐使用带时间的格式

    // console.log('选择的日期:', selectedValues);
    // console.log('转换后的时间:', addTeamData.value.expireTime);
  }
  showPicker.value = false;
};
const onSubmit =  async () =>{
  //参数修正
  const postData = {
    ...addTeamData.value,
    status: Number(addTeamData.value.status)
  }
  console.log(postData)

  const res = await myAxios.post("/team/add",postData);
  console.log(res)
  if (res?.code === 0 && res.data){
    showToast({
      message: '登陆成功',
      type: 'success'
    });
    router.push({
      path:'/team',
      replace:true,
    })
  }else {
    showToast({
      message: '登陆失败',
      type: 'fail'
    });
  }
}
</script>

<style scoped>

</style>