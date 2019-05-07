<template>
  <div>
    <v-card class="elevation-0 mx-auto" color="white" max-width="150">
      <v-img :aspect-ratio="1/1" src="https://image.flaticon.com/icons/png/512/64/64572.png"></v-img>
    </v-card>

    <br>
    <center>
      <nuxt-link to="">
        <p>Edit Profile</p>
      </nuxt-link>
    </center>

    <h2>Your Group</h2>
    <br>
    {{yourGroup}}
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <h2>Going</h2>
    <br>
    {{yourEvent}}
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <h2>Interests</h2>
    <br>
    {{yourInterests}}
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
  </div>
</template> 
 
 
<script>
export default {
  name: "userProfileForm",
  data() {
    return {
      selectedFile: null,
      valid: true,
      yourGroup: "Your Group",
      yourEvent: "Your Event",
      yourInterests: ""
    };
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
