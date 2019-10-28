<template>
  <div>
    <br />
    <v-layout>
      <h2 style="color:#341646">Edit Event</h2>
      <!-- <span></span> -->
      <v-flex class="text-xs-right">
        <v-btn depressed flat @click=" isEditing= !isEditing">
          <v-icon color="#341646" medium>edit</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>
    <br />

    <v-layout column>
      <v-form ref="form" v-model="valid">
        <!-- <h2>Information</h2> -->
        <v-flex xs12 sm5 d-flex>
          <v-autocomplete
            :items="organizeList"
            item-text="organizeName"
            item-value="organizeId"
            :menu-props="{ maxHeight: '400' }"
            label="* Select Organize"
            persistent-hint
            :disabled="isEditing"
          ></v-autocomplete>
        </v-flex>

        <v-text-field label="* Event Name" required :disabled="isEditing"></v-text-field>

        <br />

        <v-layout class="mb-4">
          <v-textarea name="description" label="Description" rows="3" :disabled="isEditing"></v-textarea>
        </v-layout>

        <v-flex xs12 sm5 d-flex>
          <v-autocomplete
            :items="getCategory"
            item-text="categoryLabel"
            item-value="categoryName"
            :menu-props="{ maxHeight: '400' }"
            label="Category (Limit to 3 tags only)"
            multiple
            persistent-hint
            :disabled="isEditing"
          ></v-autocomplete>
        </v-flex>

        <v-menu
          ref="menu"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
          :disabled="isEditing"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              label="eventStartDate"
              prepend-icon="today"
              readonly
              v-on="on"
              :disabled="isEditing"
            ></v-text-field>
          </template>
          <v-date-picker ref="picker" min="1950-01-01" @change="save" :disabled="isEditing"></v-date-picker>
        </v-menu>

        <v-menu
          ref="menu"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
          :disabled="isEditing"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              label="eventEndDate"
              prepend-icon="today"
              readonly
              v-on="on"
              :disabled="isEditing"
            ></v-text-field>
          </template>
          <v-date-picker ref="picker" min="1950-01-01" @change="save" :disabled="isEditing"></v-date-picker>
        </v-menu>

        <!-- t -->
<!-- 
        <span class="location" id="locationMap">Location</span>
        <p>{{getEventTemplate.location.detail}}</p>
        <v-btn class="addLocationButton" color="white" @click="addLocation()">Add Location</v-btn>
        <v-layout v-show="isShowLocation" row wrap>
          <client-only>
            <label>
              AutoComplete
              <GmapAutocomplete @place_changed="setPlace"></GmapAutocomplete>
              <button @click="usePlace">Add</button>
            </label>
            <br />
            <GmapMap
              :center="marker.position"
              :zoom="14"
              map-type-id="terrain"
              style="width: 500px; height: 300px"
              :options="{
                scaleControl: true
            }"
            >
              <gmap-info-window
                :position="infoWindowPos"
                :opened="infoWinOpen"
                @closeclick="infoWinOpen=false"
              >
                <p>{{getEventTemplate.location.detail}}</p>
              </gmap-info-window>

              <GmapMarker
                :position="marker.position"
                @click="toggleInfoWindow(marker,0)"
                :clickable="true"
              />
            </GmapMap>
          </client-only>
        </v-layout>
        <br />
        <br />
        <span class="location" id="locationMap">Event Detail</span>
        <v-text-field
          type="number"
          label="amount of ticket"
          hint="Please fill number of ticket for this event."
          :disabled="isEditing"
        ></v-text-field>
        <br /> -->
        <!-- <v-flex xs12>
          <v-text-field
            v-model="eventForm.badge.exp"
            placeholder="Exp of Event"
            label="Exp Of Event"
            type="number"
          ></v-text-field>
        </v-flex> -->

        <!-- <v-flex xs12 d-flex @click="findMatchingBadge()">
          <v-autocomplete
            :items="badgeList"
            box
            chips
            color="#341646"
            label="Select Badge"
            item-value="badgeId"
          >
            <template v-slot:selection="data">
              <v-chip
                v-bind="data.attrs"
                :selected="data.selected"
                color="#341646"
                class="chip--select-multi white--text"
              >
                <v-avatar>
                  <img :src="data.item.badgePicture" />
                </v-avatar>
                <h3>{{ data.item.badgeName }}</h3>
              </v-chip>
            </template>

            <template v-slot:item="data">
              <template v-if="typeof data.item !== 'object'">
                <v-list-tile-content v-text="data.item"></v-list-tile-content>
              </template>
              <template v-else>
                <v-list-tile-avatar>
                  <img :src="data.item.badgePicture" />
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title v-html="data.item.badgeName"></v-list-tile-title>
                </v-list-tile-content>
              </template>
            </template>
          </v-autocomplete>
        </v-flex> -->

        <!--  -->
      </v-form>
    </v-layout>
    <br />
    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;"> -->
      <v-btn
        class="saveButton white--text"
        color="#341646"
        :disabled="isEditing"
        @click="updateProfile()"
        depressed
        large
        height="50"
      >Save</v-btn>
      <!-- </nuxt-link> -->
      <!-- <v-btn color="primary" :disabled="!valid" @click="onSubmit()">ถัดไป</v-btn> -->
    </center>
    <br />
    <br />
  </div>
</template> 
 
 
<script>
import axios from "axios";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "editEventForm",
  components: {},
  data() {
    return {
      isCameraOpen: false,
      limitedSelectNumber: 3,
      badges: [
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
        }
      ],
      date: null,
      menu: false,
      menuEventStartDate: false,

      genderList: ["Male", "Female", "Unspecified"],
      organizeList: [],

      numberRules: [
        v => !!v || "This field is required",
        v => (v && !Number.isNaN(v)) || "Please insert only number"
      ],
      genderRules: [v => !!v || "Please select from choice"],
      textRules: [v => !!v || "This field is required"],
      phoneRules: [
        v => !!v || "Phone Number is required",
        v => (v && v.length === 10) || "Phone Number msut be 10 digit"
      ],
      emailRules: [
        v => !!v || "E-mail is required",
        v => /.+@.+\..+/.test(v) || "E-mail must be valid"
      ],
      nameRules: [
        v => !!v || "Name is required",
        v => (v && v.length >= 2) || "Name must be more than 2 characters"
      ],
      lastnameRules: [
        v => !!v || "Lastname is required",
        v => (v && v.length >= 2) || "Lastname must be more than 2 characters"
      ],
      passwordRules: [
        v => !!v || "Password is required",
        v => v.length >= 8 || "Password must be 8 character",
        v =>
          this.password === this.confirmPassword ||
          "Password and Confirm Password need to be match"
      ],
      passwordRule: [
        v => !!v || "Password is required",
        v => v.length >= 8 || "Password must be 8 character"
      ],
      valid: true,
      isEditing: true,
      userForm: {
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
    ...mapGetters(["getUser", "getEventTemplate"])
  },
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    },
    "eventForm.eventTags"(eventTags) {
      if (eventTags.length > 3) {
        this.eventForm.eventTags.shift();
      }
    }
  },
  mounted() {
    this.initUserProfile();
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
    findMatchingBadge() {
      console.log("mating badge");
      let eventTagsQuery = "";
      if (this.eventForm.eventTags.length > 0) {
        eventTagsQuery = "?badgeTags=";
        for (let i = 0; i < this.eventForm.eventTags.length; i++) {
          eventTagsQuery += `${this.eventForm.eventTags[i]},`;
        }
        eventTagsQuery += "&contentPerPage=50";
      }
      console.log(eventTagsQuery);
      axios
        .get(`${process.env.USER_SERVICE}/badges${eventTagsQuery}`)
        .then(badgeResponse => {
          this.badgeList = badgeResponse.data;
          console.log(badgeResponse.data);
          this.badgeImg = [
            { header: "Social" },
            { name: "Collaborator", avatar: this.badgeList[0].badgePicture },
            { name: "Lucky", avatar: this.srcs[1] },
            { name: "Making a Difference", avatar: this.srcs[2] },
            { name: "Speaker", avatar: this.srcs[3] }
          ];
        })
        .catch(error => {});
      console.log(eventTagsQuery);
      axios
        .get(`http://localhost:4000/userservice/badges${eventTagsQuery}`)
        .then(badgeResponse => {
          this.badgeList = badgeResponse.data;
          console.log(badgeResponse.data);
        })
        .catch(error => {});
    },
    initUserProfile: function() {
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
          //   this.userForm.gender = userProfileForm.gender;
          this.userForm.facebook = userProfileForm.facebook;
          this.userForm.line = userProfileForm.line;
          this.userForm.website = userProfileForm.website;
          this.userForm.twitter = userProfileForm.twitter;
          this.userForm.instagram = userProfileForm.instagram;
          this.userForm.phone = userProfileForm.phone || "";
        })
        .catch(err => {});
    },

    loadEventTemplate() {
      console.log(this.getEventTemplate);
      let eventTemplate = this.getEventTemplate;
      this.eventForm.eventName = eventTemplate.eventName;
      this.eventForm.eventDetail = eventTemplate.eventDetail;
      this.eventForm.eventTags = eventTemplate.eventTags;
      this.eventForm.createEventDate = eventTemplate.createEventDate;
      this.eventForm.endRegisterDate = eventTemplate.endRegisterDate;
      this.eventForm.eventStartDate = eventTemplate.eventStartDate;
      this.eventForm.eventEndDate = eventTemplate.eventEndDate;
      this.eventForm.badge = eventTemplate.badge;
      this.eventForm.exp = eventTemplate.exp;
      this.eventForm.organize.organizeId = eventTemplate.organize.organizeId;
      this.eventForm.numberOfTicket = eventTemplate.numberOfTicket;

      let geopoint = eventTemplate.location.geopoint;

      if ((geopoint.lat === 0) & (geopoint.lon === 0)) {
        console.log("initital value");
        navigator.geolocation.getCurrentPosition(location => {
          this.marker.position = {
            lat: location.coords.latitude,
            lng: location.coords.longitude
          };
          console.log(location.coords.latitude);
          console.log(location.coords.longitude);
        });
      } else {
        this.marker.position = {
          lat: geopoint.lat,
          lng: geopoint.lon
        };
      }
      // this.marker.position = {
      //   lat: geopoint.lat,
      //   lng: geopoint.lon
      // };
    },

        toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = marker.position;
      this.infoTitle = marker.title;
      this.infoDetail = marker.detail;

      //check if its the same marker that was selected if yes toggle
      if (this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      //if different marker set infowindow to open and reset current marker index
      else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    },
    addLocation() {
      this.isShowLocation = true;
      this.$vuetify.goTo("#locationMap");
    },

    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload: function(e) {
      // upload file, get it from this.selectedFile
    },
    logout: function() {
      this.$store.dispatch("signOut").then(() => {
        this.$swal({
          type: "success",
          title: "Sign Out !",
          text: `Bye bye see you next time ;)`
        });
        this.$router.push("/");
      });
    },
    save(date) {
      this.$refs.menu.save(date);
    },
    activateInEditMode() {
      this.isEditing = false;
    },
    deActivateInEditMode() {
      this.isEditing = true;
    },
    editProfile() {
      this.edit = false;
    },
    validate() {
      console.log(this.userForm);

      if (this.$refs.form.validate()) {
        console.log("correct fuq you");
        this.snackbar = true;
      } else {
        console.log("stupid please correct");
      }
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    onSubmit() {
      console.log(this.userForm);

      let rawTel = this.userForm.telephone;
      this.userForm.telephone = rawTel.replace(/^0/g, "+66");
      this.userForm.gender = this.genderList.values;
    },
    updateProfile: function() {
      console.log(this.userForm);
      axios
        .put(
          `${process.env.USER_SERVICE}/user/${this.getUser.uid}`,
          this.userForm,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
            }
          }
        )
        .then(updateResponse => {
          this.$swal({
            type: "success",
            title: "Update Profile success !",
            text: `Update Profile success`
          });
        })
        .catch(err => {
          console.log(err);
          this.$swal({
            type: "error",
            title: "Update Profile fail !",
            text: err
          });
        });

      // console.log({
      //   email: this.userForm.email,
      //   password: this.userForm.password,
      //   birthDate: this.userForm.dateOfBirth,
      //   firstName: this.userForm.firstname,
      //   lastName: this.userForm.lastname,
      //   gender: this.userForm.gender
      // });
    }
  }
};
</script> 
 
 
<style>
.accountButton {
  width: 500px;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
</style> 


