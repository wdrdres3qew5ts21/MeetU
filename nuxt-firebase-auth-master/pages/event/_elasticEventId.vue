<template>
  <div>
    <div v-if="isViewTicketDetail">
      <!-- <v-carousel hide-delimiters hide-controls xs6 sm12 height="200px;">
        <v-carousel-item v-for="(pic,i) in eventPictureLists" :key="i" :src="pic"></v-carousel-item>
      </v-carousel>-->
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
      <h3 class="eventName">{{eventName}}</h3>
      <br />

      <v-flex>
        <v-chip v-for="(eventTag,index) in eventTags" :key="index" text-color="#341646">
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
          {{formatDateForReadable(eventStartDate)}}
        </v-flex>
        <v-flex xs12 sm5 md5 offset-(xs0 | lg2) class="content">
          <v-icon size="23">alarm</v-icon>
          {{formatAMPM(eventStartDate)}}
        </v-flex>
      </v-layout>
      <p></p>End
      <v-layout>
        <v-flex xs12 sm5 md3 class="content">
          <v-icon size="23">today</v-icon>
          {{formatDateForReadable(eventEndDate)}}
        </v-flex>
        <v-flex xs12 sm5 md5 offset-(xs0 | lg2) class="content">
          <v-icon size="23">alarm</v-icon>
          {{formatAMPM(eventEndDate)}}
        </v-flex>
      </v-layout>

      <br />
      <br />

      <div>
        <p></p>
        <p class="eventDate">Event Detail</p>
        <div>
          <span>{{readMoreActivated ? eventDetail : eventDetail.slice(0, 200) }}</span>
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

      <!-- <nuxt-link :to="`/ticket?`" > -->
      <!-- @click="userReserveTicket()" -->
      <v-btn
        @click="userReserveTicket()"
        block
        :disabled="!isTicketSelected"
        color="primary"
        id="ticketSection"
      >GET TICKET</v-btn>
      <!-- </nuxt-link> -->
      <!-- <center>
      <qrcode :value="qrCodeSrc" :options="{ width: 200 }"></qrcode>
      </center>-->

      <br />
      <v-divider></v-divider>
      <h3>Contract</h3>
      <p></p>

      <center>
        <div style="width:150px;overflow:hidden">
          <v-img
            :src="  organizeImageCover ||  'https://picsum.photos/id/11/500/300' "
            aspect-ratio="1"
            class="grey lighten-2"
            max-width="300"
            style="border-radius:60%;"
          ></v-img>
        </div>
      </center>
      <br />
      <b>Organizer Name :</b>
      {{organizeName || '404 Network might be trouble...'}}
      <br />

      <b>Website : {{website || 'Not have information...'}}</b>
      <br />
      <b>Email : {{email || 'Not have information...'}}</b>
      <br />
      <br />
      <center>
        <nuxt-link :to="`/organize/event/${organizeId}`">
          <v-btn block color="#341646" style="color:white">Organize Information</v-btn>
        </nuxt-link>
      </center>
    </div>

    <div v-else>
      <confirmTicket :reserveTicket="reserveTicket"></confirmTicket>
    </div>
  </div>
</template> 
 
<script>
import Carousel from "vue-carousel/src/Carousel.vue";
import Slide from "vue-carousel/src/Slide.vue";
import { eventNotFound } from "~/utils/errorMessage";
import confirmTicket from "@/components/confirmTicket";
import axios from "axios";
import { mapMutations, mapActions, mapGetters } from "vuex";
import { error, log } from "util";
export default {
  components: {
    confirmTicket,
    Carousel,
    Slide
  },
  data() {
    return {
       show: true,
      readMoreActivated: false,
      reserveTicket: {},
      readMore: false,
      qrCodeSrc: "demo",
      isTicketSelected: true,
      isViewTicketDetail: true,
      organizeImageCover: "",
      organizeId: "",
      organizeName: "",
      eventTags: [],
      eventName: "",
      numberOfTicket: 0,
      eventDetail: "",
      eventStartDate: "",
      eventEndDate: "",
      createEventDate: "",
      website: "",
      location: "",
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      badge: "",
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
          eventStartDate: data.eventStartDate,
          eventEndDate: data.eventEndDate,
          createEventDate: data.createEventDate,
          location: data.location,
          badge: data.badge,
          organizeId: data.organize.organizeId,
          organizeName: data.organize.organizeName,
          eventTags: data.eventTags,
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
    this.loadOrganizeDetail();
  },
  computed: {
    ...mapGetters(["getCurrentLocation", "getUser"])
  },
  methods: {
    ...mapActions(["updateCurrentLocation"]),
    loadOrganizeDetail() {
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
        .then(organizeResponse => {
          organizeResponse = organizeResponse.data;
          console.log(organizeResponse);
          this.organizeImageCover = organizeResponse.organizeImageCover;
          this.organizeName = organizeResponse.organizeName;
          this.email = organizeResponse.email;
          this.website = organizeResponse.website;
        });
    },
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
      console.log(reserveTicket);
      axios
        .post(`${process.env.EVENT_SERVICE}/event/reserve`, reserveTicket, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(reserveTicket => {
          console.log(reserveTicket.data);
          this.reserveTicket = reserveTicket.data;
          this.isViewTicketDetail = !this.isViewTicketDetail;
        })
        .catch(error => {
          console.log("fsdfsdf");
          console.log(error.response);
          this.$swal({
            type: "error",
            title: "Fail to reserve ticket !",
            text: `${
              error.response === undefined
                ? "Please Login first!"
                : error.response
            }`
          });
          this.$router.push("/login");
        });
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
    },
    formatAMPM: function(AMPM) {
      let date = new Date(AMPM);
      const options = {
        hour: "2-digit",
        minute: "2-digit"
      };
      let time = date.toLocaleTimeString("en-US", options);
      return time;
    },
    activateReadMore() {
      this.readMoreActivated = true;
    },
    formatDateForReadable: function(formatDate) {
      const months = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
      ];
      let date = new Date(formatDate);
      formatDate =
        date.getDate() +
        " " +
        months[date.getMonth()] +
        " " +
        date.getFullYear();
      console.log(formatDate);
      return formatDate;
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

 
