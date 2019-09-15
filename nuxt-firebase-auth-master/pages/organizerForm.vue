<template>
  <div>
    <br />
    <h3>Organizer Account</h3>
    <br />
    <br />
    <p>You are signed in as :</p>
    {{names}}
    <br />
    <br />

    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="switchAccount #341646--text"
          color="#fff"
          depressed
          large
          height="40"
        >Switch Account</v-btn>
      </nuxt-link>
    </center>
    <br />

    <v-form>
      <v-text-field
        v-model="organizerName"
        :rules="organizerNameRules"
        label="* Organizer Name"
        required
      ></v-text-field>

      <v-layout row wrap>
        <v-flex xs3>
          <v-select :items="phone" label="TH" prepend-icon="phone"></v-select>
        </v-flex>
        <v-flex xs9 sm9 md9>
          <v-text-field block label="* Mobile Phone" v-model="phone"></v-text-field>
        </v-flex>
      </v-layout>
    </v-form>

    <v-checkbox v-model="checkbox" :error-messages="checkboxErrors" label="* Accept" required></v-checkbox>

    <nuxt-link to="/">Term of service</nuxt-link>
    <p class="termOfServiceDescription grey--text">
      I hereby accept this agreement and provided
      <br />my identification and company registration document as proof.
    </p>

    <br />
    <br />
    <center>
      <nuxt-link :to="`/organizerAccountCreate?`" style="text-decoration-line:none;">
        <v-btn
          block
          class="setupOrganizerAccount white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Setup Organizer Account</v-btn>
      </nuxt-link>
    </center>

    <br />
    <br />
    <br />
    <br />

    <!-- <v-form>
        <span>* Organizer Name</span>
        <v-text-field
            outlined
          ></v-text-field>

          <span>* Mobile Phone</span>
        <v-text-field
            outlined
          ></v-text-field>
    </v-form>-->
  </div>
</template>


<script>
export default {
  name: "organizerForm",
  data() {
    return {
      names: [
        {
          name: "A"
        }
      ],
      organizerNameRules: [v => !!v || "Organizer Name is required"],

      checkbox: false
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

.h3 {
  font-family: Roboto !important;
  font-weight: bold !important;
  font-size: 24px !important;
}

.switchAccount {
  font-family: Roboto !important;

  border: solid 1px #341646 !important;
}

</style>