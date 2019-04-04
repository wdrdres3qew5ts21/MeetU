<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" fixed app style="background-color: #341646">
      <v-list>
        <nuxt-link class="link" to="/">
          <v-list-tile>
            <v-list-tile>
              <v-icon class="icon">home</v-icon>
            </v-list-tile>
            <v-list-tile-content>
                Meet U
            </v-list-tile-content>
          </v-list-tile>
        </nuxt-link>

             <v-list-group>
              <v-list-tile slot="activator" >
                <v-list-tile >
                  <v-icon class="icon">category</v-icon>
                </v-list-tile>
                <v-list-tile-content style="color:#fff;">
                  Category
                </v-list-tile-content>
              </v-list-tile>       
              <v-list-tile  v-for="(category, i) in categoryList" :key="i" ripple @click="close">
                <v-list-tile-action >
                </v-list-tile-action>
                <v-list-tile-content>
                  <nuxt-link  class="categoryLink"  :to='`/event?${category}`'>
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
            <v-list-tile-content>
                About
            </v-list-tile-content>
          </v-list-tile>
      </nuxt-link>

        <!-- </nuxt-link> -->


      </v-list>
    </v-navigation-drawer>



  
    <v-toolbar app >
      <v-btn
        icon
        @click.native.stop="drawer = !drawer">
        <v-icon>menu</v-icon>
      </v-btn>
      <v-toolbar-title v-text="title"></v-toolbar-title>
      
      <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>search</v-icon>
        </v-btn>

        

        <v-btn icon href="/admin">
          <v-icon>account_circle</v-icon>
        </v-btn>

      <div v-if="user" id="user" class="text-xs-center">
        <v-menu
        v-for="itemsCategory in items"
          v-model="itemsCategory.active"
          :key="itemsCategory.title"
          :prepend-icon="itemsCategory.action"
          offset-x
          :close-on-content-click="false"
          :nudge-top="200"
          @input="onItemClick($items,$itemsCategory)"
          >
        </v-menu> 
          
          <v-btn icon slot="activator"><v-icon medium>settings</v-icon></v-btn> -->
            <v-card>
              <v-list>
                <v-list-tile avatar>
                  <v-list-tile-avatar>
                    <img :src="$store.state.user.photoURL" alt="John">
                  </v-list-tile-avatar>
                  <v-list-tile-content>
                    <v-list-tile-title v-if="user.displayName">{{user.displayName}}</v-list-tile-title>
                  </v-list-tile-content>
                </v-list-tile>
                <v-divider></v-divider>

                <v-list-tile>
                  <v-spacer></v-spacer>
                  <v-list-tile-action>
                    <v-btn primary class="mt-2" color="primary" @click.native="logout">
                      Logout
                    </v-btn>
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
    <!-- <v-content>
     <v-container fluid>
      <nuxt />
     </v-container>
   </v-content> -->
    <!-- <v-footer app :fixed="fixed" class="justify-center px-4">
      <span>&copy; 2019</span>
    </v-footer> -->
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      drawer: null,
      fixed: false,
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
      menu: false
    };
  },
  computed: {
    user() {
      return this.$store.getters.activeUser;
    }
  },
  methods: {
    onItemClick(event, itemsCategory) {
      if (event) {
        this.selected = itemsCategory;
      }
    },

    logout() {
      this.$store.dispatch("signOut").then(() => {
        alert("logged out!");
        this.$router.push("/");
      });
    }
  }
};
</script>

<style>
.v-navigation-drawer > .list:not(.list--dense) .list__tile {
  font-size: 17px;
}

.v-icon.icon{
  color: #fff;
}



.avatar {
  max-width: 75px;
}

a.link{
  color: #fff;
}
.categoryLink{
  text-decoration-line: none;
  text-decoration: none;
  color: #fff;
}





a:hover {
  text-decoration-line: none;
  text-decoration: none;
  color: #FC5577;
  /* background: yellowgreen; */
  

}



.link {
  color: #fff;
  text-decoration: none;
  
}



/* .list__tile--active.list__tile.list__tile--link {

} */

/* a.nuxt-link-exact-active.list__tile--active.list__tile.list__tile--link { */
  /* font-weight: 900 !important;
  color: #3f51b5 !important; */
/* } */

/* .v-navigation-drawer{
  background-color: #341646;
} */
.v-list__tile--link {
  border-left: 10px solid transparent;
}
.v-list__tile--link.v-list__tile--active {
  /* color: rgba(0, 0, 0, 0.87); */
  color: #fff;
  border-left: 10px solid gnray;
}
</style>
