<template>
  <div>
    <h2>Ticket(s)</h2>
    <br />
    <v-flex v-for="(eventTicket, index) in userEventTicketList" :key="index" xs12>
      <v-card min-width="370" height="180" color="white">
        <v-layout>
          <v-flex xs8>
            <v-img
              :src="eventTicket.ticketDetail[0].eventPictureCover"
              height="180px"
              width="170px"
            ></v-img>
          </v-flex>
          <v-flex xs7>
            <v-card-title primary-title>
              <div>
                <div
                  class="headline"
                >{{eventTicket.ticketDetail[0].eventName.length > 10 ? eventTicket.ticketDetail[0].eventName.substr(0,10)+"..." : eventTicket.ticketDetail[0].eventName }}</div>
                <div>{{new Date().toISOString() || eventTicket.ticketDetail[0].eventStartDate.substr(0,10) }}</div>
                <div>{{eventTicket.ticketDetail[0].location.province+", " +eventTicket.ticketDetail[0].location.country}}</div>
                <nuxt-link :to="`/ticket/${eventTicket.elasticEventId}`">
                  <br />
                  <b color="deep-purple darken-3">View Detail</b>
                </nuxt-link>
              </div>
            </v-card-title>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <br />
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import axios from "axios";
export default {
  data() {
    return {
      location: "BKK, Thailand",
      userEventTicketList: []
    };
  },
  mounted() {
    this.loadAllUserEventTicket();
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    loadAllUserEventTicket() {
      axios
        .get(`${process.env.EVENT_SERVICE}/events/tickets/${this.getUser.uid}`)
        .then(userEventTicketList => {
          console.log(userEventTicketList.data);
          this.userEventTicketList = userEventTicketList.data;
        })
        .catch(err => {});
    }
  }
};
</script>