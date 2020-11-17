import service from '@/utils/service'

export function getList() {
  return service({
    baseURL: process.env.VUE_APP_AA_SERVER,
    url: '/api/users',
    method: 'get'
  })
}

export function create(data) {
  return service({
    baseURL: process.env.VUE_APP_AA_SERVER,
    url: '/api/user',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return service({
    baseURL: process.env.VUE_APP_AA_SERVER,
    url: '/api/user',
    method: 'put',
    data: data
  })
}

export function doDelete(id) {
  return service({
    baseURL: process.env.VUE_APP_AA_SERVER,
    url: '/api/user/' + id,
    method: 'delete'
  })
}

export function filter(data) {
  return service({
    baseURL: process.env.VUE_APP_AA_SERVER,
    url: '/api/user/search',
    method: 'post',
    data: data
  })
}
