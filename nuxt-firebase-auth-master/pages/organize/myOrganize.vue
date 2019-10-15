<template>
  <div>
    <h1>My Organize</h1>
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
      organizeList: null
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
    }
  }
};
</script>
