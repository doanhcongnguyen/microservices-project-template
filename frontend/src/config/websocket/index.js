import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import EventBus from '@/config/event-bus'

import { showSuccessWithMessage } from '@/utils/commons'

const webSocketsService = {}

webSocketsService.install = function(Vue, options) {
  const websocketUrl = process.env.VUE_APP_NOTIFICATION_SEVICE_URL
  const topicName = process.env.VUE_APP_NOTIFICATION_SERVICE_TOPIC
  let stompClient = null
  webSocketsConnect()

  function webSocketsConnect() {
    var socket = new SockJS(websocketUrl)
    stompClient = Stomp.over(socket)
    stompClient.connect({}, onConnected, onDisconnect)
  }

  function onConnected() {
    stompClient.subscribe(topicName, onMessageReceived)
  }

  function onMessageReceived(data) {
    const bodyData = JSON.parse(data.body)
    EventBus.$emit(bodyData.actionType, bodyData)
    showSuccessWithMessage(data.body)
  }

  function onDisconnect(err) {
    console.error('Disconnect: ', err)
    onReconnect()
  }

  function onReconnect() {
    let reconnectInterval = options.reconnectInterval || 1000
    const maxReconnectInterval = options.maxReconnectInterval || 3000
    setTimeout(() => {
      console.log('Websocket reconnect..')
      if (reconnectInterval < maxReconnectInterval) {
        // Reconnect interval can't be > x seconds
        reconnectInterval += 1000
      }
      webSocketsConnect()
    })
  }
}

export default webSocketsService
