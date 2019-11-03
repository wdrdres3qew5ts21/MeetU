<template>
  <div>
    <v-data-table
      :items="adminList"
      :pagination.sync="pagination"
      item-key="name"
      class="elevation-1"
    >
      <template v-slot:no-data>
        <v-alert :value="true" color="pink" icon="info">
          <center>Badge not found !</center>
        </v-alert>
      </template>
      <template v-slot:items="props">
        <nuxt-link :to="`/ranking/badge/${props.item.uid}`">
          <tr>
            <td>
              <br />
              <center>
                <v-avatar size="70">
                  <img :src="props.item.badgePicture" />
                </v-avatar>
              </center>
              <br />
            </td>
            <td>
              <h3>{{ props.item.badgeName }}</h3>
            </td>
          </tr>
        </nuxt-link>
      </template>
    </v-data-table>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "editAdmin",
  props: {
      adminList: Array
  },
  data() {
    return;
    {
    }
  },
  mounted(){
  },
  methods: {
    loadAdminInOrganize() {
      axios
        .get(`${process.env.EVENT_SERVICE}/category`)
        .then(categoryList => {
          this.categoryList = categoryList.data;
          this.setCategory(this.categoryList);
        })
        .catch(error => {
          this.categoryList = mockCategoryList;
        });
    }
  }
};
</script>