<template>
    <div>
        <br>
        <h2>Subscribed Community</h2>
         <community-card
        v-for="(community, index) in communityList"
        :key="index"
        :communityPictureCover="community.communityPictureCover"
        :communityName="community.communityName"
      ></community-card>
    </div>
</template>
<script>
import CommunityCard from "@/components/communityCard";
import {
  mockCarouselsPhoto,
  mockCommunityList,
  mockPopularEventList
} from "@/utils/eventJson";
export default {
    data() {
    return {
      communityList: []
    };
  },
  components: {
    CommunityCard
  },
   mounted() {
     this.communityList = mockCommunityList;
  }
}
</script>

<style lang="css">

.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}
    
</style>