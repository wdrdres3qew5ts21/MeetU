<template>
  <div>
    <br />
    <h2 class="h2">Create a New Events</h2>
    <br />

    <span>
      Poster Image
      <br />
      <v-btn class="chooseFileButton" color="white" @click="$refs.inputUpload.click()">Choose file</v-btn>
      <input v-show="false" ref="inputUpload" type="file" @change="imgFunction" />
    </span>
    <br />
    <br />
    <span>
      Cover image
      <br />
      <v-btn class="chooseFileButton" color="white" @click="$refs.inputUpload.click()">Choose file</v-btn>
      <input v-show="false" ref="inputUpload" type="file" @change="imgFunction" />
    </span>

    <br />
    <br />
    <span>
      Square poster image
      <br />
      <v-btn class="chooseFileButton" color="white" @click="$refs.inputUpload.click()">Choose file</v-btn>
      <input v-show="false" ref="inputUpload" type="file" @change="imgFunction" />
    </span>

    <br />
    <br />
    <span>
      Background image
      <br />
      <v-btn class="chooseFileButton" color="white" @click="$refs.inputUpload.click()">Choose file</v-btn>
      <input v-show="false" ref="inputUpload" type="file" @change="imgFunction" />
    </span>

        <br><br><br>
    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="saveButton white--text" color="#341646" depressed large height="50">Save</v-btn>
      </nuxt-link>
    </center>
    <br><br>
  </div>
</template>


<script>
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

.chooseFileButton {
  border: solid 1px #341646 !important;
}
</style>