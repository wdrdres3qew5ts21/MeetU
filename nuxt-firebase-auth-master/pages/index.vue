<template>
  <div>
    <!-- <transition name="router-anim"  leave-active-class="animated slideOutLeft"> -->
    <v-container class="bg">
      <v-carousel>
        <v-carousel-item sm6 xs2 v-for="(item,i) in carouselsPhoto" :src="item.src" :key="i"></v-carousel-item>
      </v-carousel>

      <br>
      <br>
      <nuxt-link to="/selectGenres"><h3>Setting Recommedation</h3></nuxt-link>
      <h1>Recomended Event for {{$store.getters.mockGetUser.firstname}}</h1>
      <br>
      <event-list :eventList="recommendedEventList"></event-list>

      <br>
      <h1>Popular Event</h1>
      <br>
      <event-list :eventList="popularEventList"></event-list>

      <br>
      <h1>Recently Event</h1>
      <br>
      <event-list :eventList="recentlyEventList"></event-list>

      <br>
      <h1>Art Event</h1>
      <event-list :eventList="artsEventList"></event-list>

      <br>
      <h1>Book Event</h1>
      <br>
      <event-list :eventList="bookEventList"></event-list>

      <br>
      <br>
      <center>
        <h2 class="upgradeToOrganizer">
          Use MeetU for your
          <br>next events & activities.
        </h2>

        <br>

        <nuxt-link :to="`/organizer?`" style="text-decoration-line:none;">
          <v-btn
            class="upgradeToOrganizerButton white--text"
            :round="true"
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
import EventList from "@/components/EventList";
//import CarouselCard from '../components/CarouselCard.vue';
import axios from "axios";
import { mapActions } from "vuex";
export default {
  components: {
    EventList
    //CarouselCard
  },
  data() {
    return {
      recommendedEventList: [],
      recentlyEventList: [],
      bookEventList: [],
      artsEventList: [],
      popularEventList: [
        {
          eventId: "11111a",
          eventName: "Icecream Social",
          eventPictureCover:
            "https://news.cci.fsu.edu/files/2014/03/2014-CCI-SLC-Ice-Cream-Social-Flyer.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11112a",
          eventName: "Charity Auction",
          eventPictureCover:
            "http://jenniferhowedesigns.com/wp-content/uploads/2018/06/goodcharityposter.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11113a",
          eventName: "Original Festival",
          eventPictureCover:
            "https://i.pinimg.com/originals/0f/04/6f/0f046f654320d387d7608a754f205d1e.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11114a",
          eventName: "Mars Event",
          eventPictureCover:
            "https://s3.amazonaws.com/thumbnails.venngage.com/template/70fdebc4-7bb7-49fc-9caf-700f890de92b.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11115a",
          eventName: "Night Market",
          eventPictureCover:
            "https://stumbler.co.za/wp-content/uploads/2017/11/26685184_140352653308218_9209494944451264947_o-4.jpg",
          eventDate: new Date(1554653418)
        }
      ],
      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ],

      carouselsPhoto: [
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/bangkok-block-party-2019/bangkok-block-party-2019-event-banner.jpg?la=th-TH&hash=FE90C817F1C953E5DCB6834242EE517801037D28"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/atlantiswaterfestival2019/atlantiswaterfestival2019-banner.jpg?la=th-TH&hash=5346DE429E1704AF25E6CCBC7FDC25CA56AF09ADg"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/ed-sheeran-divide-world-tour-2019/ed-sheeran-divide-world-tour-2019-event-rabbit-today-banner.jpg?la=th-TH&hash=C6B48BA5F7F79606DCE4B88DDE9A51EE9FE25A1C"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/siamsongkranmusicfestival/siamsongkranmusicfestival-banner.jpg?la=th-TH&hash=6DBCBED42B97294F2E6AAE635F84BCE455518957"
        }
      ]
    };
  },
  mounted() {
    this.artsEvent = [];
    //this.recentlyEvent = []
    this.getRecentlyEvent();
    this.getArtsEvent();
    this.getRecommendEvent();
    console.log(this.getEventByTags("art"));
    console.log(this.getEventByTags("book"));
  },
  methods: {
    ...mapActions(["getEventByTags"]),
    getRecommendEvent: async function(){
      let recommendEventList = await axios.post(`${
        process.env.EVENT_SERVICE
      }/events/recommend/persona`,this.$store.getters.mockGetUser);
      console.log('-- rec ---')
      console.log(recommendEventList.data)
      this.recommendedEventList = recommendEventList.data
    },
    getRecentlyEvent: async function() {
      let concentPerPage = 3;
      let recentlyEventList = await axios(`${
        process.env.EVENT_SERVICE
      }/events?isRecently=true
      &contentPerPage=${concentPerPage}`);
      recentlyEventList = recentlyEventList.data;
      this.recentlyEventList = recentlyEventList;
//      console.log(this.recentlyEventList)
    },
    getArtsEvent: async function() {
      let concentPerPage = 3;
      let artsEventList = await axios(`${
        process.env.EVENT_SERVICE
      }/events?isRecently=true
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
  background-image: url(../assets/bg.png) !important;
  /* background-repeat: repeat; */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent;
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
  color: #000;
}
.eventName:hover {
  color: #fc5577;
}

.eventDate {
  margin-top: -10%;
  color: #000;
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

.v-carousel {
  height: 300px !important;
}

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


