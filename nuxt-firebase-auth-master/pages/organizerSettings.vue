<template>
  <div>
    <!-- <br />
    <h2 class="h2">Organizer Settings</h2>
    <br />
    <h3 class="h3">Profile</h3>
    <br />
    <center>
      <v-avatar color="#DEDEDE" size="120">
        <span class="black--text">Image</span>
      </v-avatar>
      <br />
      <br />
      <nuxt-link class="editProfile" to="/editOrganizerSettings?">Edit Profile</nuxt-link>
    </center>
    <br />
    <h3 class="h3">Contact Information</h3>
    <br />
    <p>Email :</p>
    <p>Phone :</p>
    <br />
    <h3 class="h3">Organizer Social Networks</h3>
    <br />
    <p>Website :</p>
    <p>Facebook :</p>
    <p>Twitter :</p>
    <p>Line :</p>
    <p>Instagram :</p>
    <br />
    <h3 class="h3">Submitted Documents</h3>
    <p style="font-size:14px;">
      No documents submitted yet.
      <br />Please upload at least one document to prove your identity here.
    </p>

    <v-btn class="chooseFileButton" color="white" @click="$refs.inputUpload.click()">Choose file</v-btn>
    <input v-show="false" ref="inputUpload" type="file" @change="fileFunction" />
    <br><br> -->
  </div>
</template>

<script>
export default {

}
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

.h2 {
  font-family: Roboto;
  color: #341646;
  font-size: 28px;
}

.h3 {
  font-family: Roboto;
  color: #341646;
  font-size: 24px;
}

.editProfile {
  font-family: Roboto;
  font-size: 16px;
  font-weight: bold;
  color: #100c4b;
  text-decoration: underline;
}

.chooseFileButton {
  border: solid 1px #341646 !important;
}
</style>