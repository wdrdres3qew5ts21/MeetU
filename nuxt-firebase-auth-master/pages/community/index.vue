<template>
  <div>
    <h1>Popular Communities</h1>
    <br />
    <v-carousel>
      <v-carousel-item sm6 xs2 v-for="(item,i) in carouselsPhoto" :src="item.src" :key="i"></v-carousel-item>
    </v-carousel>
    <br />
    <h2>Recent activity</h2>
    <br />

    <ul>
      <li v-for="(n,i) in 4" :key="i">
        <img v-for="(item,i) in items" :src="item.src" :key="i" />
      </li>
    </ul>

    <center>
      <v-btn class="black--text" block outline color="#341646" depressed large>
        Filter community
        <v-icon>filter_list</v-icon>
      </v-btn>
    </center>
    <br />

    <center>
      <community-card
        v-for="(community, index) in communityList"
        :key="index"
        :communityPictureCover="community.communityPictureCover"
        :communityName="community.communityName"
      ></community-card>
    </center>
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
      <h2>Let's Get Started !</h2>

      <br />

      <nuxt-link :to="`/community/createCommunity`" style="text-decoration-line:none;">
        <v-btn
          class="createCummunityButton white--text"
          depressed
          large
          color="#341646"
        >Create community</v-btn>
      </nuxt-link>
    </center>
  </div>
</template>
<style>
</style>
<script>
import axios from "axios";
import { mapActions } from "vuex";
import CommunityCard from "@/components/communityCard";
import { communityList } from "@/utils/eventJson";
export default {
  components: {
    CommunityCard
  },
  data() {
    return {
      communityList: [],
      items: [
        {
          src: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
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
            "https://d1.awsstatic.com/Marketplace/cases/600x400_ClubAutomation_Logo.e312129c6220eb729d9c7b7912be2ee59a108421.png"
        },
        {
          src:
            "https://eventbrowse.com/wp-content/uploads/2018/11/DoG1Z3FXUAEfR3N.jpg"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/bangkok-block-party-2019/bangkok-block-party-2019-event-banner.jpg?la=th-TH&hash=FE90C817F1C953E5DCB6834242EE517801037D28"
        }
      ]
    };
  },
  mounted() {
    this.communityList = communityList;
  }
};
</script>
<style lang="css">
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";
.v-carousel {
  height: 300px !important;
}

.createCummunityButton {
  font-weight: bold;
  margin-bottom: 10%;
}

/* .avatars {
  text-align: left;  
  padding-left: 25px; 
}
.avatar img {
  border-radius: 50%;
  position: relative;
  left: -5px;
  margin-left: -20px;
  z-index: 1;
} */

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

li {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: inline-block;
  position: relative;
  -webkit-transition: 0.2s ease;
  transition: 0.2s ease;
}

img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: inline-block;
  position: relative;
  box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.2);
  -webkit-transition: 0.2s ease;
  transition: 0.2s ease;
}

li:nth-child(n + 2) {
  margin-left: -50px;
}

ul:hover li:nth-child(n + 2) {
  margin-left: -10px;
}
</style>

