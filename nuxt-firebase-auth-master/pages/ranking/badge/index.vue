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
                    </v-list>
                    <v-btn>Search</v-btn>
                  </v-card>
                </v-dialog>
                <v-flex class="text-xs-right">
                  <v-text-field
                    v-model="message"
                    :append-outer-icon="message ? 'search' : 'search'"
                    box
                    clear-icon="close"
                    clearable
                    placeholder="Search..."
                    type="text"
                    @click:append="toggleMarker"
                    @click:append-outer="sendMessage"
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
    <!-- <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-list two-line >
          <template v-for="(item, index) in items" >
            <v-subheader v-if="item.header" :key="item.header">{{ item.header }}</v-subheader>
            <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>
            <v-list-tile v-else :key="item.title" avatar>
              <v-list-tile-avatar size="50px"  >
                <img :src="item.avatar" />
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title v-html="item.title"></v-list-tile-title>
                <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-card>
    </v-flex>-->
    <v-data-table
      :items="badges"
      :pagination.sync="pagination"
      item-key="name"
      class="elevation-1"
    >
      <template v-slot:items="props">
        <tr>
          <td>
            <br />
            <center>
              <v-avatar size="70">
                <img :src="props.item.avatar" />
              </v-avatar>
            </center>
            <br />
          </td>
          <td>
            <h3>{{ props.item.name }}</h3>
            <br />Detail:
          </td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  data: () => ({
    show: false,
    dialog: false,
    message: "",
    marker: true,
    iconIndex: 0,
    icons: ["filter_list"],

    pagination: {
      sortBy: "name"
    },
    badges: [
      {
        name: "Frozen Yogurt",

        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
      },
      {
        name: "Ice cream sandwich",

        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
      },
      {
        name: "Eclair",

        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Cupcake",

        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Gingerbread",

        avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg"
      },
      {
        name: "Jelly bean",

        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
      },
      {
        name: "Lollipop",

        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Honeycomb",

        avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg"
      },
      {
        name: "Donut",

        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "KitKat",

        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      }
    ]

    // items: [
    //   {
    //     avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
    //     title: "Brunch this weekend?",
    //     subtitle:
    //       "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
    //   },
    //   { divider: true, inset: true },
    //   {
    //     avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
    //     title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
    //     subtitle:
    //       "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
    //   },
    //   { divider: true, inset: true },
    //   {
    //     avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
    //     title: "Oui oui",
    //     subtitle:
    //       "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?"
    //   },
    //   { divider: true, inset: true },
    //   {
    //     avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
    //     title: "Birthday gift",
    //     subtitle:
    //       "<span class='text--primary'>Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?"
    //   },
    //   { divider: true, inset: true },
    //   {
    //     avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
    //     title: "Recipe to try",
    //     subtitle:
    //       "<span class='text--primary'>Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
    //   }
    // ]
  }),

  computed: {
    icon() {
      return this.icons[this.iconIndex];
    }
  },

  methods: {
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