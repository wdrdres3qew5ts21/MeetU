<template>
  <v-container>
    <!-- <h1>Ticket(s)</h1> -->
    <!-- <ticketDetail :ticketEvent="ticketEvent"></ticketDetail> -->
    <div class="text-center">
      ! link back numberID Ticket
      <br />
      <br />
      <h2>{{ticketEvent.ticketDetail[0].eventName}}</h2>

      <br />Please show this QR code to enter the event
      and don’t share this QR to other people.
      <br />
      <br />
      <center>
        <qrcode :value="qrCodeSrc" :options="{ width: 230 }"></qrcode>
      </center>

      <!-- <v-btn
        block
        :round="true"
        class="signIn mb-2"
        primary
        @click.native="qrCodeGenerator()"
      >QR Code Generate</v-btn>-->

      <div class="text-center">
        <v-btn block round="16px;" color="#341646" class="mb-2 white--text">DOWNLOAD MY TICKET</v-btn>
      </div>
      <br />
      <br />

      <v-btn @click="isCameraOpen = !isCameraOpen">Open camera</v-btn>
      <div v-if="isCameraOpen">
        <no-ssr placeholder="loading...">
          <qrcode-stream @decode="onDecode"></qrcode-stream>
        </no-ssr>
      </div>
      <div></div>
      <!-- <qrcode-stream :camera="camera" @init="onCameraChange"></qrcode-stream> -->

      <div align="left">
        <h3>Event</h3>
        <br />
        Event name : {{eventName}}
        <br />
        <br />
        <h3>Price</h3>
        <br />Free
        <br />
        <br />
        <h3>Owner</h3>
        <br />Username
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
        <h3>Organize Name</h3>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import ticketDetail from "~/components/ticketDetail";

export default {
  components: {
    ticketDetail
  },
  data() {
    return {
      isCameraOpen: false,
      ticketEvent: {
        ticketDetail: [{ eventName: "E-Ticket" }]
      },
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
          let test = ticketEvent.ticketId

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
