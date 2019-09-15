<template>
  <v-layout justify-center align-center>
    <v-flex xs12 sm8>
      <v-card>
        <v-card-title class="headline">About Nuxt.js Firebase Auth</v-card-title>
        <v-card-text>
          <p>If you are not logged in then you should be redirected to login when clicking this link to the Admin page</p>
          <p class="title">Test Me</p>

          <div>
            Admin:
            <nuxt-link to="/admin">/admin</nuxt-link>
          </div>
          <div>
            Admin Subroute:
            <nuxt-link to="/admin/settings">/admin/settings</nuxt-link>
          </div>
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {};
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

