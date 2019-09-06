<template>
  <div class="text-center">
    ! link back numberID Ticket
    <br />
    <br />
    <h2>E Ticket</h2>

    <br />Please show this QR code to enter the event
    and don’t share this QR to other people.

    <br />
    <h1>Not finish na ja</h1> 
     
     <!-- <v-btn
        block
        :round="true"
        class="signIn mb-2"
        primary
        @click.native="qrCodeGenerator()"
      >QR Code Generate</v-btn> -->

     
    <v-card  max-width="330" class="mx-auto">

      <v-card-title>
        <img src="http://localhost:3002/events/qrcode" alt srcset width="300" />
      </v-card-title>
    </v-card>
    <br />
    <div class="text-center">
      <v-btn block round="16px;" color="#341646" class="mb-2 white--text">DOWNLOAD MY TICKET</v-btn>
    </div>
    <br />
    <br />
   
     <!-- <no-ssr placeholder="loading...">
   
       <qrcode-stream @decode="onDecode"></qrcode-stream> 
    
   
     <qrcode-stream @decode="onDecode"></qrcode-stream>
     
      </no-ssr> 
       -->
       <!-- <qrcode-stream :camera="camera" @init="onCameraChange"></qrcode-stream>
     -->
    <div align="left">
      <h3>Event</h3>
      <br />Event name
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
import axios from "axios";


export default {
  components: {
   
  },
  data() {
    return {
      qrCode: ""
    };
  },
  methods: {
    onDecode(decodedString) {
      console.log("QR Code is Decoding...");
    },
    async qrCodeGenerator() {
      let qrCode = await axios.get("http://localhost:3002/events/qrcode");
      console.log(qrCode);
      this.qrCode = qrCode.data;
    }
  }
};
</script>