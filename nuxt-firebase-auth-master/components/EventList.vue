<template>
  <v-container fluid grid-list-md>
    <v-layout row wrap>
      <v-flex v-for="(event, index) in eventList" :key="index" v-bind="{ [`xs6`]: true }">
        <event-card :eventPictureCover="event.eventPictureCover" :eventName="event.eventName"></event-card>
      </v-flex>
    </v-layout>
    <center>
      <nuxt-link :to="link" style="text-decoration-line:none;">
        <v-btn class="black--text" outline color="#341646" depressed large>View more</v-btn>
      </nuxt-link>
    </center>
  </v-container>
</template>
<script>
import EventCard from "@/components/eventCard";
export default {
  name: "EventList",
  components: {
    EventCard
  },
  props: {
    eventList: Array,
    link: String
  },
  data() {
    return {};
  },
  mounted() {
    this.$nextTick(() => {
      this.$nuxt.$loading.start();

      setTimeout(() => this.$nuxt.$loading.finish(), 500);
    });
  }
};
</script>