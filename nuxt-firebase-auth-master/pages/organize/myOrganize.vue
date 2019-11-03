<template>
  <div>
    <br />
    <v-layout>
      <h2>My Organize</h2>
      <br />
    </v-layout>
    <br />
    <v-layout row wrap>
      <v-flex xs6>
        <v-card color="white" max-width="120" style="border-radius:50%">
          <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
        </v-card>
      </v-flex>
      <v-flex xs6 text-right>
        <br />
              <h3>Organizer name: {{userForm.firstName}} {{userForm.lastName}}</h3>
      </v-flex>
    </v-layout>
    <br />


    <!-- เอาจริงๆ -->
    <!-- <v-tabs v-model="tabModel" centered color="white" slider-color="#341646">
      <v-tab v-for="i in tabTopics" :key="i" :href="`#tab-${i}`">{{ i }}</v-tab>
    </v-tabs>


    <v-tabs-items v-model="tabModel">
      <v-tab-item v-for="i in tabTopics" :key="i" :value="`tab-${i}`">
        <v-card flat>
          <div v-if="organizeList">
            <nuxt-link
              v-for="(organize, index) in organizeList"
              :key="index"
              :to="`/organize/event/${organize.organizeId}`"
            >
              <organize-card :organizeName="organize.organizeName" />
            </nuxt-link>
          </div>
          <div v-else>
            <center>
              <br />
              <p style="color:grey">You not own any Organize.</p>
            </center>
          </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>-->

    <!-- เอาจริงๆ  -->

    <!-- <v-card flat> -->
    <!-- <div v-if="organizeList">
      <nuxt-link
        v-for="(organize, index) in organizeList"
        :key="index"
        :to="`/organize/event/${organize.organizeId}`"
      > -->
        <!-- <v-badge 
        overlap 
        color="#341646">
          <template v-slot:badge>
            <v-icon dark small>create</v-icon>
          </template>
        </v-badge>-->

        <!-- code ซัน -->
    
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
      },
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
          loader.hide()
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

.editOrganizerLink {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}
</style>