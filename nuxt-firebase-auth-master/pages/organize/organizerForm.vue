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
          v-model="organizeName"
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
          v-model="phone"
          type="number"
        ></v-text-field>

<br>

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


      <br>
      <v-flex xs12 d-flex>
        <v-combobox
          :items="items"
          label="Add Admin"
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
              <v-avatar
                class="accent white--text"
                v-text="data.item.slice(0, 1).toUpperCase()"
              ></v-avatar>
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
      valid: true,
      isUpgradeSuccess: false,
      phone: "",
      organizeName: "",
      valid: true,
      createdOrganizeId: '',
      eventPictureCoverUrl: {},
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
        items: [
        ]
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    ...mapActions(["signOut", "setUser"]),
    logout: function() {
      this.$router.push("/userProfile");
    },
    upgradeOrganize: async function() {
      console.log("upgrade fuq")
      await axios
        .post(`${process.env.USER_SERVICE}/organize/${this.getUser.uid}`, {
          organizeName: this.organizeName,
          phone: this.phone
        })
        .then(upgradeResponse => {
          this.organizeId = upgradeResponse.data.organizeId
          this.isUpgradeSuccess = true;
          this.$swal({
            type: "success",
            title: "Upgrade success !!!",
            text: `upgrade successs`
          });
        })
        .catch(error => {
          console.log(error.response);
          this.$swal({
            type: "error",
            title: "Failed to upgrade !!!",
            text: `${error.response}`
          });
        });
    },
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
</style>