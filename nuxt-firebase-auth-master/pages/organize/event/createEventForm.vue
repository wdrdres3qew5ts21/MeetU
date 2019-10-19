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

    <!-- test -->

    <!-- <br />

    <v-btn color="primary" dark @click="badgeSelect = true">Select Badge</v-btn>

    <v-dialog v-model="badgeSelect" max-width="500px">
        <v-card>
          <v-card-title>
            <span>Dialog 3</span>
            <v-spacer></v-spacer>
            <v-menu bottom left>
              <template v-slot:activator="{ on }">
                <v-btn icon v-on="on">
                  <v-icon>more_vert</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-tile v-for="(item, i) in items" :key="i">
                  <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                </v-list-tile>
              </v-list>
            </v-menu>
          </v-card-title>
          <v-card-actions>
            <v-btn color="primary" flat @click="badgeSelect=false">Close</v-btn>
          </v-card-actions>
        </v-card>
    </v-dialog>-->

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

    <br />
    <br />
    <p style="margin:0" class="uploadPosterImg" @click="goToEventConditionPage()">Event Conditions Setting</p>
    <p style="margin:0" class="uploadPosterImg" @click="goToUploadImagePage()">Upload poster image</p>
    <p style="margin:0" class="uploadPosterImg" @click="goToBadgeSettingPage()">Create Badge</p>
    <!-- <nuxt-link class="eventCondition" to="/organize/event/eventCondition" @click="saveEventTemplate()">Event Conditions Setting</nuxt-link>
    <br />
    <nuxt-link class="uploadPosterImg" to="/organize/event/uploadPosterImg" @click="saveEventTemplate()">Upload poster image</nuxt-link>
    <br />
    <nuxt-link class="createBadge" to="/organize/event/createBadge" @click="saveEventTemplate()">Create Badge</nuxt-link> -->
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
        selectedCategory: ""
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
      badgeSelect: false
    };
  },
  computed: {
    ...mapGetters(["getCategory", "getEventTemplate"])
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
    this.loadEventTemplate()
  },
  methods: {
    ...mapActions(["setEventTemplate"]),
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
    },
    goToPreviewEvent(){
      this.saveEventTemplate()
      this.$router.push('/organize/event/previewEvent')
    },
    goToUploadImagePage(){
      this.saveEventTemplate()
      this.$router.push('/organize/event/uploadPosterImg')
    },
    goToEventConditionPage(){
      this.saveEventTemplate()
      this.$router.push('/organize/event/eventCondition')
    },
    goToBadgeSettingPage(){
      this.saveEventTemplate()
      this.$router.push('/organize/event/createBadge')
    },
    saveEventTemplate(){
      console.log("SAve Tempalte")
      console.log(this.eventForm)
      this.setEventTemplate(this.eventForm)
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