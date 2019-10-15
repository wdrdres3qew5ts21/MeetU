<template>
  <div>
    <br />
    <h1>{{organize.organizeName}}</h1>
    <br />
    <br />
    <center>
      <nuxt-link :to="`/organize/event/createEventForm`" style="text-decoration-line:none;">
        <v-btn
          class="createEvent white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Create an Event</v-btn>
      </nuxt-link>
    </center>

    <center>
      <br />
      <p style="color:grey">No event, please create a new event.</p>
    </center>
  </div>
</template>


<script>
import axios from 'axios'
import { error } from 'util'
export default {
  name: "startedEvent",
  data(){
    return{
      organizeId: '',
      organize: {
        oraganizeName: '',
        organizeImageCover: '',
        organizeImageProfile: '',
      },
    }
  },
  mounted(){
    this.organizeId = this.$route.params.organizeId
    console.log(this.$route.params.organizeId)
    this.loadOrganizeDetail()
  },
  methods: {
    loadOrganizeDetail: async function(){
      axios.get(`${process.env.USER_SERVICE}/organize/${this.organizeId}`)
      .then(organizeResponse=>{
        console.log(organizeResponse.data.body)
        this.organize = organizeResponse.data.body
      })
      .catch(error=>{

      })
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

.h1 {
  font-family: Roboto;
}
</style>