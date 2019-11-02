<template>
  <div>
    <h2 class="h2">Edit Organizer Settings</h2>
    <br />

    <!-- <h3 class="h3">Organizer Details</h3>
    <br />-->

    <div>
      <v-form>
        <h3 class="h3">{{organizeForm.organizeName}}</h3>
        <!-- <v-text-field
          v-model="organizeForm.organizeName"
          :rules="organizerNameRules"
          label="* Organizer Name"
          required
        ></v-text-field>-->

        <v-textarea outlined name="input-7-4" v-model="organizeForm.organizeDetail" label="Description"></v-textarea>
      </v-form>
    </div>

    <br />

    <h3 class="h3">Profile</h3>

    <center>
      <v-avatar color="#DEDEDE" size="152">
        <img :src="organizeForm.organizeImageCover" alt srcset />
        <!-- <span class="black--text">Image</span> -->
      </v-avatar>

      <br />
      <br />

      <v-btn
        class="chooseFileButton"
        color="white"
        @click="$refs.inputUpload.click()"
      >Upload an Image</v-btn>
      <input v-show="false" ref="inputUpload" type="file" />
    </center>

    <v-form>
      <v-text-field v-model="organizeForm.email" :rules="emailRules" label="* Email" required></v-text-field>
    </v-form>

    <v-flex xs12 sm9 md9>
      <v-text-field :rules="phoneRules" label="* Phone" v-model="organizeForm.phone"></v-text-field>
    </v-flex>

    <br />
    <br />
    <h3 class="h3">Organizer Social Networks</h3>
    <br />

    <v-form>
      <v-text-field
        class="textfield"
        v-model="organizeForm.website"
        label="Website"
        placeholder="http://"
      ></v-text-field>
    </v-form>

    <v-form>
      <v-text-field class="textfield" v-model="organizeForm.line" label="Line" placeholder="@"></v-text-field>
    </v-form>

    <v-form>
      <v-text-field
        class="textfield"
        v-model="organizeForm.facebook"
        label="Facebook"
        placeholder="http://"
      ></v-text-field>
    </v-form>

    <v-form>
      <v-text-field
        class="textfield"
        v-model="organizeForm.twitter"
        label="Twitter"
        placeholder="@"
      ></v-text-field>
    </v-form>

    <v-form>
      <v-text-field
        class="textfield"
        v-model="organizeForm.instagram"
        label="Instagram"
        placeholder="@"
      ></v-text-field>
    </v-form>

    <br />
    <center>
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn class="cancelButton white--text" color="#AEAEAE" depressed large height="50">Cancel</v-btn>
      </nuxt-link>

      <v-btn
        class="saveButton white--text"
        color="#341646"
        @click="updateOrganize()"
        depressed
        large
        height="50"
      >Save</v-btn>
    </center>
    <br />
    <br />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "editOrganizerSettings",
  data() {
    return {
      organizerNameRules: [v => !!v || "Name is required"],
      emailRules: [v => /.+@.+/ || "E-mail must be valid"],
      phoneRules: [v => !!v || "Phone is required"],
      phone: ["TH", "EN"],
      organizeForm: {
        organizeImageCover: "",
        organizeName: "",
        organizeDetail: "",
        organizeOwner: {
          uid: ""
        },
        phone: "",
        email: "",
        website: "",
        line: "",
        facebook: "",
        twitter: "",
        instagram: ""
      }
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.loadOrganizeDetail();
  },
  methods: {
    loadOrganizeDetail() {
      axios
        .get(
          `${process.env.USER_SERVICE}/organize/${this.$route.params.organizeId}`
        )
        .then(organizeResponse => {
          this.organizeForm = organizeResponse.data;
          this.organizeForm.organizeOwner.uid = this.getUser.uid;
          console.log("-----organize ----");
          console.log(organizeResponse.data);
        })
        .catch(err => {
          console.log("eefdsfdsfds");
        });
    },
    updateOrganize() {
      axios
        .patch(
          `${process.env.USER_SERVICE}/organize/${this.$route.params.organizeId}`,
          this.organizeForm
        )
        .then(organizeResponse => {
          this.$swal({
            type: "success",
            title: "Update Organize Profile success !!!",
            text: "update Organize Profile success !!!"
          });
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Success to scan QR Code!!!",
            text: `${err.response.data.response}`
          });
        });
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

.chooseFileButton {
  border: solid 1px #341646 !important;
}

.textfield label {
  font-size: 20px;
}
</style>