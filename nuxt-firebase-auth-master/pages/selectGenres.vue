<template>
  <div>
    <center>
      <h3>Choose genres you like</h3>
      <h3>so we can suggest to follow</h3>
    </center>
    <v-container>
      <genres></genres>
    </v-container>
  </div>
</template>

<script>
import genres from "~/components/genres";

export default {
  components: {
    genres
  }
};
</script>

<style lang="css">
</style>