
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


<!-- <v-container> -->

      <v-flex xs12 sm5 d-flex >
        <v-select
          :items="location"
          label="Location"
          prepend-icon="place"
        ></v-select>
      </v-flex>

<v-spacer></v-spacer>


    <v-layout row wrap>
      
      <v-flex xs3 >
        <v-select
          :items="phone"
          label="TH"
          prepend-icon="phone"
        ></v-select>

        
      </v-flex>
      <v-flex xs6 sm6 md6>
          <v-text-field
            label="Phone"
          ></v-text-field>
        </v-flex>
    </v-layout>


  </v-layout>

      <v-checkbox
      v-model="checkbox"
      
      label="By continuing, you agree to MeetU's 
            Terms of Service & Privacy Policy"
      required
    >
    </v-checkbox>

      <v-btn block round="16px;" color="#341646" class="signIn mb-2 white--text" 
      @click.stop="dialog = true"> 
          Sign Up
      </v-btn>

    <v-dialog
      v-model="dialog"
      max-width="280"
    >

      <v-card>
        
       <v-card-title class="headline">
         <v-container bg fill-height grid-list-md text-xs-center>
            <v-layout row wrap align-center>
              <v-flex xs12>
                  Sign up Success !
              </v-flex>


            <v-flex>
 
                <v-icon size="60px" color="green" dark>check_circle</v-icon>
            </v-flex>
 
            </v-layout>
          </v-container>
      </v-card-title>
        

        <v-card-actions>
          
          <v-container bg fill-height grid-list-md text-xs-center>
            <v-layout row wrap align-center>
          <v-btn
            href="/signup"
            color="red darken-1"
            flat="flat"
            @click="dialog = false"
          >
            Cancel
          </v-btn>
          </v-layout>
          </v-container>

            <v-container bg fill-height grid-list-md text-xs-center>
              <v-layout row wrap align-center>
          <v-btn
            href="/"
            color="green darken-1"
            flat="flat"
            @click="dialog = false"
          >
            OK
          </v-btn>
            </v-layout>
          </v-container>
        </v-card-actions>

        
      </v-card>
    </v-dialog>
    
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
      name:'signupForm',
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


      location:['Bangkok','Amnat Charoen','Ang Thong','Bueng Kan','Buriram','Chachoengsao','Chai Nat',
                'Chaiyaphum','Chanthaburi','Chiang Mai','Chiang Rai','Chonburi','Chumphon','Kalasin','Kamphaeng Phet',
                'Kanchanaburi','Khon Kaen','Krabi','Lampang','Lamphun','Loei','Lopburi','Mae Hong Son','Maha Sarakham',
                'Mukdahan','Nakhon Nayok','Nakhon Pathom','Nakhon Phanom','Nakhon Ratchasima','Nakhon Sawan','Nakhon Si Thammarat',
                'Nan','Narathiwat','Nong Bua Lam Phu','Nong Khai','Nonthaburi','Pathum Thani','Pattani','Phang Nga','Phatthalung','Phayao',
                'Phetchabun','Phetchaburi','Phichit','Phitsanulok','Phra Nakhon Si Ayutthaya','Phrae','Phuket','Prachinburi','Prachuap Khiri Khan',
                'Ranong','Ratchaburi','Rayong','Roi Et','Sa Kaeo','Sakon Nakhon','Samut Prakan','Samut Sakhon','Samut Songkhram','Saraburi','Satun',
                'Sing Buri','Sisaket','Songkhla','Sukhothai','Suphan Buri','Surat Thani','Surin','Tak','Trang','Trat','Ubon Ratchathani','Udon Thani',
                'Uthai Thani','Uttaradit','Yala','Yasothon']


                ,
        dialog:false


      

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
