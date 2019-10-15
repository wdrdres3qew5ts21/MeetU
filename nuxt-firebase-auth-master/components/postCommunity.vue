<template>
  <div>
    <v-card rounded outlined>
      <br />
      <div>
        <v-layout>
          <v-flex xs12 class="text-xs-left">
            <v-list-item class="py-4 pl-4">
              <v-avatar :size="50">
                <!-- <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>  -->
              </v-avatar>
            </v-list-item>
            <v-list-item-content class="mt-2">
              <v-list-item-title class="subtitle questrial">Permu pop</v-list-item-title>
            </v-list-item-content>
          </v-flex>

          <v-flex xs12 class="text-xs-right">
            <v-btn text icon>
              <v-icon>expand_more</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </div>
      <v-container grid-list-xs fluid style="padding:10px">
        <br />
        <v-layout wrap>
          <!-- <v-flex v-for="n in 2" :key="n" xs6>
            <v-card flat tile>
              <v-img
                :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
                aspect-ratio="1"
                class="grey lighten-2"
              >
                <template v-slot:placeholder>
                  <v-layout fill-height align-center justify-center ma-0>
                    <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                  </v-layout>
                </template>
              </v-img>
            </v-card>
          </v-flex> -->
        </v-layout>
      </v-container>
      <v-card-text rounded outlined class="mx-auto">
        <div class="questrial body1 mb-4">{{lorem}}</div>
        <v-divider></v-divider>

 
        <v-layout class="text-xs-center">
          
          <v-menu attach open-on-hover top offset-y min-width="200px">
            <template v-slot:activator="{ on }">
              <v-flex >
                <v-tooltip top>
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on" text block  flat >
                      <!-- class="btn" @click="activeButton = 1" v-bind:class="{'greenBtn':true, 'blackBtn': activeButton == 1}" -->
                      <!-- <v-icon class="icon" @click="activeButton = 1" v-bind:class="{'blackBtn':true, 'purpleBtn': activeButton == 1}">thumb_up_alt</v-icon>Like -->
                  <!-- <v-btn flat icon color="blue lighten-2"> -->
        <v-icon>thumb_up</v-icon>
      <!-- </v-btn> -->
                    </v-btn>
                  </template>
                  <span>Like this post</span>
                </v-tooltip>
              </v-flex>
            </template>
          </v-menu>

            
          <v-flex class="text-right">
            <v-layout wrap justify-end>
              <v-tooltip top>
                <template v-slot:activator="{ on }">
                  <v-btn v-on="on" text block flat >
                    <v-icon>comment</v-icon>Comment
                  </v-btn>
                </template>
                <span>Write comment</span>
              </v-tooltip>
            </v-layout>
          </v-flex>

        </v-layout>


        <v-divider></v-divider>
        <v-layout>
          <v-flex xs12>
            <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              placeholder="Write somthing..."
            ></v-text-field>
          </v-flex>
          <v-flex class="text-xs-right">
            <br />
            <v-btn text small>post</v-btn>
          </v-flex>
          
        </v-layout>

       <v-btn text icon>
          <v-icon>photo_camera</v-icon>
        </v-btn>
      </v-card-text>
      <v-list-item style="align-items:normal"></v-list-item>
      <v-list-item></v-list-item>
    </v-card>


  </div>

  
</template>

<script>
import { types } from "util";
export default {
  name: "postCommunity",
  data() {
    return{
       activeButton: 0
  }  }
};
</script>


 <style scoped>
 .purpleBtn {
   background-color: purple ;
 }

 .blackBtn {
  color: lightblue;
 }

</style>
