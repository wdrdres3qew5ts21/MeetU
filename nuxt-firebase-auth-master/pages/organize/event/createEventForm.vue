<template>
  <div>
    <br />
    <h2 class="h2">Create New Events</h2>
    <br />

    <v-text-field v-model="eventForm.eventName" label="* Event Name" required></v-text-field>
    <!-- <v-text-field v-model="eventForm.eventDetail"  placeholder="Event Detail" required></v-text-field> -->
    <br />
    <v-layout class="mb-4">
      <v-textarea name="description" label="Description" v-model="eventForm.eventDetail" rows="3"></v-textarea>
    </v-layout>

    <v-flex xs12 sm5 d-flex>
      <v-select :items="categoryEventList" label="Category" v-model="eventForm.selectedCategory"></v-select>
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

    <br />
    <br />
    <br />
    <!-- 
        <v-file-input
    label="File input"
    filled
    prepend-icon="mdi-camera"
    ></v-file-input>-->

    <nuxt-link class="uploadPosterImg" to="/organize/event/uploadPosterImg">Upload poster image</nuxt-link>

    <br />
    <nuxt-link class="eventCondition" to="/organize/event/eventCondition">Event Conditions Setting</nuxt-link>

    <br />
    <br />
    <br />

    <center>
      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;"> -->
      <v-btn
        block
        class="saveButton white--text"
        color="#341646"
        depressed
        large
        height="50"
        @click="onSubmit()"
      >Save</v-btn>
      <!-- </nuxt-link> -->
    </center>

    <br />
    <br />
  </div>
</template>


<script>
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  name: "createEventForm",
  data() {
    return {
      menuEventStartDate: false,
      menuEventEndDate: false,
      eventForm: {
        eventName: "",
        eventDetail: "",
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
      ]
    };
  },
  computed: {
    ...mapGetters(["getCategory"])
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
  },
  methods: {
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

  // console.log({
  //   email: this.userForm.email,
  //   password: this.userForm.password,
  //   birthDate: this.userForm.dateOfBirth,
  //   firstName: this.userForm.firstname,
  //   lastName: this.userForm.lastname,
  //   gender: this.userForm.gender
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

.locationDescription {
  color: #707070 !important;
}

.addLocationButton {
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
</style>