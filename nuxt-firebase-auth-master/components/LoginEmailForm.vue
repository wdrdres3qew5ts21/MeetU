<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

      <v-text-field
        v-model="password"
        type="password"
        :rules="passwordRules"
        label="Password"
        required
      ></v-text-field>

      <!-- <v-text-field 
      v-model="password" 
      type="confirmPassword" 
      :rules="passwordRules" 
      label="confirmPassword" 
      required 
      ></v-text-field>-->

      <v-checkbox v-model="checkbox" label="Remember me" required></v-checkbox>

      <v-btn
        block
        :round="true"
        color="#341646"
        class="signIn mb-2 white--text"
        @click="loginPopup"
      >Log In</v-btn>

      <center>
        <a class="linkForgotPassword" href>Forgot your password</a>
      </center>

      <br>
      <br>

      <center>
        <h3>
          Not a member ?
          <nuxt-link :to="`/signup`" style="text-decoration-line:none;">
            <a class="linkSignup">Sign Up</a>
          </nuxt-link>
        </h3>
      </center>
    </v-form>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";
export default {
  name: "LoginEmailForm",
  data: () => ({
    valid: true,
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+/.test(v) || "E-mail must be valid"
    ],
    password: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => /.+@.+/.test(v) || "Password must be valid"
    ],

    //   confirmPassword: '',
    //   passwordRules: [
    //     v => !!v || 'Password is required',
    //     v => /.+@.+/.test(v) || 'Password must be valid'
    //   ],

    checkbox: false
  }),

  forgotPassword: "Forgot your password?",

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    loginPopup: function(e) {
      Swal.fire({
        title: "Log in Success!",
        type: "success",
        confirmButtonColor: "#4BB543",
        confirmButtonText: "OK"
      });
    }
  }
};
</script> 
 
 
<style>
.linkForgotPassword {
  color: #341646;
  text-decoration-line: none;
}

.linkForgotPassword:hover,
a.linkForgotPassword:active {
  color: #fc5577;
  /* text-decoration-line: underline; */
}

.linkSignup {
  color: #341646;
  font-weight: bold;
  text-decoration-line: none;
}

.linkSignup:hover {
  color: #fc5577;
  font-weight: bold;
  text-decoration-line: none;
}

.popupLoginSuccess:hover {
  background-color: #fff;
}

.h3 {
  font-family: "Roboto";
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 5%;
}

.p {
  font-size: 16px;
}
</style> 
