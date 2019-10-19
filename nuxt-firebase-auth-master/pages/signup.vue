<template>
  <v-container>
    <signup-form></signup-form>
  </v-container>
</template>

<script>
import signupForm from "~/components/signupForm";

export default {
  name:"signup",
  components: {
    signupForm
  }
};
</script>

<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}

</style>
