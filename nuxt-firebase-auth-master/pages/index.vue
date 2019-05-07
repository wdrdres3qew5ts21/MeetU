<template>
  <v-container>
    <v-carousel>
      <v-carousel-item sm6 xs2 v-for="(item,i) in carouselsPhoto" :src="item.src" :key="i"></v-carousel-item>
    </v-carousel>
    <br>
    <h1>Popular Event</h1>
    <br>
    <v-layout>
      <v-layout row wrap>
        <v-flex v-for="event in popularEventList" :key="event.eventId" xs4>
          <v-card flat tile>
            <v-img img v-bind:src="event.eventPhoto" max-height="230px"></v-img>
          </v-card>
        </v-flex>
        <v-flex xs4>
          <v-card flat tile height="230px">
            <v-card-title primary-title>
              <div class="headline">More Event ...</div>
            </v-card-title>
          </v-card>
        </v-flex>
      </v-layout>
    </v-layout>

    <br>
    <h1>Recently Event</h1>
    <br>
    <v-layout>
      <v-layout row wrap>
        <v-flex v-for="event in recentlyAdded" :key="event.eventId" xs4>
          <v-card flat tile>
            <v-img img v-bind:src="event.eventPhoto" max-height="230px"></v-img>
          </v-card>
        </v-flex>
      </v-layout>
    </v-layout>

    <br>
    <h1>Art Event</h1>
    <br>
    <v-layout>
      <v-layout row wrap>
        <v-flex v-for="event in artsEvent" :key="event.eventId" xs4>
          <v-card flat tile>
            <v-img img v-bind:src="event.eventPhoto" max-height="230px"></v-img>
          </v-card>
        </v-flex>
      </v-layout>
    </v-layout>

    <br>
    <h1>Book Event</h1>
    <br>
    <v-layout>
      <v-layout row wrap>
        <v-flex v-for="event in bookEvent" :key="event.eventId" xs4>
          <v-card flat tile>
            <v-img img v-bind:src="event.eventPhoto" max-height="230px"></v-img>
          </v-card>
        </v-flex>
      </v-layout>
    </v-layout>

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
          round="16"
          depressed
          large
          color="#341646"
        >Become an Organizer</v-btn>
      </nuxt-link>
    </center>
    <!-- <h1>Recently Added</h1>

    <br>
    <v-layout row justify-start align-start>
      <v-layout>
        <v-flex
          text-xs-center
          v-for="event in recentlyAdded"
          :key="event.eventId"
          xs6
          :to="{path:'/event',
            query:{eventId:event.eventId}}"
        >
          <img v-bind:src="event.eventPhoto" alt height="150px">
          <nuxt-link :to="`/eventDetail?${event.eventName}`" style="text-decoration-line:none;">
            <p>
              <a class="eventName">{{event.eventName}}</a>
            </p>
          </nuxt-link>

          <p
            class="eventDate"
          >{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
          <nuxt-link :to="`/login?${event.eventId}`" style="text-decoration-line:none;">
            <v-btn class="joinButton" depressed color="success">JOIN</v-btn>
          </nuxt-link>
        </v-flex>
      </v-layout>
    </v-layout>

    <h1>Arts</h1>

    <br>
    <v-layout row justify-start align-start>
      <v-layout>
        <v-flex
          text-xs-center
          v-for="event in artsEvent"
          :key="event.eventId"
          xs6
          :to="{path:'/event',query:{eventId:event.eventId}}"
        >
          <img v-bind:src="event.eventPhoto" alt height="150px">
          <nuxt-link :to="`/eventDetail?${event.eventName}`" style="text-decoration-line:none;">
            <p>
              <a class="eventName">{{event.eventName}}</a>
            </p>
          </nuxt-link>

          <p
            class="eventDate"
          >{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
          <nuxt-link :to="`/login?${event.eventId}`" style="text-decoration-line:none;">
            <v-btn class="joinButton" depressed color="success">JOIN</v-btn>
          </nuxt-link>
        </v-flex>
      </v-layout>
    </v-layout>
    <h1>Book Clubs</h1>

    <br>
    <v-layout row justify-start align-start>
      <v-layout>
        <v-flex
          text-xs-center
          v-for="event in bookEvent"
          :key="event.eventId"
          xs6
          :to="{path:'/event',query:{eventId:event.eventId}}"
        >
          <img v-bind:src="event.eventPhoto" alt height="150px">
          <nuxt-link :to="`/eventDetail?${event.eventName}`" style="text-decoration-line:none;">
            <p>
              <a class="eventName">{{event.eventName}}</a>
            </p>
          </nuxt-link>

          <p
            class="eventDate"
          >{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
          <nuxt-link :to="`/login?${event.eventId}`" style="text-decoration-line:none;">
            <v-btn class="joinButton" depressed color="success">JOIN</v-btn>
          </nuxt-link>
        </v-flex>
      </v-layout>
    </v-layout>

    <center>
      <h2 class="upgradeToOrganizer">
        Use MeetU for your
        <br>next events & activities.
      </h2>

      <nuxt-link :to="`/organizer?`" style="text-decoration-line:none;">
        <v-btn
          class="upgradeToOrganizerButton white--text"
          round="16"
          depressed
          large
          color="#341646"
        >Become an Organizer</v-btn>
      </nuxt-link>
    </center>-->

    <!-- ใช้code จบ -->

    <!-- <div class="flex-container">
        <nuxt-link v-for="event in recentlyAdded" :key="event.eventId" 
              :to="{path:'/event',query:{eventId:event.eventId}}">       


          <v-flex xs12 >
              <h1 style="color: black"> {{$store.state.user}} </h1>
              <div class="flex-item">
                  <div class="mt-5">
                      <img v-bind:src="event.eventPhoto" alt="" height="100px">
                      <p>{{event.eventName}}</p>
                      <p>{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
                    
                  </div>
              </div>

          </v-flex>
    </nuxt-link>-->
    <!-- </div> -->

    <!-- </v-layout> -->
  </v-container>
</template>


<script>
export default {
  components: {},
  data() {
    return {
      popularEventList: [
        {
          eventId: "11111a",
          eventName: "Icecream Social",
          eventPhoto:
            "https://news.cci.fsu.edu/files/2014/03/2014-CCI-SLC-Ice-Cream-Social-Flyer.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11112a",
          eventName: "Charity Auction",
          eventPhoto:
            "http://jenniferhowedesigns.com/wp-content/uploads/2018/06/goodcharityposter.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11113a",
          eventName: "Original Festival",
          eventPhoto:
            "https://i.pinimg.com/originals/0f/04/6f/0f046f654320d387d7608a754f205d1e.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11114a",
          eventName: "Mars Event",
          eventPhoto:
            "https://s3.amazonaws.com/thumbnails.venngage.com/template/70fdebc4-7bb7-49fc-9caf-700f890de92b.png",
          eventDate: new Date(1554653418)
        },
        {
          eventId: "11115a",
          eventName: "Night Market",
          eventPhoto:
            "https://stumbler.co.za/wp-content/uploads/2017/11/26685184_140352653308218_9209494944451264947_o-4.jpg",
          eventDate: new Date(1554653418)
        }
      ],
      recentlyAdded: [
        {
          eventId: "11111b",
          eventName: "Ballon Festival",
          eventPhoto:
            "https://marketplace.canva.com/MADOPmFuuDk/1/0/thumbnail_large/canva-blue-red-balloon-festival-event-poster-MADOPmFuuDk.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11112b",
          eventName: "Annual Sports Fest",
          eventPhoto:
            "https://marketplace.canva.com/MADOPq1rLE0/1/0/thumbnail_large/canva-black-simple-sports-event-poster-MADOPq1rLE0.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11113b",
          eventName: "Will Draw For Food",
          eventPhoto:
            "https://marketplace.canva.com/MADOPkH9JIQ/1/0/thumbnail_large/canva-sketch-food-drawing-event-poster-MADOPkH9JIQ.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11114b",
          eventName: "SPLASH Art Festival",
          eventPhoto:
            "https://marketplace.canva.com/MADOPrc2V5c/1/0/thumbnail_large/canva-blue-green-festival-event-poster-MADOPrc2V5c.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11115b",
          eventName: "Cady At Home",
          eventPhoto:
            "https://marketplace.canva.com/MADI6arDCak/1/0/thumbnail_large/canva-blue-and-red-christmas-event-letter-sized-poster-MADI6arDCak.jpg",
          eventDate: new Date(1554681600)
        }
      ],
      artsEvent: [
        {
          eventId: "11111c",
          eventName: "Art Rising",
          eventPhoto:
            "https://chambermaster.blob.core.windows.net/userfiles/UserFiles/chambers/1397/CMS/ArtRising_Posters6_Final.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11112c",
          eventName: "Live Art&Music",
          eventPhoto:
            "https://s3.amazonaws.com/thumbnails.venngage.com/template/92bd8005-628b-43a5-86fa-106aeadf55e5.png",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11113c",
          eventName: "Wonderland",
          eventPhoto:
            "https://www.ticketbooth.com.au/ticketing/wp-content/uploads/2016/03/1.png",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11114c",
          eventName: "Maintenant",
          eventPhoto:
            "https://www.ticketbooth.com.au/ticketing/wp-content/uploads/2016/03/3.png",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11115c",
          eventName: "CEST LA Fest",
          eventPhoto:
            "https://www.ticketbooth.com.au/ticketing/wp-content/uploads/2016/03/25.png",
          eventDate: new Date(1554681600)
        }
      ],

      bookEvent: [
        {
          eventId: "11111d",
          eventName: "Rock Books",
          eventPhoto:
            "https://img-cache.oppcdn.com/img/v1.0/s:10530/t:QkxBTksrVEVYVCtIRVJF/p:12/g:tl/o:2.5/a:50/q:90/1400x720-y8blC3xl.jpg/591x720/cd20a7652eb81337c036685d0651f932.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11112d",
          eventName: "Book Fair",
          eventPhoto:
            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/book-fair-poster-template-bca4ee73213af679660af906a698446f_screen.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11113d",
          eventName: "BOOKFAIR",
          eventPhoto:
            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/book-fair-poster-design-template-3757b0dcb9b98249f42205cb8ea89c4b_screen.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11114d",
          eventName: "Childrens Book",
          eventPhoto:
            "https://www.anikadenise.com/wp-content/uploads/2017/09/princeton-poster-art-final2-650x900-1.jpg",
          eventDate: new Date(1554681600)
        },
        {
          eventId: "11115d",
          eventName: "CMU Book Fair",
          eventPhoto:
            "http://library.cmu.ac.th/prcorner/sites/default/files/img_news/Poster_CMUBookFair25.jpg",
          eventDate: new Date(1554681600)
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

};
</script>



<style scoped>
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
</style>


