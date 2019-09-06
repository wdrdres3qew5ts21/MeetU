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
    <v-btn
      block
      round="16px;"
      color="#341646"
      class="mb-2 white--text"
      @click.stop="upgradeAccountPopup"
    >Create Community</v-btn>
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

    upgradeAccountPopup: function(e) {
      Swal.fire({
        title: "Are you sure?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#4BB543",
        cancelButtonColor: "#d33",
        confirmButtonText: "Create organize!"
      }).then(result => {
        if (result.value) {
          Swal.fire({
            title: "Create organize success!",
            type: "success",
            showConfirmButton: true,
            confirmButtonColor: "#4BB543"
          });
        }
      });
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
