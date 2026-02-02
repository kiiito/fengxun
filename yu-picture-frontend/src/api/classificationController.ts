// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** listPictureTagCategory GET /api/classification/tag_category */
export async function listPictureTagCategoryUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponsePictureTagCategory_>('/api/classification/tag_category', {
    method: 'GET',
    ...(options || {}),
  })
}
