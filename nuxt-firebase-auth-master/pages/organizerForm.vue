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

    <v-form ref="form" v-model="valid" :lazy-validation="false">
      <v-text-field
        v-model="organizerName"
        :rules="organizerNameRules"
        label="* Organizer Name"
        required
      ></v-text-field>

      <v-text-field
        label="Phone number"
        :rules="phoneRules"
        placeholder="phone number"
        counter="10"
        prepend-icon="phone"
        v-model="telephone"
        type="number"
      ></v-text-field>
    </v-form>

    <v-checkbox v-model="agreement" :rules="[rules.required]" color="#341646">
      <template v-slot:label>
        I agree to the&nbsp;
        <nuxt-link to="/" @click.stop.prevent="dialog = true">Terms of Service</nuxt-link>
      </template>
    </v-checkbox>

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
  </div>
</template>


<script>
export default {
  name: "organizerForm",
  data() {
    return {
      telephone: "",
      valid: true,
      names: [
        {
          name: "A"
        }
      ],
      organizerNameRules: [
        v => !!v || "Organizer Name is required",
        v => (v && v.length >= 2) || "Name must be more than 2 characters"
      ],
      checkbox: false,
      agreement: false,
      rules: {
        required: v => !!v || "This field is required",
       
      },
       phoneRules: [
          v => !!v || "Phone Number is required",
          v => (v && v.length === 10) || "Phone Number msut be 10 digit"
        ]
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