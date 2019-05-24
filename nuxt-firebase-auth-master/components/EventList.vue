<template>
  <v-layout>
    <v-layout row wrap>
      <v-flex v-for="event in eventList" :key="event.eventId" xs4>
        <v-card flat tile>
          <v-img img v-bind:src="event.eventPictureCover" max-height="230px"></v-img>
        </v-card>
      </v-flex>
      <v-flex xs4>
        <v-card flat tile height="230px">
          <v-card-title primary-title>
            <div class="headline">More Event ...</div>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
  </v-layout>
</template>
<script>
export default {
  name: "EventList",
  props: {
      eventList: Array
  },
  data() {
    return {

    };
  }
};
</script>