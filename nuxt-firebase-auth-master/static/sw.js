importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.00be1359acbb4d1510a1.js",
    "revision": "ed5b7968da8ee94f46acc5735e72f71d"
  },
  {
    "url": "/_nuxt/app.0958bc043c7c9ace9a1d42f7f1742414.css",
    "revision": "0958bc043c7c9ace9a1d42f7f1742414"
  },
  {
    "url": "/_nuxt/layouts_default.154ebb0b54377c725d8c.js",
    "revision": "2237f145113cf8f0546c2330b7f9c527"
  },
  {
    "url": "/_nuxt/manifest.c9398012a1923ba498b3.js",
    "revision": "d47b8f128d7935600a6e53358da5ea36"
  },
  {
    "url": "/_nuxt/pages_about.c1932ae184201ae94f32.js",
    "revision": "bde4af67ca46390bfc51f61a84689f55"
  },
  {
    "url": "/_nuxt/pages_admin_index.65f520473195d4c2b010.js",
    "revision": "5e9466415f85316453e4f4dbd495a816"
  },
  {
    "url": "/_nuxt/pages_admin_items.a929107ea59f7ef16fc4.js",
    "revision": "4e41446ba74bf55386c4a124efc21923"
  },
  {
    "url": "/_nuxt/pages_admin_settings.c062d0c3c63dc3941c15.js",
    "revision": "405cb8811dcde5908a0ca2edd7035625"
  },
  {
    "url": "/_nuxt/pages_admin.1f1bc3e3d461a24c280f.js",
    "revision": "1ae6421ea29005776e161773d7be8830"
  },
  {
    "url": "/_nuxt/pages_index.f1cf3df44b375b563f2a.js",
    "revision": "5169a18fb82a78306820dd789b7270a5"
  },
  {
    "url": "/_nuxt/pages_login.fc24e3336c20e291cb37.js",
    "revision": "923e8500b90e2611ec47e1c7e5150d08"
  },
  {
    "url": "/_nuxt/vendor.f27a52115c99b8b2beb8.js",
    "revision": "acc34ab29bb16629db2474bd7f4db95e"
  }
], {
  "cacheId": "nuxt-firebase-auth",
  "directoryIndex": "/",
  "cleanUrls": false
})



workbox.clientsClaim()
workbox.skipWaiting()


workbox.routing.registerRoute(new RegExp('/_nuxt/.*'), workbox.strategies.cacheFirst({}), 'GET')

workbox.routing.registerRoute(new RegExp('/.*'), workbox.strategies.networkFirst({}), 'GET')





