<template>
  <v-card>
    <nuxt-link :to="`/event/${event.elasticEventId}`">
      <v-img :src="event.eventPictureCover" height="200px">
        <v-container fill-height fluid pa-2>
          <v-layout fill-height>
            <v-flex xs12 align-end flexbox>
              <span class="headline white--text" v-text="eventName"></span>
            </v-flex>
          </v-layout>
        </v-container>
      </v-img>
    </nuxt-link>

    <v-card-actions>
      <span class="grey--text">{{event.eventName}}</span>
      <v-spacer></v-spacer>
      <!-- <v-btn icon @click="show = !show"> -->
      <!-- <v-icon>{{ show ? 'keyboard_arrow_down' : 'keyboard_arrow_up' }}</v-icon> -->
      <!-- </v-btn> -->
    </v-card-actions>
    <v-slide-y-transition>
      <v-card-text>
        Date:
        <br />
        Location: {{location}}
      </v-card-text>
    </v-slide-y-transition>
  </v-card>
</template>
<script>
export default {
  name: "EventCard",
  props: {
    event: Object
  }
};
</script>