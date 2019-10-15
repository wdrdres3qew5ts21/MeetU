<template>
  <div>

    <br />
    <h3 class="h3">Sale Period</h3>

    <br />

    <v-menu
      ref="menu"
      v-model="menuSaleStartDate"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      full-width
      min-width="290px"
    >
    
      <template v-slot:activator="{ on }">
        <v-text-field
          v-model="ticketForm.saleStartDate"
          label="saleStartDate"
          prepend-icon="today"
          readonly
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker
        ref="picker"
        v-model="ticketForm.saleStartDate"
        min="1950-01-01"
        @change="save"
      ></v-date-picker>
    </v-menu>
    

     <v-layout row wrap>
    <v-flex xs11 sm5>
      <v-menu
        ref="menu"
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="time"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="ticketForm.startTime"
            label="Start Time"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu2"
          v-model="ticketForm.startTime"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-flex>
  </v-layout>

    <v-menu
      ref="menu"
      v-model="menuSaleEndDate"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      full-width
      min-width="290px"
    >
      <template v-slot:activator="{ on }">
        <v-text-field
          v-model="ticketForm.saleEndDate"
          label="saleEndDate"
          prepend-icon="today"
          readonly
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker ref="picker" v-model="ticketForm.saleEndDate" min="1950-01-01" @change="save"></v-date-picker>
    </v-menu>

<v-flex xs11 sm5>
      <v-menu
        ref="menu"
        v-model="menu1"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="time"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="ticketForm.endTime"
            label="Start Time"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu1"
          v-model="ticketForm.endTime"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-flex>

    <!-- <h3 class="h3">Age Restriction</h3>
    Minimum Age
    <p class="minAgeDescription">Restrict attendees age must be at least</p>
    <v-form>
      <v-text-field type="number" max="100" min="1" step="1" v-model="ticketForm.minAge">

      </v-text-field>
    </v-form>

    <br />

    Maximum Age
    <p class="maxAgeDescription">Restrict attendees age must be under</p>
    <v-form>
      <v-text-field type="number" max="100" min="1" step="1"
      v-model="ticketForm.maxAge"></v-text-field>
    </v-form> -->
    <br />

    <center>
      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;"> -->
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      <!-- </nuxt-link> -->

      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;"> -->
        <v-btn class="saveButton white--text" color="#341646" depressed large height="50"
        @click="onSubmit()">Save</v-btn>
      <!-- </nuxt-link> -->
    </center>
    <br />
  </div>
</template>

<script>
export default {
  name: "createEventForm",
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      menuSaleStartDate: false,
      menuSaleEndDate: false,
    
        time: null,
        menu2: false,
        menu1: false,
        modal2: false,
      startTime:'',
      ticketForm: {
        quantityOfTicket: "",
        saleStartDate: "",
        startTime: "",
        saleEndDate: "",
        minAge: "",
        maxAge: "",
        endTime: ""
      }
    };
  },
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    }
  },
  methods: {
    save(date) {
      this.$refs.menu.save(date);
    },
    onSubmit() {
      console.log({
        quantity: this.ticketForm.quantityOfTicket,
        saleStartDate: this.ticketForm.saleStartDate,
        saleEndDate: this.ticketForm.saleEndDate,
        startTime: this.ticketForm.startTime,
        endTime: this.ticketForm.endTime
      });
    },
    
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

.h3 {
  color: #341646;
}

.minAge {
  font-family: Roboto;
  font-size: 16px;
  font-weight: bold;
}

.minAgeDescription {
  color: #707070;
  font-size: 14px;
}

.maxAgeDescription {
  color: #707070;
  font-size: 14px;
}
</style>