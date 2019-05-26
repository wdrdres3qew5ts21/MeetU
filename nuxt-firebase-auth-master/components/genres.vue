<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-layout row wrap>
          <div class="position-relative">
           <v-img  :src="require('@/assets/genresPhoto/art.png')" width="85px"></v-img>
            <center>
              <h3>Arts</h3>
              
                <!-- v-if="art.commplete" -->
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/beauty.png')" width="85px"></v-img>
            <center>
              <h3>Beauty</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>

          <v-spacer></v-spacer>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/books.png')" width="85px"></v-img>
            <center>
              <h3>Books</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>
        </v-layout>

        <br>
        <v-layout row wrap>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/business.png')" width="85px"></v-img>
            <center>
              <h3>Business</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/family.png')" width="85px"></v-img>
            <center>
              <h3>Family</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/food.png')" width="85px"></v-img>
            <center>
              <h3>Food</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>
        </v-layout>
        <br>

        <v-layout row wrap>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/games.png')" width="85px"></v-img>
            <center>
              <h3>Games</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>
          <div class="position-relative"> 
            <v-img :src="require('@/assets/genresPhoto/movie.png')" width="85px"></v-img>
            <center>
              <h3>Movies</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/music.png')" width="85px"></v-img>
            <center>
              <h3>Music</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>

          <v-spacer></v-spacer>
        </v-layout>
        <br>

        <v-layout row wrap>
          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/social.png')" width="85px"></v-img>
            <center>
              <h3>Social</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/sport.png')" width="85px"></v-img>
            <center>
              <h3>Sport</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
          </div>
          <v-spacer></v-spacer>

          <div class="position-relative">
            <v-img :src="require('@/assets/genresPhoto/technology.png')" width="85px"></v-img>
            <center>
              <h3>Tech</h3>
              <img
                class="complete-icon"
                src="@/assets/genresPhoto/select.png"
                width="85px"
                alt="complete icon"
              />
            </center>
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
  data: () => ({
   
  }),

    methods: {
     
     }
    
  }
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

</style>
