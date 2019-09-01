<template>
  <v-layout justify-center row wrap>
    <v-flex xs12>
      <v-card class="elevation-0 mx-auto" color="white" max-width="150">
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
    <v-flex xs12>
      <h2>Your Group</h2>
    </v-flex>
    <v-flex xs12>
      <h2>Going</h2>
    </v-flex>
    <v-flex xs12>
      <h2>Your Interest</h2>
    </v-flex>
  </v-layout>
</template> 
 
 
<script>
import { mapGetters } from "vuex";
export default {
  name: "userProfileForm",
  components: {},
  data() {
    return {
      selectedFile: null,
      valid: true,
      yourGroup: "Your Group",
      yourEvent: "Your Event",
      yourInterests: ""
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload: function(e) {
      // upload file, get it from this.selectedFile
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
