<template>
  <div> 
    <br>
    <h1>{{categoryLabel}}</h1>
    <event-list v-if="eventList.length>0" :eventList="eventList"></event-list>
    <center v-else>
      <br><br>
      <v-img class="notFoundImg" v-if="showTest" :src="notFoundImg"></v-img>
</center>
    <!-- <v-layout row wrap>
      <v-text-field
        class="questrial no-top-padding"
        height="20px"
        placeholder="Search an event"
        v-model="search"
      ></v-text-field>
      <v-flex class="text-xs-right">
        <v-btn class="white--text" depressed small color="#341646" ref="searchButton">Search</v-btn>
      </v-flex>
    </v-layout> -->
    <!-- <input type="text" v-model="areaOfEvent" placeholder="input area" /> -->
    <center>
      <!-- <h2>Search an event</h2> -->
      <!-- <p>or</p> -->


      <!-- <v-btn
        color="primary"
        @click="findEventInArea()"
      >Click to search nearby event for {{areaOfEvent}}</v-btn> -->

      <!-- <v-flex class="text-xs-right">
              <v-btn
                class="white--text"
                depressed
                small
                color="#341646"
                ref="searchButton"
                @click="searchEventByFilter()"
              >Search</v-btn>
            </v-flex> -->




    </center>
  </div>
</template>





<script>
import axios from "axios";
import EventList from "@/components/eventList";
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  components: {
    EventList
  },
  watch: {
    "$route.query.category"() {
      this.loadCategory();
      this.findAllEvent();
    }
  },
  data() {
    return {
      categoryLabel: "",
      eventList: [],
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
      ],

      // password: 'Password',
      show: false,
      // message: 'Search ..',
      marker: true,
      iconIndex: 0,
      icons: ["mdi-emoticon"],
      showTest: true,
      notFoundImg: require("@/assets/default/e1.png")
    
    };
  },
  mounted() {
    this.getLocation();
  },
  computed: {
    ...mapGetters(["getCurrentLocation", "getUser", "getCategory"]),
    icon() {
      return this.icons[this.iconIndex];
    }
  },
  mounted() {
    this.findAllEvent();
    this.loadCategory();
  },
  methods: {
    toggleMarker() {
      this.marker = !this.marker;
    },
    sendMessage() {
      this.resetIcon();
      this.clearMessage();
    },
    clearMessage() {
      this.message = "";
    },
    resetIcon() {
      this.iconIndex = 0;
    },
    changeIcon() {
      this.iconIndex === this.icons.length - 1
        ? (this.iconIndex = 0)
        : this.iconIndex++;
    },

    ...mapActions(["updateCurrentLocation"]),
    loadCategory: function() {
      console.log(this.getCategory);
      for (let i = 0; i < this.getCategory.length; i++) {
        if (this.$route.query.category === this.getCategory[i].categoryName) {
          this.categoryLabel = this.getCategory[i].categoryLabel;
          return;
        }
      }
    },
    findAllEvent: async function() {
      let category = this.$route.query.category;
      axios
        .get(`${process.env.EVENT_SERVICE}/events?eventTags=${category}`)
        .then(eventList => {
          this.eventList = eventList.data;
        })
        .catch(error => {});
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
    },

    //     searchByFilter() {
    //   console.log(this.filterForm.sortByDate);
    //   console.log(this.filterForm.distance);
    //   console.log(this.filterForm.categorySelected);
    // }
  }
};
</script>

 <style lang="css">
.container.fluid {
  max-width: 100%;
  /* background-image: url(~assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}

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

