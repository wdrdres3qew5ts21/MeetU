import Vue from 'vue'
import vQr from 'vue-qrcode-reader'

const VueQrcodeReader = {
  install (Vue, options) {
    Vue.use(vQr)
  }
}
Vue.use(VueQrcodeReader)
export default VueQrcodeReader

// Vue.use(VueQrcodeReader)
// if (process.client) {
// //   require('vue-qrcode-reader')
//   // Vue.use(VueQrcodeReader)
//   console.log('fuq client')
// }
