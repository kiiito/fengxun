import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import {getLoginUserUsingGet} from "@/api/userController";

/**
 * 存储用户信息的store
 */

export const useLoginUserStore = defineStore('loginUser', () => {
  const loginUser = ref<API.LoginUserVO>(
    {userName: '未登录'}
  )

  /**
   * 远程获取登录用户信息
   */
  async function fetchLoginUser() {
    //todo 由于后端还没有提供接口
    const res = await getLoginUserUsingGet()
    if(res.data.code === 0 && res.data.data){
      loginUser.value = res.data.data
      // console.log(res.data.data, '登录用户信息')
    }
    //测试用户
    // setTimeout(()=>{
    //   loginUser.value = {userName: '测试用户',id: 1}
    // },3000)
  }

  /**
   * 设置登录用户
   */
  function setLoginUser(user: any) {
    loginUser.value = user
  }

  return {loginUser, fetchLoginUser, setLoginUser}
})

