import service from '@/utils/service'

export function getList() {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/devices',
    method: 'get'
  })
}

export function sync(serial) {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/device/sync/' + serial,
    method: 'get'
  })
}

export function create(data) {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/device',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/device',
    method: 'put',
    data: data
  })
}

export function doDelete(serial) {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/device/' + serial,
    method: 'delete'
  })
}

export function filter(data) {
  return service({
    baseURL: process.env.VUE_APP_DEVICE_MANAGER_SERVER,
    url: '/api/device/search',
    method: 'post',
    data: data
  })
}
