<template>
  <!-- <transition name="slide"> -->
  <div>
    <!-- <transition name="router-anim"  leave-active-class="animated slideOutLeft"> -->
    <v-carousel class="mycarousel" prev-icon="mdi-arrow-left" next-icon="mdi-arrow-right">
      <nuxt-link
        :to="`/event/${recentlyEvent.elasticEventId}`"
        v-for="(recentlyEvent,i) in recentlyEventList"
        :key="i"
      >
        <v-carousel-item :src="recentlyEvent.eventPictureCover"></v-carousel-item>
      </nuxt-link>
    </v-carousel>
    <v-container class="bg">
      <!-- Event List -->
      <div v-show="getUser.uid!=null">
        <!-- <nuxt-link to="/selectGenres">
          <h3>Setting Recommedation</h3>
        </nuxt-link>-->
        <br />
        <h2>Recommended Event</h2>
        <client-only>
          <carousel :perPage="1" :paginationEnabled="false">
            <slide v-for="(event, index) in recommendedEventList" :key="index">
              <nuxt-link :to="`/event/${event.elasticEventId}`">
                <event-card :event="event" :location="event.location" :badge="event.badge">
                </event-card>
              </nuxt-link>
            </slide>
            <slide>
              <v-flex  xs12 sm6 offset-sm>
                <nuxt-link :to="`/search?`">
                  <v-card class="BackgroundImg" width="400px" height="295px">
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                  </v-card>
                </nuxt-link>
              </v-flex>
            </slide>
          </carousel>
        </client-only>
        <br />
      </div>

      <h2>Popular Event</h2>
      <client-only>
        <carousel :perPage="1" :paginationEnabled="false">
          <slide v-for="(event, index) in popularEventList" :key="index">
            <nuxt-link :to="`/event/${event.elasticEventId}`">
              <event-card :event="event" :location="event.location" :badge="event.badge"></event-card>
            </nuxt-link>
          </slide>
          <slide>
            <v-flex 3 xs12 sm6 offset-sm>
              <nuxt-link :to="`/search?filter=isPopular`">
                <v-card class="BackgroundImg" width="400px" height="295px">
                  <br />
                  <br />
                  <br />
                  <br />
                  <br />
                  <!-- <center>
                    <v-icon style="color:#fff" large>add_circle_outline</v-icon>
                  </center>
                  <center>
                    <h2 style="color:#fff">View More</h2>
                  </center> -->
                </v-card>
              </nuxt-link>
            </v-flex>
          </slide>
        </carousel>
      </client-only>
      <!-- <event-list :eventList="recentlyEventList" link="/event"></event-list> -->
      <br />

      <h2>New Event</h2>
      <client-only>
        <carousel :perPage="1" :paginationEnabled="false">
          <slide v-for="(event, index) in recentlyEventList" :key="index">
            <nuxt-link :to="`/event/${event.elasticEventId}`">
              <event-card :event="event" :location="event.location" :badge="event.badge"></event-card>
            </nuxt-link>
          </slide>
          <slide>
            <v-flex 3 xs12 sm6 offset-sm>
              <nuxt-link :to="`/search?filter=isRecently`">
                <v-card class="BackgroundImg" width="400px" height="295px">
                  <br />
                  <br />
                  <br />
                  <br />
                  <br />
                  <!-- <center>
                    <v-icon style="color:#fff" large>add_circle_outline</v-icon>
                  </center>
                  <center>
                    <h2 style="color:#fff">View More</h2>
                  </center> -->
                </v-card>
              </nuxt-link>
            </v-flex>
          </slide>
        </carousel>
      </client-only>
      <!-- <event-list :eventList="recentlyEventList" link="/event"></event-list> -->
      <br />
      <!-- Community -->
      <h2>Community</h2>
      <nuxt-link :to="`/community`">
        <community-card
          v-for="(community, index) in communityList"
          :key="index"
          :communityPictureCover="community.communityPictureCover"
          :communityName="community.communityName"
        ></community-card>
      </nuxt-link>
      <br />
      <br />
      <center>
        <nuxt-link :to="`/community?all=true`" style="text-decoration-line:none;">
          <v-btn class="black--text" outline color="#341646" depressed large>View more</v-btn>
        </nuxt-link>
      </center>
      <br />
      <br />
      <center>
        <h2 class="upgradeToOrganizer">
          Use MeetU for your
          <br />next events &amp; activities.
        </h2>
        <br />
        <nuxt-link to="/organize/becomeOrganizer" style="text-decoration-line:none;">
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
import "@mdi/font/css/materialdesignicons.css";
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
    this.getPopularEvent();
    if (this.isLogin) {
      this.getRecommendEvent();
    }
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    ...mapActions(["getEventByTags"]),
    getRecommendEvent: async function() {
      let recommendEventList = await axios.post(
        `${process.env.EVENT_SERVICE}/events/recommend/persona`,
        {
          uid: this.getUser.uid
        }
      );
      console.log("-- recommend ---");
      console.log(recommendEventList.data);

      this.recommendedEventList = recommendEventList.data.body;
    },
    getPopularEvent: async function() {
      let concentPerPage = 6;
      await axios
        .get(
          `${process.env.EVENT_SERVICE}/events?isPopularEvent=true&contentPerPage=${concentPerPage}`
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
      let concentPerPage = 6;
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

/* .testBlur-img{
   filter: blur(8px);
  -webkit-filter: blur(8px);
} */

.BackgroundImg {
  background-color: #eeeeee !important;
  /* filter: blur(8px);
  -webkit-filter: blur(8px); */
  /* background-image: linear-gradient(#ffecd2, #fcb69f); */
  /* border: none !important; */
  background-image: url(../assets/default/vm1.png);
  background-size: 350px;
  background-repeat:no-repeat;
  background-position:center top;
  opacity: 0.7;
  background-position-y: -100%;
  background-position-x: -20%;
  filter: alpha(opacity=20);
}

/* .slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter,.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
} */

.v-carousel:prev-icon{
  color: yellow;
}
</style>


