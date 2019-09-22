<template>
  <transition name="router-anim" enter-active-class="animated slideInRight">
    <v-container fluid grid-list-md>
      <v-layout row wrap>
        <v-flex xs12>
          <v-card class="mx-auto" elevation="0" outlined width="100%">
            <div class="px-3">
              <v-layout>
                <v-text-field
                  class="questrial no-top-padding"
                  height="44px"
                  rounded
                  placeholder="Search..."
                  v-model="search"
                  @keyup.enter="searchEventByFilter()"
                ></v-text-field>
              </v-layout>
            </div>
          </v-card>
        </v-flex>
        <v-flex xs12>
          <center>
            <v-btn
              class="white--text"
              depressed
              large
              color="#341646"
              ref="searchButton"
              @click="searchEventByFilter()"
            >Search</v-btn>
          </center>
        </v-flex>
        <br />

        <br />

        <event-list v-if="searchedEventList.length>0" :eventList="searchedEventList"></event-list>
        <v-flex xs12 v-else>
          <h3>
            <center>You can search event ;)</center>
          </h3>
        </v-flex>

        <!-- <v-btn
            color="#fc5577"
            
            :round="true"
            text-color="white"
            @click="findEventInArea()">
            Click to search nearby event for {{areaOfEvent}}
            
        </v-btn>-->
      </v-layout>
    </v-container>
  </transition>
</template>

<script>
import EventList from "@/components/eventList";
import { mapGetters } from "vuex";
import axios from "axios";
export default {
  components: { EventList },
  data() {
    return {
      chips: [
        "Programming",
        "Playing video games",
        "Watching movies",
        "Sleeping"
      ],
      items: ["Streaming", "Eating"],
      search: "",
      isRecently: false,
      isShowEventTag: false,
      categoryList: [],
      searchedEventList: [],
      selectedCategoryList: []
    };
  },
  computed: {
    ...mapGetters(["getCategory"])
  },
  mounted() {
    this.categoryList = this.getCategory;
  },
  methods: {
    remove: function(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    searchEventByFilter: async function() {
      let query = `${process.env.EVENT_SERVICE}/events?isRecently=${
        this.isRecently
      }&eventDetail=${this.search.toLowerCase()}`;
      if (this.selectedCategoryList.length > 0) {
        query += `&eventTags=`;
        for (let i = 0; i < this.selectedCategoryList.length; i++) {
          query += `${this.selectedCategoryList[i]},`;
        }
      }
      console.log(query);
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
