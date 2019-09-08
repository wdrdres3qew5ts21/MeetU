<template>
  <v-container>
    <community-detail></community-detail>
   
  </v-container>
</template>

<script>
import communityDetail from "~/components/communityDetail";


export default {
  components: {

    communityDetail
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