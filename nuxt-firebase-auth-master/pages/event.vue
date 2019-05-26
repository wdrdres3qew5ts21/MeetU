<template>
  <v-container>
    <v-layout row wrap>
      <no-ssr>
        <GmapMap
          :center="getCurrentLocation"
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
            <h2>{{infoTitle}}</h2>
            <p>{{infoDetail}}</p>
            <nuxt-link to="/">click</nuxt-link>
          </gmap-info-window>
          <GmapMarker
            :key="index"
            v-for="(marker, index) in markers"
            :position="marker.position"
            :clickable="true"
            @click="toggleInfoWindow(marker,index)"
          />
        </GmapMap>
      </no-ssr>

      <h1>{{getCurrentLocation}}</h1>
      <input type="text" v-model="areaOfEvent" placeholder="input area">
      <v-btn
        color="info"
        @click="findEventInArea()"
      >Click to search nearby event for {{areaOfEvent}}</v-btn>
    </v-layout>
  </v-container>
</template>
<script>
import axios from "axios";
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      position: {},
      infoTitle: "",
      infoDetail: "",
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
      markers: [
        {
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
      ]
    };
  },
  beforeMount() {
    this.getLocation();
  },
  computed: {
    ...mapGetters(["getCurrentLocation"])
  },
  methods: {
    ...mapActions(["updateCurrentLocation"]),
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
      console.log("fuq click marker");
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
.container.fluid {
  max-width: 100%;
  background-image: url(../assets/bg.png) !important;
  /* background-repeat: repeat; */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent;
}
</style>

