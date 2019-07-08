<template>
  <v-container>
    <login-form></login-form>
    <login-email-form></login-email-form>
  </v-container>
</template>

<script>
import LoginForm from "~/components/LoginForm";
import LoginEmailForm from "~/components/LoginEmailForm";

export default {
  components: {
    LoginForm,
    LoginEmailForm
  }
};
</script>

<style lang="css">
.v-content {
  max-width: 100%;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}
</style>
