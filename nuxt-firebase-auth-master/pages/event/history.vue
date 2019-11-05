<template>
  <div>
    <br />
    <h2>Ticket(s)</h2>
    <br />
    <div v-for="(eventTicket, index) in userEventTicketList" :key="index">
      <v-flex xs12>
        <nuxt-link :to="`/ticket/${eventTicket.elasticEventId}`">
          <v-card min-width="375" color="white">
            <v-layout v-if="!eventTicket.isEventDelete">
              <v-flex xs8 pa-2>
                <br />

                <v-img
                  :src="eventTicket.ticketDetail[0].eventPictureCover"
                  width="175px"
                  height="130px"
                ></v-img>
              </v-flex>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <div
                      class="headline"
                    >{{eventTicket.ticketDetail[0].eventName.length > 10 ? eventTicket.ticketDetail[0].eventName.substr(0,10)+"..." : eventTicket.ticketDetail[0].eventName }}</div>
                    <div
                      class="detail"
                    >Date: {{ formatDateForReadable(eventTicket.ticketDetail[0].eventStartDate)}}</div>
                    <div
                      class="detail"
                    >Place : {{eventTicket.ticketDetail[0].location.province+", " +eventTicket.ticketDetail[0].location.country}}</div>
                    <br />
                    <b color="deep-purple darken-3">View Detail</b>
                    <div v-if="eventTicket.isParticipate == false">Status: available</div>
                    <div v-else>Status: joined</div>
                  </div>
                </v-card-title>
              </v-flex>
            </v-layout>
            <v-layout v-else>
              <v-flex xs8>
                <center>
                  <p width="170px">{{eventTicket.deleteMessageDetail}}</p>
                </center>
              </v-flex>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <div
                      class="headline"
                    >{{eventTicket.eventName.length > 10 ? eventTicket.eventName.substr(0,10)+"..." : eventTicket.eventName }}</div>
                    <div>{{new Date().toISOString() || eventTicket.eventStartDate.substr(0,10) }}</div>
                    <br />
                    <b color="deep-purple darken-3">View Detail</b>
                    <div>Used: {{eventTicket.isParticipate}}</div>
                  </div>
                </v-card-title>
              </v-flex>
            </v-layout>
          </v-card>
        </nuxt-link>
      </v-flex>
      <br />
    </div>


    <div v-if="userEventTicketList.length == 0">
      <center>
        <br />
        <v-icon medium>mdi-emoticon-cry-outline</v-icon>
        <br />

        <p style="color:grey">No ticket, let's start to join an event!</p>
        <nuxt-link :to="`/`" class="linkToHomepage">Click here</nuxt-link>

        <!-- <p style="color:grey">You can create new event.</p> -->
      </center>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import axios from "axios";
import "@mdi/font/css/materialdesignicons.css";
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
      let loader = this.$loading.show();
      axios
        .get(`${process.env.EVENT_SERVICE}/events/tickets/${this.getUser.uid}`)
        .then(userEventTicketList => {
          console.log(userEventTicketList.data);
          this.userEventTicketList = userEventTicketList.data;
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    formatDateForReadable: function(formatDate) {
      let date = new Date(formatDate);
      formatDate =
        date.getDate() + "-" + date.getMonth() + "-" + date.getFullYear();
      console.log(formatDate);
      return formatDate;
    }
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
.linkToHomepage {
  text-decoration: none;
  font-weight: bold;
  font-size: 18px;
  color: #100c4b;
}
.linkToHomepage:hover {
  text-decoration: underline;
  font-weight: bold;
  font-size: 18px;
  color: #341646;
}
.headline {
  font-size: 12px;
  color: #341646;
}
.detail {
  font-size: 15px;
  color: dimgrey;
}
</style>