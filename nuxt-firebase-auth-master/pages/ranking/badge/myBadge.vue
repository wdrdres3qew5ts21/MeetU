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
                <img :src="props.item.avatar" />
              </v-avatar>
            </center>
            <br />
          </td>
          <td>
            <h2>{{ props.item.name }}</h2>
            <br />Detail:
          </td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({
    pagination: {
      sortBy: "level",
      descending: true
    },
    badges: [
      {
        name: "Frozen Yogurt",
        level: 1,
        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg"
      },
      {
        name: "Ice cream sandwich",
        level: 2,
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg"
      }
    ]
  }),
  methods: {
    loadMyBadge(){
      axios.get(`${process.env.USER_SERVICE}/`).then(badgeListResponse =>{

      })
      .catch(err=>{

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