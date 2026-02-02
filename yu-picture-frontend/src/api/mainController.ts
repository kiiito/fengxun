// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** healthCheck GET /api/health */
export async function healthCheckUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseString_>('/api/health', {
    method: 'GET',
    ...(options || {}),
  })
}
