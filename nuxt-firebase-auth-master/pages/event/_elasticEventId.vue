<template>
<div>
  <div v-if = "isViewTicketDetail ">
    <v-carousel hide-delimiters hide-controls xs6 sm12 height="200px;">
      <v-carousel-item v-for="(pic,i) in eventPictureLists" :key="i" :src="pic"></v-carousel-item>
    </v-carousel>
    <br />
    <h3>{{eventName}}</h3>
    <v-btn
      block
      color="#341646"
      style="color:white"
      @click="$vuetify.goTo('#ticketSection')"
    >View Ticket</v-btn>
    <br />

    <p>Date</p>
    <p>
      <b>{{createEventDate}}</b>
    </p>

    <p>
      <a href>Add to Calendar</a>
    </p>

    <h4>Event Detail</h4>
    <p class="text-justify">{{eventDetail}}</p>

    <p>Location</p>
    <p>
      <b>{{location.country}}, {{location.province}}</b>
    </p>
    <center>
      <v-container>
        <v-layout row wrap>
          <client-only>
          <GmapMap
              :center="marker.position"
              :zoom="14"
              map-type-id="terrain"
              style="width: 500px; height: 300px"
              :options="{
                scaleControl: true
            }"
            >
              <gmap-info-window
                :position="infoWindowPos"
                :opened="infoWinOpen"
                @closeclick="infoWinOpen=false"
              >
                <h2>{{eventName}}</h2>
                <p>{{eventDetail}}</p>
                <nuxt-link to="/">click</nuxt-link>
              </gmap-info-window>
              <GmapMarker
                :position="marker.position"
                :clickable="true"
                @click="toggleInfoWindow(marker,0)"
              />
          </GmapMap>
          </client-only>
        </v-layout>
      </v-container>
      <br />
    </center>
    <p>Share with...</p>
    <h3>Tickets</h3>
    <p>
      <b>{{eventName}}</b>
    </p>
    <v-layout row wrap>
      <v-flex xs7>Free</v-flex>
      <v-spacer></v-spacer>
      <v-flex xs5>{{numberOfTicket}}  Ticket Left(s)</v-flex>
      <!-- <v-flex xs6>
        <v-select :items="numberOfTicket" label="numberOfTicket"></v-select>
      </v-flex>-->
    </v-layout>
   
       <!-- <nuxt-link :to="`/ticket?`" > -->
       <!-- @click="userReserveTicket()" -->
    <v-btn
      @click="isViewTicketDetail = false "
      block
      :disabled="!isTicketSelected"
      color="primary"
      id="ticketSection"
    >GET TICKET</v-btn>
       <!-- </nuxt-link> -->
    <!-- <center>
      <qrcode :value="qrCodeSrc" :options="{ width: 200 }"></qrcode>
    </center> -->

    <h3>Contract</h3>
    <p>Contract the oraganizer for more information</p>
    <v-card class="mx-auto" elevation="1">
      <center>
        <div style="width:200px;overflow:hidden">
          <v-img
            src="https://picsum.photos/id/11/500/300"
            lazy-src="https://picsum.photos/id/11/10/6"
            aspect-ratio="1"
            class="grey lighten-2"
            max-width="300"
            style="border-radius:60%;"
          ></v-img>
        </div>
      </center>
      <v-card-title justify-center>Organizer</v-card-title>
      <v-card-text>Website</v-card-text>
      <v-card-text>Email</v-card-text>
      <v-card-actions>
        <v-btn text>Click</v-btn>
      </v-card-actions>
    </v-card>
  </div>

  <div v-else> 
<confirmTicket> </confirmTicket>
    </div>

</div>
</template> 
 
<script>
import { eventNotFound } from "~/utils/errorMessage";
import confirmTicket from "@/components/confirmTicket";
import axios from "axios";
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  components: {
      confirmTicket
  },
  data() {
    return {
      qrCodeSrc: "demo",
      isTicketSelected: true,
      isViewTicketDetail: true,
      eventName: "",
      numberOfTicket: 0,
      eventDetail: "",
      createEventDate: "",
      location: "",
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      areaOfEvent: "",
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35
        }
      },
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
      },
      eventPictureLists: [],
      eventPictureCover:
        "https://www.blognone.com/sites/default/files/externals/41bbf3e3153999d8d2111d753cf1d5f2.jpg"
    };
  },
  asyncData({ params, error }) {
    let elasticEventId = params.elasticEventId;
    console.log(elasticEventId);
    return axios
      .get(`${process.env.EVENT_SERVICE}/event/${elasticEventId}`)
      .then(response => {
        console.log("------------ Async Data  -----------");
        let data = response.data;
        console.log(data);
        return {
          elasticEventId: data.elasticEventId,
          numberOfTicket: data.numberOfTicket,
          eventName: data.eventName,
          eventDetail: data.eventDetail,
          eventPictureCover: data.eventPictureCover,
          eventPictureLists: data.eventPictureLists,
          createEventDate: data.createEventDate,
          location: data.location,
          organizeId: data.organize.organizeId,
          organizeName: data.organize.organizeName,
          marker: {
            title: data.eventName,
            detail: data.eventDetail,
            position: {
              lat: data.location.geopoint.lat,
              lng: data.location.geopoint.lon
            }
          }
        };
      })
      .catch(err => {
        console.log("!!!!!!!!!!!!!!!!! Boom Not found !!!!!!!!!!");
        console.log(err);
        return error({ statusCode: 404, message: eventNotFound(err) });
      });
  },
  mounted() {
    console.log(this.$route.params.elasticEventId);
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
        elasticEventId: this.$route.params.elasticEventId
      });
    },
    userReserveTicket: function() {
      console.log("User Reserve Ticket Event!");
      let reserveTicket = {
        uid: this.getUser.uid,
        elasticEventId: this.$route.params.elasticEventId
      };
      this.qrCodeSrc = JSON.stringify(reserveTicket);
      console.log(reserveTicket)
      axios.post(`${process.env.EVENT_SERVICE}/event/reserve`, reserveTicket)
      .then(reserveTicket=>{
        console.log(reserveTicket)
        this.isViewTicketDetail = false
      })
      .catch(err =>{
        this.$swal({
          type: "error",
          title: "Ticket Sold Out !!!",
          text: `Ticket had been sold or Error`
        });
      })
    },
    findEventInArea: async function() {
      let geolocation = {
        lat: this.getCurrentLocation.lat,
        lon: this.getCurrentLocation.lng
      };

      let eventLocation = {
        areaOfEvent: this.areaOfEvent,
        geolocation
      };
      console.log(process.env.USER_SERVICE);
      let searchEventLocation = await axios.get(
        `${process.env.EVENT_SERVICE}/events`
      );
      console.log(searchEventLocation.data);
    },
    toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = marker.position;
      this.infoTitle = marker.title;
      this.infoDetail = marker.detail;

      //check if its the same marker that was selected if yes toggle
      if (this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      //if different marker set infowindow to open and reset current marker index
      else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    },
    getLocation: function() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
          this.position = position;
          let lat = parseFloat(position.coords.latitude);
          let lng = parseFloat(position.coords.longitude);
          this.$store.dispatch("updateCurrentLocation", { lat: lat, lng: lng });
        });
      } else {
        console.log("not support fuq");
      }
    }
  }
};
</script>

 
 
 
<style lang="css">
.v-content {
  max-width: 100%;
  /* background-image: url(~assets/bg.png) !important; */
  /* background-repeat: repeat; */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent;
}
</style>

 
