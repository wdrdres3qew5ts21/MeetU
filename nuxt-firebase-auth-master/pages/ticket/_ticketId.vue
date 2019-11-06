<template>
  <v-container>
    <p>#{{ticketEvent.ticketId}}</p>
    <!-- <h1>Ticket(s)</h1> -->
    <!-- <ticketDetail :ticketEvent="ticketEvent"></ticketDetail> -->
    <div v-if="isParticipate">
      <rating-event :ticketEvent="ticketEvent" />
    </div>
    <div class="text-center">
      <br />
      <br />
      <h2>{{ ticketEvent.eventName}}</h2>

      <br />Please show this QR code to enter the event
      and don’t share this QR to other people.
      <br />
      <br />
      <center>
        <qrcode :value="qrCodeSrc" :options="{ width: 230 }"></qrcode>
      </center>

      <div class="text-center">
        <v-btn
          v-if="!ticketEvent.isEventDelete"
          block
          @click="$router.push(`/event/${ticketEvent.elasticEventId}`)"
          color="#341646"
          class="mb-2 white--text"
        >View Event Detail</v-btn>
        <v-btn
          v-else
          block
          color="#341646"
          @click="$router.push(`/event/history`)"
          class="mb-2 white--text"
        >Back to My Ticket</v-btn>
      </div>
      <br />
      <br />

      <!-- <div align="left">
        <br />
        <h3>Event : {{ticketEvent.eventName}}</h3> 
        <br />
        <h3>Organize</h3>
        <br /><h3>{{ticketEvent.organize.organizeName}}</h3>
        <br />Email
        <br />Mobile phone
        <br />
      </div>
      <br />
      <div align="center">
        <v-avatar :size="100">
          <img src="https://image.flaticon.com/icons/png/512/64/64572.png" alt="John" />
        </v-avatar>
        <br />
        <br />
        <h3>{{ticketEvent.organize.organizeName}}</h3>
      </div>-->
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import ratingEvent from "~/components/ratingEvent";

export default {
  components: {
    ratingEvent
  },
  data() {
    return {
      isCameraOpen: false,
      ticketEvent: {
        ticketDetail: [
          { eventName: "E-Ticket", organize: { organizeName: "" } }
        ]
      },
      isParticipate: false,
      qrCodeSrc: {}
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.loadTicketDetail();
    console.log(this.$route.params.ticketId);
  },
  methods: {
    loadTicketDetail() {
      let ticketId = this.$route.params.ticketId;
      console.log(this.getUser.uid);
      axios
        .get(
          `${process.env.EVENT_SERVICE}/events/tickets/${this.getUser.uid}/${ticketId}`
        )
        .then(ticketResponse => {
          this.ticketEvent = ticketResponse.data[0];
          let ticketEvent = ticketResponse.data[0];
          let test = ticketEvent.ticketId;
          this.isParticipate = ticketEvent.isParticipate;

          console.log(ticketEvent);
          this.qrCodeSrc = JSON.stringify({
            ticketId: ticketEvent.ticketId,
            elasticEventId: ticketEvent.elasticEventId,
            ticketKey: ticketEvent.ticketKey,
            uid: this.getUser.uid
          });
        });
    },
    onDecode(decodedString) {
      this.$swal({
        type: "success",
        title: "Save Your Preference !!!",
        text: `${decodedString}`
      });
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
</style>
