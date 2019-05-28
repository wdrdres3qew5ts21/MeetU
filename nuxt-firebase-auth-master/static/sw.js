importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.38d70c2257b07ffbe96105742c96bf17.css",
    "revision": "38d70c2257b07ffbe96105742c96bf17"
  },
  {
    "url": "/_nuxt/app.e4678b6ca13372da2f81.js",
    "revision": "45e8cabe06ebac9db48ab02d0feb1d38"
  },
  {
    "url": "/_nuxt/layouts_default.9bd20080034143d1dbe0.js",
    "revision": "a22c598531aea678de7014f79d31283a"
  },
  {
    "url": "/_nuxt/manifest.dcb302c5ca976abf3e0b.js",
    "revision": "e76d81d5eed82d2e41a5a9a2c19a458e"
  },
  {
    "url": "/_nuxt/pages_about.0d3009c2542adbfc0076.js",
    "revision": "6b52f4d912118c79003b6089d78d964f"
  },
  {
    "url": "/_nuxt/pages_admin_index.fc7b34671ac56538efc3.js",
    "revision": "6081482f8730f649a91a2db54121ae67"
  },
  {
    "url": "/_nuxt/pages_admin_items.a5de4841bb464f2ea54e.js",
    "revision": "a38675bc04f6f8bee49ed9265f318d34"
  },
  {
    "url": "/_nuxt/pages_admin_settings.f2de7fe03530ff80d40d.js",
    "revision": "5ca9bb78cd94e26e9489f4522abedfda"
  },
  {
    "url": "/_nuxt/pages_admin.50709c84838f857e4593.js",
    "revision": "1007f981bc0f5b91972920b5d7e0c8a8"
  },
  {
    "url": "/_nuxt/pages_event__eventElasticId.2be503f061cfec993168.js",
    "revision": "f3f6a74721a5a610737140f713ba499a"
  },
  {
    "url": "/_nuxt/pages_event_index.66f6729be3a1b63da353.js",
    "revision": "3f40cba03aeaa80e91c6f255fe591d8f"
  },
  {
    "url": "/_nuxt/pages_index.d9dc798236aa26010e21.js",
    "revision": "d4683c8e8f93ebbcbe7985eb3c0a3d28"
  },
  {
    "url": "/_nuxt/pages_indexLogin.06ae239bb27a0b6ad1ef.js",
    "revision": "69ba21c73944738ffd19a4661bdcef7a"
  },
  {
    "url": "/_nuxt/pages_login.15634ea46107b32259f5.js",
    "revision": "500e061a122b83702d4fc6d2559ae843"
  },
  {
    "url": "/_nuxt/pages_organizer.ffbeaa597de5a76dc53b.js",
    "revision": "0d7005375db574e0257b80c7207c7859"
  },
  {
    "url": "/_nuxt/pages_search.4b560c09e6def7916912.js",
    "revision": "45948157f938dacd861f2991823c26ae"
  },
  {
    "url": "/_nuxt/pages_selectGenres.4408f8f4172b5f2e8b5b.js",
    "revision": "36660ffbaf069b71a1c7057cf73cf8dc"
  },
  {
    "url": "/_nuxt/pages_signup.e4f7c9fc9007d9bceeec.js",
    "revision": "6abb0e1d6befa8c34c577fc086915cd3"
  },
  {
    "url": "/_nuxt/pages_userProfile.0428ce75210e6a5002ad.js",
    "revision": "2a3d32ed42fc9db46ddf935352a9f8fd"
  },
  {
    "url": "/_nuxt/vendor.e08c40447449fe1a767b.js",
    "revision": "ddc3091e5cd883d676e47b34cfb4c197"
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





