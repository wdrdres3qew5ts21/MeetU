<template>
  <div>
    <br />
    <h2 class="h2">Create Badge</h2>
    <br />

    <span>
      <v-text-field label="* Badge Name" required v-model="badgeName"></v-text-field>
    </span>
    <br />
    <span>
      Badge image
      <br />

      <v-btn
        class="chooseFileButton"
        color="white"
        @click="$refs.badgePictureUpload.click()"
      >Choose file</v-btn>
      <input
        v-show="false"
        ref="badgePictureUpload"
        type="file"
        @change="onBadgeUpload"
        accept="image/*"
      />
      <p v-if="badgePicture">{{badgePicture.name}}</p>
      <div v-if="badgePicture">
        <v-img
          :src="badgePictureUrl"
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
          v-model="selectBadgeTags"
          :items="getCategory"
          :menu-props="{ maxHeight: '400' }"
          item-text="categoryLabel"
          item-value="categoryName"
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
      <nuxt-link to="/organize/event/createEventForm" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <v-btn
        class="saveButton white--text"
        color="#341646"
        depressed
        large
        height="50"
        @click="uploadBadgeToFirebase()"
      >Save</v-btn>
      <!-- <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="saveButton white--text" color="#341646" depressed large height="50">Save</v-btn>
      </nuxt-link>-->
    </center>
    <br />
    <br />
    <!-- <v-progress-circular
      :size="70"
      :width="7"
      color="purple"
      indeterminate
    ></v-progress-circular> -->
  </div>
</template>


<script>
import axios from "axios"
import {mapGetters} from "vuex"
import * as firebase from "firebase/app";
import "firebase/storage";
export default {
  name: "createEventForm",
  data() {
    return {
      badgeName: "",
      badgePictureUrl: "",
      badgePicture: null,
      selectBadgeTags: [],
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
  computed: {
    ...mapGetters(['getCategory'])
  },
  mounted(){
  
  },
  methods: {
    onBadgeUpload(event) {
      console.log("uplaod din");
      this.badgePicture = event.target.files[0];

      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.badgePictureUrl = fileReader.result;
      });
      fileReader.readAsDataURL(this.badgePicture);
    },
    async uploadBadgeToFirebase() {
      let loader = this.$loading.show()
      let dateobj = new Date();
      let fileName = this.badgePicture.name + "_" + dateobj.toISOString();
      let storage = firebase.storage();
      let storageRef = storage.ref();
      let setupFile = storageRef.child(fileName);

      try {
        setupFile.put(this.badgePicture).then(snapshot => {
          snapshot.ref.getDownloadURL().then(downloadURL => {
            console.log("Picture Cover", downloadURL);
            let badgeRequest = {
              badgeName: this.badgeName,
              badgeTags: this.selectBadgeTags,
              badgePicture: downloadURL
            }
            console.log(badgeRequest)
            axios.post(`${process.env.EVENT_SERVICE}/badge`,badgeRequest,{
                headers: {
                  'Authorization': `Bearer ${localStorage.getItem('jwtToken') || ''}`
              }
            }).then(badgeResponse=>{
              console.log(badgeResponse.data)
              loader.hide()
              this.$swal({
                type: "success",
                title: "Upload Badge success!!",
                text: `Upload Badge success!!`
              });
            }).catch(error => {
              this.$swal({
                type: "error",
                title: "Fail to Upload badge !!!",
                text: `${error.response.data.response}`
              });
              loader.hide()
              setupFile.delete().then(()=> {
                // File deleted successfully
                console.log("delete file success because upload fail")
              }).catch((error)=> {
                // Uh-oh, an error occurred!
              });
            });
          });
        });
      } catch (err) {
        console.log(err);
      }
    },
    saveBadge() {
      // this.uploadCoverToFirebase();
      // this.uploadPictureListToFirebase();
      
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