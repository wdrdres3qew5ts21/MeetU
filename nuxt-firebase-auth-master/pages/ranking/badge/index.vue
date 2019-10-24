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

                      <v-subheader>Filter by category</v-subheader>
                      <v-layout class="mb-4">
                        <v-combobox
                          :items="categoryList"
                          item-text="categoryLabel"
                          item-value="categoryName"
                          label="category"
                          @input="updateCategoryFilter"
                          chips
                          clearable
                          solo
                          multiple
                          sm6
                          xs2
                        >
                          <template v-slot:selection="data">
                            <v-chip
                              :selected="data.selected"
                              close
                              @input="remove(data.item.categoryName)"
                            >
                              <strong>{{ data.item.categoryName}}</strong>&nbsp;
                            </v-chip>
                          </template>
                        </v-combobox>
                      </v-layout>
                    </v-list>
                    <v-btn
                      class="white--text"
                      depressed
                      large
                      block
                      color="#341646"
                      @click="searchByFilter()"
                    >Search</v-btn>
                    {{filterForm.categorySelected}}
                  </v-card>
                </v-dialog>

                <v-flex class="text-xs-right">
                  <v-text-field
                    v-model="badgeName"
                    :append-outer-icon="badgeName ? 'search' : 'search'"
                    box
                    clear-icon="close"
                    clearable
                    placeholder="Search..."
                    type="text"
                    @click:append="toggleMarker"
                    @click:append-outer="searchBadgeByName"
                    @click:clear="clearMessage"
                  ></v-text-field>
                </v-flex>
                <br />
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


    <v-data-table :items="badgeList" :pagination.sync="pagination" item-key="name" class="elevation-1">
      <template v-slot:no-data>
        <v-alert :value="true" color="pink" icon="info">
          <center>Badge not found !</center>
        </v-alert>
      </template>
      <template v-slot:items="props">
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
    ...mapActions(["autoSignIn", "setCategory","setBadgeDetail"]),
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
    findMatchingBadge(){
      console.log("mating badge")
      let eventTagsQuery = ""
      axios.get(`${process.env.EVENT_SERVICE}/badges${eventTagsQuery}`)
      .then(badgeResponse =>{
        this.badgeList = badgeResponse.data;
        console.log(badgeResponse.data);
      })
      .catch(error =>{

      })

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