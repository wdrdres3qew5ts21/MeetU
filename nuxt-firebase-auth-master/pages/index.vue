<template>
  <div>
    <!-- <transition name="router-anim"  leave-active-class="animated slideOutLeft"> -->
    <v-container class="bg">
      <v-carousel class="mycarousel">
        <v-carousel-item sm6 xs2 v-for="(item,i) in carouselsPhoto" :src="item.src" :key="i"></v-carousel-item>
      </v-carousel>
      <br />
      <br />
      <nuxt-link to="/selectGenres">
        <h3>Setting Recommedation</h3>
      </nuxt-link>
      <!-- Event List -->
      <div v-if="isLogin">
        <h1>Recomended Event</h1>
        <client-only>
          <carousel :perPage="1" :paginationEnabled="false">
            <slide v-for="(event, index) in recentlyEventList" :key="index">
              <event-card :event="event"></event-card>
            </slide>
          </carousel>
        </client-only>
        <!-- <event-list :eventList="recentlyEventList" link="/event"></event-list> -->
        <br />
      </div>

      <h1>Popular Event</h1>
      <carousel :perPage="1" :paginationEnabled="false">
        <slide v-for="(event, index) in recentlyEventList" :key="index">
          <event-card :event="event"></event-card>
        </slide>
      </carousel>
      <!-- <event-list :eventList="recentlyEventList" link="/event"></event-list> -->
      <br />

      <h1>New Event</h1>
      <carousel :perPage="1" :paginationEnabled="false">
        <slide v-for="(event, index) in recentlyEventList" :key="index">
          <event-card :event="event"></event-card>
        </slide>
      </carousel>
      <!-- <event-list :eventList="recentlyEventList" link="/event"></event-list> -->
      <br />
      <!-- Community -->
      <h1>Community</h1>
      <community-card
        v-for="(community, index) in communityList"
        :key="index"
        :communityPictureCover="community.communityPictureCover"
        :communityName="community.communityName"
      ></community-card>
      <br />
      <br />
      <center>
        <nuxt-link :to="``" style="text-decoration-line:none;">
          <v-btn class="black--text" outline color="#341646" depressed large>View more</v-btn>
        </nuxt-link>
      </center>
      <br />
      <br />
      <center>
        <h2 class="upgradeToOrganizer">
          Use MeetU for your
          <br />next events & activities.
        </h2>
        <br />
        <nuxt-link :to="`/becomeOrganizer?`" style="text-decoration-line:none;">
          <v-btn
            class="upgradeToOrganizerButton white--text"
            depressed
            large
            color="#341646"
          >Become an Organizer</v-btn>
        </nuxt-link>
      </center>
    </v-container>
  </div>
  <!-- </transition> -->
</template>

<script>
import Carousel from "vue-carousel/src/Carousel.vue";
import Slide from "vue-carousel/src/Slide.vue";
import { mapGetters } from "vuex";
//import CarouselCard from '../components/CarouselCard.vue';
import axios from "axios";
import { mapActions } from "vuex";
import EventList from "@/components/eventList";
import CommunityCard from "@/components/communityCard";
import EventCard from "@/components/eventCard";
import { isLogin } from "@/utils/loginVerify";
import {
  mockCarouselsPhoto,
  mockCommunityList,
  mockPopularEventList
} from "@/utils/eventJson";
import { error } from "util";

export default {
  components: {
    Carousel,
    Slide,
    EventList,
    CommunityCard,
    EventCard
    //CarouselCard
  },
  data() {
    return {
      isLogin: false,
      carouselsPhoto: [],
      recommendedEventList: [],
      recentlyEventList: [],
      popularEventList: [],
      communityList: [],
      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ]
    };
  },
  mounted() {
    this.carouselsPhoto = mockCarouselsPhoto;
    this.popularEventList = mockPopularEventList;
    this.communityList = mockCommunityList;
    this.isLogin = isLogin();
    this.getRecentlyEvent();
    this.getArtsEvent();
    this.getRecommendEvent();
    console.log(this.getEventByTags("art"));
    console.log(this.getEventByTags("book"));
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    ...mapActions(["getEventByTags"]),
    getRecommendEvent: async function() {
      let recommendEventList = await axios.post(
        `${process.env.EVENT_SERVICE}/events/recommend/persona`,
        this.$store.getters.mockGetUser
      );
      console.log("-- recommend ---");
      console.log(recommendEventList.data);
      this.recommendedEventList = recommendEventList.data;
    },
    getPopularEvent: async function() {
      await axios
        .get(
          `${process.env.EVENT_SERVICE}/events?isPopular=true&contentPerPage=${concentPerPage}`
        )
        .then(popularEventList => {
          this.popularEventList = popularEventList.data;
          console.log(this.recentlyEventList);
        })
        .catch(error => {
          this.popularEventList = mockPopularEventList;
        });
    },
    getRecentlyEvent: async function() {
      let concentPerPage = 3;
      let recentlyEventList = await axios
        .get(
          `${process.env.EVENT_SERVICE}/events?isRecently=true&contentPerPage=${concentPerPage}`
        )
        .then(recentlyEventList => {
          this.recentlyEventList = recentlyEventList.data;
          console.log(this.recentlyEventList);
        })
        .catch(error => {
          this.recentlyEventList = mockPopularEventList;
        });

      //      console.log(this.recentlyEventList)
    },
    getArtsEvent: async function() {
      let concentPerPage = 3;
      let artsEventList = await axios(`${process.env.EVENT_SERVICE}/events?isRecently=true
      &contentPerPage=${concentPerPage}
      &eventTags=art`);
      artsEventList = artsEventList.data;
      this.artsEventList = artsEventList;
    },
    createArt: function() {
      for (let i = 0; i < this.artsEvent.length; i++) {
        console.log(this.artsEvent[i]);
        axios.post(`${process.env.EVENT_SERVICE}/event`, {
          eventPictureCover: this.artsEvent[i].eventPictureCover,
          eventName: this.artsEvent[i].eventName,
          eventTags: ["Art"]
        });
      }
    },
    createBook: function() {
      for (let i = 0; i < this.bookEvent.length; i++) {
        console.log(this.bookEvent[i]);
        axios.post(`${process.env.EVENT_SERVICE}/event`, {
          eventPictureCover: this.bookEvent[i].eventPictureCover,
          eventName: this.bookEvent[i].eventName,
          eventTags: ["Book"]
        });
      }
    }
  }
};
</script>

<style lang="css" >
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";
/* .fake {
  background: blue;
}
.wrapper {
  margin-top: -7.5em;
}
.flexWrapper {
  display: flex;
  justify-content: center;
  flex-flow: row wrap;

}
/* .container {
  height: calc(100vh - 100px);
  align-items: center;
  align-content: center;
  display: flex;
  justify-content: center;
} */

.v-content {
  max-width: 100%;
  background-color: #eeeeee;
}

.flex-container {
  display: flex;
  flex-wrap: wrap;
}
.flex-container::after {
  content: "";
  width: 100%;
}
.flex-item:last-child {
  /* or `:nth-child(n + 4)` */
  order: 1;
}

.linkEvent {
  text-decoration-line: none;
}

.eventName {
  color: #341646;
}
/* .eventName:hover {
  color: #fc5577;
} */

.eventDate {
  margin-top: -10%;
  color: #341646;
}

.joinButton {
  margin-top: -5%;
  margin-bottom: 30%;
}

/* .wrapper{
  position: absolute;
  top: 20%;
  left: 100%;
} */

/*  
.ribbon{
  position: absolute;
}
 

.ribbon-4{
  width: 350px;
  height: 50px;
  background: #21759a;
  top: 150px;
  right: -10px;
} */
/*  
.ribbon-4:before{
  content: "";
  position: absolute;
  top: -10px;
  right: 0;
  border-left: 5px solid #14455b;
  border-right: 5px solid transparent;
  border-top: 5px solid transparent;
  border-bottom: 5px solid #14455b;
} */

/* .ribbon-4:after{
  content: "";
  position: absolute;
  top: 0px;
  left: 0px;
  border-left: 25px solid #fff;
  border-right: 25px solid transparent;
  border-top: 25px solid transparent;
  border-bottom: 25px solid transparent;
} */

.mycarousel {
  height: 250px !important;
}

/* .v-carousel {
  height: 300px !important;
} */

.upgradeToOrganizer {
  margin-top: 5%;
}

.upgradeToOrganizerButton {
  font-weight: bold;
  margin-bottom: 10%;
}

.page {
  position: fixed;
  width: inherit;
}
</style>


