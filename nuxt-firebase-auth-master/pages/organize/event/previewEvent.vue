<template>
  <div>
    <v-carousel height="auto" hide-delimiters>
      <v-carousel-item :src="eventPictureCover.url"></v-carousel-item>
      <v-carousel-item v-for="(pic,i) in eventPictureLists" :key="i" :src="pic.url"></v-carousel-item>
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
      <v-layout row wrap>
        <client-only>
          <label>
            AutoComplete
            <GmapAutocomplete @place_changed="setPlace"></GmapAutocomplete>
            <button @click="usePlace">Add</button>
          </label>
          <br />
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
      <v-flex xs5>{{numberOfTicket}} Ticket Left(s)</v-flex>
      <!-- <v-flex xs6>
        <v-select :items="numberOfTicket" label="numberOfTicket"></v-select>
      </v-flex>-->
    </v-layout>

    <v-btn
      @click="userReserveTicket()"
      block
      :disabled="!isTicketSelected"
      color="primary"
      id="ticketSection"
    >GET TICKET</v-btn>

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
</template> 
<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      qrCodeSrc: "demo",
      isTicketSelected: true,
      eventName: "",
      eventTags: [],
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
        "https://www.blognone.com/sites/default/files/externals/41bbf3e3153999d8d2111d753cf1d5f2.jpg",
        description: '',
        place: null,

    };
  },
  computed: {
    ...mapGetters(["getEventTemplate"])
  },
  mounted() {
    this.loadEventTemplate();
  },
  methods: {
    setDescription(description) {
      this.description = description;
    },
    setPlace(place) {
      this.place = place
    },
    usePlace(place) {
      if (this.place) {
        this.markers.push({
          position: {
            lat: this.place.geometry.location.lat(),
            lng: this.place.geometry.location.lng(),
          }
        })
        this.place = null;
      }
    },
    loadEventTemplate() {
      let eventTemplate = this.getEventTemplate;
      this.eventName = eventTemplate.eventName;
      this.eventDetail = eventTemplate.eventDetail;
      this.eventTags = eventTemplate.eventTags;
      this.eventPictureCover = eventTemplate.eventPictureCoverBase;
      this.eventPictureLists = eventTemplate.eventPictureListsBase;
      this.eventStartDate = eventTemplate.eventStartDate;
    }
  }
};
</script>