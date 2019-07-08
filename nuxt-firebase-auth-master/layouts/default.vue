<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" fixed app style="background-color: #fff">
      <v-list>
        <nuxt-link to="/">
          <v-list-tile>
            <v-list-tile>
              <v-icon class="icon">home</v-icon>
            </v-list-tile>
            <v-list-tile-content>Meet U</v-list-tile-content>
          </v-list-tile>
        </nuxt-link>

        <v-list-group>
          <v-list-tile slot="activator">
            <v-list-tile>
              <v-icon class="icon">category</v-icon>
            </v-list-tile>
            <v-list-tile-content class="categoryIcon" style="color:#341646;">Category</v-list-tile-content>
          </v-list-tile>
          <v-list-tile v-for="(category, i) in categoryList" :key="i" ripple>
            <v-list-tile-action></v-list-tile-action>
            <v-list-tile-content>
              <nuxt-link class="categoryLink" :to="`/event?category=${category}`">
                <v-list-tile-title>{{ category }}</v-list-tile-title>
              </nuxt-link>
            </v-list-tile-content>
          </v-list-tile>
        </v-list-group>

        <nuxt-link class="link" to="/about">
          <v-list-tile>
            <v-list-tile>
              <v-icon class="icon">info</v-icon>
            </v-list-tile>
            <v-list-tile-content>About</v-list-tile-content>
          </v-list-tile>
        </nuxt-link>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar color="#fff" app style="color:#fff;">
      <v-btn icon @click.native.stop="drawer = !drawer" class="hidden-lg-and-up">
        <v-icon class="menuButton" color="#341646;">menu</v-icon>
      </v-btn>
      <nuxt-link to="/" style="text-decoration-line:none;">
        <v-toolbar-title style="text-decoration-line:none;color: #341646" v-text="title"></v-toolbar-title>
      </nuxt-link>

      <v-spacer></v-spacer>

      <nuxt-link :to="`/login`" style="text-decoration-line:none;">
        <v-btn icon>
          <v-icon class="accountButton" color="#341646;">person</v-icon>
        </v-btn>
      </nuxt-link>


       <div>
        <nuxt-link to="/search">
          <v-btn icon>
            <v-icon color="#341646;">search</v-icon>
          </v-btn>
        </nuxt-link>
      </div>

      <!-- <v-text-field
        class="hidden-xs-only"
        v-model="search"
        placeholder="Search..."
        append-icon="search"
        color="#341646"
        @click:append="() => (openSearch = !openSearch)"
        light
      ></v-text-field> -->

      <div v-if="user" id="user" class="text-xs-center">
        <v-btn icon slot="activator">
          <v-icon medium>settings</v-icon>
        </v-btn>
        <v-card>
          <v-list>
            <v-list-tile avatar>
              <v-list-tile-avatar>
                <img :src="user.photoURL" alt="Guest">
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title v-if="user.displayName">{{user.displayName}}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
            <v-divider></v-divider>

            <v-list-tile>
              <v-spacer></v-spacer>
              <v-list-tile-action>
                <v-btn primary class="mt-2" color="primary" @click.native="logout()">Logout</v-btn>
              </v-list-tile-action>
            </v-list-tile>
          </v-list>
        </v-card>
      </div>
    </v-toolbar>

    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>

    <v-footer height="auto" color="#341646">
      <v-layout row wrap justify-center>
        <v-btn v-for="link in linksFooter" :key="link" color="#fff" flat>{{ link }}</v-btn>
        <v-flex xs12 py-3 text-xs-center white--text>
          &copy;2019 —
          <strong>Meet U</strong>
        </v-flex>
      </v-layout>
    </v-footer>
  </v-app>
</template>

<script>
import Swal from "sweetalert2";
export default {
  data() {
    return {
      height: {
        height: "10px"
      },
      drawer: false,
      fixed: false,
      search: null,
      categoryList: [
        "Arts",
        "Book Clubs",
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
      title: "MeetU",
      menu: false,
      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ]
    };
  },

  computed: {
    user() {
      return this.$store.getters.activeUser;
      // return 'hj'
    },
    mockUser() {
      return this.$store.getters.mockGetUser;
    }
  },
  methods: {
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
    }

    // searchButton: function(e){
    //   Swal.fire({
    //     title: "Are you sure?",
    //     type: "warning",
    //     showCancelButton: true,
    //     confirmButtonColor: "#4BB543",
    //     cancelButtonColor: "#d33",
    //     confirmButtonText: "Upgrade account!"})
    // }
  }
};
</script>

<style>
.textfield {
  text-decoration-line: none !important;
  text-decoration: none !important;
  border-bottom: 0;
}
.v-autocomplete {
  width: calc(10% - 30px);
}
/* .v-autocomplete-list{
  height: 10px;
} */
.v-navigation-drawer > .list:not(.list--dense) .list__tile {
  font-size: 17px;
}

.v-icon.icon {
  color: #341646;
}

.avatar {
  max-width: 75px;
}

a.link {
  color: #341646;
}
a {
  text-decoration-line: none;
  text-decoration: none;
}
.categoryLink {
  text-decoration-line: none;
  text-decoration: none;
  color: #341646;
}
/* 
.v-btn:hover {
  background-color: #fff;
} */

/* a:hover {
  text-decoration-line: none;
  text-decoration: none;
  color: #fc5577;
} */

.link {
  color: #341646;
  text-decoration: none;
}

.v-list__tile--link {
  border-left: 10px solid transparent;
}
.v-list__tile--link.v-list__tile--active {
  /* color: rgba(0, 0, 0, 0.87); */
  color: #341646;
  border-left: 10px solid gnray;
}
</style>
