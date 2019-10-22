<template>
  <div>
    <v-carousel :show-arrows="false" xs6 lg12 :height="350">
      <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
    </v-carousel>

    <br />
    <br />

    <center>
      <nuxt-link :to="`/organize/organizerForm`" style="text-decoration-line:none;">
        <v-btn
          class="getStartButton white--text"
          depressed
          large
          height="50"
          color="#341646"
        >Get Started Now</v-btn>
      </nuxt-link>

      <br />
      <br />
      <h3>or</h3>

      <nuxt-link class="linkToContactUs" :to="`/?`">
        <br />
        <h2 class="contactUs">Contact Us</h2>
      </nuxt-link>

      <p>For more information</p>
    </center>

    <!-- <v-img src="https://kvillagebkk.com/wp-content/uploads/2019/05/YARD-SALE-001-1030x687.jpg"></v-img> -->
  </div>
</template>


<script>
export default {
  name: "becomeOrganizer",
  data() {
    return {
      items: [
        {
          src:
            "https://blogmedia.evbstatic.com/wp-content/uploads/wpmulti/sites/8/2018/01/15155312/iStock-667709450.jpg"
        },
        {
          src: "https://venues.be/website/image/bm.place/19/visual4_id/752x478"
        },
        {
          src:
            "https://kvillagebkk.com/wp-content/uploads/2019/05/YARD-SALE-001-1030x687.jpg"
        }
      ]
    };
  }
};
</script>


<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
}
.linkToContactUs {
  color: #100c4b;
}
.linkToContactUs:hover {
  text-decoration: underline #100c4b;
}
.getStartedButton {
  font-family: Roboto !important;
}
</style>