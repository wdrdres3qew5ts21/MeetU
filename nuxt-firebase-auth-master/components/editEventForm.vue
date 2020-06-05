<template>
  <div>
    <br />
    <v-layout>
      <h2 style="color:#341646">Edit Event</h2>

      <v-flex class="text-xs-right">
        <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
          <v-icon color="#fff" medium>edit</v-icon>
        </v-btn>

        <v-btn fab dark small color="red" @click="confirmPopup">
      <v-icon color="#fff" medium>delete</v-icon>
    </v-btn>
      
        <!-- <v-btn depressed flat @click="confirmPopup">
          <v-icon color="#341646" medium>delete</v-icon>
        </v-btn> -->
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

        <span class="location" id="locationMap">Location</span>
        <p>{{getEventTemplate.location.detail}}</p>
        <v-btn
          class="addLocationButton #341646--text"
          outline color="primary"
          @click="addLocation()"
          :disabled="isEditing"
        >Add Location</v-btn>

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
        <br />
        <v-flex xs12>
          <v-text-field
            placeholder="Exp of Event"
            label="Exp Of Event"
            type="number"
            :disabled="isEditing"
          ></v-text-field>
        </v-flex>

        <v-flex xs12 d-flex @click="findMatchingBadge()">
          <v-autocomplete
            v-model="eventForm.badge.badgeId"
            :items="badgeList"
            box
            chips
            color="#341646"
            label="Select Badge"
            item-value="badgeId"
            :disabled="badgeSelectDisabled"
          >
            <template v-slot:selection="data">
              <v-chip
                v-bind="data.attrs"
                :selected="data.selected"
                color="#341646"
                class="chip--select-multi white--text"
                @click:close="remove(data.item)"
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
        </v-flex>
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
      <br />
      <br />
      <v-btn color="error" @click="confirmPopup()" block>Delete an event</v-btn>
    </center>
    <br />
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";
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
      badgeSelectDisabled: true,
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
      },
      eventForm: {
        numberOfTicket: 10,
        organize: {
          organizeId: ""
        },
        eventName: "",
        eventDetail: "",
        eventTags: [],
        location: "",
        eventStartDate: "",
        eventEndDate: "",
        selectedCategory: "",
        location: {
          detail: "",
          subDistrict: "",
          distrct: "",
          province: "",
          country: "",
          geopoint: {
            lat: 0,
            lon: 0
          }
        },
        badge: {
          badgeId: "",
          exp: 30
        },
        badgeSelect: ["", ""],
        badgeImg: [
          // { header: "Social" },
          // { name: "Collaborator", avatar: this.srcs[0] },
          // { name: "Lucky", avatar: this.srcs[1] },
          // { name: "Making a Difference", avatar: this.srcs[2] },
          // { name: "Speaker", avatar: this.srcs[3] }
        ]
      },
      isShowLocation: false,
      marker: {
        icon:
          "https://png2.kisspng.com/sh/5a457e82acb6e22a2ae8836f35448931/L0KzQYm3VMAzN6dBiZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfJ2e5pzfeV8LYfygrztjP94NZVuf9t9YXywhMPojwNnd6NyRd94dnWwRbLqUvQ2bGE3e9dqN0axQ4S8Vsc2OGc2TaQ7N0G7QYe3Ucg1NqFzf3==/kisspng-computer-icons-business-workflow-digital-transform-move-5ac2d5d02cea76.335675061522718160184.png",
        title: "Digital Transformation 4.0 By IMC",
        detail:
          "พบกับผู้ที่คร่ำหวอดในวงการอุตสาหกรรมที่พร้อมจะมาพลิกโฉมอุตสาหกรรมของท่านให้ก้าวไปสู่ยุค Thailand 4.0",
        position: {
          lat: 13.6518128,
          lng: 100.4937549
        }
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
    this.findMatchingBadge();
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
        .get(`${process.env.USER_SERVICE}/badges${eventTagsQuery}`)
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

    setPlace(place) {
      this.place = place;
      console.log("----set place----");
      console.log(this.place);
      let addresscomponents = this.place.address_components;
      let detail = this.place.formatted_address;
      let streetNumber = addresscomponents[0].long_name;
      let road = addresscomponents[1].long_name;
      let subDistrict = addresscomponents[2].long_name;
      let distrct = addresscomponents[3].long_name;
      let province = addresscomponents[4].long_name;
      let country = addresscomponents[5].long_name;

      this.setEventLocation({
        place,
        detail,
        streetNumber,
        road,
        subDistrict,
        distrct,
        province,
        country
      });
      console.log({
        place,
        detail,
        streetNumber,
        road,
        subDistrict,
        distrct,
        province,
        country
      });
      this.usePlace(this.place);
    },
    usePlace(place) {
      if (this.place) {
        this.marker.position = {
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng()
        };
        this.center = {
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng()
        };
      }
      console.log("--- place ----");
      console.log("---- pin location ---- ");
      console.log(this.marker);
      this.setGeopoint({
        lat: this.marker.position.lat,
        lon: this.marker.position.lng
      });
      this.place = null;
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

    saveEventTemplate() {
      console.log("SAve Tempalte");
      console.log(this.eventForm);
      this.setEventTemplate(this.eventForm);
      this.setBadgeDetail(this.eventForm.badge);
    },

    save(date) {
      this.$refs.menu.save(date);
    },

    onSubmit() {
      console.log({
        eventname: this.eventForm.eventName,
        eventdetail: this.eventForm.eventDetail,
        eventStartDate: this.eventForm.eventStartDate,
        eventEndDate: this.eventForm.eventEndDate,
        location: this.location,
        category: this.eventForm.selectedCategoryList
      });
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
    remove(item) {
      const index = this.badgeSelect.indexOf(item.badgeName);
      if (index >= 0) this.badgeSelect.splice(index, 1);
      // console.log(item.badgeName)
      //   // const index = this.badgeSelect.indexOf(item.badgeSelect)
      //   console.log(this.badgeSelect)
      //   console.log(index)
      //   if (index >= 0) this.badgeSelect.splice(index, 1)
    },
    confirmPopup: function(e) {
      Swal.fire({
        title: "Do you want to delete this event?",
        inputPlaceholder: "Enter event name for confirmation",
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
            cancelButtonText: "No, keep it!",

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

.location {
  font-family: Roboto !important;
  font-size: 18px;
  color: #341646 !important;
  font-weight: bold !important;
}
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


