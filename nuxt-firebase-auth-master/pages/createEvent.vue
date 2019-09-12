<template>
  <div>
    <br />
    <br />

    <h3 class="h3">Event Name:</h3>
    <h3 class="h3">Date & Time:</h3>
    <h3 class="h3">Location:</h3>

    <br />
    <br />
    <br />

    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="previewButton white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Preview & Publish</v-btn>
      </nuxt-link>

      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="cancelButton white--text"
          color="#AEAEAE"
          depressed
          large
          height="50"
        >Cancel</v-btn>
      </nuxt-link>
    </center>

    <br />
    <br />
    <h2 class="h2">Ticket Types</h2>

    <br />

    <center>
      <nuxt-link :to="`/createNewTicket?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="createTicketTypeButton white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Create Ticket Type</v-btn>
      </nuxt-link>
    </center>

    <!-- Table test -->

    <br />

    <v-data-table :headers="headers" :items="tickets" class="table-1">
      <template v-slot:header.name="{ headers }">{{ header.text.toUpperCase() }}</template>
    </v-data-table>

    <br />

    <center>
      <nuxt-link
        to="/createNewTicket?"
        class="createNewTicketLink #341646--text" style="font-weight:bold"
      >Create Ticket Types</nuxt-link>
    </center>

    <br />
    <br />
    <h2>Summary</h2>
    <br />

    <v-data-table :headersTotalTickets="headersTotalTickets" :items="totalTickets" class="table-2">
      <template v-slot:header.name="{ header }">{{ header.text.toUpperCase() }}</template>
    </v-data-table>
  </div>
</template>


<script>
export default {
  data: () => ({
    headers: [
      {
        text: "Name",
        align: "left",
        value: "name"
      },
      { text: "Price", value: "price" },
      { text: "Sales Start", value: "salesStart" },
      { text: "Sales End", value: "salesEnd" },
      { text: "Sold / Quota", value: "sold" }
    ],
    headersTotalTickets: [
      {
        text: "Name",
        align: "left",
        value: "name"
      },
      { text: "Price", value: "price" },
      { text: "Sales Start", value: "salesStart" },
      { text: "Sales End", value: "salesEnd" }
    ],
    tickets: [{}]
  }),

  totalTickets: [{}]
};
</script>





<style lang="css">
.h3 {
  font-family: Roboto;
  color: #341646;
}

.h2 {
  font-family: Roboto;
  color: #341646;
}


</style>