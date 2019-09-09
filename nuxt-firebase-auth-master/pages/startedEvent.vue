<template>
  <div>
    <br />
    <h1 style="color:#341646">All Events</h1>

    <br />
    <br />
    <center>
      <nuxt-link :to="`/createEventForm?`" style="text-decoration-line:none;">
        <v-btn
          class="createEvent white--text"
          color="#341646"
          depressed
          large
          height="50"
        >Create an Event</v-btn>
      </nuxt-link>
    </center>

    <center>
      <br />
      <p style="color:grey">No event, please create a new event.</p>
    </center>
  </div>
</template>


<script>
export default {
  name: "startedEvent"
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

.h1 {
  font-family: Roboto;
}
</style>