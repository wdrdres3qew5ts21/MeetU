<template>
  <v-container>
    <organizer-form></organizer-form>
  </v-container>
</template>

<script>
import organizerForm from "~/components/organizerForm";

export default {
  components: {
    organizerForm
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

