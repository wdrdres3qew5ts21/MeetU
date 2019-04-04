<template>
  <v-container>
    <login-form></login-form>
    <login-email-form></login-email-form>
  </v-container>
</template>

<script>
import LoginForm from '~/components/LoginForm'
import LoginEmailForm from '~/components/LoginEmailForm'

export default {
  components: {
    LoginForm,
    LoginEmailForm,
  }
}
</script>

<style lang="css">
</style>
