<template>
  <v-card class="mx-auto elevation-0">
    <div v-if="reviewContent.isReview">
      <v-layout row wrap align-start justify-center fill-height>
        <v-layout row pt-2>
          <v-flex xs1 md1 lg1 mt-3 ml-3>
            <!-- <v-icon>today</v-icon> -->
            <v-avatar size="40" color="grey">
              <v-img :src="reviewContent.photoURL"></v-img>
            </v-avatar>
          </v-flex>
          <v-flex xs11 md11 lg11 mt-1 ml-3 mr-1>
            <div>{{reviewContent.displayName}}</div>

            <p
              class="date"
            >{{formatDateForReadable(reviewContent.reviewDate)}} {{formatAMPM(reviewContent.reviewDate)}}</p>
          </v-flex>
        </v-layout>

        <v-card-text class="px-3 pt-3 pb-0">
          {{reviewContent.reviewDetail  }}
        </v-card-text>

        <v-card-text class="px-3 pt-3 pb-0">
          <v-layout row>
            <v-flex>Given rating :</v-flex>
            <v-flex xs7>
              <v-rating
                readonly
                v-model="reviewContent.rating"
                color="yellow accent-4"
                dense
                half-increments
                hover
                size="18"
              ></v-rating>
            </v-flex>
            <v-flex xs1>({{reviewContent.rating}})</v-flex>
          </v-layout>
        </v-card-text>
      </v-layout>
      <br />
    </div>
    <div v-else>
      <v-layout justify-space-between></v-layout>
      <v-divider dark></v-divider>
      <v-layout class="pa-3">
        <div class="headline">Review Event</div>
      </v-layout>

      <v-spacer></v-spacer>

      <v-layout ml-3>
        <v-rating
          v-model="reviewForm.rating"
          color="yellow accent-4"
          dense
          half-increments
          hover
          size="30"
        ></v-rating>

        <span class="grey--text pl-2">({{ reviewForm.rating }})</span>

        <!-- <v-layout pa-3 my-0> -->

        <!-- <v-card-title> -->

        <!-- </v-layout> -->
      </v-layout>

      <!-- </v-card-title> -->
      <v-card-title primary-title>
        <v-textarea
          v-model="reviewForm.reviewDetail"
          outline
          name="description"
          label="My Feeling for this event :)"
          color="#004c99"
          rows="6"
          required
          hide-details
        ></v-textarea>
      </v-card-title>

      <v-layout justify-center>
        <v-btn flat outline @click="reviewEvent()">submit</v-btn>
      </v-layout>
    </div>
  </v-card>
</template>



<script>
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  name: "ratingEvent",
  data: () => ({
    reviewForm: {
      rating: 0,
      reviewDetail: "",
      elasticEventId: ""
    }
  }),
  props: {
    ticketEvent: Object,
    reviewContent: {
      type: Object,
      default: {
        isReview: false
      }
    }
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.reviewForm.elasticEventId = this.ticketEvent.elasticEventId;
  },
  methods: {
    reviewEvent() {
      console.log(this.reviewForm);
      axios
        .post(`${process.env.EVENT_SERVICE}/event/review`, this.reviewForm, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
          }
        })
        .then(reviewResponse => {
          this.$swal({
            type: "success",
            title: "Review Success !",
            text: "Review Success"
          });
          this.$emit('refreshReview')
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Review failed !",
            text: err.response
          });
        });
    },
    formatDateForReadable: function(formatDate) {
      let date = new Date(formatDate);
      formatDate =
        date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear();
      console.log(formatDate);
      return formatDate;
    },
    formatAMPM: function(AMPM) {
      let date = new Date(AMPM);
      const options = {
        hour: "2-digit",
        minute: "2-digit"
      };
      let time = date.toLocaleTimeString("en-US", options);
      return time;
    }
  }
};
</script>


<style lang="css">
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
}

.headline {
  font-weight: bold;
}
</style>