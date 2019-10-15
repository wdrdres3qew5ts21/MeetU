<template>
  <div>
    <h1>My Organize</h1>
    <organize-card v-for="(organize, index) in organizeList" :key="index" :organizeName="organize.organizeName">

    </organize-card>
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
      organizeList: []
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
