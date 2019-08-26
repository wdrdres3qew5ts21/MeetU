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
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' }
    ]
  },
  mode: 'universal',
  loading: { color: '#3f51b5' },
  workbox: {
  },
  manifest: {
    "name": "MeetU",
    "display": "fullscreen",
    "icons": [
      {
        "src": "~/assets/meetu.png",
        "type": "image/png",
        "sizes": "512x512"
      }
    ],
    "gcm_sender_id": "103953800507"
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
    '@nuxtjs/pwa'
  ],
  router: {
    middleware: 'router-auth'
  },
  plugins: [
    { src: '~/plugins/vuetify.js' },
    { src: '~/plugins/fireinit.js' },
    { src: '~/plugins/fireauth.js' },
    { src: '~/plugins/vue-qrcode-reader.js', ssr: false },
    { src: '~/plugins/vue2-google-maps.js', ssr: false }
  ],
  css: [
    { src: '~/assets/css/main.css', lang: 'css' },
    { src: '~/assets/css/app.styl', lang: 'styl' }
  ],
  vendor: [
    'firebase',
    'vuetify',
    'vue-qrcode-reader',
    'vue2-google-maps',
    'vue-flickity',
  ]
}
