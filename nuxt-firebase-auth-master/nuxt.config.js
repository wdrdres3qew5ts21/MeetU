const env = require('dotenv').config()
console.log(env)
module.exports = {
  env: env.parsed,
  head: {
    title: 'MeetU',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Nuxt.js project' }
    ],
    link: [
      // { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'icon', type: 'image/png', href: '/icon.png' },
      { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' }
    ]
  },
  mode: 'universal',
  loading: { color: '#3f51b5' },
  workbox: {
    importScripts: [
      'custom-sw.js'
    ],
  },
  manifest: {
    "name": "MeetU",
    "display": "fullscreen",
    "icons": [
      {
        "src": "~/assets/logo/meetu-alt.png",
        "type": "image/png",
        "sizes": "512x512"
      }
    ],
    "gcm_sender_id": "1058128161659"
  },
  build: {
    extend (config, ctx) {
      if (ctx.isDev && ctx.isClient) {
        // config.module.rules.push({
        //   enforce: 'pre',
        //   test: /\.(js|vue)$/,
        //   loader: 'eslint-loader',
        //   exclude: /(node_modules)/
        // })
      }
      if (process.client) {
        require('vue-flickity')
      }
    },
    extractCSS: true,
    analyze: {
      analyzerMode: 'static'
    }
  },
  modules: [
    '@nuxtjs/pwa',
    'nuxt-sweetalert2',
    '@nuxtjs/axios'
  ],
  router: {
    middleware: 'router-auth'
  },
  plugins: [
    { src: '~/plugins/vuetify.js' },
    { src: '~/plugins/fireinit.js', mode: 'client' },
    { src: '~/plugins/fireauth.js', mode: 'client' },
    { src: '~/plugins/vue-carousel.js', ssr: false },
    { src: '~/plugins/axios.js',  mode: 'client'},
    { src: '~/plugins/vue-loading.js',  mode: 'client'},
    { src: '~/plugins/vue-qrcode-generator.js', mode: 'client' },
    { src: '~/plugins/vue-qrcode-reader.js', ssr: false },
    { src: '~/plugins/vue2-google-maps.js', ssr: false },
    { src: '~/plugins/vue-croppa.js', ssr: false }
   
    
  ],
  css: [
    { src: '~/assets/css/main.css', lang: 'css' },
    { src: '~/assets/css/app.styl', lang: 'styl' }
  ],
  vendor: [
    'firebase',
    'vuetify',
  ]
}
