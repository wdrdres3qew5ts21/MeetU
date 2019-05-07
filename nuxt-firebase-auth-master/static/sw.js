importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.38d70c2257b07ffbe96105742c96bf17.css",
    "revision": "38d70c2257b07ffbe96105742c96bf17"
  },
  {
    "url": "/_nuxt/app.c0f07bde4ddaa83cc666.js",
    "revision": "d8b0ccc4a60cf52a3b38bdd3f3fc66f1"
  },
  {
    "url": "/_nuxt/layouts_default.6be7a7709383c4ce6df9.js",
    "revision": "ae69224a32750ac3745425d60bbd180d"
  },
  {
    "url": "/_nuxt/manifest.89bb7a18ae9401a9d365.js",
    "revision": "b915686490c909f6f0f9515e3052b68a"
  },
  {
    "url": "/_nuxt/pages_about.8568c606fe8947ff64f8.js",
    "revision": "11207f076456873d076f95a612c46c64"
  },
  {
    "url": "/_nuxt/pages_admin_index.984f197b0c197ff8dcfd.js",
    "revision": "aa52d85d44f2972c76c79836e02008b6"
  },
  {
    "url": "/_nuxt/pages_admin_items.a062adf85192a399c3f2.js",
    "revision": "c7edac5eeefa20b9bb53acaacc4aa9a7"
  },
  {
    "url": "/_nuxt/pages_admin_settings.05b38e61b3ab84d354c0.js",
    "revision": "aeb3a57756d0c07dc8415024aad13452"
  },
  {
    "url": "/_nuxt/pages_admin.392cbc3c995e373c4b4b.js",
    "revision": "66323f049b1f8c1b19df80ddc177eeb6"
  },
  {
    "url": "/_nuxt/pages_event.bf3b6f90ab90b566659d.js",
    "revision": "067418d2d3584345a31899528e2980b0"
  },
  {
    "url": "/_nuxt/pages_eventDetail.a0c4616499f043088e8b.js",
    "revision": "d87ee54bcbb1cbd22195255c18bce9f5"
  },
  {
    "url": "/_nuxt/pages_index.a85f2d877b7b5b8b3206.js",
    "revision": "aeda05dc032ec4e50df4023da590f43a"
  },
  {
    "url": "/_nuxt/pages_indexLogin.fc83f4fc85e6084a5d27.js",
    "revision": "821294e08bd9477f6018222cc98bab3d"
  },
  {
    "url": "/_nuxt/pages_login.d38c555cb08deafb4ad4.js",
    "revision": "37b9aea890356ef063a176fced443ce6"
  },
  {
    "url": "/_nuxt/pages_organizer.6873fcc71a22de73536d.js",
    "revision": "9c74722038b2a0869d8086bfb57b3343"
  },
  {
    "url": "/_nuxt/pages_signup.4dd9837557eee8eea23f.js",
    "revision": "70fbd6b4fb39091918c467c177817ffd"
  },
  {
    "url": "/_nuxt/pages_userProfile.7cc3c7005211c7f2d86c.js",
    "revision": "1c9728019e2f07d7d2b3b533a9f5b436"
  },
  {
    "url": "/_nuxt/vendor.c49ad7099d151e934243.js",
    "revision": "f008e7127aaa9d17a680729bce64fe24"
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





