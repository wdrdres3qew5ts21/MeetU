importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.5b5725dc4695d5e9cf20.js",
    "revision": "3acb4331004d75bcd8523ca371e87998"
  },
  {
    "url": "/_nuxt/app.ddcc4f1f86c23d3f16b5da1d61af7251.css",
    "revision": "ddcc4f1f86c23d3f16b5da1d61af7251"
  },
  {
    "url": "/_nuxt/layouts_default.4c41648c29d8abf49249.js",
    "revision": "93e08c59e93db37ce30db6dea184a273"
  },
  {
    "url": "/_nuxt/manifest.c96997964ec2238b7287.js",
    "revision": "26d755c2ab7fb84be1d98d1909a1ee11"
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
    "url": "/_nuxt/pages_admin_items.cfa5069a2c0010cef90f.js",
    "revision": "76e15c0a4916614ea36069054b3e6ea3"
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
    "url": "/_nuxt/pages_event__eventElasticId.6affa182ae48896094f9.js",
    "revision": "dce3f3a9e5d864a14597742f56b2c897"
  },
  {
    "url": "/_nuxt/pages_event_index.c093af1a1b0d2aedb12a.js",
    "revision": "0f0a76e013b366473af81bb3c10a3581"
  },
  {
    "url": "/_nuxt/pages_index.7b67e66a43d8937b7a12.js",
    "revision": "1d25f275f63298f934152309e1f725c9"
  },
  {
    "url": "/_nuxt/pages_indexLogin.2cd9196d40fdd20c1024.js",
    "revision": "f7bc296502a215f845c03a87410d894f"
  },
  {
    "url": "/_nuxt/pages_login.c60a9b208cc7a522d971.js",
    "revision": "d4c2e35ba9ec7b2e0b52ee29b33c0a82"
  },
  {
    "url": "/_nuxt/pages_organizer.c48b89f437a786c20c6a.js",
    "revision": "d799568a11232f4cd69ba34ed6ff2137"
  },
  {
    "url": "/_nuxt/pages_search.202c9ccd23a7b94e2355.js",
    "revision": "df1c2fbfb0af474773061877095b0719"
  },
  {
    "url": "/_nuxt/pages_selectGenres.5f7baf4d08ae24ed5d2a.js",
    "revision": "5d4bd1feccdf1265804b184ccd794534"
  },
  {
    "url": "/_nuxt/pages_signup.a7902ce2bcfacf598e5c.js",
    "revision": "597df0114a12e34c686b5984008fb732"
  },
  {
    "url": "/_nuxt/pages_userProfile.de4bcd674c38f7b1680e.js",
    "revision": "8fbedb6234349b9fbb7cc8556a1343cd"
  },
  {
    "url": "/_nuxt/vendor.8c41d2459e364cac9493.js",
    "revision": "c9bc9b125961653a0bd05bd1a27568d0"
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





