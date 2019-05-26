<template>
  <div>
    <v-carousel hide-delimiters hide-controls xs6 sm12 height="200px;">
      <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
    </v-carousel>
<br>
    <h3>Event name:</h3>
    <br>
    <p>Date & Time:</p>
    <p>Location:</p>
    <center>

        <!-- ใส่รูป map -->

        <img src="https://maspalomaspride.es/wp-content/uploads/2018/11/ice_cream_web_2-696x1024.jpg" 
        alt="ice cream event"
        width="250px;" height="350px;">
        <br>
    <v-btn block color="success" large>JOIN</v-btn></center>

    <!-- {{event.eventPhoto}} -->
  </div>
</template> 
 
 
<script>
export default {
  data() {
    return {
      items: [
        {
          src: "https://res.cloudinary.com/xceed-me/image/upload/f_auto/v1553892848/events/cover/freedom-party-ice-cream-closing-pool-party-official-event-maspalomas-pride-2019-1553892847.jpeg.jpg"
        }
      ]
    };
  },
  mounted: function() {
    console.log("Hello");
    console.log(this.$nuxt.$route);
  },
  methods: {}
};
</script> 
 
 
 
 
<style>

</style> 
 
