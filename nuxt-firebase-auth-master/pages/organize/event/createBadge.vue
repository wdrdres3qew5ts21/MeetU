<template>
  <div>
    <br />
    <h2 class="h2">Create Badge</h2>
    <br />

    <span>
      <v-text-field label="* Badge Name" required></v-text-field>
    </span>
    <br />
    <span>
      Badge image
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
      <p v-if="eventPictureCover">{{eventPictureCover.name}}</p>
      <div v-if="eventPictureCover">
        <v-img
          :src="eventPictureCoverUrl"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        ></v-img>
      </div>
    </span>

    <br />
    <br />

    <span>Badge Tag</span>

    <v-layout row wrap align-center>
      <v-flex xs12 sm6>
        <v-select
          v-model="selectBadgeTag"
          :items="categoryEventList"
          :menu-props="{ maxHeight: '400' }"
          label="Select"
          multiple
          hint="Select Badge Tag"
          persistent-hint
        ></v-select>
      </v-flex>
    </v-layout>


    <!-- <v-chip label color="#F81A93" text-color="white">
      <v-icon left>label</v-icon>Tags
    </v-chip>-->

    <!-- <div>

    <v-combobox
    v-model="chips"
    :Items="Items"
    label="ฺBadge Tag"
    chips
    prepend-icon="label"
    block
    multiple
  >
    <template v-slot:selection="data">
      <v-chip
        :selected="data.selected"
        close
        @input="remove(data.item)"
      >
        <strong>{{ data.item }}</strong>&nbsp;
       
      </v-chip>
    </template>
  </v-combobox>
    </div>-->

    <br />
    <br />
    <br />
    <center>
      <nuxt-link :to="``" style="text-decoration-line:none;">
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
      eventPictureCoverUrl: "",
      eventPictureListsUrl: [],
      eventPictureCover: null,
      eventPictureLists: null,
      selectBadgeTag: [],
      categoryEventList: [
        "Art",
        "Beauty",
        "Book",
        "Business",
        "Family",
        "Film",
        "Game",
        "Music",
        "Photography",
        "Social",
        "Technology"
      ],
      items:['Test','Testtttt','112222']

    };
  },
  methods: {
    onCoverPictureUpload(event) {
      console.log("uplaod din");
      this.eventPictureCover = event.target.files[0];

      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.eventPictureCoverUrl = fileReader.result;
      });
      fileReader.readAsDataURL(this.eventPictureCover);
    },
    onPictureListUpload(event) {
      console.log("uplaod din");
      this.eventPictureLists = event.target.files;

      for (let i = 0; i < this.eventPictureLists.length; i++) {
        const fileReader = new FileReader();
        fileReader.addEventListener("load", () => {
          this.eventPictureListsUrl.push(fileReader.result);
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
      this.uploadCoverToFirebase();
      this.uploadPictureListToFirebase();
    },
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
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