<template>
  <v-card class="mx-auto elevation-0">
    <v-layout justify-space-between></v-layout>
    <v-divider dark></v-divider>
    <v-layout class="pa-3">
      <div class="headline">Review Event</div>
    </v-layout>

      <v-spacer></v-spacer>
      
      <v-layout ml-3>
  
      
      <v-rating
        v-model="reviewForm.rating"
        color="yellow accent-4"
        dense
        half-increments
        hover
        size="30"
      ></v-rating> 
      
      
      <span class="grey--text pl-2">({{ reviewForm.rating }})</span>
      

      <!-- <v-layout pa-3 my-0> -->
        
     
<!-- <v-card-title> -->
      
      <!-- </v-layout> -->

    </v-layout>

<!-- </v-card-title> -->
    <v-card-title primary-title>
      <v-textarea
        v-model="reviewForm.reviewDetail"
        outline
        name="description"
        label="My Feeling for this event :)"
        color="#004c99"
        rows="6"
        required
        hide-details
      ></v-textarea>
    </v-card-title>

    <v-layout justify-center>
      <v-btn flat outline @click="reviewEvent()">submit</v-btn>
    </v-layout>
  </v-card>
</template>



<script>
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  name: "ratingEvent",
  data: () => ({
    reviewForm: {
      rating: 0,
      reviewDetail: "",
      elasticEventId: ""
    }
  }),
  props: {
    ticketEvent: Object
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.reviewForm.elasticEventId = this.ticketEvent.elasticEventId;
  },
  methods: {
    reviewEvent() {
      console.log(this.reviewForm)
      axios
        .post(`${process.env.EVENT_SERVICE}/event/review`, this.reviewForm, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
          }
        })
        .then(reviewResponse => {
          this.$swal({
            type: "success",
            title: "Review Success !",
            text: "Review Success"
          });
        })
        .catch(err => {
           this.$swal({
            type: "error",
            title: "Review failed !",
            text: err.response
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
}

.headline{
  font-weight: bold;

}
</style>