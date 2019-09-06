<template>
  <v-container>
    <become-organizer></become-organizer>
  </v-container>
</template>

<script>
import becomeOrganizer from "~/components/becomeOrganizer";

export default {
  components: {
    becomeOrganizer
  }
};
</script>

<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}
</style>

