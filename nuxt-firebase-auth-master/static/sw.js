importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.37cd6249dfba22fc4747.js",
    "revision": "d08f04d0eee1f79665d0838ff6359b6d"
  },
  {
    "url": "/_nuxt/app.38d70c2257b07ffbe96105742c96bf17.css",
    "revision": "38d70c2257b07ffbe96105742c96bf17"
  },
  {
    "url": "/_nuxt/layouts_default.81ab00a77cf4637242ee.js",
    "revision": "38c2361c5c1594ccd3925bf5a96fcc24"
  },
  {
    "url": "/_nuxt/manifest.0a8c091407e21d5174f9.js",
    "revision": "d5c750f5e233de0842f063de46a80a27"
  },
  {
    "url": "/_nuxt/pages_about.6bca9008072f6fd78edd.js",
    "revision": "87f3601e324811025414c1f986f1a652"
  },
  {
    "url": "/_nuxt/pages_admin_index.3778ff50c117dddb6016.js",
    "revision": "0308bd6dd36d3898d126f15434988e86"
  },
  {
    "url": "/_nuxt/pages_admin_items.b71b1ad41014b6425efc.js",
    "revision": "54ddb869a2c9b10c99c1aca2db30e462"
  },
  {
    "url": "/_nuxt/pages_admin_settings.7b4d6f998c090571a8c9.js",
    "revision": "b191ae73210ac06afef1f49fc8a4b803"
  },
  {
    "url": "/_nuxt/pages_admin.5a4c5d8a49da05db11fe.js",
    "revision": "73811865ee2cdc9264f235cb7b1ce32e"
  },
  {
    "url": "/_nuxt/pages_event.6eb2e582b3fd49ee7702.js",
    "revision": "11da35001337c1f7e2b0f939e696ea89"
  },
  {
    "url": "/_nuxt/pages_eventDetail.88cd6bc935bb61963f8b.js",
    "revision": "9b06d9714cf62514f2c5627c5cb7056d"
  },
  {
    "url": "/_nuxt/pages_index.d2fe001c416d18a5c6ec.js",
    "revision": "5284a70ac4c9acd0e487a58ddd4c266d"
  },
  {
    "url": "/_nuxt/pages_indexLogin.44667dc457dae63076fe.js",
    "revision": "d1d2a5b21d8b4541e7a84c3defd75ec7"
  },
  {
    "url": "/_nuxt/pages_login.02c2e94c6f150a18e291.js",
    "revision": "a64a4f88181888547d3da2e3407de161"
  },
  {
    "url": "/_nuxt/pages_organizer.151edf08a72ba70de8d1.js",
    "revision": "260f3de60bc43b3d0e0daded11a7e2a9"
  },
  {
    "url": "/_nuxt/pages_search.b6a0f0fc1ce0ecfab558.js",
    "revision": "5725ab22593e4cf0d1ccd2fbbac4f460"
  },
  {
    "url": "/_nuxt/pages_signup.aa1df72af71b554acbce.js",
    "revision": "828ac4a14e80a06b83f344eaf3c90655"
  },
  {
    "url": "/_nuxt/pages_userProfile.7ed26495f697d02e747f.js",
    "revision": "c441d1c1b6d892995d98e636ed06160e"
  },
  {
    "url": "/_nuxt/vendor.5710b0564d84ae3ac957.js",
    "revision": "8cf57305caa7b3bc34190313ee7e5a4d"
  }
], {
  "cacheId": "meetu-project",
  "directoryIndex": "/",
  "cleanUrls": false
})



workbox.clientsClaim()
workbox.skipWaiting()


workbox.routing.registerRoute(new RegExp('/_nuxt/.*'), workbox.strategies.cacheFirst({}), 'GET')

workbox.routing.registerRoute(new RegExp('/.*'), workbox.strategies.networkFirst({}), 'GET')





