<template>
  <div>
    <h2>FreeScroll</h2>
    <!-- <flickity class="flickity" ref="flickity" :options="flickityOptions">
      <div class="carousel-cell"></div>
      <div class="carousel-cell"></div>
      <div class="carousel-cell"></div>
      <div class="carousel-cell"></div>
      <div class="carousel-cell"></div>
    </flickity> -->
  </div>
</template>
<script>
import Flickity from "vue-flickity";
export default {
  name: "CarouselCard",
  components: {
    Flickity
  },
  data() {
    return {
      flickityOptions: {
        initialIndex: 3,
        prevNextButtons: true,
        pageDots: true,
        wrapAround: true,
        freeScroll: true
        // any options from Flickity can be used
      }
    };
  }
};
</script>