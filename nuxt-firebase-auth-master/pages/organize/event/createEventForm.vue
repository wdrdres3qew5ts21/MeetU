<template>
  <div>
    <br />
    <h2 class="h2">Create New Events</h2>
    <br />
    <v-form ref="form" v-model="valid" :lazy-validation="false">
      <v-flex xs12 sm5 d-flex>
        <v-autocomplete
          :items="organizeList"
          item-text="organizeName"
          item-value="organizeId"
          :menu-props="{ maxHeight: '400' }"
          label="* Select Organize"
          v-model="eventForm.organize.organizeId"
          persistent-hint
          required
          :rules="[v => !!v || 'organize is required']"
        ></v-autocomplete>
      </v-flex>
      <v-text-field v-model="eventForm.eventName" label="* Event Name" required :rules="nameRules"></v-text-field>
      <!-- <v-text-field v-model="eventForm.eventDetail"  placeholder="Event Detail" required></v-text-field> -->
      <v-layout class="mb-4">
        <v-textarea
          name="description"
          label="Description"
          v-model="eventForm.eventDetail"
          rows="3"
          required
          :rules="[v => !!v || 'Description is required']"
        ></v-textarea>
      </v-layout>
      <v-flex xs12 sm5 d-flex>
        <v-autocomplete
          :items="getCategory"
          item-text="categoryLabel"
          item-value="categoryName"
          :menu-props="{ maxHeight: '400' }"
          label="Category (Limit to 3 tags only)"
          v-model="eventForm.eventTags"
          multiple
          persistent-hint
          required
          :rules="[v => !!v || 'Category is required']"
        ></v-autocomplete>

        <!-- <v-date-picker v-model="eventForm.eventStartDate"></v-date-picker>
        <v-time-picker v-model="eventForm.eventStartTime" format="24hr"></v-time-picker>-->
      </v-flex>
      <!-- Evant Start Date -->

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
            v-model="eventForm.eventStartDateTempt"
            label="eventStartDate"
            prepend-icon="today"
            readonly
            v-on="on"
            required
            :rules="[v => !!v || 'eventStartDate is required']"
          ></v-text-field>
        </template>
        <v-date-picker
          ref="picker"
          v-model="eventForm.eventStartDateTempt"
          :min="nowDate"
          @change="save"
        ></v-date-picker>
      </v-menu>

      <!-- Evant Start Date -->
      <!-- Event Start time -->
      <v-dialog
        ref="dialogEventStartTime"
        v-model="modalForStartTime"
        :return-value.sync="eventForm.eventStartTimeTempt"
        persistent
        lazy
        full-width
        width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="eventForm.eventStartTimeTempt"
            label="eventStartTime"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="modalForStartTime"
          v-model="eventForm.eventStartTimeTempt"
          full-width
          use-seconds
        >
          <v-spacer></v-spacer>
          <v-btn flat color="primary" @click="modalForStartTime = false">Cancel</v-btn>
          <v-btn
            flat
            color="primary"
            @click="$refs.dialogEventStartTime.save(eventForm.eventStartTimeTempt)"
          >OK</v-btn>
        </v-time-picker>
      </v-dialog>

      <!-- Event Start time -->
      <!-- Event End Date -->
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
            :disabled="eventForm.eventStartDateTempt ==''"
            v-model="eventForm.eventEndDateTempt"
            required
            label="eventEndDate"
            prepend-icon="today"
            readonly
            v-on="on"
            :rules="[v => !!v || 'eventEndDate is required']"
          ></v-text-field>
        </template>
        <v-date-picker
          ref="picker"
          v-model="eventForm.eventEndDateTempt"
          :min="eventForm.eventStartDateTempt"
          @change="save"
        ></v-date-picker>
      </v-menu>
      <!-- Event End Date -->

      <!-- Event End time -->
      <v-dialog
        ref="dialogEventEndTime"
        v-model="modalForEndTime"
        persistent
        lazy
        full-width
        width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            :disabled="eventForm.eventStartTimeTempt == null"
            v-model="eventForm.eventEndTimeTempt"
            label="eventEndTime"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="modalForEndTime"
          v-model="eventForm.eventEndTimeTempt"
          full-width
          use-seconds
        >
          <v-spacer></v-spacer>
          <v-btn flat color="primary" @click="modalForEndTime = false">Cancel</v-btn>
          <v-btn
            flat
            color="primary"
            @click="$refs.dialogEventEndTime.save(eventForm.eventEndTimeTempt)"
          >OK</v-btn>
        </v-time-picker>
      </v-dialog>
      <!-- Event End time -->
      <!-- <v-btn @click="test()"> Test</v-btn> -->
      <br />

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
        v-model="eventForm.numberOfTicket"
        label="amount of ticket"
        hint="Please fill number of ticket for this event."
        required
        :rules="[v => !!v || 'Number of Ticket is required']"
      ></v-text-field>
      <br />
      <v-flex xs12>
        <v-text-field
          v-model="eventForm.badge.exp"
          placeholder="Exp of Event"
          label="Exp Of Event"
          type="number"
          required
          :rules="[v => !!v || '']"
        ></v-text-field>
      </v-flex>

      <!-- <v-flex xs12 sm5 d-flex @click="findMatchingBadge()">
      <v-autocomplete
        :items="badgeList"
        item-text="badgeName"
        item-value="badgeId"
        :menu-props="{ maxHeight: '400' }"
        label="Select Existing Badge"
        v-model="eventForm.badge.badgeId"
        persistent-hint
      ></v-autocomplete>
      </v-flex>-->

      <!-- test -->

      <v-flex xs12 d-flex @click="findMatchingBadge()">
        <v-autocomplete
          v-model="eventForm.badge.badgeId"
          :items="badgeList"
          box
          chips
          color="#341646"
          label="Select Badge"
          item-value="badgeId"
          required
          :rules="[v => !!v || 'badge is required']"
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

      <!-- ----- -->

      <!-- Test อีกรอบ -->

      <!-- <v-flex xs12 d-flex @click="findMatchingBadge()">
            <v-autocomplete
              v-model="badgeSelect"
              :items="badgeList"
              box
              chips
              color="#341646"
              label="Select Badge"
              item-value="badgeId"
              multiple
            >
              <template v-slot:selection="data">
                <v-chip
                  :selected="data.selected"
                  close
                  color="grey"
                  class="chip--select-multi white--text"
                  @input="remove(data.item)"
                >
                  <v-avatar>
                    <img :src="data.item.badgePicture" />
                  </v-avatar>
                  {{ data.item.name }}
                </v-chip>
              </template>
              <template v-slot:item="data">
                <template v-if="typeof data.item !== 'object'">
                  <v-list-tile-content v-text="data.item.badgeName"></v-list-tile-content>
                </template>
                <template v-else>
                  <v-list-tile-avatar>
                    <img :src="data.item.badgePicture" />
                  </v-list-tile-avatar>
                  <v-list-tile-content>
                    <v-list-tile-title v-html="data.item.name"></v-list-tile-title>
                    <v-list-tile-sub-title v-html="data.item.badgeName"></v-list-tile-sub-title>
                  </v-list-tile-content>
                </template>
              </template>
            </v-autocomplete>
      </v-flex>-->

      <!--  -->

      <p style="margin:0" class="uploadPosterImg" @click="goToBadgeSettingPage()">Create Badge</p>
      <!-- <p
      style="margin:0"
      class="uploadPosterImg"
      @click="goToEventConditionPage()"
      >Event Conditions Setting</p>-->
      <p style="margin:0" class="uploadPosterImg" @click="goToUploadImagePage()">Upload poster image</p>

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
    </v-form>
  </div>
</template>


<script>
import { mapGetters, mapActions } from "vuex";
import axios from "axios";
export default {
  name: "createEventForm",
  data() {
    return {
      nowDate: new Date().toISOString().slice(0, 10),
      srcs: [
        "https://vignette.wikia.nocookie.net/badges/images/7/70/Collaborator-icon.png/revision/latest?cb=20131203084742",
        "https://vignette.wikia.nocookie.net/badges/images/8/83/Lucky_Edit-icon.png/revision/latest?cb=20131203085335",
        "https://vignette.wikia.nocookie.net/badges/images/2/28/Making_a_Difference-icon.png/revision/latest?cb=20131203084745",
        "https://vignette.wikia.nocookie.net/badges/images/1/1f/Speaker-icon.png/revision/latest?cb=20131203085342"
      ],
      isShowLocation: false,
      menuEventStartDate: false,
      menuEventEndDate: false,
      valid: true,
      organizeList: [],
      eventForm: {
        numberOfTicket: 10,
        organize: {
          organizeId: ""
        },
        eventName: "",
        eventDetail: "",
        eventTags: [],
        location: "",
        eventEndDateAndTime: "",
        eventStartDateAndTime: "",
        eventStartDate: "",
        eventEndDate: "",
        eventStartTime: null,
        eventEndTime: null,

        eventStartDateTempt: "",
        eventEndDateTempt: "",
        eventStartTimeTempt: null,
        eventEndTimeTempt: null,

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
          exp: 0
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
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      areaOfEvent: "",
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35
        }
      },
      badgeList: [],
      nameRules: [
        v => !!v || "Name is required",
        v => v.length <= 100 || "Name must be less than 100 characters"
      ],
      badgeSelect: false,
      description: "",
      place: null,
      pinLocation: {},
      center: {
        lat: 0,
        lng: 0
      },
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
      },
      modalForStartTime: false,
      modalForEndTime: false
    };
  },
  computed: {
    ...mapGetters([
      "getCategory",
      "getEventTemplate",
      "getCurrentLocation",
      "getUser"
    ]),
    eventStartDateTime() {
      const startDate = new Date(this.eventForm.eventStartDateTempt);
      if (typeof this.eventForm.eventStartTimeTempt === "string") {
        let hours = this.eventForm.eventStartTimeTempt.match(/^(\d+)/)[1];
        const minutes = this.eventForm.eventStartTimeTempt.match(/:(\d+)/)[1];
        startDate.setHours(hours);
        startDate.setMinutes(minutes);
      } else {
        startDate.setHours(this.eventForm.eventStartTimeTempt.getHours());
        startDate.setMinutes(this.eventForm.eventStartTimeTempt.getMinutes());
      }
      return startDate;
    },
    eventEndDateTime() {
      const endDate = new Date(this.eventForm.eventEndDateTempt);
      if (typeof this.eventForm.eventEndTimeTempt === "string") {
        let hours = this.eventForm.eventEndTimeTempt.match(/^(\d+)/)[1];
        const minutes = this.eventForm.eventEndTimeTempt.match(/:(\d+)/)[1];
        endDate.setHours(hours);
        endDate.setMinutes(minutes);
      } else {
        endDate.setHours(this.eventForm.eventEndTimeTempt.getHours());
        endDate.setMinutes(this.eventForm.eventEndTimeTempt.getMinutes());
      }
      return endDate;
    }
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
    this.loadEventTemplate();
    this.loadOrganizeFromUser();
    this.findMatchingBadge();
    if(this.$route.query.organizeId){
      let presetOrganizeId = this.$route.query.organizeId;
      this.eventForm.organize.organizeId = presetOrganizeId;
      console.log(presetOrganizeId);
    }
  },
  methods: {
    ...mapActions([
      "setEventTemplate",
      "setEventLocation",
      "setGeopoint",
      "setBadgeDetail"
    ]),
    setDescription(description) {
      this.description = description;
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
    loadOrganizeFromUser() {
      axios
        .get(`${process.env.USER_SERVICE}/organize/user/${this.getUser.uid}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organizeList = organizeResponse.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    getAddressObject(address_components) {
      let ShouldBeComponent = {
        home: ["street_number"],
        postal_code: ["postal_code"],
        street: ["street_address", "route"],
        region: [
          "administrative_area_level_1",
          "administrative_area_level_2",
          "administrative_area_level_3",
          "administrative_area_level_4",
          "administrative_area_level_5"
        ],
        city: [
          "locality",
          "sublocality",
          "sublocality_level_1",
          "sublocality_level_2",
          "sublocality_level_3",
          "sublocality_level_4"
        ],
        country: ["country"]
      };

      let address = {
        home: "",
        postal_code: "",
        street: "",
        region: "",
        city: "",
        country: ""
      };
      address_components.forEach(component => {
        for (var shouldBe in ShouldBeComponent) {
          if (ShouldBeComponent[shouldBe].indexOf(component.types[0]) !== -1) {
            if (shouldBe === "country") {
              address[shouldBe] = component.short_name;
            } else {
              address[shouldBe] = component.long_name;
            }
          }
        }
      });
      console.log("--------------- parsed fuck you -----------");
      console.log(address);
      return address;
    },
    setPlace(place) {
      this.place = place;
      console.log("----set place----");
      console.log(this.place);

      let addresscomponents = this.place.address_components;
      let parsedAddress = this.getAddressObject(addresscomponents);
      this.usePlace(this.place);

      let detail = place.formatted_address;
      let streetNumber = parsedAddress.home;
      let road = parsedAddress.street;
      // let subDistrict = parsedAddress;
      let distrct = parsedAddress.region;
      let province = parsedAddress.city;
      let country = parsedAddress.country;

      this.setEventLocation({
        place,
        detail,
        streetNumber,
        road,
        // subDistrict,
        distrct,
        province,
        country
      });
      console.log({
        place,
        detail,
        streetNumber,
        road,
        // subDistrict,
        distrct,
        province,
        country
      });
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

      this.eventForm.eventStartDateTempt = eventTemplate.eventStartDateTempt;
      this.eventForm.eventEndDateTempt = eventTemplate.eventEndDateTempt;
      this.eventForm.eventStartTimeTempt = eventTemplate.eventStartTimeTempt;
      this.eventForm.eventEndTimeTempt = eventTemplate.eventEndTimeTempt;

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
      this.formatDateTimeToIsoWhenSave();
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
    loadCategory() {
      axios
        .get(`${process.env.EVENT_SERVICE}/category`)
        .then(categoryResponse => {
          this.categoryEventList = categoryResponse.data;
        });
    },
    createEvent() {
      axios.post(`${process.env.EVENT_SERVICE}/event`);
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
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    formatDateTimeToIsoWhenSave() {
      this.eventForm.eventStartDate = this.eventStartDateTime;
      this.eventForm.eventEndDate = this.eventEndDateTime;
      console.log("------Parsed date to iso8601---------");
      console.log(this.eventForm.eventStartDate);
      console.log(this.eventForm.eventEndDate);

      // let date = new Date(eventEndDateAndTime);
      // eventEndDateAndTime = date.getDate()  + "-" + date.getMonth() + "-" + date.getFullYear() + "  Time  "+ date.getHours() + ":" + date.getMinutes();
      // console.log(eventEndDateAndTime);
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
.v-chip:focus:not(.v-chip--selected):after {
  background: none !important;
}
</style>