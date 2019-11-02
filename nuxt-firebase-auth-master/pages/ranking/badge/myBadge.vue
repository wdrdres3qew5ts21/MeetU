<template>
  <div>
    <div>
      <br />
      <center>
        <h1>My Badges</h1>
      </center>
      <br />
    </div>

    <v-data-table
      :items="badges"
      :pagination.sync="pagination"
      item-key="level"
      class="elevation-1"
    >
      <template v-slot:no-data>
        <v-alert :value="true" color="pink" icon="info">
          <center>
            Your badge not found !
            <br />Let's join event for get badge
          </center>
        </v-alert>
      </template>

      <template v-slot:items="props">
        <tr>
          <!-- <td>{{badges.index}} </td> -->
          <td>
            <br />
            <center>
              <v-avatar size="60">
                <img :src="props.item.badgePicture" />
              </v-avatar>
            </center>
            <br />
          </td>
          <td>
            <h2>{{ props.item.badgeName }}</h2>
            <br />
            <p>Level: {{ props.item.level }} |  EXP: {{ props.item.exp }}</p>
          </td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import {mapGetters} from "vuex"
import axios from 'axios'
export default {
  data: () => ({
    pagination: {
      // sortBy: "level",
      // descending: true
    },
    badges: [
      {
        badgeName: "My badge",
        level: 1,
        badgePicture: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
      },
    ]
  }),
  computed: {
    ...mapGetters(['getUser'])
  },
  mounted(){
    this.loadMyBadge()
  },
  methods: {
    loadMyBadge(){
    let loader = this.$loading.show()
      axios.get(`${process.env.USER_SERVICE}/badges/user/${this.getUser.uid}`).then(badgeListResponse =>{
        this.badges = badgeListResponse.data
        console.log(this.badges)
        loader.hide()
      })
      .catch(err=>{
        loader.hide()
      })
    }
  }
};
</script>

<style >
h1 {
  color: #341646;
}
</style>