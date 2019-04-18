<template> 
    <div> 
<!--  
        <v-header max-width="150px;"> 
            <img src="https://d1csarkz8obe9u.cloudfront.net/posterpreviews/ice-cream-poster-template-90594d7d131183339dc218dd2cc30311_screen.jpg?ts=1438252613"> 
        </v-header> --> 
 
 
 <!-- <v-app id="inspire"> --> 
<!-- <v-parallax 
 
    src="https://d1csarkz8obe9u.cloudfront.net/posterpreviews/ice-cream-poster-template-90594d7d131183339dc218dd2cc30311_screen.jpg?ts=1438252613" 
    height="200px;" 
    jumbotron 
 
  > 
 
   
  </v-parallax> --> 
 
 
 
 <!-- </v-app> --> 
 
 
    <center><h1>Hello</h1></center> 
 
 
    <!-- {{event.eventPhoto}} --> 
 
    </div> 
</template> 
 
 
<script> 
export default { 
   
    data(){ 
        return{ 
 
        } 
    }, 
    mounted:function(){ 
        console.log("Hello") 
        console.log(this.$nuxt.$route) 
    }, 
    methods:{ 
 
    } 
} 
</script> 
 
 
 
 
<style> 
 
.v-parallax__image { 
  /* transform: none !important; */ 
  max-width: 200%; 
  width: 100%; 
} 
 
 
</style> 
 
