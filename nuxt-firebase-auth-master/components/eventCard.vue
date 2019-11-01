<template>
  <nuxt-link :to="`/event/${event.elasticEventId}`">
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
          {{formatDateForReadable(event.createEventDate)}}
         
          
          <br/>

          <b>location </b>
          
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
  
  },
  methods: {
     formatDateForReadable: function(formatDate){
      const months = ["JAN", "FEB", "MAR","APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
      let date = new Date(formatDate);
      formatDate = date.getDate()  + "-" + months[date.getMonth()] + "-" + date.getFullYear();
      console.log(formatDate);
      return formatDate
    },
    getTime: function(time){
      let date = new Date(time);
      time =  date.getHours() + ":" + date.getMinutes() + " ";
      return time
   }    
  },
  

};
</script>