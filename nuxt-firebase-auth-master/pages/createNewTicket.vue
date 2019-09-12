<template>
  <div>
    <br />
    <h2 class="h2">Create a New Tickets</h2>
    <br />
    <h3 class="h3">Pricing</h3>
    <v-flex>
      <v-select :items="priceTicket" v-model="priceTicket"></v-select>
    </v-flex>

    <br />

    <h3 class="h3">Ticket Type Details</h3>
    <v-form>
      <v-text-field
        v-model="ticketTypeName"
        :rules="ticketTypeNameRules"
        label="* Ticket Type Name"
        required
      ></v-text-field>
    </v-form>

    <v-text-field
      v-model="rows"
      type="number"
      label="* Quantity Available"
      max="100000"
      min="1"
      step="1"
    ></v-text-field>

    <br />
    <h3 class="h3">Sale Period</h3>

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
          <v-text-field
            v-model="date"
            label="* Sale Starts"
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
          <v-text-field v-model="time" label="Time :" prepend-icon="access_time" readonly v-on="on"></v-text-field>
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
          <v-text-field v-model="date" label="* Sale Ends" prepend-icon="event" readonly v-on="on"></v-text-field>
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
          <v-text-field v-model="time" label="Time :" prepend-icon="access_time" readonly v-on="on"></v-text-field>
        </template>
        <v-time-picker
          v-if="menuTime2"
          v-model="time"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>

    <br />
    <h3 class="h3">Age Restriction</h3>
    <span class="minAge">Minimum Age</span>
    <p class="minAgeDescription">Restrict attendees age must be at least</p>
    <v-form>
      <v-text-field v-model="rows" type="number" max="100" min="1" step="1"></v-text-field>
    </v-form>

    <br />

    <span class="minAge">Maximum Age</span>
    <p class="maxAgeDescription">Restrict attendees age must be under</p>
    <v-form>
      <v-text-field v-model="rows" type="number" max="100" min="1" step="1"></v-text-field>
    </v-form>
    <br><br>

    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="saveButton white--text" color="#341646" depressed large height="50">Save</v-btn>
      </nuxt-link>
    </center>
    <br>
  </div>
</template>

<script>
export default {
  name: "createEventForm",
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      menuDate1: false,
      menuDate2: false,
      time: null,
      menuTime1: false,
      menuTime2: false
    };
  }
};
</script>

<style lang="css">
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