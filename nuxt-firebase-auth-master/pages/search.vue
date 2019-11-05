<template >
  <!-- <transition name="slide"> -->
  <div>
    <!-- <transition name="router-anim" enter-active-class="animated slideInRight"> -->
    <v-layout row wrap>
      <v-flex xs12>
        <!-- <v-card class="mx-auto" elevation="0" outlined width="100%"> -->
        <div>
          <v-layout>
            <v-dialog
              v-model="dialog"
              fullscreen
              hide-overlay
              transition="dialog-bottom-transition"
            >
              <template v-slot:activator="{ on }">
                <v-btn flat icon color="#341646" v-on="on">
                  <v-icon>filter_list</v-icon>
                </v-btn>
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

                  <v-container grid-list-md>
                    <v-layout wrap>
                      <v-icon color="primary">description</v-icon>Search by Detail
                      <v-flex xs12>
                        <v-text-field
                          class="questrial no-top-padding"
                          height="20px"
                          placeholder="Search..."
                          v-model="search"
                          @keyup.enter="searchEventByFilter()"
                        ></v-text-field>
                      </v-flex>

                      <v-flex xs12>
                        <v-icon color="primary">category</v-icon>Filter by category
                      </v-flex>

                      <v-layout class="mb-4">
                        <v-autocomplete
                          v-model="selectedCategoryList"
                          :items="categoryList"
                          chips
                          label=" Search by Category"
                          color="blue-grey lighten-2"
                          item-text="categoryLabel"
                          item-value="categoryName"
                          multiple
                        >
                          <template v-slot:selection="data">
                            <v-chip :selected="data.selected">{{ data.item.categoryName}}</v-chip>
                          </template>
                        </v-autocomplete>
                      </v-layout>

                      <v-flex xs12>
                        <v-icon color="primary">today</v-icon>Filter by Date
                      </v-flex>

                      <v-layout class="mb-4">
                        <v-autocomplete
                          v-model="filterForm.sortByDate"
                          :items="sortDate"
                          chips
                          label=" Sort by Date"
                          color="blue-grey lighten-2"
                          item-text="label"
                          item-value="value"
                        >
                          <template v-slot:selection="data">
                            <v-chip :selected="data.selected">{{ data.item.label}}</v-chip>
                          </template>
                        </v-autocomplete>
                      </v-layout>

                      <v-flex xs12>
                        <v-icon color="primary">room</v-icon>Near your location
                      </v-flex>

                      <v-layout row wrap>
                        <v-flex xs4>
                          <v-select
                            :items="unit"
                            item-value="value"
                            item-text="label"
                            v-model="filterForm.unit"
                            label="unit "
                          ></v-select>
                        </v-flex>
                        <v-flex xs3>
                          <v-text-field
                            label="distance"
                            type="number"
                            v-model="filterForm.distance"
                          ></v-text-field>
                        </v-flex>

                        <v-flex xs5>
                          <v-checkbox v-model="isPopular" label="Popular Event"></v-checkbox>
                        </v-flex>
                      </v-layout>
                    </v-layout>
                  </v-container>
                </v-list>

                <v-btn
                  class="white--text"
                  depressed
                  large
                  block
                  color="#341646"
                  @click="searchEventByFilter()"
                >Search</v-btn>
              </v-card>
            </v-dialog>

            <v-text-field
              class="questrial no-top-padding"
              height="20px"
              placeholder="Search..."
              v-model="search"
              @keyup.enter="searchEventByFilter()"
            ></v-text-field>
            <v-flex class="text-xs-right">
              <v-btn
                class="white--text"
                depressed
                small
                color="#341646"
                ref="searchButton"
                @click="searchEventByFilter()"
              >Search</v-btn>
            </v-flex>
          </v-layout>

          <br />
          <v-layout row wrap>
            <v-chip
              v-for="(categoryChip, index) in selectedCategoryList"
              @click="$router.push(`/event?category=${categoryChip}`)"
              :key="index"
            >
              <strong>{{ categoryChip}}</strong>&nbsp;
            </v-chip>
          </v-layout>
        </div>
        <!-- </v-card> -->
      </v-flex>
      <br />

      <event-list v-if="searchedEventList.length>0" :eventList="searchedEventList"></event-list>
      <center v-else></center>
      <!-- <v-flex xs12 v-else>
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
    <!-- </transition> -->

    <center>
      <div v-if="searchedEventList.length==0">
        <!-- <v-icon large @click="showTest = !showTest"> mdi-calendar-search </v-icon> -->
        <!-- <v-icon large @click="showTest = !showTest">mdi mdi-emoticon-excited-outline spin</v-icon>
        <transition
          name="custom-classes-transition"
          enter-active-class="animated tada"
          leave-active-class="animated tada"
        >
          <p v-if="showTest">You can search event!</p>
          <br />
        

        </transition>-->

        <v-img class="searchImg" v-if="showTest" :src="searchImg"></v-img>

        <!-- <v-flex align-center>
          <v-btn
            class="#341646--text"
            depressed
            color outline="#341646"
            ref="searchButton"
            @click="searchEventByFilter()"
          >Let's started !</v-btn>


        </v-flex>-->
      </div>
    </center>
  </div>
  <!-- </transition> -->
</template>


<script>
import EventList from "@/components/eventList";
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";
import { mockCategoryList } from "@/utils/categoryJson";
import "@mdi/font/css/materialdesignicons.css";

export default {
  name: "search",
  components: { EventList },
  data() {
    return {
      checkbox: true,
      chips: [],
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      search: "",
      sortDate: [
        { label: "Recently", value: "desc" },
        { label: "Old date", value: "asc" }
      ],
      popular: ["Most popular", ""],
      unit: [
        {
          label: "meter",
          value: "m"
        },
        {
          label: "kilometer",
          value: "km"
        }
      ],
      isPopular: false,
      isRecently: false,
      isShowEventTag: false,
      categoryList: [],
      searchedEventList: [],
      selectedCategoryList: [],
      filterForm: {
        distance: "",
        sortByDate: "",
        sortByPopular: "",
        categorySelected: [],
        unit: ""
      },
      showTest: true,
      searchImg: require("@/assets/searchImg.png")
    };
  },
  watch: {
    selectedCategoryList(categorySelected) {
      if (categorySelected.length > 3) {
        this.selectedCategoryList.shift();
      }
    }
  },
  computed: {
    ...mapGetters(["getCategory"])
  },
  mounted() {
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
      console.log(item);
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    updateCategoryFilter: async function(category) {
      console.log(JSON.parse(JSON.stringify(category)));
      // this.filterForm.categorySelected.push(categoryO)
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

      query += `&isPopularEvent=${this.isPopular}`;

      query += `&sortDate=${this.filterForm.sortByDate}`;

      console.log(query);
      let searchedEventList = await axios.get(query);
      searchedEventList = searchedEventList.data;
      this.searchedEventList = searchedEventList;
      console.log(this.searchedEventList);
      this.dialog = false;
    },
    searchByFilter() {
      console.log(this.filterForm.sortByDate);
      console.log(this.filterForm.distance);
      console.log(this.filterForm.categorySelected);
    }
  }
};
</script>

<style lang="css">
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";
/* 
.page {
  position: fixed;
  width: inherit;
} */
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
  /* background-image: url(../assets/bgCom1.png) !important; */
  /* background-repeat: repeat; */
  /* background-size: 100px; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}

/* @keyframes spin {
    from {
        transform:rotate(0deg);
    }
    to {
        transform:rotate(360deg);
    }
}
.spin {
  animation-name: spin;
  animation-duration: 4000ms;
  animation-iteration-count: infinite;
  animation-timing-function: linear;
} */

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
</style>



