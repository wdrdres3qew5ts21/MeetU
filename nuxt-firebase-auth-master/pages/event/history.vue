<template>
  <div>
    <h2>Ticket(s)</h2>
    <br />
    <v-flex xs12>
      
    </v-flex>
    <br />
    <v-flex xs12>
      <v-card min-width="370" height="180" color="white" >
        <v-layout>
          <v-flex xs8>
            <v-img src="https://cdn.vuetifyjs.com/images/cards/foster.jpg"  height="180px" width="170px"></v-img>
          </v-flex>
          <v-flex xs7>
            <v-card-title primary-title>
              <div>
                <div class="headline">Event name</div>
                <div>{{new Date().toISOString()}}</div>
                <div>{{location}}</div>
                 <nuxt-link :to="`/ticket?`">
                  <br/>
                  <b color="deep-purple darken-3">View Detail</b>
                </nuxt-link>
              </div>
            </v-card-title>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <br />
    <v-flex xs12>
      <v-card min-width="370" height="180" color="white" >
        <v-layout>
          <v-flex xs8>
            <v-img src="https://cdn.vuetifyjs.com/images/cards/foster.jpg"  height="180px" width="170px"></v-img>
          </v-flex>
          <v-flex xs7>
            <v-card-title primary-title>
              <div>
                <div class="headline">Event name</div>
                <div>{{new Date().toISOString()}}</div>
                <div>{{location}}</div>
                 <nuxt-link :to="`/ticket?`">
                  <br/>
                  <b color="deep-purple darken-3">View Detail</b>
                </nuxt-link>
              </div>
            </v-card-title>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
  </div>
</template>
<script>
export default {
  data() {
    return {
      location: "BKK, Thailand"
    };
  }
};
</script>