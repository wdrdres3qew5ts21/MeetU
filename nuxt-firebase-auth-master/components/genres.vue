<template>
  <div>
    <v-layout row wrap>
      <v-flex xs12>
        <center>
          <h3>{{selectedItems}} : {{selectedItems.length}}/{{limitedSelectNumber}}</h3>
        </center>
      </v-flex>
      <v-flex xs4 v-for="(category, i) in getCategory" :key="i+category.categoryName">
        <input
          type="checkbox"
          :id="`cb${i+1}`"
          name="eee"
          v-model="selectedItems"
          :value="category.categoryName"
          :disabled="selectedItems.length >= limitedSelectNumber && selectedItems.includes(category.categoryName) == false"
        />
        <label :for="`cb${i+1}`">
          <center>
            <v-img
              :src="require(`@/assets/genresPhoto/${category.categoryName}.png`)"
              width="85px"
            />
            <h3>{{category.categoryLabel}}</h3>
          </center>
        </label>
      </v-flex>
      <v-spacer></v-spacer>
    </v-layout>
    <v-fab-transition>
      <v-btn large dark fab fixed bottom right color="#F81A93" @click="saveInterestIdea()">
        <v-icon>arrow_forward</v-icon>
      </v-btn>
    </v-fab-transition>
  </div>
</template>
<script>
import axios from "axios";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      genrePictureList: [
        "art",
        "beauty",
        "book",
        "business",
        "family",
        "flim",
        "food",
        "game",
        "music",
        "photography",
        "social",
        "sport",
        "technology"
      ],
      checkbox: "cb",
      selectedItems: [],
      limitedSelectNumber: 3
    };
  },
  computed: {
    ...mapGetters(["getCategory", "getUser"])
  },
  mounted() {
    this.initUserProfile();
  },
  methods: {
    ...mapActions(["setInterestIdea"]),
    saveInterestIdea: function() {
      axios
        .post(`${process.env.USER_SERVICE}/user/interest/preference`, {
          uid: this.getUser.uid,
          interest: this.selectedItems
        })
        .then(savedInterestIdea => {
          this.$swal({
            type: "success",
            title: "Save Your Preference !!!",
            text: `Your preference have been save`
          });
          this.$router.push("/userProfile")
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Fail to Save Your Preference !!!",
            text: `Fail to Save Your Preference  ${error.message}`
          });
        });
    },
    initUserProfile: function() {
      axios
        .get(`${process.env.USER_SERVICE}/user/${this.getUser.uid}`)
        .then(userProfileForm => {
          userProfileForm = userProfileForm.data;
          console.log(userProfileForm);
          this.selectedItems = userProfileForm.interest;
        })
        .catch(err => {});
    }
  }
};
</script>

<style lang="css" scoped>
.position-relative {
  position: relative;
}

.complete-icon {
  position: absolute;
  top: 0;
  left: 0;
}

input[type="checkbox"][id^="cb"] {
  display: none;
}

label {
  border: 1px solid none;
  display: block;

  cursor: pointer;
}

:checked + label:before {
  content: "✓";
  background-color: green;
  transform: scale(1);
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: none;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 100px;
  width: 100px;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

/* :checked + label {
  border-color: none;
} */

/* :checked + label img {
  transform: scale(0.9);

  z-index: -1;
} */
</style>
