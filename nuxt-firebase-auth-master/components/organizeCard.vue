<template>
  <v-card class="mx-auto" max-width="400">
    <v-img class="#341646--text" height="200px" :src="organizePictureCover">
      <!-- <v-card-title class="align-end fill-height">Top 10 Australian beaches</v-card-title> -->
    </v-img>
    <v-card-text>
      <span class="text--primary">
        <h3>{{organizeName}}</h3>
        <!-- <br />
        <span>Members :</span> -->
      </span>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: {
    organizePictureCover: String,
    organizeName: String,
    memberLists: Array
  }
};
</script>