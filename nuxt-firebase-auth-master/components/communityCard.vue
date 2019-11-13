<template>
  <v-card class="mx-auto" max-width="400">
    <v-img class="#341646--text" height="200px" :src="communityPictureCover">
      <!-- <v-card-title class="align-end fill-height">Top 10 Australian beaches</v-card-title> -->
    </v-img>

    <v-card-text>
      <span class="text--primary">
        <span class="name">{{communityName}}</span>
        <br />
        <span>Members :</span>
       
      </span>
    </v-card-text>

    <br />
    <v-card-actions>
      <v-flex class="text-xs-right">
       <v-btn color="#341646" class="b">VIEW</v-btn>
      
 <!-- color="#341646" -->
      </v-flex>
    </v-card-actions>
  </v-card>
  
</template>

<script>
export default {
  name: "CommunityCard",
  props: {
    communityPictureCover: {
      type: String,
      default: "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png"
    },
    communityName: String,
    memberLists: Array
  }
};
</script>
<style scoped>
.b {
  color: white;
}
.name{
  font-size: 18px;
  font-weight: bold;
}
</style>