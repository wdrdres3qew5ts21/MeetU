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
        @click="emailLogin()"
      >Log In</v-btn>

      <center>
        <a class="linkForgotPassword" href>Forgot your password</a>
      </center>

      <br />
      <br />

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
    password: "",
    email: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 8) || "Name must be less than 8 characters"
    ],
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+/ || "E-mail must be valid"
    ],
    passwordRules: [
      v => !!v || "Password is required",
      v => /.+@.+/ || "Password must be valid"
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
    validate: function(e) {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    reset: function(e) {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    emailLogin: function() {
      this.$store
        .dispatch("signInWithEmail", {
          email: this.email,
          password: this.email
        })
        .then(() => {
          // this.email = "";
          // this.password = "";
        })
        .catch(error => {
          console.log(error.message);
          this.errorPopUp(error);
        });
    },
    loginPopup: function(e) {
      Swal.fire({
        title: "Log in Success!",
        type: "success",
        confirmButtonColor: "#4BB543",
        confirmButtonText: "OK"
      });
    },
    errorPopUp: function(error) {
      this.$swal({
        type: "error",
        title: "Login Failed !!!",
        text: `Please Check your user name or password again ! ${error.message}`
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
  color: #808080;
  /* text-decoration-line: underline; */
}

.linkSignup {
  color: #341646;
  font-weight: bold;
  text-decoration-line: none;
}

.linkSignup:hover {
  color: #808080;
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
