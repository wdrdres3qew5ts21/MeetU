<template>
  <div class="text-center">
    ! link back numberID Ticket
    <br />
    <br />
    <!-- <h2>{{ticketEvent.ticketDetail[0].eventName}}</h2> -->

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
   <div>


   </div> 
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
</template>

<script>
import { eventNotFound } from "~/utils/errorMessage";
import axios from "axios";
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  props: {
    ticketEvent: Object
  },
  data() {
    return {
      isCameraOpen: false,
      qrCodeSrc: "demo",
      isTicketSelected: true,
      numberOfTicket: [],
      eventName: "",
      eventDetail: "",
      createEventDate: "",

      location: "",
      marker: {
        icon:
          "https://png2.kisspng.com/sh/5a457e82acb6e22a2ae8836f35448931/L0KzQYm3VMAzN6dBiZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfJ2e5pzfeV8LYfygrztjP94NZVuf9t9YXywhMPojwNnd6NyRd94dnWwRbLqUvQ2bGE3e9dqN0axQ4S8Vsc2OGc2TaQ7N0G7QYe3Ucg1NqFzf3==/kisspng-computer-icons-business-workflow-digital-transform-move-5ac2d5d02cea76.335675061522718160184.png",
        title: "Digital Transformation 4.0 By IMC",
        detail:
          "พบกับผู้ที่คร่ำหวอดในวงการอุตสาหกรรมที่พร้อมจะมาพลิกโฉมอุตสาหกรรมของท่านให้ก้าวไปสู่ยุค Thailand 4.0",
        position: {
          lat: 13.6518128,
          lng: 100.4937549
        }
      }
    };
  },
  asyncData({ params, error }) {
    let eventElasticId = params.eventElasticId;
    console.log(eventElasticId);
    return axios
      .get(`${process.env.EVENT_SERVICE}/event/${eventElasticId}`)
      .then(response => {
        console.log("------------ Async Data  -----------");
        let data = response.data;
        console.log(data);
        return {
          eventElasticId: data.eventElasticId,
          eventName: data.eventName,
          eventDetail: data.eventDetail,
          createEventDate: data.createEventDate,
          location: data.location,
          organizeId: data.organize.organizeId,
          organizeName: data.organize.organizeName
        };
      })
      .catch(err => {
        console.log("!!!!!!!!!!!!!!!!! Boom Not found !!!!!!!!!!");
        console.log(err);
        return error({ statusCode: 404, message: eventNotFound(err) });
      });
  },
  mounted() {
    console.log(this.$route.params.eventElasticId);
    console.log(this.getUser.uid);
    this.userViewEvent();
  },
  computed: {
    ...mapGetters(["getCurrentLocation", "getUser"])
  },
  methods: {
    ...mapActions(["updateCurrentLocation"]),
    userViewEvent: function() {
      axios.post(`${process.env.EVENT_SERVICE}/event/view`, {
        uid: this.getUser.uid,
        elasticEventId: this.$route.params.eventElasticId
      });
    },
    userReserveTicket: function() {
      console.log("User Reserve Ticket Event!");

      let userJoinEventBody = {
        uid: this.getUser.uid,
        eventElasticId: this.$route.params.eventElasticId
      };
      this.qrCodeSrc = JSON.stringify(userJoinEventBody);
      //axios.post(`${process.env.EVENT_SERVICE}/event/ticket`, userJoinEventBody)
    },
    onDecode(decodedString) {
      console.log("QR Code is Decoding...");
    }
  }
};
// export default {
//   components: {

//   },
//   data() {
//     return {
//       qrCode: ""
//     };
//   },
//   methods: {
//     onDecode(decodedString) {
//       console.log("QR Code is Decoding...");
//     },
//     async qrCodeGenerator() {
//       let qrCode = await axios.get("http://localhost:3002/events/qrcode");
//       console.log(qrCode);
//       this.qrCode = qrCode.data;
//     }
//   }
// };
</script>