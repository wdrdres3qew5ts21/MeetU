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

    <!-- <div class="avatars">
     
      <span class="avatar">
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" width="50" height="50" />
      </span>

      <span class="avatar">
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" width="50" height="50" />
      </span>

      <span class="avatar">
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" width="50" height="50" />
      </span>
    </div> -->
    <ul>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
</ul>


    <center>
      <v-btn class="black--text" block outline color="#341646" depressed large>
        Filter community
        <v-icon>filter_list</v-icon>
      </v-btn>
    </center>
    <br />

    <center>
      <v-flex v-for="card in cards" :key="card.title" v-bind="{ [`xs${card.flex}`]: true }">
        <v-card class="mx-auto">
          <!-- :src="meetu.imageUrl" -->
          <v-img :src="card.src" height="200px"></v-img>
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
      </v-flex>
    </center>
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
      cards: [
        {
          // title: "Ice-cream social",
          src:
            "https://news.cci.fsu.edu/files/2014/03/2014-CCI-SLC-Ice-Cream-Social-Flyer.png",
          flex: 6
        },
        {
          // title: "Mars event",
          src:
            "https://s3.amazonaws.com/thumbnails.venngage.com/template/70fdebc4-7bb7-49fc-9caf-700f890de92b.png",
          flex: 6
        },
        {
          // title: "Charity auction",
          src:
            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/copy-of-event-flyer-design-template-166759b5d38435948e0d0998f22ef4f3_screen.jpg?ts=1566601755",
          flex: 6
        },
        {
          // title: "Ice-cream social",
          src:
            "https://i.pinimg.com/originals/0f/04/6f/0f046f654320d387d7608a754f205d1e.png",
          flex: 6
        }
      ],

      linksFooter: [
        "Home",
        "About Us",
        "Team",
        "Services",
        "Blog",
        "Contact Us"
      ],

      carouselsPhoto: [
        {
          src:
            "https://d1.awsstatic.com/Marketplace/cases/600x400_ClubAutomation_Logo.e312129c6220eb729d9c7b7912be2ee59a108421.png"
        },
        {
          src: "https://eventbrowse.com/wp-content/uploads/2018/11/DoG1Z3FXUAEfR3N.jpg"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/bangkok-block-party-2019/bangkok-block-party-2019-event-banner.jpg?la=th-TH&hash=FE90C817F1C953E5DCB6834242EE517801037D28"
        },
        {
          src:
            "https://www.rabbittoday.com/-/media/rabbittoday/content/events/atlantiswaterfestival2019/atlantiswaterfestival2019-banner.jpg?la=th-TH&hash=5346DE429E1704AF25E6CCBC7FDC25CA56AF09ADg"
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

/* .avatars {
  text-align: left;  
  padding-left: 25px; 
}
.avatar img {
  border-radius: 50%;
  position: relative;
  left: -5px;
  margin-left: -20px;
  z-index: 1;
} */

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

li {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 2px solid white;
  display: inline-block;
  position: relative;
  box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.2);
  -webkit-transition: 0.2s ease;
  transition: 0.2s ease;
}

li:nth-child(1) {
  background: blue;
}
li:nth-child(2) {
  background: green;
}
li:nth-child(3) {
  background: purple;
}
li:nth-child(4) {
  background: tomato;
}
li:nth-child(n + 2) {
  margin-left: -50px;
}

ul:hover li:nth-child(n + 2) {
  margin-left: -10px;
}
</style>

