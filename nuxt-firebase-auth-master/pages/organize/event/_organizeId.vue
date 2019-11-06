<template>
  <div>
    <v-img
      :src="organize.organizeImageCover ||defaultImage"
      aspect-ratio="1"
      class="grey lighten-2"
      max-height="200"
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
      </v-flex>-->

      <v-flex xs9>
        <br />
        <h2>{{organize.organizeName}}</h2>
      </v-flex>

      <v-flex class="text-xs-right">
        <div v-if="isOwner">
          <nuxt-link :to="`/organize/editOrganizeSetting/${$route.params.organizeId}`">
            <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
              <v-icon color="#fff" medium>edit</v-icon>
            </v-btn>
          </nuxt-link>
        </div>

        <!-- <v-btn fab dark small color="red" @click="confirmPopup">
          <v-icon color="#fff" medium>delete</v-icon>
        </v-btn>-->
      </v-flex>
    </v-layout>

    <br />
    <div>
      <v-tabs color="#341646" dark slider-color="white" centered>
        <v-tab ripple>{{ isAdmin? 'Detail' : 'Organize Detail' }}</v-tab>
        <v-tab ripple>Events</v-tab>
        <v-tab v-if="isAdmin==true" ripple>Management</v-tab>
        <v-tab v-if="isAdmin==true" ripple>QR code</v-tab>
        <!-- Organize Detail-->
        <v-tab-item>
          <v-card flat>
            <v-card-text>{{ organize.organizeDetail ||'Organize Description ...'}}</v-card-text>
          </v-card>
        </v-tab-item>
        <!-- Event Of Organize List -->
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <center>
                <nuxt-link
                  :to="`/organize/event/createEventForm?organizeId=${$route.params.organizeId}`"
                  style="text-decoration-line:none;"
                >
                  <br />
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

              <!-- <v-flex class="text-xs-right">
                <v-btn fab dark small color="red" @click="confirmPopup">
                  <v-icon color="#fff" medium>delete</v-icon>
                </v-btn>
              </v-flex>-->

              <event-card
                v-for="(event, index) in eventList"
                :key="index"
                @editEvent="editEvent"
                @deleteEvent="deleteEvent"
                :event="event"
                :isOwner="isOwner"
              />

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
        <!-- Management Organize -->
        <v-tab-item>
          <br />
          <h3>Admin Lists</h3>
          <v-layout row>
            <v-flex xs12>
              <v-card>
                <v-list two-line subheader v-if="adminList[0].userDetail.length > 0">
                  <v-list-tile
                    v-for="(admin, index) in adminList[0].userDetail"
                    :key="index"
                    avatar
                    @click="$router.push(`/userProfile/${admin.uid}`)"
                  >
                    <v-list-tile-avatar>
                      <img :src="admin.photoURL" />
                    </v-list-tile-avatar>

                    <v-list-tile-content>
                      <v-list-tile-title>{{ admin.displayName }}</v-list-tile-title>
                      <v-list-tile-sub-title>{{ admin.email }}</v-list-tile-sub-title>
                    </v-list-tile-content>

                    <v-list-tile-action v-if="isOwner">
                      <v-btn icon ripple @click="removeItem(admin.uid)">
                        <v-icon color="primary">person_add_disabled</v-icon>
                      </v-btn>
                    </v-list-tile-action>
                  </v-list-tile>
                </v-list>
                <!-- ปุ่มเพิ่ม Admin-->

                <v-list-tile v-if="isOwner">
                  <v-list-tile-avatar>
                    <img />
                  </v-list-tile-avatar>

                  <v-list-tile-content>
                    <v-text-field placeholder="add admin email..." v-model="newAdminEmail"></v-text-field>
                  </v-list-tile-content>
                  <v-list-tile-action @click="addAdminToOrganize()">
                    <v-btn icon ripple>
                      <v-icon color="primary">person_add</v-icon>
                    </v-btn>
                  </v-list-tile-action>
                </v-list-tile>
              </v-card>
            </v-flex>
          </v-layout>
          <br />
          <!-- <v-text-field
            label="Add new admin"
            placeholder="Fill admin email to assign role of admin"
            append-outer-icon="person_add"
          ></v-text-field>-->

          <!-- <v-layout row wrap>
                    <v-flex xs11>
                 <v-text-field placeholder="add admin email..." v-model="newAdminEmail"></v-text-field>
              </v-flex>
              <v-flex >
                <v-btn icon ripple>
                    <v-icon color="primary">person_add</v-icon>
                  </v-btn>
              </v-flex>
          </v-layout>-->
        </v-tab-item>
        <!-- QR Code Scanner-->
        <v-tab-item v-if="isAdmin==true">
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
import editOrganizeSetting from "~/components/editOrganizeSetting";
import Swal from "sweetalert2";
import eventCard from "~/components/eventCard";
import eventCardOrganize from "~/components/eventCardOrganize";
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
import { error } from "util";
import "@mdi/font/css/materialdesignicons.css";
import { async } from "q";
export default {
  name: "startedEvent",
  components: {
    eventCardOrganize,
    editOrganizeSetting,
    eventCard
  },
  props: {},
  data() {
    return {
      newAdminEmail: null,
      showCancelButton: true,
      isAdmin: false,
      isOwner: false,
      isCameraOpen: false,
      currentItem: "tab-Web",
      // items: ["Organize Detail", "View Event"],
      items: [
        {
          id: "1",
          icon: "folder",
          iconClass: "grey lighten-1 white--text",
          title: "Photos",
          subtitle: "Jan 9, 2014"
        },
        {
          id: "2",
          icon: "folder",
          iconClass: "grey lighten-1 white--text",
          title: "Recipes",
          subtitle: "Jan 17, 2014"
        },
        {
          id: "3",
          icon: "folder",
          iconClass: "grey lighten-1 white--text",
          title: "Work",
          subtitle: "Jan 28, 2014"
        }
      ],
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
      adminList: [
        {
          userDetail: []
        }
      ],
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
    this.organizeId = this.$route.params.organizeId;
    console.log(this.$route.params.organizeId);
    this.loadAllEventOfOrganize();
    this.loadOrganizeDetail();
    this.initUserProfile();
    if (this.getUser.uid) {
      this.verifyIfUserIsOrganizeMember();
      this.loadAdminDetail();
    }
  },
  methods: {
    ...mapActions(["testContext"]),
    onDecode: function(decodedString) {
      let parsedTicket = "";
      try {
        parsedTicket = JSON.parse(decodedString);
        parsedTicket.organizeId = this.$route.params.organizeId;
        console.log("---- parsed scaning ticket ----");
        console.log(parsedTicket);
      } catch (error) {
        console.log("fail to QR decode", parsedTicket);
      }
      axios
        .post(`${process.env.EVENT_SERVICE}/event/join`, parsedTicket, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
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
    editEvent: function(event) {
      console.log(event);
      this.$router.push(`/organize/event/statistic/${event.elasticEventId}`);
    },
    deleteEvent: async function(event) {
      console.log(event);
      const { value: formValues } = await Swal.fire({
        title: "Do you want to delete this event?",
        html:
          `<p>ElasticEvent ID : <b>${event.elasticEventId}</b></p> ` +
          `<p>Event Name : <b>${event.eventName}</b></p>` +
          `<p> <input id="confirmDelete" placeholder='Type  "confirmed" to delete this event' class="swal2-input"> <p>` +
          `<p> <input id="deleteMessageDetail"   placeholder='Please type reason for delete this event'  class="swal2-input"> </p>`,
        // inputPlaceholder: "Type  'confirmed' to delete this event ",
        // input: "text",
        inputAttributes: {
          autocapitalize: "off"
        },
        preConfirm: () => {
          return {
            confirmDelete: document.getElementById("confirmDelete").value,
            deleteMessageDetail: document.getElementById("deleteMessageDetail")
              .value
          };
        },
        showCancelButton: true,
        confirmButtonText: "Confirm",
        allowOutsideClick: () => !Swal.isLoading()
      });

      if (formValues) {
        console.log(formValues);

        if (formValues) {
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
              axios
                .post(`${process.env.EVENT_SERVICE}/event/delete`, {
                  elasticEventId: event.elasticEventId,
                  confirmDelete: formValues.confirmDelete,
                  deleteMessageDetail: formValues.deleteMessageDetail
                })
                .then(deleteResponse => {
                  Swal.fire(
                    "Deleted!",
                    "Your event has been deleted.",
                    "success"
                  );
                  this.loadAllEventOfOrganize();
                })
                .catch(err => {
                  Swal.fire(
                    "Failed to delete !",
                    err.response.data.response,
                    "error"
                  );
                });
            }
          });
        }
      }
    },
    verifyIfUserIsOrganizeMember() {
      console.log("------ verify status ------");
      axios
        .get(
          `${process.env.USER_SERVICE}/organize/${this.$route.params.organizeId}/admin/status`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(verifyResponse => {
          console.log(verifyResponse.data);
          let verifyStatus = verifyResponse.data;
          if (verifyStatus.isOwner != null) {
            this.isOwner = verifyStatus.isOwner;
            this.isAdmin = verifyStatus.isAdmin;
          } else if (verifyStatus.isAdmin != null) {
            this.isAdmin = verifyStatus.isAdmin;
          }
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    addAdminToOrganize() {
      axios
        .post(
          `${process.env.USER_SERVICE}/organize/${this.organizeId}/admin/${this.newAdminEmail}`
        )
        .then(adminResponse => {
          this.$swal({
            type: "success",
            title: "Success to add admin",
            text: `Success to add admin`
          });
          this.loadAdminDetail();
          console.log(adminResponse.datas);
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Fail to add admin",
            text: `${err.response.data.response}`
          });
        });
    },
    loadAllEventOfOrganize: async function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.EVENT_SERVICE}/events/organize/${this.organizeId}`)
        .then(eventResponse => {
          console.log(eventResponse.data);
          this.eventList = eventResponse.data;
          loader.hide();
        })
        .catch(error => {
          console.log(error);
          loader.hide();
        });
    },
    loadOrganizeDetail: async function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organize = organizeResponse.data;
          loader.hide();
        })
        .catch(error => {
          console.log(error);
          loader.hide();
        });
    },
    loadAdminDetail: async function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}/admins`)
        .then(adminResponse => {
          this.adminList = adminResponse.data;
          console.log("HIIII");
          console.log(adminResponse.data);
          console.log("Endddd");
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    removeItem(uid) {
      this.$swal({
        type: "Comfirm Delete Admin",
        text: `Comfirm for delete Admin`,
        showCancelButton: true,
        cancelButtonColor: "#FD6363",
        confirmButtonColor: "#AEAEAE",
        cancleButtonText: "Cancel",
        confirmButtonText: "Delete"
      }).then(button => {
        console.log(button);
        if (button.dismiss != "cancel")
          this.adminList = this.adminList.filter(
            admin => admin.userDetail[0].uid !== uid
          );

        axios
          .delete(
            `${process.env.USER_SERVICE}/organize/${this.organizeId}/${uid}`
          )
          .then(deleteResponse => {
            this.$swal({
              type: "success",
              title: "Delete Admin success",
              text: `Delete Admin success`
            });
            this.loadAdminDetail();
          })
          .catch(err => {
            this.$swal({
              type: "error",
              title: "Delete Admin error",
              text: `Delete Admin success`
            });
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
          console.log("organize detail");
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
          loader.hide();
        })
        .catch(err => {
          loader.hide();
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

.h3Text {
  color: #341646;
}

.textIntroduce {
  color: gray;
}
</style>