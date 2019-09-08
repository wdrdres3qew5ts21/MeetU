<template>
  <v-container>
    <community-form></community-form>
   
  </v-container>
</template>

<script>
import communityForm from "~/components/communityForm";


export default {
  components: {
    communityForm
 
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