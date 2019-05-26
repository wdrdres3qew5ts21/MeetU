<template>
  <transition name="router-anim" enter-active-class="animated slideInRight">
    <div class="text-xs-center">
      <v-flex justify-content: left>
        <v-text-field placeholder="Search..." v-model="search" @keyup.enter="searchEventByFilter()"></v-text-field>
      </v-flex>
      <v-flex justify-start>
        <v-btn
          class="white--text"
          :round="true"
          depressed
          color="#341646"
          ref="searchButton"
          @click="searchEventByFilter()"
        >Search</v-btn>
      </v-flex>

      <br>

      <v-flex justify-space-around>
        <div class="text-xs-left">
          <v-chip
            color="#fc5577"
            :round="true"
            text-color="white"
            @click="isShowEventTag=!isShowEventTag"
          >
            <v-icon left color="white">label</v-icon>Tags
          </v-chip>
          <div v-if="isShowEventTag">
            <v-chip close v-for="category in categoryList" :key="category">{{category}}</v-chip>
          </div>
        </div>
        <!-- <v-chip color="grey" text-color="white">Primary</v-chip>

        <v-chip color="grey" text-color="white">Secondary</v-chip>

        <v-chip color="grey" text-color="white">Colored Chip</v-chip>

        <v-chip color="grey" text-color="white">Colored Chip</v-chip>-->
      </v-flex>
      <h1>Founded Event</h1>
      <br>
      <v-layout>
        <v-layout row wrap>
          <v-flex v-for="event in searchedEventList" :key="event.eventId" xs4>
            <v-card flat tile>
              <v-img img v-bind:src="event.eventPictureCover" max-height="230px"></v-img>
              <v-card-text>{{event.eventName}}</v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-layout>

      <!-- <v-btn
            color="#fc5577"
            
            :round="true"
            text-color="white"
            @click="findEventInArea()">
            Click to search nearby event for {{areaOfEvent}}
            
      </v-btn>-->
    </div>
  </transition>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  data() {
    return {
      search: "",
      isRecently: false,
      isShowEventTag: false,
      categoryList: [
        "Arts",
        "Book",
        "Business",
        "Beauty",
        "Family",
        "Film",
        "Game",
        "Music",
        "Photography",
        "Social",
        "Technology"
      ],
      selectedTags: [],
      searchedEventList: []
    };
  },
  methods: {
    searchEventByFilter: async function() {
      let searchedEventList = await axios(
        `${process.env.EVENT_SERVICE}/events?isRecently=${
          this.isRecently
        }&eventDetail=${this.search.toLowerCase()}`
      );
      searchedEventList = searchedEventList.data;
      this.searchedEventList = searchedEventList;
      console.log(this.searchedEventList);
    }
  }
};
</script>

<style lang="css">
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";

.page {
  position: fixed;
  width: inherit;
}
</style>
