<template>
  <nuxt-link :to="`/event/${event.elasticEventId}`">
    <!-- <v-card color="cyan darken-2" class="white--text">
      <v-layout>
        <v-flex xs5>
          <v-img src="https://cdn.vuetifyjs.com/images/cards/foster.jpg" height="125px" contain></v-img>
        </v-flex>
        <v-flex xs7>
          <v-card-title primary-title>
            <div>
              <div class="headline">Supermodel</div>
              <div>Foster the People</div>
              <div>(2014)</div>
            </div>
          </v-card-title>
        </v-flex>
      </v-layout>
      <v-divider light></v-divider>
      <v-card-actions class="pa-3">
        Rate this album
        <v-spacer></v-spacer>
        <v-icon>star_border</v-icon>
        <v-icon>star_border</v-icon>
        <v-icon>star_border</v-icon>
        <v-icon>star_border</v-icon>
        <v-icon>star_border</v-icon>
      </v-card-actions>
    </v-card>-->
    <v-card>
      <v-img :src="event.eventPictureCover" height="200px">
        <v-container fill-height fluid pa-2>
          <v-layout fill-height>
            <v-flex xs12 align-end flexbox>
              <span class="headline white--text" v-text="event.eventName"></span>
            </v-flex>
          </v-layout>
        </v-container>
      </v-img>
      <v-card-actions>
        <span class="grey--text">{{event.eventName}}</span>
        <v-spacer></v-spacer>
      </v-card-actions>
      <v-slide-y-transition>
        <v-card-text>
          {{new Date().toString()}}
          <br />
          <!-- {{event.location.province?event.location.province:'Thailand'}} -->
        </v-card-text>
      </v-slide-y-transition>
    </v-card>
  </nuxt-link>
</template>
<script>
export default {
  name: "EventCard",
  props: {
    event: Object
  }
};
</script>