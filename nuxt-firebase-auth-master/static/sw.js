importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.ec42e97e624622f4e2a300f9bb5edd9b.css",
    "revision": "ec42e97e624622f4e2a300f9bb5edd9b"
  },
  {
    "url": "/_nuxt/app.f20c8b1bdca00cfd016d.js",
    "revision": "b39b963ba3e0867449caa1aff42502f3"
  },
  {
    "url": "/_nuxt/layouts_default.b3eec51c2d459d4173ee.js",
    "revision": "22cd765a9d14dd2a6e7e63103ca9406d"
  },
  {
    "url": "/_nuxt/manifest.da05d001df639e3e2e39.js",
    "revision": "f009c3b35af59ebbbf8f9d15137696ce"
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
    "url": "/_nuxt/pages_admin_items.0504b0a242d131201b47.js",
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
    "url": "/_nuxt/pages_event__eventElasticId.ad5ea6028509eab89829.js",
    "revision": "8c597be55d2e36bc09a278ff83b4334b"
  },
  {
    "url": "/_nuxt/pages_event_index.059796e529be84eee557.js",
    "revision": "a8b148fdfd26e0f4dc2d31fc1c353262"
  },
  {
    "url": "/_nuxt/pages_index.72f7604b62289e48ab60.js",
    "revision": "f1539f99f6a5270b52eadf95eecd64a1"
  },
  {
    "url": "/_nuxt/pages_indexLogin.9db10e8dec3510fcb046.js",
    "revision": "832607c4703f9cbe6d40c5e9a0e47bf4"
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
    "url": "/_nuxt/pages_search.74c81bf9fa0836c139c4.js",
    "revision": "06fb4638058117ad83073beb63e92db5"
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
    "url": "/_nuxt/pages_userProfile.7d7dc22323389e0d7d4c.js",
    "revision": "cf38c2425258762cd598e972497d8120"
  },
  {
    "url": "/_nuxt/vendor.6ac202d0a8ace9190a59.js",
    "revision": "243820658e3f80b9492268f057525c85"
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





