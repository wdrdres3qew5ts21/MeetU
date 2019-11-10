<template>
  <div>
    <center>
      <h2>Ranking Board</h2>
    </center>
    <v-form>
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
                          <v-flex xs12>
                            <v-icon color="primary">category</v-icon>Filter by category
                          </v-flex>

                          <v-layout class="mb-4">
                            <v-autocomplete
                              v-model="badgeTags"
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
                      @click="findMatchingBadge()"
                    >Search</v-btn>
                  </v-card>
                </v-dialog>
                <v-text-field
                  class="questrial no-top-padding"
                  height="20px"
                  placeholder="Search..."
                  v-model="badgeName"
                  @keyup.enter="findMatchingBadge()"
                ></v-text-field>
                <v-flex class="text-xs-right">
                  <v-btn
                    class="white--text"
                    depressed
                    small
                    color="#341646"
                    ref="searchButton"
                    @click="findMatchingBadge()"
                  >Search</v-btn>
                </v-flex>
              </v-layout>
              <v-layout row wrap>
                <v-chip
                  v-for="(categoryChip, index) in badgeTags"
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
    </v-form>
    <center>
      <h2>Top badge</h2>
    </center>
    <br />

    <v-data-table
      :items="badgeList"
      :pagination.sync="pagination"
      item-key="name"
      class="elevation-1"
    >
      <template v-slot:no-data>
        <v-alert :value="true" color="pink" icon="info">
          <center>Badge not found !</center>
        </v-alert>
      </template>
      <template v-slot:items="props">
        <nuxt-link :to="`/ranking/badge/${props.item.badgeId}`">
          <tr>
            <td>
              <br />
              <center>
                <v-avatar size="70">
                  <img :src="props.item.badgePicture" />
                </v-avatar>
              </center>
              <br />
            </td>
            <td>
              <h3>{{ props.item.badgeName }}</h3>
            </td>
          </tr>
        </nuxt-link>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";
import { mockCategoryList } from "@/utils/categoryJson";
export default {
  data: () => ({
    show: false,
    dialog: false,
    marker: true,
    iconIndex: 0,
    categoryList: [],
    icons: ["filter_list"],
    search: "",
    selectedCategoryList: [],
    badgeName: "",
    badgeTags: [],
    pagination: {
      sortBy: "name"
    },
    badge: {
      badgeId: "",
      exp: 0.0
    },
    badgeList: [],
    badgeSelect: false
  }),
  watch: {
    badgeTags(badgeTags) {
      if (badgeTags.length > 3) {
        this.badgeTags.shift();
      }
    }
  },
  computed: {
    ...mapGetters(["getCategory"]),
    icon() {
      return this.icons[this.iconIndex];
    }
  },
  mounted() {
    this.loadCategoryList();
    this.findMatchingBadge();
  },
  methods: {
    ...mapActions(["autoSignIn", "setCategory", "setBadgeDetail"]),
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
    findMatchingBadge() {
      console.log("mating badge");
      let badgeTags = "";
      if (this.badgeTags.length > 0) {
        badgeTags = "&badgeTags=";
        for (let i = 0; i < this.badgeTags.length; i++) {
          badgeTags += `${this.badgeTags[i]},`;
        }
      }
      axios
        .get(
          `${process.env.USER_SERVICE}/badges?badgeName=${this.badgeName}${badgeTags}`
        )
        .then(badgeResponse => {
          this.badgeList = badgeResponse.data;
          console.log(badgeResponse.data);
        })
        .catch(error => {});
      console.log(this.dialog);
      this.dialog = false;
      console.log(this.dialog);
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
    searchBadgeByName() {
      console.log(this.badgeName);
    },
    toggleMarker() {
      this.marker = !this.marker;
    },
    sendMessage() {
      this.resetIcon();
      this.clearMessage();
    },
    clearMessage() {
      this.message = "";
    },
    resetIcon() {
      this.iconIndex = 0;
    },
    toggleAll() {
      if (this.selected.length) this.selected = [];
      else this.selected = this.desserts.slice();
    },
    changeSort(column) {
      if (this.pagination.sortBy === column) {
        this.pagination.descending = !this.pagination.descending;
      } else {
        this.pagination.sortBy = column;
        this.pagination.descending = false;
      }
    }
  }
};
</script>



<style >
h2 {
  color: #341646;
}
h3 {
  color: #341646;
}
</style>