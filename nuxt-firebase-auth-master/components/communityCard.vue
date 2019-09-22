<template>
  <v-card class="mx-auto" max-width="400">
    <v-img class="#341646--text" height="200px" :src="communityPictureCover">
      <!-- <v-card-title class="align-end fill-height">Top 10 Australian beaches</v-card-title> -->
    </v-img>

    <v-card-text>
      <span class="text--primary">
        <span>{{communityName}}</span>
        <br />
        <span>Members :</span>
        <br />
      </span>
    </v-card-text>

    <v-card-actions>
      <v-btn class="white--text" color="#341646">JOIN</v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  name: "CommunityCard",
  props: {
    communityPictureCover: String,
    communityName: String,
    memberLists: Array
  }
};
</script>