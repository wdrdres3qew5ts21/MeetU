<template>
  <div>
    <v-card class="elevation-0 mx-auto" color="white" max-width="150">
      <v-img :aspect-ratio="1/1" src="https://image.flaticon.com/icons/png/512/64/64572.png"></v-img>

      <v-card-text class="pt-4" style="position: relative;">
        <v-btn absolute color="#341646" class="white--text" fab normal right top>
          <v-icon>add_a_photo</v-icon>
        </v-btn>
      </v-card-text>
    </v-card>

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

      <v-text-field
        v-model="password"
        type="password"
        :rules="passwordRules"
        label="Password"
        required
      ></v-text-field>

      <v-text-field
        v-model="password"
        type="confirmPassword"
        :rules="passwordRules"
        label="Confirm Password"
        required
      ></v-text-field>

      <v-text-field v-model="firstname" :rules="firstnameRules" label="Firstname" required></v-text-field>

      <v-text-field v-model="lastname" :rules="lastnameRules" label="Lastname" required></v-text-field>

      <v-layout row wrap>
        <v-flex xs12 sm6 md4>
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="date"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="date"
                label="Date of Birth"
                prepend-icon="event"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="error" @click="menu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <v-radio-group v-model="row" row>
          Gender: &nbsp;&nbsp;&nbsp;
          <v-radio label="Male" value="radio-1"></v-radio>
          <v-radio label="Female" value="radio-2"></v-radio>
          <!-- <v-radio label="Unspecific" value="radio-3"></v-radio> -->
        </v-radio-group>

        <!-- <v-container> -->

        <v-flex xs12 sm5 d-flex>
          <v-select :items="location" label="Location" prepend-icon="place"></v-select>
        </v-flex>

        <v-spacer></v-spacer>

        <v-layout row wrap>
          <v-flex xs3>
            <v-select :items="phone" label="TH" prepend-icon="phone"></v-select>
          </v-flex>
          <v-flex xs6 sm6 md6>
            <v-text-field label="Phone"></v-text-field>
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
        round="16px;"
        color="#341646"
        class="signIn mb-2 white--text"
        @click="signupPopup"
      >Sign Up</v-btn>

      <br>

      <center>
        <h3>
          Already member ?
          <nuxt-link :to="`/login`" style="text-decoration-line:none;">
            <a class="linkSignup">Log In</a>
          </nuxt-link>
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
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+/.test(v) || "E-mail must be valid"
    ],
    password: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => /.+@.+/.test(v) || "Password must be valid"
    ],

    valid: true,
    firstname: "",
    firstnameRules: [v => !!v || "Firstname is required"],

    valid: true,
    lastname: "",
    lastnameRules: [v => !!v || "Lastname is required"],

    confirmPassword: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => /.+@.+/.test(v) || "Password must be valid"
    ],

    date: new Date().toISOString().substr(0, 10),
    menu: false,

    location: [
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

    // })

    //   checkbox: false
  }),

  // forgotPassword:'Forgot your password?',

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },

    signupPopup: function(e) {
      Swal.fire({
        title: "Sign Up Success!",
        type: "success",
        confirmButtonColor: "#4BB543",
        confirmButtonText: "OK"
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
  color: #fc5577;
  /* text-decoration-line: underline; */
}

.linkSignup {
  color: #341646;
  font-weight: bold;
  text-decoration-line: none;
}

.linkSignup:hover {
  color: #fc5577;
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
