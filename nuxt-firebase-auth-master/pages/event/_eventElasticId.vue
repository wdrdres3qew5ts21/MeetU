<template>
  <div>
    <v-carousel hide-delimiters hide-controls xs6 sm12 height="200px;">
      <v-carousel-item v-for="(pic,i) in eventPictureLists" :key="i" :src="pic"></v-carousel-item>
    </v-carousel>
    <br />
    <h3>{{eventName}}</h3>
    <v-btn block color="#341646" style="color:white">View Ticket</v-btn>
    <br />
    <p>Date</p>
    <p>
      <b>{{createEventDate}}</b>
    </p>
    <a href>Add to Calendar</a>
    <p>Location</p>
    <p>
      <b>{{location.country}}, {{location.province}}</b>
    </p>
    <center>
      <v-container>
        <v-layout row wrap>
          <no-ssr>
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
                <h2>{{infoTitle}}</h2>
                <p>{{infoDetail}}</p>
                <nuxt-link to="/">click</nuxt-link>
              </gmap-info-window>
              <GmapMarker
                :key="index"
                :position="marker.position"
                :clickable="true"
                @click="toggleInfoWindow(marker,index)"
              />
            </GmapMap>
          </no-ssr>
        </v-layout>
      </v-container>
      <br />
    </center>
    <p>Share with...</p>
    <h3>Tickets</h3>
    <p>
      <b>{{eventName}}</b>
    </p>
    <p>Free</p>
    <v-select
      items="items"
      label="label"
      segmented overflow editable
      target="#target"
    ></v-select>
  </div>
</template> 
 
<script>
import { eventNotFound } from "~/utils/errorMessage";
import axios from "axios";
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      eventName: "",
      createEventDate: "",
      location: "",
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
    let eventElasticId = params.eventElasticId;
    console.log(eventElasticId);
    return axios
      .get(`${process.env.EVENT_SERVICE}/event/${eventElasticId}`)
      .then(response => {
        console.log("------------ Async Data  -----------");
        let data = response.data;
        console.log(data);
        return {
          eventName: data.eventName,
          eventPictureCover: data.eventPictureCover,
          eventPictureLists: data.eventPictureLists,
          createEventDate: data.createEventDate,
          location: data.location,
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
        return error({ statusCode: 404, message: eventNotFound() });
      });
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

 
