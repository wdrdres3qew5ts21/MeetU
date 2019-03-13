<template>
  <v-layout>
    <v-flex text-xs-center xs12 sm6 offset-sm3>
      <h2 class="title">Sign In with Google</h2>
      <h1>jwt {{ activeUser }}</h1>
      <v-btn class="signIn mb-2" primary @click.native="googleSignUp()">Google Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="facebookSignUp()">Facebook Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="twitterSignUp()">Twitter Sign In</v-btn>
      <v-btn class="signIn mb-2" primary @click.native="signOut()">Logout</v-btn>
    </v-flex>
  </v-layout>

</template>

<script>
import {mapActions, mapGetters} from 'vuex'
export default {
  data () {
    return {
      formEmail: '',
      formPassword: ''
    }
  },
  computed: {
    ...mapGetters(['activeUser'])
  },
  methods: {
    ...mapActions([
      'signInWithGoogle',
      'signInWithFacebook',
      'signInWithTwitter',
      'signOut'
      ]),
    emailLogin () {
      // this.$store.dispatch('signInWithEmail', {
      //   email: this.formEmail,
      //   password: this.formPassword
      // }).then(() => {
      //   this.formEmail = ''
      //   this.formPassword = ''

      // }).catch((e) => {
      //   console.log(e.message);
      // })
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
    },
    signOut(){
      this.$store.dispatch('signOut').then(()=>{

      }).catch((e)=>{
        
      })
    }
  }
}
</script>

<style lang="css">
.signIn {

}
</style>
