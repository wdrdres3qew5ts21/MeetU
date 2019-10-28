<template>
  <transition name="fade">
    <div v-if="!isUpgradeSuccess">
      <br />
      <h3>Organizer Account</h3>
      <br />
      <br />
      <p>You are signed in as :</p>
      {{getUser.displayName}}
      <br />
      <br />

      <center>
        <v-btn
          @click="logout()"
          block
          class="switchAccount #341646--text"
          color="#fff"
          depressed
          large
          height="40"
        >Switch Account</v-btn>
      </center>
      <br />

      <v-form ref="form" v-model="valid" :lazy-validation="false">
        <v-text-field
          v-model="organizeForm.organizeName"
          :rules="organizerNameRules"
          label="* Organizer Name"
          required
        ></v-text-field>

        <v-text-field
          label="Phone number"
          :rules="phoneRules"
          placeholder="phone number"
          counter="10"
          prepend-icon="phone"
          v-model="organizeForm.phone"
          type="number"
        ></v-text-field>

        <br />

        <span>
          Organize Image (Round)
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
          <p
            v-if="organizeForm.organizeImageCover.name !=undefined  "
          >{{organizeForm.organizeImageCover.name}}</p>
          <div v-if="organizeForm.organizeImageCover.url !=undefined  ">
            <v-img
              :src="organizeForm.organizeImageCover.url"
              aspect-ratio="1"
              class="grey lighten-2 img-circle"
              max-width="200"
              max-height="200"
            ></v-img>
          </div>
        </span>

        <br />
        <v-flex xs12>
          <v-alert :value="isUserEmailNotFound" color="error" outline>{{userEmailNotFound}}</v-alert>
        </v-flex>
        <v-flex xs12 d-flex>
          <v-combobox
            :items="items"
            label="Add Admin"
            v-model="adminList"
            multiple
            chips
          >
            <template v-slot:selection="data">
              <v-chip
                :key="JSON.stringify(data.item)"
                :selected="data.selected"
                :disabled="data.disabled"
                class="v-chip--select-multi"
                @input="data.parent.selectItem(data.item)"
              >
                <v-avatar class="accent white--text" v-text="data.item.slice(0, 1).toUpperCase()"></v-avatar>
                {{ data.item }}
              </v-chip>
            </template>
          </v-combobox>
        </v-flex>

        <v-checkbox v-model="agreement" :rules="[rules.required]" color="#341646">
          <template v-slot:label>
            I agree to the&nbsp;Terms of Service
            <!-- <nuxt-link to="/" @click.stop.prevent="dialog = true">Terms of Service</nuxt-link> -->
          </template>
        </v-checkbox>
        <br />
        <br />
        <center>
          <v-btn
            @click="upgradeOrganize()"
            block
            :disabled="!valid"
            class="setupOrganizerAccount white--text"
            color="#341646"
            depressed
            large
            height="50"
          >Setup Organizer Account</v-btn>
        </center>
        <br />
        <br />
      </v-form>
    </div>
    <organizerAccountCreate :organizeId="createdOrganizeId" v-else />
  </transition>
</template>


<script>
import * as firebase from "firebase/app";
import "firebase/auth";
import "firebase/storage";
import organizerAccountCreate from "~/components/organizerAccountCreate";
import { mapGetters, mapActions } from "vuex";
import axios from "axios";
import { error } from "util";
export default {
  name: "organizerForm",
  components: {
    organizerAccountCreate
  },
  data() {
    return {
      isUserEmailNotFound: false,
      userEmailNotFound: '',
      valid: true,
      isUpgradeSuccess: false,
      valid: true,
      adminList: [],
      organizeForm: {
        phone: "",
        organizeName: "",
        createdOrganizeId: "",
        adminList: [],
        organizeImageCover: {}
      },
      organizeImageCover: {},
      names: [
        {
          name: "A"
        }
      ],
      organizerNameRules: [
        v => !!v || "Organizer Name is required",
        v => (v && v.length >= 2) || "Name must be more than 2 characters"
      ],
      checkbox: false,
      agreement: false,
      rules: {
        required: v => !!v || "This field is required"
      },
      phoneRules: [
        v => !!v || "Phone Number is required",
        v => (v && v.length === 10) || "Phone Number msut be 10 digit"
      ],
      items: []
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  watch: {
    "adminList"(updateAdmin, oldAdmin) {
      this.isUserEmailNotFound = false
      this.userEmailNotFound = ''
      console.log("update admin : ",updateAdmin)
      console.log("old admin : ",oldAdmin)
      console.log(updateAdmin[updateAdmin.length-1])
      let newAdmin = updateAdmin[updateAdmin.length-1]
      let previousAdmin = oldAdmin[oldAdmin.length-1]

      if(newAdmin != previousAdmin){
        axios.get(`${process.env.USER_SERVICE}/user/email/${newAdmin}`)
        .then(userResponse =>{
          console.log(userResponse)
        })
        .catch(err =>{
          this.isUserEmailNotFound = true
          this.adminList.splice(updateAdmin.length-1, 1)
          this.userEmailNotFound = err.response.data.response
        })
      }
      
    }
  },
  methods: {
    ...mapActions(["signOut", "setUser"]),
    logout: function() {
      this.$router.push("/userProfile");
    },
    upgradeOrganize: async function() {
      let loader = this.$loading.show()
      console.log("upgrade fuq");
      console.log(this.organizeForm);
      let pictureFile = this.organizeForm.organizeImageCover;
      let dateobj = new Date();
      let fileName = pictureFile.name + "_" + dateobj.toISOString();
      let storage = firebase.storage();
      let storageRef = storage.ref("/organize");
      let setupFile = storageRef.child(fileName);
      setupFile
        .putString(pictureFile.url, "data_url", { contentType: "image/jpeg" })
        .then(snapshot => {
          snapshot.ref.getDownloadURL().then(organizeImageCover => {
            loader.hide()
            console.log(organizeImageCover)
          });
        });

      // await axios
      //   .post(`${process.env.USER_SERVICE}/organize/${this.getUser.uid}`, {
      //     organizeName: this.organizeName,
      //     phone: this.phone
      //   })
      //   .then(upgradeResponse => {
      //     this.organizeId = upgradeResponse.data.organizeId
      //     this.isUpgradeSuccess = true;
      //     this.$swal({
      //       type: "success",
      //       title: "Upgrade success !!!",
      //       text: `upgrade successs`
      //     });
      //   })
      //   .catch(error => {
      //     console.log(error.response);
      //     this.$swal({
      //       type: "error",
      //       title: "Failed to upgrade !!!",
      //       text: `${error.response}`
      //     });
      //   });
    },
    ...mapActions(["setPictureDetail"]),
    onCoverPictureUpload(event) {
      let organizeCover = event.target.files[0];
      console.log("upload picture");
      this.organizeForm.organizeImageCover = {};
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.organizeForm.organizeImageCover = {
          url: fileReader.result,
          name: event.target.files[0].name
        };
      });
      fileReader.readAsDataURL(organizeCover);
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

.h3 {
  font-family: Roboto !important;
  font-weight: bold !important;
  font-size: 24px !important;
}

.switchAccount {
  font-family: Roboto !important;

  border: solid 1px #341646 !important;
}

.chooseFileButton {
  border: solid 1px #341646 !important;
}
.img-circle {
  border-radius: 50%;
}
</style>