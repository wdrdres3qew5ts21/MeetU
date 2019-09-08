<template>
  <div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <!-- <v-img
              :src="require(`@/assets/${successLogo}.png`)"
              
    />-->
    <center>
      <v-img :src="require('@/assets/successLogo.png')" width="75" />

      <br />
      <br />

      <h2>
        Your Organizer Account
        <br />Has Been Created!
      </h2>

      <br />
      <nuxt-link :to="`/?`" style="text-decoration-line:none;">
        <v-btn
          class="getStartedEvents white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Get Started an Event!</v-btn>
      </nuxt-link>
    </center>
  </div>
</template>

<script>
export default {
  name: "organizerAccountCreate"
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