<template>
  <div>
    <br />
    <v-layout>
      <v-flex text-xs-center xs12 sm6 offset-sm3>
        <h1 class="title">Sign Up</h1>
        <br />
        <v-btn
          block
          :round="true"
          color="#DC4B3F"
          class="signIn mb-2 white--text"
          primary
          @click.native="googleSignUp()"
        >Google</v-btn>
        <v-btn
          block
          :round="true"
          color="#3B5998"
          class="signIn mb-2 white--text"
          primary
          @click.native="facebookSignUp()"
        >Facebook</v-btn>
      </v-flex>
    </v-layout>
    <br />
    <hr />
    <br />

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field v-model="email" :rules="emailRules" label="* E-mail" required></v-text-field>
      <v-text-field
        v-model="password"
        type="password"
        :rules="passwordRules"
        label="* Password"
        required
      ></v-text-field>

      <v-text-field
        v-model="confirmPassword"
        type="password"
        :rules="[passwordConfirmationRule]"
        label="* Password Confirmation"
        required
      ></v-text-field>

      <v-text-field v-model="firstname" :rules="firstnameRules" label="* Firstname" required></v-text-field>

      <v-text-field v-model="lastname" :rules="lastnameRules" label="* Lastname" required></v-text-field>

      <v-layout row wrap>
        <v-flex xs12 sm6 md4>
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="birthDate"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="birthDate"
                label="Date of Birth"
                prepend-icon="event"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="birthDate" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="error" @click="menu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.menu.save(birthDate)">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-radio-group v-model="gender" row style="justify-content:center">
          Gender: &nbsp;&nbsp;&nbsp;
          <v-radio label="Male" value="male"></v-radio>
          <v-radio label="Female" value="female"></v-radio>
        </v-radio-group>

        <v-flex xs12 sm5 d-flex>
          <v-select :items="locationList" label="Location" prepend-icon="place" v-model="location"></v-select>
        </v-flex>

        <v-spacer></v-spacer>

        <v-layout row wrap>
          <v-flex xs3>
            <v-select :items="phone" label="TH" prepend-icon="phone"></v-select>
          </v-flex>
          <v-flex xs6 sm6 md6>
            <v-text-field label="Phone" v-model="phone"></v-text-field>
          </v-flex>
        </v-layout>
      </v-layout>

      <v-checkbox
        v-model="checkbox"
        label="By continuing, you agree to MeetU's  
            Terms of Service & Privacy Policy"
        required
      ></v-checkbox>

      <v-btn
        block
        :round="true"
        color="#341646"
        class="signIn mb-2 white--text"
        @click="emailSignUp"
      >Sign Up</v-btn>

      <br />

      <center>
        <h3>
          Already member ?
          <nuxt-link to="/login" style="text-decoration-line:none;" class="linkSignup">Log In</nuxt-link>
        </h3>
      </center>
    </v-form>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";
export default {
  name: "signupForm",
  data: () => ({
    phone: ["TH", "EN"],
    gender: "",
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+/.test(v) || "E-mail must be valid"
    ],

    password: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => v.length >= 8 || "Password must be 8 character",
      v =>
        v.password === v.confirmPassword ||
        "Password and Confirm Password need to be match"
    ],
    confirmPassword: "",

    checkbox: false,

    valid: true,
    firstname: "",
    firstnameRules: [v => !!v || "Firstname is required"],

    valid: true,
    lastname: "",
    lastnameRules: [v => !!v || "Lastname is required"],

    birthDate: new Date().toISOString().substr(0, 10),
    menu: false,

    location: "",

    locationList: [
      "Bangkok",
      "Amnat Charoen",
      "Ang Thong",
      "Bueng Kan",
      "Buriram",
      "Chachoengsao",
      "Chai Nat",
      "Chaiyaphum",
      "Chanthaburi",
      "Chiang Mai",
      "Chiang Rai",
      "Chonburi",
      "Chumphon",
      "Kalasin",
      "Kamphaeng Phet",
      "Kanchanaburi",
      "Khon Kaen",
      "Krabi",
      "Lampang",
      "Lamphun",
      "Loei",
      "Lopburi",
      "Mae Hong Son",
      "Maha Sarakham",
      "Mukdahan",
      "Nakhon Nayok",
      "Nakhon Pathom",
      "Nakhon Phanom",
      "Nakhon Ratchasima",
      "Nakhon Sawan",
      "Nakhon Si Thammarat",
      "Nan",
      "Narathiwat",
      "Nong Bua Lam Phu",
      "Nong Khai",
      "Nonthaburi",
      "Pathum Thani",
      "Pattani",
      "Phang Nga",
      "Phatthalung",
      "Phayao",
      "Phetchabun",
      "Phetchaburi",
      "Phichit",
      "Phitsanulok",
      "Phra Nakhon Si Ayutthaya",
      "Phrae",
      "Phuket",
      "Prachinburi",
      "Prachuap Khiri Khan",
      "Ranong",
      "Ratchaburi",
      "Rayong",
      "Roi Et",
      "Sa Kaeo",
      "Sakon Nakhon",
      "Samut Prakan",
      "Samut Sakhon",
      "Samut Songkhram",
      "Saraburi",
      "Satun",
      "Sing Buri",
      "Sisaket",
      "Songkhla",
      "Sukhothai",
      "Suphan Buri",
      "Surat Thani",
      "Surin",
      "Tak",
      "Trang",
      "Trat",
      "Ubon Ratchathani",
      "Udon Thani",
      "Uthai Thani",
      "Uttaradit",
      "Yala",
      "Yasothon"
    ]
    //   checkbox: false
  }),
  computed: {
    passwordConfirmationRule() {
      return () =>
        this.password === this.confirmPassword || "Password must match";
    }
  },
  methods: {
    validate: function(e) {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    reset: function(e) {
      this.$refs.form.reset();
    },
    resetValidation: function(e) {
      this.$refs.form.resetValidation();
    },
    emailSignUp: function() {
      console.log({
        email: this.email,
        password: this.password,
        province: this.location,
        birthDate: this.birthDate,
        gender: this.gender,
        firstName: this.firstname,
        lastName: this.lastname
      });
      this.$store.dispatch("signUpWithEmail", {
        email: this.email,
        password: this.password,
        province: this.location,
        birthDate: this.birthDate,
        gender: this.gender,
        firstName: this.firstname,
        lastName: this.lastname,
        phone: this.phone
      });
    },
    signupPopup: function(e) {
      Swal.fire({
        title: "Sign Up Success!",
        type: "success",
        confirmButtonColor: "#4BB543",
        confirmButtonText: "OK"
      }).then(function(result) {
        window.location = "/selectGenres";
      });
    }
  }
};
</script> 
 
 
<style>
.linkForgotPassword {
  color: #341646;
  text-decoration-line: none;
}

.linkForgotPassword:hover,
a.linkForgotPassword:active {
  color: #808080;
  /* text-decoration-line: underline; */
}

.linkSignup {
  color: #341646;
  font-weight: bold;
  text-decoration-line: none;
}

.linkSignup:hover {
  color: #808080;
  font-weight: bold;
  text-decoration-line: none;
}

.popupSingupSuccess:hover {
  background-color: #fff;
}

.h3 {
  font-family: "Roboto";
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 5%;
}

.p {
  font-size: 16px;
}
</style> 
