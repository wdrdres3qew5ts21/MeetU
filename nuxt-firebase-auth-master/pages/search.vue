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
        <!-- <v-list-group>
          <v-list-tile slot="activator">
            <v-list-tile>
              <v-icon class="icon">category</v-icon>
            </v-list-tile>
            <v-list-tile-content class="categoryIcon" style="color:#341646;">Category</v-list-tile-content>
          </v-list-tile>
          <v-list-tile v-for="(category, i) in categoryList" :key="i" ripple>
            <v-list-tile-action></v-list-tile-action>
            <v-list-tile-content>
              <nuxt-link class="categoryLink" :to="`/event?category=${category.categoryName}`">
                <v-list-tile-title>{{ category.categoryLabel }}</v-list-tile-title>
              </nuxt-link>
            </v-list-tile-content>
          </v-list-tile>
        </v-list-group>
        -->

        <!-- <v-card   v-for="(category, i) in categoryList" :key="i" ripple>
            <v-list-tile-title>{{ category.categoryLabel }}</v-list-tile-title>
        </v-card>
        -->

        <br />
        <v-container>
          <v-layout row justify-center>
            <v-dialog
              v-model="dialog"
              fullscreen
              hide-overlay
              transition="dialog-bottom-transition"
            >
              <template v-slot:activator="{ on }">
                <v-btn color="primary" dark v-on="on">Filter</v-btn>
              </template>
              <v-card>
                <v-toolbar dark color="primary">
                  <v-btn icon dark @click="dialog = false">
                    <v-icon>navigate_before</v-icon>
                  </v-btn>
                  <v-toolbar-title>Filter</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>

                <v-list three-line subheader>
                  <br />

                  <v-subheader>Filter by category</v-subheader>
                  <v-layout class="mb-4">
                    <v-combobox
                      :items="categoryList"
                      label="Category"
                      chips
                      clearable
                      solo
                      multiple
                      sm6
                      xs2
                    >
                      <template v-slot:selection="data">
                        <v-chip :selected="data.selected" close @input="remove(data.item)">
                          <strong>{{ data.item}}</strong>&nbsp;
                        </v-chip>
                      </template>
                    </v-combobox>
                  </v-layout>
                  <v-subheader>Sort by Date</v-subheader>
                  <v-layout class="mb-4">
                    <v-select :items="items" label="Last Update" solo></v-select>
                  </v-layout>

                  <v-subheader>Near your location</v-subheader>
                  <v-text-field label="Distance ... Kilometer"></v-text-field>
                </v-list>

                <center>
                  <v-btn
                    class="white--text"
                    depressed
                    large
                    color="#341646"
                    @click="dialog = false"
                  >Search</v-btn>
                </center>
              </v-card>
            </v-dialog>
          </v-layout>
        </v-container>

        <br />

        <!-- <event-list v-if="searchedEventList.length>0" :eventList="searchedEventList"></event-list>
        <v-flex xs12 v-else>
          <h3>
            <center>You can search event ;)</center>
          </h3>
        </v-flex>-->

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

import { mapActions, mapGetters } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";
import { mockCategoryList } from "@/utils/categoryJson";

export default {
  components: { EventList },
  data() {
    return {
      chips: [],
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      items: ["Last Update", "First Update"],
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
    // this.categoryList = this.getCategory;
    this.loadCategoryList();
  },
  methods: {
    ...mapActions(["autoSignIn", "setCategory"]),
    loadCategoryList() {
      axios
        .get(`${process.env.EVENT_SERVICE}/category`)
        .then(categoryList => {
          this.categoryList = categoryList.data;
          this.setCategory(this.categoryList);
        })
        .catch(error => {
          this.categoryList = mockCategoryList;
        });
    },
    onItemClick(event, itemsCategory) {
      if (event) {
        this.selected = itemsCategory;
      }
    },
    logout: function(e) {
      this.$store.dispatch("signOut").then(() => {
        alert("logged out!");
        this.$router.push("/");
      });
    },
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
