<template>
  <v-container>
    <user-profile-form></user-profile-form>
  </v-container>
</template>

<script>
import userProfileForm from "~/components/userProfileForm";

export default {
  components: {
    userProfileForm
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
