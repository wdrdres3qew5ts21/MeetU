<template>
  <div>
    <v-card rounded outlined>
      <br />
      <div>
        <v-layout>
          <v-flex xs12 class="text-xs-left">
            <v-list-item class="py-4 pl-4">
              <v-avatar :size="50">
                <img src="https://image.flaticon.com/icons/png/512/64/64572.png" alt="John" />
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
        <v-flex v-for="n in 2" :key="n" xs6>
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
        </v-flex>
      </v-layout>
      </v-container>
      <v-card-text rounded outlined class="mx-auto">
        <div class="questrial body1 mb-4">{{lorem}}</div>
        <v-divider></v-divider>
        <v-layout class="py-4">
          <v-menu attach open-on-hover top offset-y min-width="200px">
            <template v-slot:activator="{ on }">
              <v-flex v-on="on">
                <v-tooltip top>
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on" text block>
                      <v-icon>thumb_up_alt</v-icon>Like
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
                  <v-btn v-on="on" text block>
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
            <v-btn text icon>
              <v-icon>photo_camera</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-card-text>
      <v-list-item style="align-items:normal"></v-list-item>
      <v-list-item></v-list-item>
    </v-card>
  </div>
</template>

<script>
import { types } from "util";
export default {
  data: () => ({})
};
</script>

<style>
</style>
