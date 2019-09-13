<template>
  <v-container>
    <community-detail></community-detail>
    <br />
    <v-card rounded outlined class="mx-auto">
      <div class="px-3">
        <v-layout>
          <v-flex xs12>
            <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              placeholder="Write somthing..."
            ></v-text-field>
          </v-flex>
        </v-layout>
        <v-layout>
          <v-flex xs12 class="text-xs-left">
            <v-btn style="margin-right: 0px" icon>
              <v-icon>photo_camera</v-icon>
            </v-btn>
            <v-btn style="margin: 0px" icon>
              <v-icon>room</v-icon>
            </v-btn>
            <v-btn style="margin: 0px" icon>
              <v-icon>assessment</v-icon>
            </v-btn>
            <v-btn style="margin: 0px" icon>
              <v-icon>event</v-icon>
            </v-btn>
          </v-flex>
          <v-flex xs12 class="text-xs-right">
            <v-btn text color="#341646" class="mb-2 white--text">Post</v-btn>
          </v-flex>
        </v-layout>
      </div>
    </v-card>
    <br />
    <br />
    <post-community></post-community>
  </v-container>
</template>

<script>
import communityDetail from "~/components/communityDetail";
import postCommunity from "~/components/postCommunity";

export default {
  components: {
    postCommunity,
    communityDetail
  }
};
</script>

<style lang="css">
.v-content {
  max-width: 100%;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}
</style>