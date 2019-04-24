importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.0958bc043c7c9ace9a1d42f7f1742414.css",
    "revision": "0958bc043c7c9ace9a1d42f7f1742414"
  },
  {
    "url": "/_nuxt/app.52301ccafc89c6cdf439.js",
    "revision": "e1449946a7064793fb394507e0270ec2"
  },
  {
    "url": "/_nuxt/layouts_default.14de6b66fc54282d986f.js",
    "revision": "8afd729e98908f24f46d1485acb88ae3"
  },
  {
    "url": "/_nuxt/manifest.233b686da392571f728a.js",
    "revision": "c19b8d8bf3427bae25e2b39ed60f8880"
  },
  {
    "url": "/_nuxt/pages_about.0c8adce74352d795ec90.js",
    "revision": "e281c2f5779208505a5020776695d8fd"
  },
  {
    "url": "/_nuxt/pages_admin_index.8eddd42d2f3ca536fbcb.js",
    "revision": "f2d9095175afa1025c4360b2f35b5665"
  },
  {
    "url": "/_nuxt/pages_admin_items.65840839fab322946a86.js",
    "revision": "ff2f7a4efb65627f20b13ecb8ab44070"
  },
  {
    "url": "/_nuxt/pages_admin_settings.92a30542cf5e5926051b.js",
    "revision": "c84d507bd2a3090b61084a7dbe1a11fd"
  },
  {
    "url": "/_nuxt/pages_admin.80853fc65ec987b87c1d.js",
    "revision": "0fec36b314f725d4829230f613b3b214"
  },
  {
    "url": "/_nuxt/pages_event.6bede6635af2056ead71.js",
    "revision": "219c081dcb994ae37d82cec014c8a047"
  },
  {
    "url": "/_nuxt/pages_index.97810a6a30ac315a0485.js",
    "revision": "1eedf48a0999eeea3ef2a1a5ac959186"
  },
  {
    "url": "/_nuxt/pages_login.5c3da672633a66645c50.js",
    "revision": "7c97eea3710e47c182724103924153c6"
  },
  {
    "url": "/_nuxt/pages_search.6b2b8db1c988cb073115.js",
    "revision": "82de9fc9d180aa0cc34c1aa5d2bd2b1b"
  },
  {
    "url": "/_nuxt/pages_signup.c48c9033706fa31f5a67.js",
    "revision": "0b4163c6a3667a22605493bfd5fa78b5"
  },
  {
    "url": "/_nuxt/vendor.2ce119d224a49c53d475.js",
    "revision": "39c16930b8052c9ae21b539b1af82e33"
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





