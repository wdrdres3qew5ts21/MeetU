<template>
    <div>

    <v-card class="elevation-0 mx-auto" 
      color="white"     
      max-width="150"
    >
        <v-img
          :aspect-ratio="1/1"
          src='https://image.flaticon.com/icons/png/512/64/64572.png'
        >
        
        </v-img> 
      
          <v-card-text
            class="pt-4"
            style="position: relative;"
          > 
              <v-btn
                absolute
                color="#341646"
                class="white--text"
                fab
                normal
                right
                top
              >
                  <v-icon>add_a_photo</v-icon>
              </v-btn> 
          </v-card-text>
    </v-card>



      <v-text-field
          v-model="organizerName"
          :rules="organizerNameRules"
          label="Organizer Name"
          required
      ></v-text-field>


       <v-flex xs12 sm5 d-flex >
        <v-select
          :items="location"
          label="Location"
        ></v-select>
      </v-flex>

        <v-flex xs6 sm3 d-flex>
        <v-text-field
          :items="location"
          label="Social Link"
        ></v-text-field>
        <v-select
          :items="socialLink"
          placeholder="Instagram"
        ></v-select>
      </v-flex>


      <v-btn block round="16px;" color="#341646" class="signIn mb-2 white--text"
      @click.stop="dialog = true"
      
    >
      Upgrade Account
    </v-btn>


    

    <v-dialog
      v-model="dialog"
      max-width="280"
    >

    
      <v-card>
        
       <v-card-title class="headline">
         <v-container bg fill-height grid-list-md text-xs-center>
            <v-layout row wrap align-center>
              <v-flex xs12 class="upgradeSuccessTextPopup">
                  Upgrade Account Success!
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
            color="red darken-1"
            flat="flat"
            @click="dialog = false"
            class="popupUpgrade"
          >
            Cancel
          </v-btn>
          </v-layout>
          </v-container>

            <v-container bg fill-height grid-list-md text-xs-center>
              <v-layout row wrap align-center>
          <v-btn
            color="green darken-1"
            flat="flat"
            @click="dialog = false"
            class="popupUpgrade"
          >
            OK
          </v-btn>
            </v-layout>
          </v-container>
        </v-card-actions>

        
      </v-card>
    </v-dialog>


        


    </div>
</template>


<script>
export default {
  name:'organizerForm',
data() {
  return {
      selectedFile: null,
      valid: true,
      organizerName: '',
      organizerNameRules: [
            v => !!v || 'Organizer name is required',
      ],
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
      socialLink:['Instagram','Twitter','Line','Youtube','Twitter','Skype','GitHub'],
      dialog: false,
      
    
  }
},
methods: {
  onFileChanged (event) {
    this.selectedFile = event.target.files[0]
  },
  onUpload() {
    // upload file, get it from this.selectedFile
      },
      
    }
  
};
</script>


<style>
.accountButton{
  width: 500px;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}


.upgradeSuccessTextPopup{
  font-size: 18px;
}

.popupUpgrade:hover{
  background-color: white
}
</style>
