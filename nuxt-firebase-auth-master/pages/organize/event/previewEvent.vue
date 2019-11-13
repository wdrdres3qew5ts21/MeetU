<template>
  <div>
    <client-only>
      <carousel :perPage="1" :paginationEnabled="false">
        <slide>
          <img width="100%" :src="eventPictureCover" alt srcset />
        </slide>
        <slide v-for="(pic,i) in eventPictureLists" :key="i">
          <img width="100%" :src="pic" alt srcset />
        </slide>
      </carousel>
    </client-only>
    <br />
    <h3 class="eventName">{{getEventTemplate.eventName}}</h3>
    <br />

    <v-flex>
      <v-chip v-for="(eventTag,index) in getEventTemplate.eventTags" :key="index" text-color="#341646">
        <v-avatar>
          <v-icon color="primary">local_offer</v-icon>
        </v-avatar>
        <nuxt-link :to="`/event?category=${eventTag}`">{{eventTag}}</nuxt-link>
      </v-chip>
    </v-flex>

    <v-flex>
      <v-btn
        block
        color="#341646"
        style="color:white"
        @click="$vuetify.goTo('#ticketSection')"
      >View Ticket</v-btn>
    </v-flex>
    <br />
    <br />
    <br />
    <v-flex>
      <p class="eventDate">Date & Time</p>
    </v-flex>Start
    <br />

    <v-layout>
      <v-flex xs12 sm5 md3 class="content">
        <v-icon size="23">today</v-icon>
        {{formatDateForReadable(getEventTemplate.eventStartDate)}}
      </v-flex>
      <v-flex xs12 sm5 md5 offset-(xs0 | lg2) class="content">
        <v-icon size="23">alarm</v-icon>
        {{formatAMPM(getEventTemplate.eventStartDate)}}
      </v-flex>
    </v-layout>
    <p></p>End
    <v-layout>
      <v-flex xs12 sm5 md3 class="content">
        <v-icon size="23">today</v-icon>
        {{formatDateForReadable(getEventTemplate.eventEndDate)}}
      </v-flex>
      <v-flex xs12 sm5 md5 offset-(xs0 | lg2) class="content">
        <v-icon size="23">alarm</v-icon>
        {{formatAMPM(getEventTemplate.eventEndDate)}}
      </v-flex>
    </v-layout>

    <br />
    <br />

    <div>
      <p></p>
      <p class="eventDate">Event Detail</p>
      <div>
        <span>{{readMoreActivated ? getEventTemplate.eventDetail : getEventTemplate.eventDetail.slice(0, 200) }}</span>
        <br />
        <br />
        <center>
          <a
            @click="readMoreActivated = !readMoreActivated"
          >{{readMoreActivated ? 'Show less' : 'Read more'}}</a>
        </center>
      </div>
      <!-- <div class="a content">{{eventDetail}}</div> -->
    </div>
    <br />
    <p></p>
    <p class="eventDate">Badge</p>
    <v-layout row>
      <v-list-tile-avatar size="125">
        <img :src="badge.badgePicture" />
      </v-list-tile-avatar>
      <v-list-tile-content>
        <v-list-tile-title>
          <b>{{badge.badgeName}}</b>
        </v-list-tile-title>
        <v-list-tile-sub-title>Exp: {{badge.exp}}</v-list-tile-sub-title>
      </v-list-tile-content>

      <br />
    </v-layout>

    <br />
    <br />
    <br />
    <p class="eventDate">Location</p>

    <p class="content">{{location.detail}}</p>

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
    <br />
    <p class="eventDate">Tickets</p>

    <p>{{eventName}}</p>
    <v-layout row wrap>
      <v-flex xs7>Free</v-flex>
      <v-spacer></v-spacer>
      <v-flex xs5>{{numberOfTicket}} Ticket Left(s)</v-flex>
      <!-- <v-flex xs6>
        <v-select :items="numberOfTicket" label="numberOfTicket"></v-select>
      </v-flex>-->
    </v-layout>

    <v-btn block :disabled="!isTicketSelected" color="primary" id="ticketSection">GET TICKET</v-btn>
    <br />
    <v-divider></v-divider>
    <br />
    <h3>Contract</h3>
    <br />
    <p></p>

    <center>
      <div style="width:150px;overflow:hidden">
        <v-img
          :src=" organize.organizeImageCover ||  'https://picsum.photos/id/11/500/300' "
          lazy-src="https://picsum.photos/id/11/10/6"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="300"
          style="border-radius:60%;"
        ></v-img>
      </div>
    </center>
    <br />
    <b>Organizer Name :</b>
    {{organize.organizeName}}
    <br />
    <b>Website :</b>
    {{organize.website || 'https://meetu-69b29.firebaseapp.com'}}
    <br />
    <b>Email :</b>
    {{organize.email || 'linjingyun@nipparn.com'}}
    <br />
    <br />
    <center>
      <v-btn block color="#341646" style="color:white">For more information</v-btn>
    </center>

    <br />
    <br />
    <v-btn
      block
      class="saveButton white--text"
      color="#341646"
      depressed
      large
      height="50"
      @click="createEventAndUploadData()"
    >Publish Event</v-btn>
    <v-btn
      block
      color="#AEAEAE"
      class="white--text"
      depressed
      large
      height="50"
      @click="$router.back()"
    >Cancle</v-btn>
  </div>
</template> 
<script>
import axios from "axios";
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
      description: "",
      place: null,
      pinLocation: {},
      organize: {
        organizeName: "",
        website: "",
        email: "",
        organizeImageCover: ""
      }
    };
  },
  computed: {
    ...mapGetters(["getEventTemplate"])
  },
  mounted() {
    this.loadEventTemplate();
    this.loadOrganizeDetail();
  },
  methods: {
    ...mapActions(["saveEventAndUpload"]),
    createEventAndUploadData() {
      console.log(this.getEventTemplate);
      this.saveEventAndUpload();
      //axios.post(`${process.env.EVENT_SERVICE}/event`)
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
    setDescription(description) {
      this.description = description;
    },
    setPlace(place) {
      this.place = place;
    },
    usePlace(place) {
      if (this.place) {
        this.pinLocation = {
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng()
        };
      }
      console.log("--- place ----");
      console.log(place);
      console.log("---- pin location ---- ");
      console.log(this.pinLocation);
      this.place = null;
    },
    loadOrganizeDetail() {
      // axios.get(`${process.env.USER_SERVICE}/organize/${this.getEventTemplate.organize.organizeId}`)
      // .then(organizeResponse)
    },
    loadEventTemplate() {
      let eventTemplate = this.getEventTemplate;
      this.eventName = eventTemplate.eventName;
      this.eventDetail = eventTemplate.eventDetail;
      this.eventTags = eventTemplate.eventTags;
      this.eventPictureCover = eventTemplate.eventPictureCoverBase;
      this.eventPictureLists = eventTemplate.eventPictureListsBase;
      this.eventStartDate = eventTemplate.eventStartDate;
      this.numberOfTicket = eventTemplate.numberOfTicket;

      axios
        .get(
          `${process.env.USER_SERVICE}/organize/${eventTemplate.organize.organizeId}`
        )
        .then(organizeResponse => {
          this.organize = organizeResponse.data;
          console.log("-----organize ----");
          console.log(organizeResponse.data);
        })
        .catch(err => {
          console.log("eefdsfdsfds");
        });

      let geopoint = eventTemplate.location.geopoint;

      // if ((geopoint.lat === 0) & (geopoint.lon === 0)) {
      //   navigator.geolocation.getCurrentPosition(location => {
      //     this.center = {
      //       lat: location.coords.latitude,
      //       lng: location.coords.longitude
      //     };
      //     console.log(location.coords.latitude);
      //     console.log(location.coords.longitude);
      //   });
      // } else {
      //   this.center = {
      //     lat: geopoint.lat,
      //     lng: geopoint.lon
      //   };
      // }
      this.marker.position = {
        lat: geopoint.lat,
        lng: geopoint.lon
      };
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
.eventDate {
  font-weight: 600;
  font-size: 16px;
  color: #341646;
}
.content {
  font-size: 16px;
}
.eventName {
  color: #341646;
  font-size: 25px;
}
div.a {
  white-space: nowrap;
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  height: 100px;
}

div.a:hover {
  overflow: visible;
}
.a {
  font-weight: 600;
}
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
  
</style>