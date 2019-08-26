importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.21a2bdbeaf507fd2f35ab7d878e2d650.css",
    "revision": "21a2bdbeaf507fd2f35ab7d878e2d650"
  },
  {
    "url": "/_nuxt/app.2c145919a871951d5b70.js",
    "revision": "294adaab11a06706bfbae66ff82db28c"
  },
  {
    "url": "/_nuxt/layouts_default.75b0887c153b6782a116.js",
    "revision": "9bf0a989c7c883c4e12787b4db609f2f"
  },
  {
    "url": "/_nuxt/manifest.edc528f3ccc868ddc264.js",
    "revision": "0670f9cc632566b62863796748cb8bc4"
  },
  {
    "url": "/_nuxt/pages_about.ed52a2ff8dee04ae5332.js",
    "revision": "9143b6a125b5588edc4a56ed0870c90e"
  },
  {
    "url": "/_nuxt/pages_admin_index.0d680219025ae7412acd.js",
    "revision": "df409d99097fb4fe6f2a228fc9ea01ed"
  },
  {
    "url": "/_nuxt/pages_admin_items.5ed8c022bd604521277b.js",
    "revision": "ce80bb2fa472b2c5c7ea0d7463570066"
  },
  {
    "url": "/_nuxt/pages_admin_settings.86f91fd3af6280316954.js",
    "revision": "770a1928b384074ec68cb03226c3396c"
  },
  {
    "url": "/_nuxt/pages_admin.545624e0784f7e4587d3.js",
    "revision": "9d6c772a382339e707833b375d84a543"
  },
  {
    "url": "/_nuxt/pages_event__eventElasticId.d19ee72d3558d9a5ee79.js",
    "revision": "d168b5d50860834ffacfb0d1c23df007"
  },
  {
    "url": "/_nuxt/pages_event_index.dae3a7f9716476bb2705.js",
    "revision": "dfbba93d4348e1142de59bdd6a648d64"
  },
  {
    "url": "/_nuxt/pages_index.021412e496f718628473.js",
    "revision": "01ab4616d0107d08e29032026485c6e5"
  },
  {
    "url": "/_nuxt/pages_indexLogin.2cd9196d40fdd20c1024.js",
    "revision": "f7bc296502a215f845c03a87410d894f"
  },
  {
    "url": "/_nuxt/pages_login.0ab38ea1dfe5bc80d92e.js",
    "revision": "aee9b3e02a592e4ab3bb8a89cd02828f"
  },
  {
    "url": "/_nuxt/pages_organizer.c48b89f437a786c20c6a.js",
    "revision": "d799568a11232f4cd69ba34ed6ff2137"
  },
  {
    "url": "/_nuxt/pages_search.2f2efd00aa24b4a22164.js",
    "revision": "5597e98543f82bfbe41f7852f558de57"
  },
  {
    "url": "/_nuxt/pages_selectGenres.5f7baf4d08ae24ed5d2a.js",
    "revision": "5d4bd1feccdf1265804b184ccd794534"
  },
  {
    "url": "/_nuxt/pages_signup.cb664a17860b39616828.js",
    "revision": "335d9e5bd0dac7d180ec1900a7146086"
  },
  {
    "url": "/_nuxt/pages_userProfile.0a266636959615803d81.js",
    "revision": "936c43f83963d785ba711b4380a88611"
  },
  {
    "url": "/_nuxt/vendor.b4a438cbb46623b94586.js",
    "revision": "758643851ba31a2f0ce3e90f6d43e6cd"
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





