importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.714f10fc55624e79cee97d412dc70f37.css",
    "revision": "714f10fc55624e79cee97d412dc70f37"
  },
  {
    "url": "/_nuxt/app.ca8b249c5a881d36aba2.js",
    "revision": "812e36fc43982fa7790ddfff02060271"
  },
  {
    "url": "/_nuxt/layouts_default.504e49d3faaf39f9b378.js",
    "revision": "0b702d03a553320e4a81d546fa1a17fc"
  },
  {
    "url": "/_nuxt/manifest.2c79bedbe2d60d59d177.js",
    "revision": "7564f72fb7612019edcb9f53556c5a0d"
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
    "url": "/_nuxt/pages_community_communityDetail.75cb6827c976d4a58e46.js",
    "revision": "88b9071a39213e401458d28c5df7d865"
  },
  {
    "url": "/_nuxt/pages_community_createCommunity.5cabf59f486309e6839f.js",
    "revision": "3b7e416f5a8b9ff5312d4661644e4fdf"
  },
  {
    "url": "/_nuxt/pages_event__eventElasticId.b67b8313303b8791b63f.js",
    "revision": "fb8c552c5380c970e7b63493a4887220"
  },
  {
    "url": "/_nuxt/pages_event_index.e5beb0e4ca376a6a8564.js",
    "revision": "0fa6095c8af4bb5483b2c86727ade72d"
  },
  {
    "url": "/_nuxt/pages_index.3febb3e2188f261dc5f0.js",
    "revision": "1883d9957465bc117d179521e25f3aa3"
  },
  {
    "url": "/_nuxt/pages_login.d3851e6cdf3352b9dcb6.js",
    "revision": "d4c2e35ba9ec7b2e0b52ee29b33c0a82"
  },
  {
    "url": "/_nuxt/pages_organizer.f8dfe3f6917abdb64f71.js",
    "revision": "a7ea2de0a99d9ec2381b56fca5f43dae"
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
    "url": "/_nuxt/pages_ticket.a6f7e3b1cddbbcf293fc.js",
    "revision": "57e560d205fee07d1962a42caa519209"
  },
  {
    "url": "/_nuxt/pages_userProfile.de4bcd674c38f7b1680e.js",
    "revision": "8fbedb6234349b9fbb7cc8556a1343cd"
  },
  {
    "url": "/_nuxt/vendor.edb320380c2e62003333.js",
    "revision": "f2d8b4d9071108fab252d40bd85e41de"
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





