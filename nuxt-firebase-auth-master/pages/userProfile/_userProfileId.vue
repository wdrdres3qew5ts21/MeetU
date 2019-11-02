<template>
  <div>
    <v-layout>
      <h2 style="color:#341646">Profile</h2>
    </v-layout>

    <br />

    <v-layout justify-center row wrap>
      <v-flex xs12>
        <v-card class="elevation-0 mx-auto" color="white" max-width="150" style="border-radius:50%">
          <v-img :aspect-ratio="1/1" :src="userForm.photoURL"></v-img>
        </v-card>
      </v-flex>
    </v-layout>
    <br />

    <h2>Information</h2>
    <br />
    <v-layout column>
      <v-form ref="form" v-model="valid">
        <v-layout row wrap>
          <v-flex xs5>
            <h3>Badges</h3>
          </v-flex>
          <v-flex xs7 v-for="(item,index) in badges" :key="index" xs2>
            <v-avatar size="40">
              <v-img :src="item.badgePicture"></v-img>
            </v-avatar>
          </v-flex>
        </v-layout>

        <br />
        <v-layout row>
          <v-flex class="text-xs-left">
            <h3>Interest</h3>
          </v-flex>
        </v-layout>
        <v-chip
          v-for="(userInterest,index) in userForm.interest"
          :key="index"
          text-color="#341646"
          class="textSize"
        >
          <v-avatar>
            <v-icon color="primary" size="21">favorite</v-icon>
          </v-avatar>
          {{userInterest}}
        </v-chip>
        <v-flex xs12>
          <center>
            <h3>
              <!-- : {{userForm.interest.length}}/{{limitedSelectNumber}} -->
            </h3>
          </center>
        </v-flex>

        <!--  โค้ดซันแบบดั้งเดิม
          
          
           <v-btn @click="isCameraOpen = !isCameraOpen" block primary>Open camera</v-btn>
        <div v-if="isCameraOpen">
          <client-only placeholder="loading...">
            <qrcode-stream @decode="onDecode"></qrcode-stream>
          </client-only>
        </div>
        
        -->

        <br />
        <v-text-field :disabled="isEditing" v-model="userForm.firstName" label="First name"></v-text-field>
        <br />

        <v-text-field v-model="userForm.lastName" :disabled="isEditing" label="Last name"></v-text-field>

        <br />
        <v-menu
          ref="menu"
          v-model="menu"
          :disabled="isEditing"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              :disabled="isEditing"
              v-model="userForm.dateOfBirth"
              label="Birthday date"
              prepend-icon="today"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            ref="picker"
            v-model="userForm.dateOfBirth"
            :max="new Date().toISOString().substr(0, 10)"
            min="1950-01-01"
          ></v-date-picker>
        </v-menu>

        <br />
        <br />

        <h3>Gender</h3>
        <!-- <v-radio-group> 
           <v-radio v-for="gender in genderList" :key="gender" :label="`${gender}`" value="gender"
          v-model="userForm.gender"
          ></v-radio>
        </v-radio-group>-->
        <v-select
          :items="genderList"
          v-model="userForm.gender"
          label="Select gender"
          :disabled="isEditing"
        ></v-select>

        <h2>Contacts</h2>
        <br />
        <v-text-field
          v-model="userForm.email"
          label="E-mail"
          :disabled="isEditing"
        ></v-text-field>
        <br />
        <v-text-field
          label="Phone number"
          placeholder="phone number"
          counter="10"
          prepend-icon="phone"
          v-model="userForm.phone"
          :disabled="isEditing"
          type="number"
        ></v-text-field>
        <br />

        <br />

        <h3 class="h3">User Social Networks</h3>
        <br />

        <v-text-field
          class="textfield"
          v-model="userForm.website"
          label="Website"
          placeholder="http://"
          :disabled="isEditing"
        ></v-text-field>

        <v-text-field
          class="textfield"
          v-model="userForm.line"
          label="Line"
          placeholder="@"
          :disabled="isEditing"
        ></v-text-field>

        <v-text-field
          class="textfield"
          v-model="userForm.facebook"
          label="Facebook"
          placeholder="http://"
          :disabled="isEditing"
        ></v-text-field>

        <v-text-field
          class="textfield"
          v-model="userForm.twitter"
          label="Twitter"
          placeholder="@"
          :disabled="isEditing"
        ></v-text-field>

        <v-text-field
          class="textfield"
          v-model="userForm.instagram"
          label="Instagram"
          placeholder="@"
          :disabled="isEditing"
        ></v-text-field>
      </v-form>
    </v-layout>
    <br />

    <br />
    <br />
  </div>
</template> 

 
<script>
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
import "@mdi/font/css/materialdesignicons.css";

export default {
  name: "userProfileForm",
  components: {},
  data() {
    return {
      isCameraOpen: false,
      // isCameraClose: true,
      limitedSelectNumber: 3,
      badges: [
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
        }
      ],
      date: null,
      menu: false,
      genderList: ["Male", "Female", "Unspecified"],
      valid: true,
      isEditing: true,
      userForm: {
        photoURL: "",
        badgeList: [],
        interest: [],
        firstName: "",
        lastName: "",
        gender: "",
        dateArray: [],
        dateOfBirth: "",
        phone: "",
        email: "",
        password: "",
        confirmPassword: "",
        password: "",
        website: "",
        line: "",
        facebook: "",
        twitter: "",
        instagram: ""
      },
      dialog: false,
      organizeForm: {
        organizeName: ""
      }
    };
  },
  mounted() {
    this.initUserProfile();
    this.loadMyBadge();
  },
  methods: {
    ...mapActions(["testContext"]),
    initUserProfile: function() {
      axios
        .get(
          `${process.env.USER_SERVICE}/user/${this.$route.params.userProfileId}`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
            }
          }
        )
        .then(userProfileForm => {
          console.log("haate my self");
          userProfileForm = userProfileForm.data;
          console.log(userProfileForm);
          this.userForm.photoURL = userProfileForm.photoURL;
          this.userForm.interest = userProfileForm.interest;
          this.userForm.firstName = userProfileForm.firstName;
          this.userForm.lastName = userProfileForm.lastName;
          this.userForm.email = userProfileForm.email;
          this.userForm.gender = userProfileForm.gender;
          this.userForm.facebook = userProfileForm.facebook;
          this.userForm.line = userProfileForm.line;
          this.userForm.website = userProfileForm.website;
          this.userForm.twitter = userProfileForm.twitter;
          this.userForm.instagram = userProfileForm.instagram;
          this.userForm.phone = userProfileForm.phone || "";
        })
        .catch(err => {});
    },
    loadMyBadge() {
      let loader = this.$loading.show();
      axios
        .get(
          `${process.env.USER_SERVICE}/badges/user/${this.$route.params.userProfileId}`
        )
        .then(badgeListResponse => {
          this.badges = badgeListResponse.data;
          console.log(this.badges);
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
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

.logoutButton {
  font-weight: bold;
}
.textSize {
  font-size: 15px;
}
</style> 
