<template>
  <div>
    <br />
    <h2>Subscribed Community</h2>
    <div v-for="(community, index) in communityList" :key="index">
      <nuxt-link :to="`/community/${community.communityDetail[0].communityId}`">
        <community-card
        :communityPictureCover="community.communityDetail[0].communityPictureCover"
        :communityName="community.communityDetail[0].communityName"
      ></community-card>
      </nuxt-link>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { mapGetters } from "vuex";
import CommunityCard from "@/components/communityCard";
import {
  mockCarouselsPhoto,
  mockCommunityList,
  mockPopularEventList
} from "@/utils/eventJson";
export default {
  data() {
    return {
      communityList: [
        {
          communityDetail: [
            {
              communityPictureCover: "",
              communityName: ""
            }
          ]
        }
      ]
    };
  },
  components: {
    CommunityCard
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    //  this.communityList = mockCommunityList;
    this.loadUserSubscribeCommunity();
  },
  methods: {
    loadUserSubscribeCommunity() {
      let loader = this.$loading.show();
      axios
        .get(
          `${process.env.COMMUNITY_SERVICE}/communitys/subscribe/user/${this.getUser.uid}`
        )
        .then(subscribeCommunityResponse => {
          this.communityList = subscribeCommunityResponse.data;
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    }
  }
};
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