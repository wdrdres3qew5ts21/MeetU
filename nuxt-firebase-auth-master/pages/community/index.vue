<template>
  <div>
    <!-- <br /> -->
    <center>
      <!-- <h2 id="top">Let's Find Community !</h2> -->
      <v-container>
        <v-layout row wrap>
          <v-flex xs12>
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
                          <v-icon color="primary">description</v-icon>Community Name
                          <v-flex xs12>
                            <v-text-field
                              class="questrial no-top-padding"
                              height="20px"
                              placeholder="Search..."
                              v-model="communityName"
                              @keyup.enter="findMatchingCommunity()"
                            ></v-text-field>
                          </v-flex>
                          <v-flex xs12>
                            <v-icon color="primary">category</v-icon>Filter by category
                          </v-flex>

                          <v-layout class="mb-4">
                            <v-autocomplete
                              v-model="interestTags"
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
                        </v-layout>
                      </v-container>
                    </v-list>

                    <v-btn
                      class="white--text"
                      depressed
                      large
                      block
                      color="#341646"
                      @click="findMatchingCommunity()"
                    >Search</v-btn>
                  </v-card>
                </v-dialog>
                <v-text-field
                  class="questrial no-top-padding"
                  height="20px"
                  placeholder="Search..."
                  v-model="communityName"
                  @keyup.enter="findMatchingCommunity()"
                ></v-text-field>
                <v-flex class="text-xs-right">
                  <v-btn
                    class="white--text"
                    depressed
                    small
                    color="#341646"
                    id="searchButton"
                    @click="findMatchingCommunity()"
                  >Search</v-btn>
                </v-flex>



                
              </v-layout>
              <br>
                  <div v-if="communityList.length==0">
                    <v-img class="findCommunityImg" :src="findCommunityImg"></v-img>
                  </div>
              <v-layout row wrap>
                <v-chip
                  v-for="(categoryChip, index) in interestTags"
                  @click="$router.push(`/event?category=${categoryChip}`)"
                  :key="index"
                >
                  <strong>{{ categoryChip}}</strong>&nbsp;
                </v-chip>

              </v-layout>
            </div>
          </v-flex>
        </v-layout>
      </v-container>
      <!-- content of community list from search -->
      <div v-if="communityList.length > 0">
        <div v-for="(community, index) in communityList" :key="index">
          <nuxt-link :to="`/community/${community.communityId}`">
            <!-- communityForm.communityPictureCover==undefined?defaultImage: communityForm.communityPictureCover  -->
            <community-card
              :communityPictureCover="community.communityPictureCover==undefined?defaultImage: community.communityPictureCover"
              :communityName="community.communityName"
              :interestTags="community.interestTags"
            ></community-card>
          </nuxt-link>
        </div>
        <client-only>
          <infinite-loading spinner="spiral" @infinite="infiniteScroll">
            <div slot="no-results">
              <v-btn color="primary" block @click="$vuetify.goTo('#top')">Go to Top</v-btn>
            </div>
          </infinite-loading>
        </client-only>
      </div>
    </center>
  </div>
</template>
<style>
</style>
<script>
import axios from "axios";
import { mapActions, mapGetters } from "vuex";
import CommunityCard from "@/components/communityCard";
import { mockCommunityList } from "@/utils/eventJson";
export default {
  components: {
    CommunityCard
  },
  data() {
    return {
      page: 0,
      communityList: [],
      dialog: false,
      interestTags: [],
      communityName: "",
      interestTags: [],
      categoryList: [],
      defaultImage: require(`@/assets/default/community.png`),
      findCommunityImg: require(`@/assets/default/findCommunity.png`)
    };
  },
  watch: {
    interestTags(interestTags) {
      if (interestTags.length > 3) {
        this.interestTags.shift();
      }
    }
  },
  mounted() {
    this.loadCategoryList();
    let allQuery = this.$route.query.all;
    console.log(this.$route.query);
    if (allQuery == "true") {
      this.findMatchingCommunity();
    }
  },
  methods: {
    ...mapActions(["autoSignIn", "setCategory", "setBadgeDetail"]),
    infiniteScroll($state) {
      setTimeout(() => {
        this.page++;
        console.log(this.page);
        let interestTags = "";
        if (this.interestTags.length > 0) {
          interestTags = "&interestTags=";
          for (let i = 0; i < this.interestTags.length; i++) {
            interestTags += `${this.interestTags[i]},`;
          }
        }
        axios
          .get(
            `${process.env.COMMUNITY_SERVICE}/communitys?communityName=${this.communityName}${interestTags}&page=${this.page}`
          )
          .then(response => {
            if (response.data.length > 0) {
              response.data.forEach(community =>
                this.communityList.push(community)
              );
              $state.loaded();
            } else {
              $state.complete();
            }
          })
          .catch(err => {
            console.log(err);
          });
      }, 500);
    },
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
    findMatchingCommunity() {
      console.log("matching community");
      let interestTags = "";
      if (this.interestTags.length > 0) {
        interestTags = "&interestTags=";
        for (let i = 0; i < this.interestTags.length; i++) {
          interestTags += `${this.interestTags[i]},`;
        }
      }
      axios
        .get(
          `${process.env.COMMUNITY_SERVICE}/communitys?communityName=${this.communityName}${interestTags}`
        )
        .then(communityListResponse => {
          this.communityList = communityListResponse.data.content;
          console.log(communityList.data);
        })
        .catch(error => {});
      this.dialog = false;
    },
    remove: function(item) {
      console.log(item);
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    updateCategoryFilter: async function(category) {
      console.log(JSON.parse(JSON.stringify(category)));
      // this.filterForm.categorySelected.push(categoryO)
    }
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

