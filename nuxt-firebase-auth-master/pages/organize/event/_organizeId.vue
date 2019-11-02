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
      <!-- <v-flex xs3>
        <v-avatar size="80">
          <v-img
            :src="organize.organizeImageCover || defaultImage"
            aspect-ratio="1"
            max-width="60"
            max-height="60"
          ></v-img>
        </v-avatar>
      </v-flex> -->

      <v-flex xs9>
        <br />
        <h2>{{organize.organizeName}}</h2>
      </v-flex>

      <v-flex class="text-xs-right">
        <nuxt-link :to="`/organize/editOrganizeSetting/${$route.params.organizeId}`">
          <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
            <v-icon color="#fff" medium>edit</v-icon>
          </v-btn>
        </nuxt-link>
        <!-- <v-btn fab dark small color="red" @click="confirmPopup">
          <v-icon color="#fff" medium>delete</v-icon>
        </v-btn>-->
      </v-flex>
    </v-layout>

    <br />
    <div>
      <v-tabs color="#341646" dark slider-color="white" centered>
        <v-tab ripple>Organize Detail</v-tab>
        <v-tab ripple>Events</v-tab>
        <v-tab ripple>QR code</v-tab>

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

        <v-tab-item>
          <v-card flat>
            <center>
              <br />
              <h3 class="h3Text">You are {{userForm.firstName}} {{userForm.lastName}}</h3>
              <br />
              <div v-if="isCameraOpen">
                <client-only placeholder="loading...">
                  <qrcode-stream @decode="onDecode"></qrcode-stream>
                </client-only>
              </div>

              <v-spacer></v-spacer>
              <v-btn @click="isCameraOpen=!isCameraOpen" round block large color="primary">
                <v-icon class="spacing-playground py-0 px-2" large>mdi-qrcode-scan</v-icon>QR Code
              </v-btn>

              <p class="textIntroduce">
                Just scan a QR code for join an event!
                <br />
                <v-icon medium>mdi-emoticon-cool-outline</v-icon>
              </p>
            </center>

            <br />
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
import { mapGetters, mapActions } from "vuex";
import { error } from "util";
import "@mdi/font/css/materialdesignicons.css";
export default {
  name: "startedEvent",
  components: {
    eventCard
  },
  props: {},
  data() {
    return {
      isCameraOpen: false,
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
        "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png",
      userForm: {
        badgeList: [],
        interest: [],
        firstName: "",
        lastName: "",
        gender: "",
        dateArray: [],
        dateOfBirth: "",
        phone: "",
        email: "",
        password: "",
        confirmPassword: "",
        password: "",
        website: "",
        line: "",
        facebook: "",
        twitter: "",
        instagram: ""
      }
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.initUserProfile();
    this.organizeId = this.$route.params.organizeId;
    console.log(this.$route.params.organizeId);
    this.loadAllEventOfOrganize();
    this.loadOrganizeDetail();
  },
  methods: {
    ...mapActions(["testContext"]),
    onDecode: function(decodedString) {
      let parsedTicket = "";
      try {
        parsedTicket = JSON.parse(decodedString);
        console.log(parsedTicket);
      } catch (error) {
        console.log("fail to QR decode", parsedTicket);
      }
      axios
        .post(`${process.env.EVENT_SERVICE}/event/join`, parsedTicket, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(scanResponse => {
          console.log(scanResponse);
          this.$swal({
            type: "success",
            title: "Success to scan QR Code!!!",
            text: "Success to scan QR Code !!!"
          });
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Fail to scan QR Code !!!",
            text: `${err.response.data.response}`
          });
        });
    },
    initUserProfile: function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/user/${this.getUser.uid}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(userProfileForm => {
          console.log("haate my self");
          userProfileForm = userProfileForm.data;
          console.log(userProfileForm);
          this.userForm.interest = userProfileForm.interest;
          this.userForm.firstName = userProfileForm.firstName;
          this.userForm.lastName = userProfileForm.lastName;
          this.userForm.email = userProfileForm.email;
          this.userForm.gender = userProfileForm.gender;
          this.userForm.facebook = userProfileForm.facebook;
          this.userForm.line = userProfileForm.line;
          this.userForm.website = userProfileForm.website;
          this.userForm.twitter = userProfileForm.twitter;
          this.userForm.instagram = userProfileForm.instagram;
          this.userForm.phone = userProfileForm.phone || "";
          loader.hide()
        })
        .catch(err => {
          loader.hide()
        });
    },
    loadAllEventOfOrganize: async function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.EVENT_SERVICE}/events/organize/${this.organizeId}`)
        .then(eventResponse => {
          console.log(eventResponse.data);
          this.eventList = eventResponse.data;
          loader.hide()
        })
        .catch(error => {
          console.log(error);
          loader.hide()
        });
    },
    loadOrganizeDetail: async function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organize = organizeResponse.data;
          loader.hide()
        })
        .catch(error => {
          console.log(error);
          loader.hide()
        });
    }
    // confirmPopup: function(e) {
    //   Swal.fire({
    //     title: "Do you want to delete this organize?",
    //     inputPlaceholder: "Enter organize name for confirmation",
    //     input: "text",
    //     inputAttributes: {
    //       autocapitalize: "off"
    //     },
    //     showCancelButton: true,
    //     confirmButtonText: "Confirm",
    //     showLoaderOnConfirm: true,
    //     preConfirm: login => {
    //       return fetch(`//api.github.com/users/${login}`)
    //         .then(response => {
    //           if (!response.ok) {
    //             throw new Error(response.statusText);
    //           }
    //           return response.json();
    //         })
    //         .catch(error => {
    //           Swal.showValidationMessage(`Request failed: ${error}`);
    //         });
    //     },
    //     allowOutsideClick: () => !Swal.isLoading()
    //   }).then(result => {
    //     if (result.value) {
    //       Swal.fire({
    //         title: "Are you sure?",
    //         text: "You won't be able to revert this!",
    //         type: "warning",
    //         inputAttributes: {
    //           autocapitalize: "off"
    //         },
    //         showCancelButton: true,
    //         confirmButtonColor: "#FD6363",
    //         cancelButtonColor: "#4CAF50",
    //         confirmButtonText: "Yes, delete it!",
    //         cancelButtonText: "No, keep it!"
    //       }).then(result => {
    //         if (result.value) {
    //           Swal.fire("Deleted!", "Your event has been deleted.", "success");
    //         }
    //       });
    //     }
    //   });
    // }
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

.h3Text {
  color: #341646;
}

.textIntroduce {
  color: gray;
}
</style>