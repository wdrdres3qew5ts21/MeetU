<template>
  <div>
    <div>
      <br />
      <center>
        <v-avatar size="100px" color="primary">
          <!-- <v-icon  dark size="100px">account_circle</v-icon> -->
          <img :src="badge.badgePicture" width="100px">
        </v-avatar>
      </center>
      <center>


        <h2>{{badge.badgeName}}</h2>
        <v-chip v-for="(badgeTag, index) in badge.badgeTags" :key="index">
          <v-avatar>
                  <v-icon color="primary">local_offer</v-icon>
        </v-avatar>
             <nuxt-link :to="`/event?category=${badgeTag}`">{{badgeTag}}</nuxt-link>
        </v-chip>



      </center>
      <br />
    </div>
    <v-data-table
      :items="rankingList"
      :pagination.sync="pagination"
      item-key="level"
      class="elevation-1"
    >
    <template v-slot:no-data>
    <v-alert :value="true" color="pink" icon="info"
    >
    <center>
    Badge not found !
    </center>
    </v-alert>
    </template>
      <template v-slot:items="props" v-if="isRankingRender">

        <tr>
          <!-- <td>{{badges.index}} </td> -->
          <td>
            <br />
            <center>
              <v-avatar size="60">
                <img :src="props.item.userDetail[0].photoURL" />
              </v-avatar>
            </center>
            <br />
          </td>
          <td>
            <h2>{{ props.item.userDetail[0].displayName }}</h2>
            <br />
            <p>Level: {{ props.item.level }} |  EXP: {{ props.item.exp }}</p>
          </td>
        </tr>
        
      </template>
    </v-data-table>
  </div>
</template>

<script>
import axios from "axios"
export default {
  data: () => ({
    pagination: {
      // sortBy: "level",
      // descending: true
    },
    rankingList: [
      {
        userDetail: [{
          displayName: '',
          level: 0,
          exp: 0
        }]
      },
      {
        name: "Frozen Yogurt",
        level: 1,
        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
      },
      {
        name: "Ice cream sandwich",
        level: 2,
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
      },
      {
        name: "Eclair",
        level: 3,
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Cupcake",
        level: 4,
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Gingerbread",
        level: 5,
        avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg"
      },
      {
        name: "Jelly bean",
        level: 7,
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
      },
      {
        name: "Lollipop",
        level: 6,
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "Honeycomb",
        level: 8,
        avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg"
      },
      {
        name: "Donut",
        level: 9,
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      },
      {
        name: "KitKat",
        level: 10,
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg"
      }
    ],
    isRankingRender: false,
    badge: {}
  }),
  mounted(){
    console.log(this.$route.params.badgeId)
    this.loadRankingOfUserInBadge()
    this.loadBadgeDetail()
  },
  methods: {
    loadRankingOfUserInBadge(){
      let badgeId = this.$route.params.badgeId
      axios.get(`${process.env.USER_SERVICE}/ranking/${badgeId}`)
      .then(rankingResponse=>{
        this.rankingList = rankingResponse.data
        this.isRankingRender = true
        console.log(this.rankingList)
      })
      .catch(err=>{
        console.log(err)
      })

    },
    loadBadgeDetail(){
      let badgeId = this.$route.params.badgeId
      axios.get(`${process.env.USER_SERVICE}/badge/${badgeId}`)
      .then(badgeResponse=>{
        this.badge = badgeResponse.data
        console.log(this.badge)
      })
      .catch(err=>{
        console.log(err)
      })

    }
  }
};
</script>

<style >
h2 {
  color: #341646;
}
</style>