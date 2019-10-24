<template>
  <div>
    <div>
      <br />
      <center>
        <v-avatar size="100px" color="primary">
          <!-- <img src="src" alt="alt"> -->
          <v-icon dark size="100px">account_circle</v-icon>
        </v-avatar>
      </center>
      <center>
        <h2>Badge name</h2>
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
    <v-alert :value="true" color="pink" icon="info"
    >
    <center>
    Badge not found !
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
    ]
  })
};
</script>

<style >
h2 {
  color: #341646;
}
</style>