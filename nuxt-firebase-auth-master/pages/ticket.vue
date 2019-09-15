<template>
  <v-container>
    <h1>Ticket(s)</h1>
    <ticketDetail></ticketDetail>
      
  </v-container>
</template>

<script>
import ticketDetail from "~/components/ticketDetail";

export default {
  components: {
    ticketDetail,
   
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
