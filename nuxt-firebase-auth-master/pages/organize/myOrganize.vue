<template>
  <div>
    <br />
    <v-layout>
      <h2>My Organize</h2>
      <br />
    </v-layout>

    <br />
   <v-layout justify-center row wrap>
      <v-flex xs12>
        <v-card class="elevation-0 mx-auto" color="white" max-width="150" style="border-radius:50%">
          <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
        </v-card>
      </v-flex>
       </v-layout>

      <v-layout justify-center row wrap>
        <!-- <v-flex xs8> -->
          
        
        <br />
        <h3>{{userForm.firstName}} {{userForm.lastName}}</h3>
      <!-- </v-flex> -->
      </v-layout>


      <div v-if="organizeList == 0">
        <!-- <h3>Name: {{userForm.firstName}} {{userForm.lastName}}</h3> -->
        <center><p style="color:grey">( You are not the organize. )</p></center>
        
     
    
    <br />

      <center>
        <!-- <br /> -->
        <!-- <v-icon medium>mdi-emoticon-cry-outline</v-icon> -->
        <br />
        <br />

        
        <p style="color:grey">
          You can
          <nuxt-link 
          :to="`/organize/organizerForm`" 
          class="linkToHomepage">
          click
          </nuxt-link> to upgrade account.
        </p>
        <!-- <p style="color:grey">You can create new event.</p> -->
      </center>

      </div>

     

    <br />

    <div v-if="organizeList">
      <nuxt-link
        v-for="(organize, index) in organizeList"
        :key="index"
        :to="`/organize/event/${organize.organizeId}`"
      >
        <organize-card :organize="organize" />
      </nuxt-link>
    </div>
    <div v-else>
      <center>
        <br />
        <p style="color:grey">You not own any Organize.</p>
      </center>
    </div>
    
  </div>
</template>

<script>
import organizeCard from "~/components/organizeCard";
import axios from "axios";
import { mapGetters } from "vuex";
import { error } from "util";
export default {
  components: {
    organizeCard
  },
  data() {
    return {
      organizeList: null,
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
      }
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.initUserProfile();
    this.loadOrganizeFromUser();
  },
  methods: {
    loadOrganizeFromUser: function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/organize/user/${this.getUser.uid}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organizeList = organizeResponse.data;
          loader.hide();
        })
        .catch(error => {
          console.log(error);
          loader.hide();
        });
    },
    initUserProfile: function() {
      let loader = this.$loading.show();
      axios
        .get(`${process.env.USER_SERVICE}/user/${this.getUser.uid}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken") || ""}`
          }
        })
        .then(userProfileForm => {
          console.log("organize detail");
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
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    }
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

.linkToHomepage {
  text-decoration: none;
  font-weight: bold;
  font-size: 18px;
  color: #100c4b;
}

.editOrganizerLink {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}
</style>