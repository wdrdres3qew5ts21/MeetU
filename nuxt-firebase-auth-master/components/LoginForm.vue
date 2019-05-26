<template>
  <v-layout>
    <v-flex text-xs-center xs12 sm6 offset-sm3>
      <h1 class="title">Log In</h1>
      <br>
      <v-btn
        block
        :round="true"
        color="#DC4B3F"
        class="signIn mb-2 white--text"
        primary
        @click.native="googleSignUp()"
      >Google</v-btn>
      <v-btn
        block
        :round="true"
        color="#3B5998"
        class="signIn mb-2 white--text"
        primary
        @click.native="facebookSignUp()"
      >Facebook</v-btn>
     
     
     
      <!-- <v-btn round="16px;" class="signIn mb-2" primary @click.native="twitterSignUp()">Twitter Sign In</v-btn> -->
      <!-- <v-btn
        block
        :round="true"
        class="signIn mb-2"
        primary
        @click.native="qrCodeGenerate()"
      >QR Code Generate</v-btn>
      <img src="http://localhost:3002/events/qrcode" alt srcset width="200" />
      <p>Or</p> -->

      <!-- {{qrCodeGenerate()}} -->
      <!-- <no-ssr placeholder="loading...">
  
       <qrcode-stream @decode="onDecode"></qrcode-stream> 
    
   
     <qrcode-stream @decode="onDecode"></qrcode-stream>
     
      </no-ssr>-->
    </v-flex>
  </v-layout>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      formEmail: "",
      formPassword: "",
      qrCode: ""
    };
  },
  methods: {
    onDecode(decodedString) {
      console.log("QR Code is Decoding...");
    },
    async qrCodeGenerate() {
      let qrCode = await axios.get("http://localhost:3002/events/qrcode");
      console.log(qrCode);
      this.qrCode = qrCode.data;
    },
    emailLogin: function(e)  {
      this.$store
        .dispatch("signInWithEmail", {
          email: this.formEmail,
          password: this.formPassword
        })
        .then(() => {
          this.formEmail = "";
          this.formPassword = "";
        })
        .catch(e => {
          console.log(e.message);
        });
    },
    googleSignUp: function(e)  {
      this.$store
        .dispatch("signInWithGoogle")
        .then(() => {
          console.log("inside then statement on login");
        })
        .catch(e => {
          console.log(e.message);
        });
    },
    facebookSignUp: function(e)  {
      this.$store
        .dispatch("signInWithFacebook")
        .then(() => {
          console.log("inside then statement on login");
        })
        .catch(e => {
          console.log(e.message);
        });
    },
    twitterSignUp: function(e)  {
      this.$store
        .dispatch("signInWithTwitter")
        .then(() => {
          console.log("inside then statement on login");
        })
        .catch(e => {
          console.log(e.message);
        });
    }
  }
};
</script>

<style lang="css">


</style>
