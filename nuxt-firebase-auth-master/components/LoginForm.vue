<template>
  <v-layout>
    <v-flex text-xs-center xs12 sm6 offset-sm3>
      <h2 class="title">Sign In with Google</h2>
      <v-btn class="signIn mb-2" primary @click.native="googleSignUp()">Google Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="facebookSignUp()">Facebook Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="twitterSignUp()">Twitter Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="qrCodeGenerate()"> QR Code Generate</v-btn>
     <img src="http://localhost:3002/events/qrcode" alt="" srcset="" width="200"> 
   
    </v-flex>
        {{qrCodeGenerate()}}
    <no-ssr placeholder="loading...">
  
       <qrcode-stream @decode="onDecode"></qrcode-stream> 
    
     <!--<qrcode-stream @decode="onDecode"></qrcode-stream> -->
     
    </no-ssr>
  </v-layout>
</template>

<script>
import axios from 'axios'

export default {
  components: {
  },
  data () {
    return {
      formEmail: '',
      formPassword: '',
      qrCode: '',
    }
  },
  methods: {
    onDecode (decodedString) {
      console.log('QR Code is Decoding...')
    },
    async qrCodeGenerate () {
      let qrCode = await axios.get('http://localhost:3002/events/qrcode')
      console.log(qrCode)
      this.qrCode = qrCode.data
    },
    emailLogin () {
      this.$store.dispatch('signInWithEmail', {
        email: this.formEmail,
        password: this.formPassword
      }).then(() => {
        this.formEmail = ''
        this.formPassword = ''

      }).catch((e) => {
        console.log(e.message);
      })
    },
    googleSignUp () {
      this.$store.dispatch('signInWithGoogle').then(() => {
        console.log('inside then statement on login');
      }).catch((e) => {
        console.log(e.message);
      })
    },
    facebookSignUp () {
      this.$store.dispatch('signInWithFacebook').then(() => {
        console.log('inside then statement on login');
      }).catch((e) => {
        console.log(e.message);
      })
    },
    twitterSignUp () {
      this.$store.dispatch('signInWithTwitter').then(() => {
        console.log('inside then statement on login');
      }).catch((e) => {
        console.log(e.message);
      })
    }
  }
}
</script>

<style lang="css">
.signIn {

}
</style>
