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
        <h3>Organizer name</h3>
        <p style="margin:0" class="editOrganizerLink" @click="goToEditOrganizerPage">Edit Organizer</p>
      </v-flex>
    </v-layout>
    <br />

    <!-- tabs -->

    <!-- <v-tabs
        slot="extension"
        v-model="tabModel"
        centered
        color="white"
        slider-color="#341646"
      >
        <v-tab
          v-for="i in tabTopics"
          :key="i"
          :href="`#tab-${i}`"
        >
           {{ i }}
        </v-tab>
      </v-tabs>

    <v-tabs-items v-model="tabModel">
      <v-tab-item
        v-for="i in tabTopics"
        :key="i"
        :id="`tab-${i}`"
      >
        <v-card flat>
          <v-card-text v-text="text"></v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>-->

    <v-tabs v-model="tabModel" centered color="white" slider-color="#341646">
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
          <!-- <v-card-text v-text="text"></v-card-text> -->
        </v-card>
      </v-tab-item>
    </v-tabs-items>

    <!-- <div v-if="organizeList">
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
    </div>-->
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
      model: "tabModel",
      tabTopics: ["Organize", "Review", "Edit"],
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.loadOrganizeFromUser();
  },
  methods: {
    loadOrganizeFromUser: function() {
      axios
        .get(`${process.env.USER_SERVICE}/organize/user/${this.getUser.uid}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organizeList = organizeResponse.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    goToEditOrganizerPage() {
      this.saveEventTemplate();
      this.$router.push("/organize/event/editOrganizer");
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

.editOrganizerLink {
  font-family: Roboto;
  font-size: 18px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}
</style>