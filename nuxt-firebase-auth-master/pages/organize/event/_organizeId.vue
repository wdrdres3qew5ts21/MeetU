<template>
  <div>
    <h1>{{organize.organizeName}}</h1>
    <center>
      <nuxt-link :to="`/organize/event/createEventForm`" style="text-decoration-line:none;">
        <v-btn
          class="createEvent white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Create an Event</v-btn>
      </nuxt-link>
    </center>
    <div v-if="eventList == null">
      <center>
        <p style="color:grey">No event, please create a new event.</p>
      </center>
    </div>
    <div v-else>
      <center>
        <br />
        <p style="color:grey">You can create new event.</p>
      </center>
    </div>
    <event-card v-for="(event, index) in eventList" :key="index" :event="event" />
    <br />
    <center>
      <nuxt-link :to="`/organize/event/createEventForm`" style="text-decoration-line:none;">
        <v-btn
          class="createEvent white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Create an Event</v-btn>
      </nuxt-link>
    </center>
  </div>
</template>


<script>
import eventCard from "~/components/eventCard";
import axios from "axios";
import { error } from "util";
export default {
  name: "startedEvent",
  components: {
    eventCard
  },
  props: {},
  data() {
    return {
      organizeId: "",
      eventList: null,
      organize: {
        organizeId: "",
        organizeName: ""
      }
    };
  },
  mounted() {
    this.organizeId = this.$route.params.organizeId;
    console.log(this.$route.params.organizeId);
    this.loadAllEventOfOrganize();
    this.loadOrganizeDetail();
  },
  methods: {
    loadAllEventOfOrganize: async function() {
      axios
        .get(`${process.env.EVENT_SERVICE}/events/organize/${this.organizeId}`)
        .then(eventResponse => {
          console.log(eventResponse.data);
          this.eventList = eventResponse.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    loadOrganizeDetail: async function() {
      axios
        .get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
        .then(organizeResponse => {
          console.log(organizeResponse.data);
          this.organize = organizeResponse.data.body;
        })
        .catch(error => {
          console.log(error);
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

.h1 {
  font-family: Roboto;
}
</style>