<template>
  <div>
    <h1>Create New Community</h1>
    <v-text-field
      v-model="communityName"
      :rules="communityNameRules"
      label="Community Name"
      required
    ></v-text-field>

    <v-flex>
      <v-select :items="Category" label="Category"></v-select>
    </v-flex>

    <v-text-field label="Add some people" required></v-text-field>

    <v-flex>
      <v-select :items="privacy" label="Select privacy"></v-select>
    </v-flex>


        <v-layout.mb-4>
            <v-text-field
              name="description"
              label="Description"
              color="pink"
              textarea
              rows="6"
              required
              hide-details>
            </v-text-field>
        </v-layout.mb-4>
        <nuxt-link :to="`/community/communityId/?`" style="text-decoration-line:none;">      
    <v-btn
      block
      round="16px;"
      color="#341646"
      class="mb-2 white--text"
    >Create Community</v-btn>
        </nuxt-link>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";
export default {
  name: "communityForm",
  data() {
    return {
      selectedFile: null,
      valid: true,
      communityName: "",
      communityNameRules: [v => !!v || "Community name is required"],
      privacy: ["Public", "Closed", "Secret"],

      Category: ["Education"],
      dialog1: false,
      dialog2: false
    };
  },
  methods: {
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload: function(e) {
      // upload file, get it from this.selectedFile
    },

 
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

.upgradeSuccessTextPopup {
  font-weight: bold;
  font-size: 18px;
}

.popupUpgrade:hover {
  background-color: white;
}

.h3 {
  font-family: "Roboto";
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 5%;
}

.p {
  font-size: 16px;
}
</style> 
