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
    <organizerAccountCreate v-else />
  </transition>
</template>


<script>
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
      ]
    };
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    ...mapActions(["signOut"]),
    logout: function() {
      this.$router.push("/userProfile");
    },
    upgradeOrganize: async function() {
      await axios.post(`${process.env.USER_SERVICE}/organize/${this.getUser.uid}`, {
          organizeName: this.organizeName,
          phone: this.phone
        })
        .then(upgradeResponse => {
          this.isUpgradeSuccess = true;
          this.$swal({
            type: "success",
            title: "Upgrade success !!!",
            text: `upgrade successs`
          });
        })
        .catch(error => {
          console.log(error.response)
          this.$swal({
            type: "error",
            title: "Failed to upgrade !!!",
            text: `${error.response.data.response}`
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

.h3 {
  font-family: Roboto !important;
  font-weight: bold !important;
  font-size: 24px !important;
}

.switchAccount {
  font-family: Roboto !important;

  border: solid 1px #341646 !important;
}
</style>