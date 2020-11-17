import axios from 'axios'
import { getToken } from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.VUE_APP_AA_SERVER,
  timeout: 5000
})

export function login(data) {
  const formData = createLoginFormData(data)
  const basicAuth = 'Basic ' + btoa(process.env.VUE_APP_AA_CLIENT + ':' + process.env.VUE_APP_AA_PASSWORD)
  return service({
    url: '/oauth/token',
    method: 'post',
    headers: {
      'Authorization': basicAuth,
      'Content-Type': 'application/json'
    },
    data: formData
  })
}

export function logout() {
  // TODO: send logout to backend
  return new Promise((resolve, reject) => {
    resolve()
  })
}

export function changePass(data) {
  return service({
    url: '/change-pass',
    method: 'post',
    headers: {
      'Authorization': 'Bearer ' + getToken(),
      'Content-Type': 'application/json'
    },
    data: data
  })
}

function createLoginFormData(data) {
  var form = new FormData()
  form.append('username', data.username)
  form.append('password', data.password)
  form.append('grant_type', 'password')
  return form
}
