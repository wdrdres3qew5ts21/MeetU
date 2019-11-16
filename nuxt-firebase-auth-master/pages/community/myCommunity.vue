<template>
  <div>
    <br />
    <h2>My Community</h2>
    <div >
      <div v-for="(community, index) in communityList" :key="index">
        <nuxt-link :to="`/community/${community.communityId}`">
          <community-card
            :communityPictureCover="community.communityPictureCover==undefined?defaultImage: community.communityPictureCover"
            :communityName="community.communityName"
          ></community-card>
        </nuxt-link>
      </div>
      <client-only>
        <infinite-loading spinner="spiral" @infinite="infiniteScroll">
          <div slot="no-results">
            <v-btn color="primary" block>Go to Top</v-btn>
          </div>
        </infinite-loading>
      </client-only>
    </div>
  </div>
</template>
<script>
import CommunityCard from "@/components/communityCard";
import axios from "axios";
import { mapGetters } from "vuex";
import {
  mockCarouselsPhoto,
  mockCommunityList,
  mockPopularEventList
} from "@/utils/eventJson";
export default {
  data() {
    return {
      communityList: [],
      page: 0,
      defaultImage: require(`@/assets/default/community.png`)
    };
  },
  components: {
    CommunityCard
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    // this.communityList = mockCommunityList;
    this.loadMyOwnedCommunity();
  },
  methods: {
    loadMyOwnedCommunity() {
      axios
        .get(
          `${process.env.COMMUNITY_SERVICE}/communitys/admin/user/${this.getUser.uid}`
        )
        .then(ownedCommunityList => {
          this.communityList = ownedCommunityList.data.content;
          console.log(ownedCommunityList.data);
        })
        .catch(err => {});
    },
    infiniteScroll($state) {
      setTimeout(() => {
        this.page++;
        axios
          .get(
            `${process.env.COMMUNITY_SERVICE}/communitys/admin/user/${this.getUser.uid}?page=${this.page}`
          )
          .then(ownedCommunityList => {
            if (ownedCommunityList.data.content.length > 0) {
              ownedCommunityList.data.content.forEach(community =>
                this.communityList.push(community)
              );
              $state.loaded();
            } else {
              $state.complete();
            }
          })
          .catch(err => {
            console.log(err);
          });
      }, 500);
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