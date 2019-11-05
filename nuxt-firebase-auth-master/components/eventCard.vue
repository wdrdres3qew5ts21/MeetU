<template>
  <!-- <nuxt-link :to="`/event/${event.elasticEventId}`"> -->
    <v-card>
      <v-img :src="event.eventPictureCover" height="200px"></v-img>
      <v-container fill-height fluid pa-2>
        <v-layout fill-height>
          <v-flex xs12 align-end flexbox>
            <span
              class="textName"
              v-text="event.eventName.length > 20 ? event.eventName.substr(0,40)+'...' :event.eventName "
            ></span>
          </v-flex>
        </v-layout>
      </v-container>
      <!-- <v-card-actions>
        <h3 class="#AEAEAE--text">{{event.eventName}}</h3>
      </v-card-actions>-->
      <v-flex v-if="isOwner" class="text-xs-right">
        <v-btn fab dark small color="#341646" @click="$emit('editEvent', event)" >
          <v-icon color="#fff" medium>edit</v-icon>
        </v-btn>
        <v-btn fab dark small color="red"  @click="$emit('deleteEvent', event)">
          <v-icon color="#fff" medium>delete</v-icon>
        </v-btn>
      </v-flex>

      <v-slide-y-transition>
        <v-card-text>
          <v-layout row wrap>
            <v-flex class="text-justify-left">
              <h2 class="eventDate">{{formatDate(event.createEventDate)}}</h2>
              <span class="eventMonth">{{formatDateForReadable(event.createEventDate)}}</span>
            </v-flex>
            <br />
            <v-flex>
              <b>location</b>
            </v-flex>
          </v-layout>
          <!-- {{event.location.province?event.location.province:'Thailand'}} -->
        </v-card-text>
      </v-slide-y-transition>
    </v-card>
  <!-- </nuxt-link> -->
</template>
<script>
import Swal from "sweetalert2";
export default {
  name: "EventCard",
  components: {},
  data() {
    return {
      isEditing: true
    };
  },
  props: {
    event: Object,
    isOwner: Boolean
  },
  methods: {
    formatDate: function(dateFormat) {
      let date = new Date(dateFormat);
      dateFormat = date.getDate();
      return dateFormat;
    },
    formatDateForReadable: function(formatDate) {
      const months = [
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC"
      ];
      let date = new Date(formatDate);
      formatDate = months[date.getMonth()];
      // "-" +
      // date.getFullYear();
      console.log(formatDate);
      return formatDate;
    },
    getTime: function(time) {
      let date = new Date(time);
      time = date.getHours() + ":" + date.getMinutes() + " ";
      return time;
    },

    activateInEditMode() {
      this.isEditing = false;
    },
    deActivateInEditMode() {
      this.isEditing = true;
    },

  }
};
</script>


<style lang="css">
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

/* 
.imgBlur{
   filter: blur(1px) ;
  -webkit-filter: blur(1px);
} */

.textName {
  color: #fff;
  background-color: rgb(102, 36, 93); /* Fallback color */
  background-color: rgba(40, 25, 58, 0.6); /* Black w/opacity/see-through */
  font-weight: bold;
  /* border: 3px solid #341646; */
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* z-index: 2; */
  width: 100%;
  padding: 20px;
  text-align: left;
}
.eventDate {
  color: #341646;
}
.eventMonth {
  font-size: 18px;
  font-weight: bold;
}
</style>