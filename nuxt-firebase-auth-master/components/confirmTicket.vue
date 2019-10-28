<template>
  <div justify-center>
     <br />
    <br />
    <br />
    <br />
    <br />
    <center>
      <v-icon size="100px" color="success">check_circle</v-icon>
    </center>
    <center>
      <h1>THANK YOU!</h1>
    </center>
    <center>
      <h2>Your order is complete</h2>
      <h3># {{reserveTicket.ticketId}}</h3>
    </center>
    <br />
    <h2>Event: {{reserveTicket.eventName}}</h2>
   <br/>

   <nuxt-link :to="`/ticket/${reserveTicket.elasticEventId}`">
      <v-btn block color="#341646" style="color:white" >View My Ticket</v-btn>
   </nuxt-link>
   
  </div>
</template>
<script>
export default {
  name: "ConfirmTicket",
  props: {
    ticketId: String,
    reserveTicket: {
      type: Object,
      default: {
        elasticEventId: '',
        eventName: ''
      }
    }
  }
};
</script>
