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
                <v-btn flat icon color="#341646">
                  <v-icon>filter_list</v-icon>
                </v-btn>
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
    filterForm: {
      categorySelected: []
    },
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
      let eventTagsQuery = "";
      axios
        .get(`${process.env.USER_SERVICE}/badges${eventTagsQuery}`)
        .then(badgeResponse => {
          this.badgeList = badgeResponse.data;
          console.log(badgeResponse.data);
        })
        .catch(error => {});
    },
    // onItemClick(event, itemsCategory) {
    //   if (event) {
    //     this.selected = itemsCategory;
    //   }
    // },
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