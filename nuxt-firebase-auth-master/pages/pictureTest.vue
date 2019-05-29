<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <!-- <v-flex xs6><img v-for="i in picList" :key="i" :src="require(`@/assets/genresPhoto/${i}`)" alt="" srcset=""></v-flex>
      <v-flex xs6><img src="~assets/genresPhoto/art.png" alt="" srcset=""></v-flex> -->
    </v-layout>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
        picList: ['art.png',
        'beauty.png']
    };
  }
};
</script>