<template>
  <div justify-center>
    <center>
      <v-icon size="100px" color="success">check_circle</v-icon>
    </center>
    <center>
      <h1>THANK YOU!</h1>
    </center>
    <center>
      <h3>Your order is complete</h3>
    </center>
    <br />
    <h2>Event name:</h2>
   <br/>

   <nuxt-link :to="`/event/history`">
      <v-btn block color="#341646" style="color:white" >View My Ticket</v-btn>
   </nuxt-link>
   
  </div>
</template>
<script>
export default {
  name: "ConfirmTicket"
};
</script>