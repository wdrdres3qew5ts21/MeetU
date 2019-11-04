<template>
  <nuxt-link :to="`/event/${event.elasticEventId}`">
    <v-card>
      <v-img :src="event.eventPictureCover" height="200px"></v-img>
      <v-container fill-height fluid pa-2>
        <v-layout fill-height>
          <v-flex xs12 align-end flexbox>
            <span class="textName" v-text="event.eventName.length > 20 ? event.eventName.substr(0,40)+'...' :event.eventName "></span>
          </v-flex>
        </v-layout>
      </v-container>
      <!-- <v-card-actions>
        <h3 class="#AEAEAE--text">{{event.eventName}}</h3>
      </v-card-actions> -->

      <!-- <v-flex class="text-xs-right">
         <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
          <v-icon color="#fff" medium>edit</v-icon>
        </v-btn>
            <v-btn fab dark small color="red" @click="confirmPopup">
              <v-icon color="#fff" medium>delete</v-icon>
            </v-btn>
      </v-flex>-->

      <v-slide-y-transition>
        <v-card-text>
          {{formatDateForReadable(event.createEventDate)}}
          <br />

          <b>location</b>

          <!-- {{event.location.province?event.location.province:'Thailand'}} -->
        </v-card-text>
      </v-slide-y-transition>
    </v-card>
  </nuxt-link>
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
    event: Object
  },
  methods: {
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
      formatDate =
        date.getDate() +
        "-" +
        months[date.getMonth()] +
        "-" +
        date.getFullYear();
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

    confirmPopup: function(e) {
      Swal.fire({
        title: "Do you want to delete this event?",
        inputPlaceholder: "Enter organize name for confirmation",
        input: "text",
        inputAttributes: {
          autocapitalize: "off"
        },
        showCancelButton: true,
        confirmButtonText: "Confirm",
        showLoaderOnConfirm: true,
        preConfirm: login => {
          return fetch(`//api.github.com/users/${login}`)
            .then(response => {
              if (!response.ok) {
                throw new Error(response.statusText);
              }
              return response.json();
            })
            .catch(error => {
              Swal.showValidationMessage(`Request failed: ${error}`);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
      }).then(result => {
        if (result.value) {
          Swal.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            type: "warning",
            inputAttributes: {
              autocapitalize: "off"
            },
            showCancelButton: true,
            confirmButtonColor: "#FD6363",
            cancelButtonColor: "#4CAF50",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, keep it!"
          }).then(result => {
            if (result.value) {
              Swal.fire("Deleted!", "Your event has been deleted.", "success");
            }
          });
        }
      });
    }
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
</style>