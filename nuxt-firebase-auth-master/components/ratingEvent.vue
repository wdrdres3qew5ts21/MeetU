<template>
  <v-card
    class="mx-auto elevation-10"
    color="#F81A93"
    dark
    style="max-width: 400px;"
  >
    <!-- <v-layout justify-space-between>
      <v-flex xs8>
        <v-card-title primary-title>
          <div>
            <div class="headline">Event Name</div>
            <div>Oraganizer name</div>
            <div>Location</div>
          </div>
        </v-card-title>
      </v-flex> -->

      <!-- image -->

      <!-- <v-img
        class="shrink ma-2"
        contain
        height="125px"
        src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"
        style="flex-basis: 125px"
      ></v-img> -->


    <!-- </v-layout> -->
    <v-divider dark></v-divider>
    <v-card-actions class="pa-3">
      Rate this event
      <v-spacer></v-spacer>
      <span class="grey--text text--lighten-2 caption mr-2">
        ({{ rating }})
      </span>

      <v-rating
        v-model="rating"
        background-color="white"
        color="yellow accent-4"
        dense
        half-increments
        hover
        size="18"
       
      ></v-rating>
      
    </v-card-actions>

<v-layout justify-center>
    
    <v-btn flat outline @click="clickStar()">submit</v-btn>
</v-layout>
  </v-card>
</template>



<script>

  export default {
      name:"ratingEvent",
    data: () => ({
      rating: 0
    }),

    methods:{
        clickStar(){
             console.log(this.rating);
        }
    }
  }
</script>


<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;

}
    
</style>