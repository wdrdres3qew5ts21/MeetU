<template>
  <div>
    <br />
    <h2 class="h2">Create New Events</h2>
    <br />

    <v-form>
      <v-text-field v-model="event.eventName" :rules="eventNameRules" label="* Event Name" required></v-text-field>
    </v-form>

    <v-flex xs12 sm5 d-flex>
      <v-select :items="categoryEventList" label="Category" v-model="categoryEvent"></v-select>
    </v-flex>

    <!-- <v-flex xs12 sm5 d-flex>
      <v-select :items="eventTypes" label="Event Types" v-model="eventTypes"></v-select>
    </v-flex>-->

<v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="Start Date: "
            prepend-icon="event"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>

<!-- <v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menu"
        v-model="menu2"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="event.eventEndDate"
            label="* Event Ends"
            prepend-icon="event"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col> -->

    



    <br />

    <span class="location">Location</span>
    <p
      class="locationDescription"
    >Add a location of your event and a short description of how to get there.</p>

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

    <nuxt-link class="uploadPosterImg" to="/uploadPosterImg?">Upload poster image</nuxt-link>

    <br />
    <nuxt-link class="createTicket" to="/createNewTicket?">Create ticket</nuxt-link>

    <br />
    <br />
    <br />

    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn 
        block 
        class="saveButton white--text" 
        color="#341646" 
        depressed 
        large 
        height="50"
        @click="createEventSave()"
        >Save
        
        </v-btn>
      </nuxt-link>
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
      event: {
        eventName: "",
        eventDetail: "",
        eventStartDate: new Date().toISOString().substr(0, 10),
        eventEndDate: new Date().toISOString().substr(0, 10),
        endRegisterDate: new Date().toISOString().substr(0, 10)
      },
      date:new Date().toISOString().substr(0, 10),
     
      menu:false,
      eventNameRules: [v => !!v || "Event name is required"],
      location:"",
    };
  },
  computed: {
    ...mapGetters(["getCategory"])
  },
  watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },

  mounted() {
    axios.get("http://localhost:4000/userservice/users").then(value => {
      console.log(value);
    });
  },
  methods: {
    save (date) {
        this.$refs.menu.save(date)
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

.createTicket {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}
</style>