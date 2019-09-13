<template>
  <div>
    <h1>Popular Communities</h1>
    <br />
    <v-carousel>
      <v-carousel-item sm6 xs2 v-for="(item,i) in carouselsPhoto" :src="item.src" :key="i"></v-carousel-item>
    </v-carousel>
    <br />
    <h2>Recent activity</h2>
    <br />
    <center>
      
        <v-btn class="black--text"   block outline color="#341646" depressed large>Filter community <v-icon>filter_list </v-icon></v-btn>
      
    </center>
    <br />
    <v-card class="mx-auto" max-width="800"> 
      <v-img
        class="#341646--text"
        height="300px"
        src="http://www.openbiomedical.org/wordpress/wp-content/uploads/2016/08/community-025.jpg"
      ></v-img>
      <v-card-text>
        <span class="text--primary">
          <span>Community name :</span>
          <br />
          <span>Members :</span>
          <br />
        </span>
      </v-card-text>

      <v-card-actions>
        <v-btn class="white--text" color="#341646">JOIN</v-btn>
      </v-card-actions>
    </v-card>

    <br />
    <br />
    <center>
      <nuxt-link :to="``" style="text-decoration-line:none;">
        <v-btn class="black--text" outline color="#341646" depressed large>View more</v-btn>
      </nuxt-link>
    </center>

    <br />
    <br />
    <center>
      <h2>Let's Get Started !</h2>

      <br />

      <nuxt-link :to="`/community/createCommunity`" style="text-decoration-line:none;">
        <v-btn
          class="createCummunityButton white--text"
          depressed
          large
          color="#341646"
        >Create community</v-btn>
      </nuxt-link>
    </center>
  </div>
</template>
<style>
</style>
<script>
export default {
  data() {
    return {
      carouselsPhoto: [
        {
          src:
            "https://d1.awsstatic.com/Marketplace/cases/600x400_ClubAutomation_Logo.e312129c6220eb729d9c7b7912be2ee59a108421.png"
        },
        {
          src: "http://sites.ieee.org/vancouver-cs/files/2012/07/CIS_logo.png"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/ed-sheeran-divide-world-tour-2019/ed-sheeran-divide-world-tour-2019-event-rabbit-today-banner.jpg?la=th-TH&hash=C6B48BA5F7F79606DCE4B88DDE9A51EE9FE25A1C"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/siamsongkranmusicfestival/siamsongkranmusicfestival-banner.jpg?la=th-TH&hash=6DBCBED42B97294F2E6AAE635F84BCE455518957"
        }
      ]
    };
  }
};
</script>
<style lang="css">
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";
.v-carousel {
  height: 300px !important;
}

.createCummunityButton {
  font-weight: bold;
  margin-bottom: 10%;
}
</style>

