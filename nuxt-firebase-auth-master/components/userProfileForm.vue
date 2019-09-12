<template>
  <div>
    <h1>My Profile</h1>
    <v-layout justify-center row wrap>
      <v-flex xs12>
        <v-card class="elevation-0 mx-auto" color="white" max-width="150" style="border-radius:50%">
          <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
        </v-card>
      </v-flex>
      <v-flex xs12>
        <center>
          <nuxt-link to>
            <p>Edit Profile</p>
          </nuxt-link>
        </center>
      </v-flex>
    </v-layout>
    <h2>Level:</h2>
    <br />
    <br />
    <v-flex xs12>
      <h2>Information</h2>
      <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

      <br />
      <v-text-field
        v-model="firstname"
        :rules="nameRules"
        :counter="10"
        label="First name"
        required
      ></v-text-field>
      <br />

      <v-text-field v-model="lastname" :rules="nameRules" :counter="10" label="Last name" required></v-text-field>
    </v-flex>
    <br />
    <v-menu
      ref="menu"
      v-model="menu"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      full-width
      min-width="290px"
    >
      <template v-slot:activator="{ on }">
        <v-text-field v-model="date" label="Birthday date" prepend-icon="event" readonly v-on="on"></v-text-field>
      </template>
      <v-date-picker
        ref="picker"
        v-model="date"
        :max="new Date().toISOString().substr(0, 10)"
        min="1950-01-01"
        @change="save"
      ></v-date-picker>
    </v-menu>
    <br />
    <br />
    <h3>Gender</h3>

    <v-radio-group v-model="column" column>
      <v-radio label="Male" value="radio-1"></v-radio>
      <v-radio label="Female" value="radio-2"></v-radio>
      <v-radio label="Unspecified" value="radio-3"></v-radio>
    </v-radio-group>
    <br />
    <h2>Contacts</h2>
    <br />
    <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>
    <br />
    <v-text-field label="Phone number" prepend-icon="phone"></v-text-field>
    <br />
    <h2>Credentials</h2>
    <br />
    <v-text-field
      v-model="password"
      :append-icon="show1 ? 'visibility' : 'visibility_off'"
      :rules="[rules.required, rules.min]"
      :type="show1 ? 'text' : 'password'"
      name="input-10-1"
      label="Password"
      hint="At least 8 characters"
      counter
      @click:append="show1 = !show1"
    ></v-text-field>

    <v-text-field
      :append-icon="show4 ? 'visibility' : 'visibility_off'"
      :rules="[rules.required, rules.emailMatch]"
      :type="show4 ? 'text' : 'password'"
      name="input-10-2"
      label="Password Comfermation"
      hint="At least 8 characters"
      value="Pa"
      error
      @click:append="show4 = !show4"
    ></v-text-field>
    <v-text-field
      v-model="password"
      :append-icon="show3 ? 'visibility' : 'visibility_off'"
      :rules="[rules.required, rules.min]"
      :type="show3 ? 'text' : 'password'"
      name="input-10-1"
      label="Current Password"
      hint="At least 8 characters"
      counter
      @click:append="show3 = !show3"
    ></v-text-field>
    <br />

    <h2>Social Links</h2>something
    <br />
    <v-layout justify-center row wrap>
      <v-btn class="white--text" color="#341646">CANCEL</v-btn>
      <v-btn class="white--text" color="#341646">SAVE</v-btn>
    </v-layout>
  </div>
</template> 
 
 
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "userProfileForm",
  components: {},
  data() {
    return {
      date: null,
      menu: false,
      show1: false,
      show3: false,
      show4: false,
      password: "Password",
      firstname: "",
      lastname: "",
      nameRules: [
        v => !!v || "Name is required",
        v => v.length <= 10 || "Name must be less than 10 characters"
      ],
      email: "",
      emailRules: [
        v => !!v || "E-mail is required",
        v => /.+@.+/.test(v) || "E-mail must be valid"
      ],
      rules: {
        required: value => !!value || "Required.",
        min: v => v.length >= 8 || "Min 8 characters",
        emailMatch: () => "The email and password you entered don't match"
      }
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    ...mapActions(["testContext"]),
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload: function(e) {
      // upload file, get it from this.selectedFile
    },
    logout: function() {
      this.$store.dispatch("signOut").then(() => {
        this.$swal({
          type: "success",
          title: "Sign Out !",
          text: `Bye bye see you next time ;)`
        });
        this.$router.push("/");
      });
    },
    save(date) {
      this.$refs.menu.save(date);
    }
  },
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    }
  }
};
</script> 
 
 
<style>
.accountButton {
  width: 500px;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
</style> 
