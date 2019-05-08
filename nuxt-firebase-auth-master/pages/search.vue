<template>
  <transition name="router-anim" enter-active-class="animated slideInRight">
    <div class="text-xs-center">
      <v-flex justify-content: left>
        <v-text-field placeholder="Search..."></v-text-field>
      </v-flex>
      <v-flex justify-start>
        <v-btn class="white--text" round="16" depressed color="#341646">Search</v-btn>
      </v-flex>

      <br>

      <v-flex justify-space-around>
        <div class="text-xs-left">
          <v-chip color="#fc5577" round="16" text-color="white">
            <v-icon left color="white">label</v-icon>Tags
          </v-chip>
          <v-chip close>Example Chip</v-chip>
          <v-chip close>Example Chip</v-chip>

          <v-chip close>Example Chip</v-chip>
        </div>
        <!-- <v-chip color="grey" text-color="white">Primary</v-chip>

        <v-chip color="grey" text-color="white">Secondary</v-chip>

        <v-chip color="grey" text-color="white">Colored Chip</v-chip>

        <v-chip color="grey" text-color="white">Colored Chip</v-chip>-->
      </v-flex>
    </div>
  </transition>
</template>

<script>
export default {
  components: {}
};
</script>

<style lang="css">
@import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";

.page {
  position: fixed;
  width: inherit;
}
</style>
