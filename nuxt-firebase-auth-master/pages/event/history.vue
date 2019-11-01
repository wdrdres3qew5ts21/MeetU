<template>
  <div>
    <br>
    <h2>Ticket(s)</h2>
    <br />
    <div v-for="(eventTicket, index) in userEventTicketList" :key="index">
      <v-flex xs12>
        <nuxt-link :to="`/ticket/${eventTicket.elasticEventId}`">
          <v-card min-width="375" color="white">
            <v-layout v-if="!eventTicket.isEventDelete">
              <v-flex xs8 pa-2>
                <br>
               
                <v-img :src="eventTicket.ticketDetail[0].eventPictureCover" width="175px" height="130px"></v-img>
              </v-flex>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <div
                      class="headline"
                    >{{eventTicket.ticketDetail[0].eventName.length > 10 ? eventTicket.ticketDetail[0].eventName.substr(0,10)+"..." : eventTicket.ticketDetail[0].eventName }}
                    </div>
                    <div class="detail">Date: {{ formatDateForReadable(eventTicket.ticketDetail[0].eventStartDate)}}</div>
                    <div class="detail">Place : {{eventTicket.ticketDetail[0].location.province+", " +eventTicket.ticketDetail[0].location.country}}</div>
                    <br />
                    <b color="deep-purple darken-3">View Detail</b>
                    <div v-if="eventTicket.isParticipate == false" >Status: available </div>
                     <div v-else >Status: unavailable </div>

                  
                  </div>
                </v-card-title>
              </v-flex>
            </v-layout>
            <v-layout v-else>
            <v-flex xs8>
              <p  width="170px">{{eventTicket.deleteMessageDetail}}</p>
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
    },
    formatDateForReadable: function(formatDate){
      let date = new Date(formatDate);
      formatDate = date.getDate()  + "-" + date.getMonth() + "-" + date.getFullYear() ;
      console.log(formatDate);
      return formatDate
    }
  }
};
</script>

<style >
.headline{
    font-size: 12px;
    color: #341646;
}
.detail{
   font-size: 15px;
    color:dimgrey;
}
</style>
