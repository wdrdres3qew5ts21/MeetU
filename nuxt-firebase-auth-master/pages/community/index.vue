<template>
  <div>

    <br>
    <center>
      <h2>Let's Get Started !</h2>


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
import { mockCommunityList } from "@/utils/eventJson";
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
      dialog: false,
      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ],

    };
  },
  mounted() {
    this.communityList = mockCommunityList;
  }
};
</script>
<style lang="css" scope>
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

.myImage {
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

