<template>
  <div justify-center>
    <h1>EVENT NAME</h1>
    <br />
    <h3>Date:</h3>

    <br />
    <h3>Location:</h3>
    <br /> 
    <h3>Tickets</h3>
    <br />
    Event name
    <v-layout>
     <v-flex>
      <h3 > free                 </h3>
     </v-flex>
      <v-flex class="text-xs-right">
        <v-select :items="amountTicket" solo  ></v-select>
      </v-flex>
      
    </v-layout>
    <v-btn block color="success" style="color:white">Confirm</v-btn>
    <v-btn block color="error" style="color:white">CANCLE</v-btn>
  </div>
</template>
<script>
export default {
  name: "buyTicket",
  data() {
    return {
      amountTicket: ["1", "2", "3"]
    };
  }
};
</script>