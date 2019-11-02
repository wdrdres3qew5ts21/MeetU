<template>
  <div>
    <v-layout>
      <h2 style="color:#341646">My Profile</h2>

      <v-flex class="text-xs-right">
        <!-- ปุ่มสแกน qr code -->

        <!-- <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
          <template v-slot:activator="{ on }">
            <v-btn v-on="on" @click="isCameraOpen = !isCameraOpen" fab dark small color="primary">
              <v-icon medium>mdi-qrcode-scan</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-toolbar dark color="primary">
              <v-btn icon dark  
              @click.native="isCameraOpen=false"
              @click="dialog = false" 
             >
                <v-icon>mdi-arrow-left</v-icon>
              </v-btn>
            </v-toolbar>
            <br />
            <br />



            <center>

              <br />
        <h3>You are {{userForm.firstName}} : {{organizeForm.organizeName}} Organize
        </h3>
        <br />


              <div v-if="isCameraOpen">
                <client-only placeholder="loading...">
                  <qrcode-stream @decode="onDecode"></qrcode-stream>
                </client-only>
              </div>

              <br />

              <h3>Just scan a QR code for join an event!</h3>
            </center>
          </v-card>
        </v-dialog>-->

        <v-btn fab dark small color="#341646" @click=" isEditing= !isEditing">
          <v-icon color="#fff" size="25">edit</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>

    <br />

    <v-layout justify-center row wrap>
      <v-flex xs12>
        <v-card class="elevation-0 mx-auto" color="white" max-width="150" style="border-radius:50%">
          <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
        </v-card>
      </v-flex>
    </v-layout>
    <br />

    <!-- โค้ดปุ่มยาวๆเหมือนตอนแรกที่ทำไว้  ฝนปรับดีไซน์จากปุ่มเดิมของซันแล้ว
              
              <v-btn @click="isCameraOpen" large color="primary">
                  <v-icon class="spacing-playground py-0 px-2" large>mdi-qrcode-scan</v-icon>QR Code
    </v-btn>-->
    <!-- <div v-if="isCameraOpen">
                <client-only placeholder="loading...">
                  <qrcode-stream @decode="onDecode"></qrcode-stream>
                </client-only>
              </div> 
              
    -->

    <v-btn class="logoutButton" outline color="red" depressed large block @click="logout()">LOG OUT</v-btn>
    <br />

    <h2>Information</h2>
    <br />
    <v-layout column>
      <v-form ref="form" v-model="valid">
        <v-layout row wrap>
          <v-flex xs5>
            <h3>Your Badges</h3>
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
          <h3>Your interest</h3>
          </v-flex>
          <v-flex class="text-xs-right">
            <nuxt-link to="/selectGenres" style="color:red">
              <v-btn fab dark small color="#341646">
                <v-icon color="#fff" size="25">edit</v-icon>
              </v-btn>
            </nuxt-link>
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
        <v-text-field
          :disabled="isEditing"
          v-model="userForm.firstName"
          :rules="nameRules"
          label="First name"
        ></v-text-field>
        <br />

        <v-text-field
          v-model="userForm.lastName"
          :rules="nameRules"
          :disabled="isEditing"
          label="Last name"
        ></v-text-field>

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
            @change="save"
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
          :rules="emailRules"
          label="E-mail"
          :disabled="isEditing"
        ></v-text-field>
        <br />
        <v-text-field
          label="Phone number"
          :rules="phoneRules"
          placeholder="phone number"
          counter="10"
          prepend-icon="phone"
          v-model="userForm.phone"
          :disabled="isEditing"
          type="number"
        ></v-text-field>
        <br />

        <h2>Credentials</h2>

        <v-text-field
          v-model="userForm.password"
          type="password"
          :rules="passwordRule"
          label="* Password"
          required
          :disabled="isEditing"
        ></v-text-field>

        <v-text-field
          v-model="userForm.confirmPassword"
          type="password"
          :rules="passwordRules"
          label="* Password Confirmation"
          required
          :disabled="isEditing"
        ></v-text-field>

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
    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;"> -->
      <v-btn
        class="saveButton white--text"
        color="#341646"
        :disabled="isEditing"
        @click="updateProfile()"
        depressed
        large
        height="50"
      >Save</v-btn>
      <!-- </nuxt-link> -->
      <!-- <v-btn color="primary" :disabled="!valid" @click="onSubmit()">ถัดไป</v-btn> -->
    </center>
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

      numberRules: [
        v => !!v || "This field is required",
        v => (v && !Number.isNaN(v)) || "Please insert only number"
      ],
      genderRules: [v => !!v || "Please select from choice"],
      textRules: [v => !!v || "This field is required"],
      phoneRules: [
        v => !!v || "Phone Number is required",
        v => (v && v.length === 10) || "Phone Number msut be 10 digit"
      ],
      emailRules: [
        v => !!v || "E-mail is required",
        v => /.+@.+\..+/.test(v) || "E-mail must be valid"
      ],
      nameRules: [
        v => !!v || "Name is required",
        v => (v && v.length >= 2) || "Name must be more than 2 characters"
      ],
      lastnameRules: [
        v => !!v || "Lastname is required",
        v => (v && v.length >= 2) || "Lastname must be more than 2 characters"
      ],
      passwordRules: [
        v => !!v || "Password is required",
        v => v.length >= 8 || "Password must be 8 character",
        v =>
          this.password === this.confirmPassword ||
          "Password and Confirm Password need to be match"
      ],
      passwordRule: [
        v => !!v || "Password is required",
        v => v.length >= 8 || "Password must be 8 character"
      ],
      valid: true,
      isEditing: true,
      userForm: {
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
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.initUserProfile();
    this.loadMyBadge();
  },
  methods: {
    ...mapActions(["testContext"]),
    onDecode: function(decodedString) {
      let parsedTicket = "";
      try {
        parsedTicket = JSON.parse(decodedString);
        console.log(parsedTicket);
      } catch (error) {
        console.log("fail to QR decode", parsedTicket);
      }
      axios
        .post(`${process.env.EVENT_SERVICE}/event/join`, parsedTicket, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(scanResponse => {
          console.log(scanResponse);
          this.$swal({
            type: "success",
            title: "Success to scan QR Code!!!",
            text: "Success to scan QR Code !!!"
          });
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Fail to scan QR Code !!!",
            text: `${err.response.data.response}`
          });
        });
    },
    initUserProfile: function() {
      axios
        .get(`${process.env.USER_SERVICE}/user/${this.getUser.uid}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(userProfileForm => {
          console.log("haate my self");
          userProfileForm = userProfileForm.data;
          console.log(userProfileForm);
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
        .get(`${process.env.USER_SERVICE}/badges/user/${this.getUser.uid}`)
        .then(badgeListResponse => {
          this.badges = badgeListResponse.data;
          console.log(this.badges);
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },

    loadUserBadge: function() {},
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
    },
    activateInEditMode() {
      this.isEditing = false;
    },
    deActivateInEditMode() {
      this.isEditing = true;
    },
    editProfile() {
      this.edit = false;
    },
    validate() {
      console.log(this.userForm);

      if (this.$refs.form.validate()) {
        console.log("correct fuq you");
        this.snackbar = true;
      } else {
        console.log("stupid please correct");
      }
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    onSubmit() {
      console.log(this.userForm);

      let rawTel = this.userForm.telephone;
      this.userForm.telephone = rawTel.replace(/^0/g, "+66");
      this.userForm.gender = this.genderList.values;
    },
    updateProfile: function() {
      console.log(this.userForm);
      axios
        .put(
          `${process.env.USER_SERVICE}/user/${this.getUser.uid}`,
          this.userForm,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
            }
          }
        )
        .then(updateResponse => {
          this.$swal({
            type: "success",
            title: "Update Profile success !",
            text: `Update Profile success`
          });
        })
        .catch(err => {
          console.log(err);
          this.$swal({
            type: "error",
            title: "Update Profile fail !",
            text: err
          });
        });

      // console.log({
      //   email: this.userForm.email,
      //   password: this.userForm.password,
      //   birthDate: this.userForm.dateOfBirth,
      //   firstName: this.userForm.firstname,
      //   lastName: this.userForm.lastname,
      //   gender: this.userForm.gender
      // });
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
