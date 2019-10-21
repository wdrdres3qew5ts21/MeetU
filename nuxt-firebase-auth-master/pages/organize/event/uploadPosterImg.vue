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
      <input
        v-show="false"
        ref="coverPictureUpload"
        type="file"
        @change="onCoverPictureUpload"
        accept="image/*"
      />
      <p v-if="eventPictureCoverUrl.name !=undefined  ">{{eventPictureCoverUrl.name}}</p>
      <div v-if="eventPictureCoverUrl.url !=undefined  ">
        <v-img
          :src="eventPictureCoverUrl.url"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        ></v-img>
      </div>
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
      <input
        v-show="false"
        ref="pictureListUpload"
        multiple
        type="file"
        @change="onPictureListUpload"
        accept="image/*"
      />
      <p v-if="eventPictureListsUrl.length>0">
        <span v-for="(eventPicture, index) in eventPictureListsUrl" :key="index">{{eventPicture.name}},</span>
      </p>
      <div v-if="eventPictureListsUrl.length>0">
        <v-img
          v-for="(image, index) in eventPictureListsUrl "
          :key="index"
          :src="image.url"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        ></v-img>
      </div>
    </span>

    <br />
    <br />
    <br />
    <center>
      <nuxt-link to="/organize/event/createEventForm">
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
import { mapGetters, mapActions } from "vuex";
import * as firebase from "firebase/app";
import "firebase/storage";
export default {
  name: "createEventForm",
  data() {
    return {
      eventPictureCoverUrl: {},
      eventPictureListsUrl: [],
      eventPictureCover: null,
      eventPictureLists: null
    };
  },
  computed: {
    ...mapGetters(["getEventTemplate"])
  },
  mounted() {
    this.loadPreviewPicture();
  },
  methods: {
    ...mapActions(["setPictureDetail"]),
    loadPreviewPicture() {
      console.log("----- preview image ----");
      let eventPictureCoverBase = this.getEventTemplate.eventPictureCoverBase;
      let eventPictureListsBase = this.getEventTemplate.eventPictureListsBase;
      console.log(eventPictureListsBase)
      if (eventPictureCoverBase != "") {
        this.eventPictureCoverUrl = eventPictureCoverBase;
        console.log(this.eventPictureCoverUrl);
      }
      if (eventPictureListsBase.length > 0) {
        this.eventPictureListsUrl = eventPictureListsBase;
        console.log(this.eventPictureListsUrl)
      }
    },
    onCoverPictureUpload(event) {
      console.log("uplaod din");
      this.eventPictureCover = event.target.files[0];
      console.log("!!!!!!!!!!!!!!!!!!!!!!")
      console.log(this.eventPictureCover)
      this.eventPictureCoverUrl = {}
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.eventPictureCoverUrl = {
          url: fileReader.result,
          name: event.target.files[0].name
        };
      });
      fileReader.readAsDataURL(this.eventPictureCover);
    },
    onPictureListUpload(event) {
      console.log("uplaod din");
      this.eventPictureLists = event.target.files;
      this.eventPictureListsUrl = []
      for (let i = 0; i < this.eventPictureLists.length; i++) {
        const fileReader = new FileReader();
        fileReader.addEventListener("load", () => {
          this.eventPictureListsUrl.push({
            url: fileReader.result,
            name: event.target.files[i].name
          });
        });
        fileReader.readAsDataURL(this.eventPictureLists[i]);
      }
    },

    async uploadPictureListToFirebase() {
      let pictureFiles = this.eventPictureLists;
      for (let i = 0; i < pictureFiles.length; i++) {
        let pictureFile = pictureFiles[i];
        let dateobj = new Date();
        let fileName = pictureFile.name + "_" + dateobj.toISOString();
        let storage = firebase.storage();
        let storageRef = storage.ref();
        let setupFile = storageRef.child(fileName);
        try {
          setupFile.put(this.eventPictureCover).then(snapshot => {
            snapshot.ref.getDownloadURL().then(downloadURL => {
              console.log(`Picture List ${i + 1} : `, downloadURL);
            });
          });
        } catch (err) {
          console.log(err);
        }
      }
    },
    async uploadCoverToFirebase() {
      let dateobj = new Date();
      let fileName = this.eventPictureCover.name + "_" + dateobj.toISOString();
      let storage = firebase.storage();
      let storageRef = storage.ref();
      let setupFile = storageRef.child(fileName);

      try {
        setupFile.put(this.eventPictureCover).then(snapshot => {
          snapshot.ref.getDownloadURL().then(downloadURL => {
            console.log("Picture Cover", downloadURL);
          });
        });
      } catch (err) {
        console.log(err);
      }
    },
    onUpload() {
      // this.uploadCoverToFirebase();
      // this.uploadPictureListToFirebase();
      console.log("---------Upload --------------");
      this.setPictureDetail({
        eventPictureCoverBase: this.eventPictureCoverUrl,
        eventPictureListsBase: this.eventPictureListsUrl
      });
      console.log(this.getEventTemplate);
      this.$router.push("/organize/event/createEventForm");
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