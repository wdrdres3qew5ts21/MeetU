
<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >

    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>

    <v-text-field
      v-model="password"
      type="password"
      :rules="passwordRules"
      label="Password"
      required
    ></v-text-field>

    <v-text-field
      v-model="password"
      type="confirmPassword"
      :rules="passwordRules"
      label="Confirm Password"
      required
    ></v-text-field>


    <v-text-field
      v-model="firstname"
      :rules="firstnameRules"
      label="Firstname"
      required
    ></v-text-field>

    <v-text-field
      v-model="lastname"
      :rules="lastnameRules"
      label="Lastname"
      required
    ></v-text-field>


    <v-layout row wrap>
    <v-flex xs12 sm6 md4>
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="date"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="Date of Birth"
            prepend-icon="event"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <v-spacer></v-spacer>
          <v-btn flat color="error" @click="menu = false">Cancel</v-btn>
          <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-flex>


    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



      <v-radio-group v-model="row" row>
        Gender: &nbsp;&nbsp;&nbsp;


      <v-radio label="Male" value="radio-1"></v-radio>
      <v-radio label="Female" value="radio-2"></v-radio>
      <!-- <v-radio label="Unspecific" value="radio-3"></v-radio> -->
    </v-radio-group>

<v-container>
      <v-flex xs12 sm3 d-flex >
        <v-select
          :items="location"
          label="Location"
        ></v-select>
      </v-flex>
</v-container>

 <!-- <v-flex xs12 sm3 d-flex >
    <v-text-field
    
      v-model="phone"
      :rules="phoneRules"
      label="Phone"
      required
    ></v-text-field>
 </v-flex> -->
  </v-layout>

    <br>
      <v-btn block round="16px;" color="#341646" class="signIn mb-2 white--text" @click="Login"> 
          Sign Up
      </v-btn>
    <br>
            
    <center>
      <h3>Already member ?<a class="linkSignup" href="/login">
          Log In</a>
      </h3>
    </center>


  </v-form>

  

  



</template>


<script>
  export default {
      name:'LoginEmailForm',
    data: () => ({
     
        email: '',
        emailRules: [
            v => !!v || 'E-mail is required',
            v => /.+@.+/.test(v) || 'E-mail must be valid'
        ],
        password: '',
        passwordRules: [
            v => !!v || 'Password is required',
            v => /.+@.+/.test(v) || 'Password must be valid'
        ],

        valid: true,
        firstname: '',
        firstnameRules: [
            v => !!v || 'Firstname is required',
        ],

        valid: true,
        lastname: '',
        lastnameRules: [
            v => !!v || 'Lastname is required',
        ],

      confirmPassword: '',
      passwordRules: [
        v => !!v || 'Password is required',
        v => /.+@.+/.test(v) || 'Password must be valid'
      ],

      date: new Date().toISOString().substr(0, 10),
      menu: false,


      // location:['Bangkok','Udon']


      

    // })
      

    //   checkbox: false

      
    }),
    
        // forgotPassword:'Forgot your password?',

    methods: {
      validate () {
        if (this.$refs.form.validate()) {
          this.snackbar = true
        }
      },
      reset () {
        this.$refs.form.reset()
      },
      resetValidation () {
        this.$refs.form.resetValidation()
      }
    }
  }
</script>


<style>
.linkForgotPassword{
    color:#341646 ;
    text-decoration-line: none;
}

.linkForgotPassword:hover,a.linkForgotPassword:active{
    color: #FC5577;
    /* text-decoration-line: underline; */
}

.linkSignup{
     color:#341646 ;
     font-weight: bold;
     text-decoration-line: none;
}

.linkSignup:hover{
    color: #FC5577;
    font-weight: bold;
    text-decoration-line: none;
}
</style>
