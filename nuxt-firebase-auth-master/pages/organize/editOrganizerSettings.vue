<template>
  <div>
    <br />
    <h2 class="h2">Edit Organizer Settings</h2>
    <br />

    <h3 class="h3">Organizer Details</h3>
    <br />

    <div>
      <v-form>
        <v-text-field
          v-model="organizerName"
          :rules="organizerNameRules"
          label="* Organizer Name"
          required
        ></v-text-field>

        <v-textarea outlined name="input-7-4" label="Description"></v-textarea>
      </v-form>
    </div>

    <br />

    <h3 class="h3">Profile</h3>

    <center>
      <v-avatar color="#DEDEDE" size="120">
        <span class="black--text">Image</span>
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
      <v-text-field v-model="email" :rules="emailRules" label="* Email" required></v-text-field>
    </v-form>

    <v-layout row wrap>
      <v-flex xs3>
        <v-select :items="phone" label="TH" prepend-icon="phone"></v-select>
      </v-flex>
      <v-flex xs9 sm9 md9>
        <v-text-field :rules="phoneRules" label="* Phone" v-model="phone"></v-text-field>
      </v-flex>
    </v-layout>

    <br />
    <br />
    <h3 class="h3">Organizer Social Networks</h3>
    <br />

    <v-form>
      <v-text-field class="textfield" v-model="website" label="Website" placeholder="http://"></v-text-field>
    </v-form>

    <v-form>
      <v-text-field class="textfield" v-model="line" label="Line" placeholder="@"></v-text-field>
    </v-form>

    <v-form>
      <v-text-field class="textfield" v-model="facebook" label="Facebook" placeholder="http://"></v-text-field>
    </v-form>

    <v-form>
      <v-text-field class="textfield" v-model="twitter" label="Twitter" placeholder="@"></v-text-field>
    </v-form>

    <v-form>
      <v-text-field class="textfield" v-model="instagram" label="Instagram" placeholder="@"></v-text-field>
    </v-form>


    <br>
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
export default {
  name:"editOrganizerSettings",
  data: () => ({
    organizerNameRules: [v => !!v || "Name is required"],
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+/ || "E-mail must be valid"
    ],
    phoneRules: [v => !!v || "Phone is required"]
  })
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