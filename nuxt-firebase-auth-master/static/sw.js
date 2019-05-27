importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.38d70c2257b07ffbe96105742c96bf17.css",
    "revision": "38d70c2257b07ffbe96105742c96bf17"
  },
  {
    "url": "/_nuxt/app.6c2580abd5264ade43c5.js",
    "revision": "0e2e2da304e00afe50e07dc2966e3d11"
  },
  {
    "url": "/_nuxt/layouts_default.5e6186487134528a27f0.js",
    "revision": "c6c5fac7b6a41e3425b9bb4b4bbdccf7"
  },
  {
    "url": "/_nuxt/manifest.756a88b84ce0f7c26a6e.js",
    "revision": "831e843598a7407d6c4df0adc5eb05bd"
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
    "url": "/_nuxt/pages_index.41f26c8511e3c97a9dae.js",
    "revision": "bd5afe81b1e0e9afa2ea5b97b3cf139f"
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
    "url": "/_nuxt/pages_search.0f367d1c5f160bf6fb56.js",
    "revision": "25ca5d1825ef4ef2cf99df3f00c14406"
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
    "url": "/_nuxt/pages_userProfile.9564a4b7540de9ff037e.js",
    "revision": "bf8ab1fe4752155c5909468da2cb5eca"
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





