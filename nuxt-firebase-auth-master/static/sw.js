importScripts('/_nuxt/workbox.4c4f5ca6.js')



workbox.precaching.precacheAndRoute([
  {
    "url": "/_nuxt/app.714f10fc55624e79cee97d412dc70f37.css",
    "revision": "714f10fc55624e79cee97d412dc70f37"
  },
  {
    "url": "/_nuxt/app.cf411e5e3a43dbc3c854.js",
    "revision": "4f729280071526574bd089c7194d712e"
  },
  {
    "url": "/_nuxt/layouts_default.ea137c5c528b69273739.js",
    "revision": "6d26f72cd0f562c782b22a2de278e7b4"
  },
  {
    "url": "/_nuxt/manifest.3d9c06b3e7d504fd202c.js",
    "revision": "1237903f187c8e8d4883fb4ab1b0461e"
  },
  {
    "url": "/_nuxt/pages_about.0c01dcfb3396d04779d0.js",
    "revision": "afeac5ca9582c1214de9ead43d27b820"
  },
  {
    "url": "/_nuxt/pages_admin_index.6e10016e1d86c3cf0e68.js",
    "revision": "5c213a8fc52d13cf8803cbd98f66a2c0"
  },
  {
    "url": "/_nuxt/pages_admin_items.7bbd9244aed5d734bb3f.js",
    "revision": "2ed6ed67724b6ddfcc1231e693ec61ec"
  },
  {
    "url": "/_nuxt/pages_admin_settings.a6be8a7a0b3dc4d0ee41.js",
    "revision": "4f7f8e8752c6735b716bbaf7573d4d82"
  },
  {
    "url": "/_nuxt/pages_admin.a3e09a5b8d9148ef71ed.js",
    "revision": "558ea5ba272cfa5ca848a58ba957322e"
  },
  {
    "url": "/_nuxt/pages_community__communityId.2244652171643faa4f14.js",
    "revision": "cbd8bc9b389a0b39a9ed649d085ef0ba"
  },
  {
    "url": "/_nuxt/pages_community_createCommunity.07fa70ccd4910b88f592.js",
    "revision": "7adc84147b06739a6ffe6910d15ece49"
  },
  {
    "url": "/_nuxt/pages_community_index.80d7e639bcc3107d4f2b.js",
    "revision": "337ed991a278f6f22ed49ad67ebc6480"
  },
  {
    "url": "/_nuxt/pages_createEvent.fadbcc29ebe86bb9fd68.js",
    "revision": "f292ed0de33b5da65ac435ddce1a3215"
  },
  {
    "url": "/_nuxt/pages_createEventForm.81ab1c566757cd2fe104.js",
    "revision": "5b53ad4872f228b4016b30bde3617f82"
  },
  {
    "url": "/_nuxt/pages_createNewTicket.20c9d0ec1c8f401410ee.js",
    "revision": "74613186366ebd42d98510a68c9e5a62"
  },
  {
    "url": "/_nuxt/pages_event__eventElasticId.716a5f45304245c89914.js",
    "revision": "81cd78ba09ae33d2bfb7f936e16afdb1"
  },
  {
    "url": "/_nuxt/pages_event_index.36ec0bc0a1de81582a80.js",
    "revision": "5a9e30d5bfdc0d86e962c74599a97cc6"
  },
  {
    "url": "/_nuxt/pages_index.7d10e0975fb289728cc6.js",
    "revision": "1883d9957465bc117d179521e25f3aa3"
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
    "url": "/_nuxt/pages_organizerAccountCreate.5f7d296f358cd6803f59.js",
    "revision": "da5405f9c9ede82ee585429492642302"
  },
  {
    "url": "/_nuxt/pages_organizerForm.65f6e7e929c7cc47a926.js",
    "revision": "38699c5d41af7e823063225256445daf"
  },
  {
    "url": "/_nuxt/pages_search.712b7cc067d779553b01.js",
    "revision": "f387434837cd344ba1015e22e188cc49"
  },
  {
    "url": "/_nuxt/pages_selectGenres.5f7baf4d08ae24ed5d2a.js",
    "revision": "5d4bd1feccdf1265804b184ccd794534"
  },
  {
    "url": "/_nuxt/pages_signup.440ac15e93d61e4d071d.js",
    "revision": "2d01aa4c25448181df04a221acda1697"
  },
  {
    "url": "/_nuxt/pages_startedEvent.943021c608ab04d8373b.js",
    "revision": "c5a492d697a8bdda512e45c7b43c114d"
  },
  {
    "url": "/_nuxt/pages_ticket.a6f7e3b1cddbbcf293fc.js",
    "revision": "57e560d205fee07d1962a42caa519209"
  },
  {
    "url": "/_nuxt/pages_userProfile.4775016f8927ae666c10.js",
    "revision": "3725f7196ce0db570fc62b10c0c01ade"
  },
  {
    "url": "/_nuxt/vendor.70d6aa1b249c224ccf54.js",
    "revision": "279f488479a4fbb43390d07544ac52f6"
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





