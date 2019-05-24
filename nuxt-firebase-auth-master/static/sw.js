importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.045f146eecf046c9f890.js",
    "revision": "ca37128998cc5ebc1488c091b29f6b8e"
  },
  {
    "url": "/_nuxt/app.38d70c2257b07ffbe96105742c96bf17.css",
    "revision": "38d70c2257b07ffbe96105742c96bf17"
  },
  {
    "url": "/_nuxt/layouts_default.f7e7f8397755dc1ff602.js",
    "revision": "40a61b1b45c60c4a84a307010d7985ed"
  },
  {
    "url": "/_nuxt/manifest.5d5383971401eef54666.js",
    "revision": "ec32e359254deb21682d01178bc36e7d"
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
    "url": "/_nuxt/pages_event.a3600ec76186f1e314cd.js",
    "revision": "6b3cf61db865c196a771b98cb2f308b0"
  },
  {
    "url": "/_nuxt/pages_eventDetail.88cd6bc935bb61963f8b.js",
    "revision": "9b06d9714cf62514f2c5627c5cb7056d"
  },
  {
    "url": "/_nuxt/pages_index.3f68b67277d09aef9c14.js",
    "revision": "effbc2dfd1d3f3e42d7f70d64b3d1408"
  },
  {
    "url": "/_nuxt/pages_indexLogin.a3ad99e9f7ff2ccbf7cc.js",
    "revision": "7309c804ecdbae638eaa5559383f1d2f"
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
    "url": "/_nuxt/pages_search.3727a0476a5307840b99.js",
    "revision": "7766b1e0cb8a2c936d404d356b621fe4"
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





