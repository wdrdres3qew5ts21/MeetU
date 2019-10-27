<template>
  <div>
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

                  <v-subheader>Filter by community category</v-subheader>
                  <v-layout class="mb-4">
                    <!-- <v-combobox
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
                         
                        >
                          <strong></strong>&nbsp;
                        </v-chip>
                      </template>
                    </v-combobox> -->
                  </v-layout>
                  
                
                </v-list>

                <v-btn
                  class="white--text"
                  depressed
                  large
                  block
                  color="#341646"
                  
                >Search</v-btn>
            

              </v-card>
            </v-dialog>

            <v-text-field
              class="questrial no-top-padding"
              height="20px"
              placeholder="Search..."
             
             
            ></v-text-field>
            <v-flex class="text-xs-right">
              <v-btn
                class="white--text"
                depressed
                small
                color="#341646"
                ref="searchButton"
               
              >Search</v-btn>
            </v-flex>
          </v-layout>
        </div>
        <!-- </v-card> -->
      </v-flex>
      <br />

     
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


<br><br>

<!-- <center><div>
  <v-icon size="50" @click="showTest = !showTest">insert_emoticon</v-icon>
  <transition
    name="custom-classes-transition"
    enter-active-class="animated tada"
    leave-active-class="animated tada"
  >

   
    <h3 v-if="showTest">You can search event!</h3>
    <br>
   
        <v-img v-if="showTest" :src="emotionImg" max-width="60"></v-img>

  </transition>
</div>
</center> -->
    <br />
    <br />
    <center>
      <h2>Let's Get Started !</h2>

      <br />

      <nuxt-link :to="`/community/createCommunity`" style="text-decoration-line:none;">
        <v-btn
          class="createCummunityButton white--text"
          depressed
          large
          color="#341646"
        >Create community</v-btn>
      </nuxt-link>
    </center>
  </div>
</template>
<style>
</style>
<script>
import axios from "axios";
import { mapActions } from "vuex";
import CommunityCard from "@/components/communityCard";
import { mockCommunityList } from "@/utils/eventJson";
export default {
  components: {
    CommunityCard
  },
  data() {
    return {
      communityList: [],
      items: [
        {
          src: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
        }
      ],
      dialog: false,
      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ],

      carouselsPhoto: [
        {
          src:
            "https://d1.awsstatic.com/Marketplace/cases/600x400_ClubAutomation_Logo.e312129c6220eb729d9c7b7912be2ee59a108421.png"
        },
        {
          src:
            "https://eventbrowse.com/wp-content/uploads/2018/11/DoG1Z3FXUAEfR3N.jpg"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/bangkok-block-party-2019/bangkok-block-party-2019-event-banner.jpg?la=th-TH&hash=FE90C817F1C953E5DCB6834242EE517801037D28"
        }
      ]
    };
  },
  mounted() {
    this.communityList = mockCommunityList;
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

