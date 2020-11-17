import Cookies from 'js-cookie'
import jwt_decode from 'jwt-decode'

const TokenKey = 'dcn_app_token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getRolesFromToken(token) {
  const decoded = jwt_decode(token)
  return decoded.authorities
}

export function getFullnameFromToken(token) {
  const decoded = jwt_decode(token)
  return decoded.fullName
}
