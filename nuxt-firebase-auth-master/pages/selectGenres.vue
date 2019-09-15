<template>
  <v-container>
    <center>
      <h3>Choose genres you like</h3>
      <h3>so we can suggest to follow</h3>
      <!-- <h3>{{$store.getters.mockGetUser.persona.interestIdea}}</h3> -->
    </center>
    <genres></genres>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import genres from "~/components/genres";

export default {
  components: {
    genres
  },
  computed: {
    ...mapGetters(["mockGetUser"])
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