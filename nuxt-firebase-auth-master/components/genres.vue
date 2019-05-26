<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-layout row wrap>
          <div class="position-relative">
            <input type="checkbox" id="cb1">
            <label for="cb1">
              <v-img :src="require('@/assets/genresPhoto/art.png')" width="85px"></v-img>
              <center>
                <h3>Arts</h3>
              </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <input type="checkbox" id="cb2">
            <label for="cb2">
            <v-img :src="require('@/assets/genresPhoto/beauty.png')" width="85px"></v-img>
            <center>
              <h3>Beauty</h3>
            </center>
            </label>
          </div>

          <v-spacer></v-spacer>
          <div class="position-relative">
            
            <input type="checkbox" id="cb3">
            <label for="cb3">
            <v-img :src="require('@/assets/genresPhoto/books.png')" width="85px"></v-img>
            <center>
              <h3>Books</h3>
            </center>
            </label>
          </div>
          <v-spacer></v-spacer>
        </v-layout>

        <br>
        <v-layout row wrap>
          <div class="position-relative">
            <input type="checkbox" id="cb4">
            <label for="cb4">
            <v-img :src="require('@/assets/genresPhoto/business.png')" width="85px"></v-img>
            <center>
              <h3>Business</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <input type="checkbox" id="cb5">
            <label for="cb5">
            <v-img :src="require('@/assets/genresPhoto/family.png')" width="85px"></v-img>
            <center>
              <h3>Family</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <input type="checkbox" id="cb6">
            <label for="cb6">
            <v-img :src="require('@/assets/genresPhoto/food.png')" width="85px"></v-img>
            <center>
              <h3>Food</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>
        </v-layout>
        <br>

        <v-layout row wrap>
          <div class="position-relative">
            <input type="checkbox" id="cb7">
            <label for="cb7">
            <v-img :src="require('@/assets/genresPhoto/games.png')" width="85px"></v-img>
            <center>
              <h3>Games</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>
          <div class="position-relative">
            <input type="checkbox" id="cb8">
            <label for="cb8">
            <v-img :src="require('@/assets/genresPhoto/films.png')" width="85px"></v-img>
            <center>
              <h3>Films</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>
          <div class="position-relative">
            <input type="checkbox" id="cb9">
            <label for="cb9">
            <v-img :src="require('@/assets/genresPhoto/music.png')" width="85px"></v-img>
            <center>
              <h3>Music</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <v-spacer></v-spacer>
        </v-layout>
        <br>

        <v-layout row wrap>
          <div class="position-relative">
            <input type="checkbox" id="cb10">
            <label for="cb10">
            <v-img :src="require('@/assets/genresPhoto/social.png')" width="85px"></v-img>
            <center>
              <h3>Social</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <input type="checkbox" id="cb11">
            <label for="cb11">
            <v-img :src="require('@/assets/genresPhoto/sport.png')" width="85px"></v-img>
            <center>
              <h3>Sport</h3>

            </center>
            </label>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <input type="checkbox" id="cb12">
            <label for="cb12">
            <v-img :src="require('@/assets/genresPhoto/technology.png')" width="85px"></v-img>
            <center>
              <h3>Tech</h3>
            </center>
            </label>
          </div>
          <v-spacer></v-spacer>
        </v-layout>

        <!-- <v-layout row wrap>
          <v-img :src="require('@/assets/genresPhoto/social.png')" width="85px"></v-img>
          <v-spacer></v-spacer>

          <v-img :src="require('@/assets/genresPhoto/sport.png')" width="85px"></v-img>
          <v-spacer></v-spacer>

          <v-img :src="require('@/assets/genresPhoto/t.png')" width="85px"></v-img>
        </v-layout>-->
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  data: () => ({}),

  methods: {}
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
  border: 1px solid #fff;
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
