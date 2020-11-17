import Layout from '@/layout'

const DeviceRouter = {
  path: '/device',
  name: 'Device',
  component: Layout,
  alwaysShow: true,
  redirect: 'noredirect',
  meta: { title: 'Device Management', icon: 'example' },
  children: [
    {
      path: 'search',
      name: 'DeviceManagement',
      component: () => import('@/views/device/index'),
      meta: { title: 'Device Search', icon: 'table' }
    }
  ]
}

export default DeviceRouter
