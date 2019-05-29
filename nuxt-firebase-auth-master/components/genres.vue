<template>
  <div>
    <v-layout row wrap>
      <v-flex xs4 v-for="(genrePicture, i) in genrePictureList" :key="i+genrePicture">
        <input type="checkbox" :id="`cb${i+1}`">
        <label :for="`cb${i+1}`">
          <center>
            <v-img
              @click="$store.dispatch('setInterestIdea',{genre: `${genrePicture}`, cb: `cb${i+1}`})"
              :src="require(`@/assets/genresPhoto/${genrePicture}.png`)"
              width="85px"
            />
            <h3>{{genrePicture}}</h3>
          </center>
        </label>
      </v-flex>
      <v-spacer></v-spacer>
    </v-layout>
    <v-fab-transition>
      <v-btn large dark fab fixed bottom right color="#fc5577">
        <v-icon>arrow_forward</v-icon>
      </v-btn>
    </v-fab-transition>
  </div>
</template>
<script>
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
      ]
    };
  },
  methods: {},
  mounted() {}
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

:checked + label {
  border-color: none;
}

:checked + label:before {
  content: "✓";
  background-color: green;
  transform: scale(1);
}

:checked + label img {
  transform: scale(0.9);

  z-index: -1;
}
</style>
