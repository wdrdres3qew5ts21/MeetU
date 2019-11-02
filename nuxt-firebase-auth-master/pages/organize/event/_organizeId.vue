<template>
  <div>
    <v-img
      :src="organize.organizeImageCover ||defaultImage"
      aspect-ratio="1"
      class="grey lighten-2"
      max-width="1250"
      max-height="150"
    ></v-img>
    <br />
    <v-layout row wrap justify-start>
      <v-flex xs3>
        <v-avatar size="80">
          <v-img
            :src="organize.organizeImageCover || defaultImage"
            aspect-ratio="1"
            max-width="60"
            max-height="60"
          ></v-img>
        </v-avatar>
      </v-flex>

      <v-flex xs3>
        <br />
        <h2>{{organize.organizeName}}</h2>
      </v-flex>

      <v-flex class="text-xs-right">
        <nuxt-link :to="`/organize/editOrganizeSetting/${$route.params.organizeId}`">
          <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
            <v-icon color="#fff" medium>edit</v-icon>
          </v-btn>
        </nuxt-link>

        <v-btn fab dark small color="red" @click="confirmPopup">
          <v-icon color="#fff" medium>delete</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>

    <br />
    <div>
      <v-tabs color="#341646" dark slider-color="yellow" centered>
        <v-tab ripple>Organize Detail</v-tab>
        <v-tab ripple>Events</v-tab>
        <v-tab-item>
          <v-card flat>
            <v-card-text>{{ organize.organizeDetail ||'Organize Description ...'}}</v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <center>
                <nuxt-link
                  :to="`/organize/event/createEventForm?organizeId=${$route.params.organizeId}`"
                  style="text-decoration-line:none;"
                >
                  <v-btn
                    class="createEvent white--text"
                    color="#341646"
                    depressed
                    large
                    height="50"
                  >Create an Event</v-btn>
                </nuxt-link>
              </center>
              <div v-if="eventList == null">
                <center>
                  <p style="color:grey">No event, please create a new event.</p>
                </center>
              </div>
              <div v-else>
                <center>
                  <br />
                  <p style="color:grey">You can create new event.</p>
                </center>
              </div>
              <event-card v-for="(event, index) in eventList" :key="index" :event="event" />
              <br />
              <center v-if="eventList == null">
                <nuxt-link
                  :to="`/organize/event/createEventForm?organizeId=${$route.params.organizeId}`"
                  style="text-decoration-line:none;"
                >
                  <v-btn
                    class="createEvent white--text"
                    color="#341646"
                    depressed
                    large
                    height="50"
                  >Create an Event!</v-btn>
                </nuxt-link>
              </center>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </div>

    <br />
  </div>
</template>


<script>
import Swal from "sweetalert2";
import eventCard from "~/components/eventCard";
import axios from "axios";
import { error } from "util";
export default {
  name: "startedEvent",
  components: {
    eventCard
  },
  props: {},
  data() {
    return {
      currentItem: "tab-Web",
      items: ["Organize Detail", "View Event"],

      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      organizeId: "",
      eventList: null,
      organize: {
        organizeId: "",
        organizeName: ""
      },
      defaultImage:
        "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png"
    };
  },
  mounted() {
    this.organizeId = this.$route.params.organizeId;
    console.log(this.$route.params.organizeId);
    this.loadAllEventOfOrganize();
    this.loadOrganizeDetail();
  },
  methods: {
    loadAllEventOfOrganize: async function() {
      axios
        .get(`${process.env.EVENT_SERVICE}/events/organize/${this.organizeId}`)
        .then(eventResponse => {
          console.log(eventResponse.data);
          this.eventList = eventResponse.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    loadOrganizeDetail: async function() {
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organize = organizeResponse.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    confirmPopup: function(e) {
      Swal.fire({
        title: "Do you want to delete this organize?",
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

.h1 {
  font-family: Roboto;
  color: #341646;
}
.img-circle {
  border-radius: 50%;
}
</style>