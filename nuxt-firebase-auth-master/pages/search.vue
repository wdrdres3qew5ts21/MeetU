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
            color="#341646"
            :round="true"
            text-color="white"
            @click="isShowEventTag=!isShowEventTag"
          >
            <v-icon left color="white">label</v-icon>Tags
          </v-chip>
          {{selectedCategoryList}}
          <v-combobox
            v-model="selectedCategoryList"
            :items="categoryList"
            label="I use chips"
            multiple
            chips
          ></v-combobox>
          <!-- <div v-if="isShowEventTag">
            <v-chip @click="selectedTags[i-1] = !selectedTags[i-1]" :selected="selectedTags[i-1]" v-for="(category, i) in categoryList" :key="category">{{category}}</v-chip>
          </div>-->
        </div>
      </v-flex>
      <h1>Founded Event</h1>
      <br>
      <no-ssr>
        <v-layout>
          <v-layout row wrap>
            <v-flex v-for="event in searchedEventList" :key="event.elasticEventId" xs4>
              <nuxt-link :to="`/event/${event.elasticEventId}`">
                <v-card flat tile>
                  <v-img img v-bind:src="event.eventPictureCover" max-height="230px"></v-img>
                  <v-card-text>{{event.eventName}}</v-card-text>
                </v-card>
              </nuxt-link>
            </v-flex>
          </v-layout>
        </v-layout>
      </no-ssr>

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
        "art",
        "book",
        "business",
        "beauty",
        "family",
        "film",
        "game",
        "music",
        "photography",
        "social",
        "technology"
      ],
      searchedEventList: [],
      selectedCategoryList: []
    };
  },
  methods: {
    remove: function(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    searchEventByFilter: async function() {
      let query= `${process.env.EVENT_SERVICE}/events?isRecently=${this.isRecently}&eventDetail=${this.search.toLowerCase()}`;
     if(this.selectedCategoryList.length>0){
       query +=`&eventTags=`
        for(let i =0;i<this.selectedCategoryList.length;i++){
          query +=`${this.selectedCategoryList[i]},`
        }
     }
     console.log(query)
      let searchedEventList = await axios.get(query);
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

.v-content {
  max-width: 100%;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed; */
  /* background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}
</style>
