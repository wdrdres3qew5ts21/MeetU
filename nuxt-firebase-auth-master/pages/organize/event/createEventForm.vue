<template>
  <div>
    <br />
    <h2 class="h2">Create New Events</h2>
    <br />

    <v-text-field v-model="eventForm.eventName" label="* Event Name" required></v-text-field>
    <!-- <v-text-field v-model="eventForm.eventDetail"  placeholder="Event Detail" required></v-text-field> -->
    <v-layout class="mb-4">
      <v-textarea name="description" label="Description" v-model="eventForm.eventDetail" rows="3"></v-textarea>
    </v-layout>
    <v-flex xs12 sm5 d-flex>
      <v-select
        :items="categoryEventList"
        :menu-props="{ maxHeight: '400' }"
        label="Category (Limit to 3 tags only)"
        v-model="eventForm.eventTags"
        multiple
        persistent-hint
      ></v-select>
    </v-flex>

    <v-menu
      ref="menu"
      v-model="menuEventStartDate"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      full-width
      min-width="290px"
    >
      <template v-slot:activator="{ on }">
        <v-text-field
          v-model="eventForm.eventStartDate"
          label="eventStartDate"
          prepend-icon="today"
          readonly
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker
        ref="picker"
        v-model="eventForm.eventStartDate"
        min="1950-01-01"
        @change="save"
      ></v-date-picker>
    </v-menu>

    <v-menu
      ref="menu"
      v-model="menuEventEndDate"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      full-width
      min-width="290px"
    >
      <template v-slot:activator="{ on }">
        <v-text-field
          v-model="eventForm.eventEndDate"
          label="eventEndDate"
          prepend-icon="today"
          readonly
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker ref="picker" v-model="eventForm.eventEndDate" min="1950-01-01" @change="save"></v-date-picker>
    </v-menu>

    <br />

    <span class="location">Location</span>
    <v-btn class="addLocationButton" color="white">Add Location</v-btn>
    <v-layout row wrap>
      <client-only>
        <label>
          AutoComplete
          <GmapAutocomplete @place_changed="setPlace"></GmapAutocomplete>
          <button @click="usePlace">Add</button>
        </label>
        <br />
        <GmapMap
          :center="center"
          :zoom="14"
          map-type-id="terrain"
          style="width: 500px; height: 300px"
          :options="{
                scaleControl: true
            }"
        >
          <GmapMarker :position="marker.position" :clickable="true" />
        </GmapMap>
      </client-only>
    </v-layout>

    <br />
    <br />
    <p
      style="margin:0"
      class="uploadPosterImg"
      @click="goToEventConditionPage()"
    >Event Conditions Setting</p>
    <p style="margin:0" class="uploadPosterImg" @click="goToUploadImagePage()">Upload poster image</p>
    <p style="margin:0" class="uploadPosterImg" @click="goToBadgeSettingPage()">Create Badge</p>
    <br />
    <br />
    <center>
      <v-btn
        block
        class="saveButton white--text"
        color="#341646"
        depressed
        large
        height="50"
        @click="goToPreviewEvent()"
      >Preview Event</v-btn>
      <v-btn
        block
        color="#AEAEAE"
        class="white--text"
        depressed
        large
        height="50"
        @click="$router.back()"
      >Cancle</v-btn>
    </center>

    <br />
    <br />
  </div>
</template>


<script>
import { mapGetters, mapActions } from "vuex";
import axios from "axios";
export default {
  name: "createEventForm",
  data() {
    return {
      menuEventStartDate: false,
      menuEventEndDate: false,
      eventForm: {
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
        }
      },
      categoryEventList: [
        "Arts",
        "Business",
        "Books",
        "Beauty",
        "Family",
        "Film",
        "Game",
        "Music",
        "Photography",
        "Social",
        "Technology"
      ],
      badgeSelect: false,
      description: "",
      place: null,
      pinLocation: {},
      center: {},
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
    ...mapGetters(["getCategory", "getEventTemplate", "getCurrentLocation"])
  },
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    }
  },
  mounted() {
    // axios.get("http://localhost:4000/userservice/users").then(value => {
    //   console.log(value);
    // });

    this.loadEventTemplate();
  },
  methods: {
    ...mapActions(["setEventTemplate", "setEventLocation", "setGeopoint"]),
    setDescription(description) {
      this.description = description;
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

      let geopoint = eventTemplate.location.geopoint;

      if (geopoint.lat === 0 & geopoint.lon === 0) {
        navigator.geolocation.getCurrentPosition(location => {
          this.center = {
            lat: location.coords.latitude,
            lng: location.coords.longitude
          };
          console.log(location.coords.latitude);
          console.log(location.coords.longitude);
        });
      } else {
        this.center = {
          lat: geopoint.lat,
          lng: geopoint.lon
        };
      }
      this.marker.position = {
        lat: geopoint.lat,
        lng: geopoint.lon
      };
    },
    goToPreviewEvent() {
      this.saveEventTemplate();
      this.$router.push("/organize/event/previewEvent");
    },
    goToUploadImagePage() {
      this.saveEventTemplate();
      this.$router.push("/organize/event/uploadPosterImg");
    },
    goToEventConditionPage() {
      this.saveEventTemplate();
      this.$router.push("/organize/event/eventCondition");
    },
    goToBadgeSettingPage() {
      this.saveEventTemplate();
      this.$router.push("/organize/event/createBadge");
    },
    saveEventTemplate() {
      console.log("SAve Tempalte");
      console.log(this.eventForm);
      this.setEventTemplate(this.eventForm);
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
    loadCategory() {
      axios
        .get(`${process.env.EVENT_SERVICE}/category`)
        .then(categoryResponse => {
          this.categoryEventList = categoryResponse.data;
        });
    },
    createEvent() {
      axios.post(`${process.env.EVENT_SERVICE}/event`);
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

.h2 {
  color: #341646;
}

.location {
  font-family: Roboto !important;
  font-size: 18px;
  color: #341646 !important;
  font-weight: bold !important;
}

.selectBadge {
  font-family: Roboto !important;
  font-size: 18px;
  color: #341646 !important;
  font-weight: bold !important;
}

.locationDescription {
  color: #707070 !important;
}

.addLocationButton {
  border: solid 1px #341646 !important;
}

.selectBudgeButton {
  border: solid 1px #341646 !important;
}

.uploadPosterImg {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}

.eventCondition {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}

.createBadge {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}
</style>