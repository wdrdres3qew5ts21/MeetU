<template>
  <div>
    <br />
    <h2 class="h2">Review Event</h2>

    <v-layout justify-end>
      <nuxt-link to="/" class="createEventForm #341646--text" style="font-weight:bold">
        <v-icon>add</v-icon>Create Event
      </nuxt-link>
    </v-layout>

    <br>

    <v-data-table :headers="headers" :items="tickets" class="table-1">
      <template v-slot:header.name="{ headers }">{{ header.text.toUpperCase() }}</template>
    </v-data-table>

    <br />

    <!-- 

    <h2>Summary</h2>
    <br />

    <v-data-table :headersTotalTickets="headersTotalTickets" :items="totalTickets" class="table-2">
      <template v-slot:header.name="{ header }">{{ header.text.toUpperCase() }}</template>
    </v-data-table>-->
  </div>
</template>


<script>
export default {
  name: "monitor",
  components: {}
};
</script>

<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
}
</style>