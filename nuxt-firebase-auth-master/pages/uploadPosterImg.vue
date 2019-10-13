<template>
  <div>
    <br />
    <h2 class="h2">Create a New Events</h2>
    <br />

    <span>
      Cover image (single picture/ landscape)
      <br />
      <v-btn
        class="chooseFileButton"
        color="white"
        @click="$refs.coverPictureUpload.click()"
      >Choose file</v-btn>
      <input v-show="false" ref="coverPictureUpload" type="file" @change="onCoverPictureUpload" accept="image/*" />
      <p v-if="eventPictureCover">{{eventPictureCover.name}}</p>
    </span>

    <br />
    <br />
    <span>
      Preview Event Image (picture list)
      <br />
      <v-btn
        class="chooseFileButton"
        color="white"
        @click="$refs.pictureListUpload.click()"
      >Choose file</v-btn>
      <input v-show="false" ref="pictureListUpload" type="file" @change="imgFunction" accept="image/*" />
    </span>

    <br />
    <br />
    <br />
    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <v-btn
        class="saveButton white--text"
        color="#341646"
        depressed
        large
        height="50"
        @click="onUpload"
      >Save</v-btn>
      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="saveButton white--text" color="#341646" depressed large height="50">Save</v-btn>
      </nuxt-link>-->
    </center>
    <br />
    <br />
  </div>
</template>


<script>
import * as firebase from "firebase/app";
import "firebase/storage";
export default {
  name: "createEventForm",
  data() {
    return {
      eventPictureCover: null,
      eventPictureLists: []
    };
  },
  methods: {
    onCoverPictureUpload(event) {
      console.log("uplaod din");
      this.eventPictureCover = event.target.files[0];
    },
    imgFunction() {},
    onUpload() {
      let dateobj = new Date(); 
      let fileName = this.eventPictureCover.name +'_'+ dateobj.toISOString();
      let storage = firebase.storage();
      let storageRef = storage.ref();
      let setupFile = storageRef.child(fileName);
      try {
        setupFile.put(this.eventPictureCover).then(snapshot => {
          console.log("Uploaded a blob or file!");
          snapshot.ref.getDownloadURL().then((downloadURL) =>{
            console.log('File available at', downloadURL);
          });
        });
      } catch (err) {
        console.log(err);
      }
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

.chooseFileButton {
  border: solid 1px #341646 !important;
}
</style>