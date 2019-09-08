<template>
  <v-layout>
    <v-flex text-xs-center xs12 sm6 offset-sm3>
      <h1 class="title">Log In</h1>
      <br />
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
    emailLogin: function(e) {
      this.$store
        .dispatch("signInWithEmail", {
          email: this.formEmail,
          password: this.formPassword
        })
        .then(() => {
          this.formEmail = "";
          this.formPassword = "";
        })
        .catch(error => {
          console.log(error.message);
          this.errorPopUp(error);
        });
    },
    googleSignUp: function(e) {
      this.$store
        .dispatch("signInWithGoogle")
        .then(() => {
          console.log("inside then statement on login");
        })
        .catch(error => {
          console.log(error.message);
          this.errorPopUp(error);
        });
    },
    facebookSignUp: function(e) {
      this.$store
        .dispatch("signInWithFacebook")
        .then(() => {
          console.log("inside then statement on login");
          this.$router.push("/");
        })
        .catch(error => {
          console.log(error.message);
          this.errorPopUp(error);
        });
    },
    successPopUp: function(success) {},
    errorPopUp: function(error) {
      this.$swal({
        type: "error",
        title: "Login Failed !!!",
        text: `Please Check your user name or password again ! ${error.message}`
      });
    }
  }
};
</script>

<style lang="css">
</style>
