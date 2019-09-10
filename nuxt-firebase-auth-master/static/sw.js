importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.714f10fc55624e79cee97d412dc70f37.css",
    "revision": "714f10fc55624e79cee97d412dc70f37"
  },
  {
    "url": "/_nuxt/app.97cb006355cafe87e235.js",
    "revision": "aa608eb5562089a35694e1a164f7ee92"
  },
  {
    "url": "/_nuxt/layouts_default.ea137c5c528b69273739.js",
    "revision": "6d26f72cd0f562c782b22a2de278e7b4"
  },
  {
    "url": "/_nuxt/manifest.e2ae0cfa634a83621d46.js",
    "revision": "c7dec436632499d683cbde7d4c77196c"
  },
  {
    "url": "/_nuxt/pages_about.32642ee6df01170c99db.js",
    "revision": "32c2b964d086a1a33401a4969db55f77"
  },
  {
    "url": "/_nuxt/pages_admin_index.1475e5cea8c4b1938c8f.js",
    "revision": "59a74185c8f26f1989e08ceedc7bcc4f"
  },
  {
    "url": "/_nuxt/pages_admin_items.e03f31b5178c5b3f0710.js",
    "revision": "5416d317797ee1112bf41ece85398715"
  },
  {
    "url": "/_nuxt/pages_admin_settings.b0406a2e182aff9806b6.js",
    "revision": "ac8975fdf5c85b24ce35e079cf1e21cc"
  },
  {
    "url": "/_nuxt/pages_admin.83755f973f0f355346d0.js",
    "revision": "bd94cd0fd27fad161d72c8215fa6023d"
  },
  {
    "url": "/_nuxt/pages_community_communityDetail.738c8c5b3d9e2dc410e3.js",
    "revision": "b5da01768ac8fb96dfb417f343823941"
  },
  {
    "url": "/_nuxt/pages_community_createCommunity.07fa70ccd4910b88f592.js",
    "revision": "7adc84147b06739a6ffe6910d15ece49"
  },
  {
    "url": "/_nuxt/pages_event__eventElasticId.a50b054918289db2a1c1.js",
    "revision": "630ce60732d4b4dffdfa296066bc6a43"
  },
  {
    "url": "/_nuxt/pages_event_index.0b01824ff7ea51cca716.js",
    "revision": "1dfbcf2a70daa907114ce549f66f479d"
  },
  {
    "url": "/_nuxt/pages_index.cf27b700e6c32b7d6186.js",
    "revision": "d3f4187ab17259af809ec0d5cb7159f5"
  },
  {
    "url": "/_nuxt/pages_login.14bd81c237bf96544840.js",
    "revision": "35f98f8c3798a45de715b66765da8880"
  },
  {
    "url": "/_nuxt/pages_organizer.338630a99520b83cdb49.js",
    "revision": "f35eb0fa343f0d7303ee49c527e58ecd"
  },
  {
    "url": "/_nuxt/pages_organizerAccountCreate.05f4afb771a64e43c44f.js",
    "revision": "f3f027fdbf761538de154b17e50d10f5"
  },
  {
    "url": "/_nuxt/pages_organizerForm.66587c5b237a4623d92e.js",
    "revision": "af5d85083aba09121064b94effa9c0d4"
  },
  {
    "url": "/_nuxt/pages_search.95603e9271eeec05533a.js",
    "revision": "214cc7addd54c2b8e7766f07f9dfb45b"
  },
  {
    "url": "/_nuxt/pages_selectGenres.5f7baf4d08ae24ed5d2a.js",
    "revision": "5d4bd1feccdf1265804b184ccd794534"
  },
  {
    "url": "/_nuxt/pages_signup.bfafbfd3e31701d41145.js",
    "revision": "f62c6f22e65836f8ff0348df0d338a91"
  },
  {
    "url": "/_nuxt/pages_ticket.a6f7e3b1cddbbcf293fc.js",
    "revision": "57e560d205fee07d1962a42caa519209"
  },
  {
    "url": "/_nuxt/pages_userProfile.520a9e9b79d390c2c097.js",
    "revision": "9d0bfc846e1db3998546c7da086cf2ed"
  },
  {
    "url": "/_nuxt/vendor.d04ec73a00a2fe4771a9.js",
    "revision": "be38d016d18df7b6ba1cd35b5835e597"
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





