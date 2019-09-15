<template>
  <div>
    <br />
    <h1 style="color:#341646">Create New Events</h1>
    <br />

    <v-form>
      <v-text-field v-model="eventName" :rules="eventNameRules" label="* Event Name" required></v-text-field>
    </v-form>

    <v-flex xs12 sm5 d-flex>
      <v-select :items="categoryEventList" label="Category" v-model="categoryEvent"></v-select>
    </v-flex>

    <v-flex xs12 sm5 d-flex>
      <v-select :items="eventTypes" label="Event Types" v-model="eventTypes"></v-select>
    </v-flex>


    <v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menuDate1"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field v-model="date" label="* Event Starts" prepend-icon="event" readonly v-on="on"></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>


    <v-col cols="11" sm="5">
      <v-menu
        ref="menuTime"
        v-model="menuTime1"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="time"
        transition="scale-transition"
        offset-y
        full-width
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="time"
            label="Time :"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menuTime1"
          v-model="time"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>

<v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menuDate2"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field v-model="date" label="* Event Ends" prepend-icon="event" readonly v-on="on"></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>


    <v-col cols="11" sm="5">
      <v-menu
        ref="menuTime"
        v-model="menuTime2"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="time"
        transition="scale-transition"
        offset-y
        full-width
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="time"
            label="Time :"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menuTime2"
          v-model="time"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>

<br>

    <span class="location">Location</span>
    <p class="locationDescription">Add a location of your event and a short description of how to get there.</p>

    <v-btn class="addLocationButton" color="white">Add Location</v-btn>

    <br><br><br>
<!-- 
        <v-file-input
    label="File input"
    filled
    prepend-icon="mdi-camera"
  ></v-file-input> -->


    <nuxt-link class="uploadPosterImg" to="/uploadPosterImg?">Upload poster image </nuxt-link>
    
    <br>
    <nuxt-link class="createTicket" to="/createNewTicket?">Create ticket </nuxt-link>

    <br />
    <br />
    <br>

    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="saveButton white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Save</v-btn>
      </nuxt-link>
    </center>

    <br><br>
  </div>
</template>


<script>
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

      date: new Date().toISOString().substr(0, 10),
      menuDate1: false,
      menuDate2:false,
      time:null,
      menuTime1:false,
      menuTime2:false,
    
    };
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


.location{
    font-family: Roboto !important;
    font-size: 18px;
    color: #341646 !important;
    font-weight: bold !important;
}


.locationDescription{
    color: #707070 !important;
}

.addLocationButton{
border: solid 1px #341646 !important;
}



.uploadPosterImg{
    font-family: Roboto;
    font-size: 18px;
    font-weight: bold;
    color: #100C4B;
    text-decoration: underline;
}


.createTicket{
    font-family: Roboto;
    font-size: 18px;
    font-weight: bold;
    color: #100C4B;
    text-decoration: underline;
}
</style>