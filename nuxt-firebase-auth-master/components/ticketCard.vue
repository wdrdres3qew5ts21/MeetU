<template>
  <v-card color="cyan darken-2" class="white--text">
    <v-layout>
      <v-flex xs5>
        <v-img src="https://cdn.vuetifyjs.com/images/cards/foster.jpg" height="125px" contain></v-img>
      </v-flex>
      <v-flex xs7>
        <v-card-title primary-title>
          <div>
            <div class="headline">Event name</div>
            <div>{{new Date().toISOString()}}</div>
            <div>{{location}}</div>
            <nuxt-link to="/ticket">
              <b>View Detail</b>
            </nuxt-link>
          </div>
        </v-card-title>
      </v-flex>
    </v-layout>
  </v-card>
</template>
<script>
export default {
  props: {
      ticketId: String,
      eventDate: Object,
      eventLocation: Object,

  },
  data() {
    return {};
  }
};
</script>