<template>
  <div>
    <div>
      <center>
        <v-avatar size="130px">
          <!-- <img src="src" alt="alt"> -->
          <v-icon dark size="130px">account_circle</v-icon>
        </v-avatar>
      </center>
      <center>
        <h2>Badge name</h2>
      </center>
      <br />
    </div>
    <v-flex xs12 sm6 offset-sm3>
      <v-card class="rounded-card">
        <v-list two-line>
          <template v-for="(item, index) in items">
            <v-subheader v-if="item.header" :key="item.header">{{ item.header }}</v-subheader>
            <v-list-tile v-else :key="item.title" avatar>
              <v-list-tile-avatar>{{index + 1}}</v-list-tile-avatar>
              <v-list-tile-avatar>
                <img :src="item.avatar" />
              </v-list-tile-avatar>

              <v-list-tile-content>
                <v-list-tile-title v-html="item.title"></v-list-tile-title>
                <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-card>
    </v-flex>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: [
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        title: "Brunch this weekend?",
        subtitle:
          "<span class='text--primary'>Total activities</span> &mdash; ",
        divider: true,
        inset: true
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
        subtitle: "<span class='text--primary'>Total activities</span> &mdash;",
        divider: true,
        inset: true
      },

      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
        title: "Oui oui",
        subtitle:
          "<span class='text--primary'>Total activitiess</span> &mdash; ",
        divider: true,
        inset: true
      },

      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
        title: "Birthday gift",
        subtitle:
          "<span class='text--primary'>Total activities</span> &mdash; ",
        divider: true,
        inset: true
      },

      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
        title: "Recipe to try",
        subtitle: "<span class='text--primary'>Total activities</span>",
        divider: true,
        inset: true
      }
    ]
  })
};
</script>

<style >

</style>