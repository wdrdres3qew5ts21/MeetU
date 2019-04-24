<template>
  <div>
    <no-ssr>
      <GmapMap
        :center="{lat: getCurrentLocation.lat,
            lng: getCurrentLocation.lng}"
        :zoom="14"
        map-type-id="terrain"
        style="width: 500px; height: 300px"
        :options="{
                scaleControl: true
            }"
      >
        <GmapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
          :clickable="true"
          :draggable="true"
          @click="center=m.position"
        />
      </GmapMap>
    </no-ssr>
    <h1>
      <no-ssr>{{getCurrentLocation}}</no-ssr>
    </h1>
  </div>
</template>
<script>
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      position: {},
      marker
    };
  },
  beforeMount() {
    this.getLocation();
    //  this.$store.dispatch("updateCurrentLocation", '99')
  },
  computed: {
    ...mapGetters(["getCurrentLocation"])
  },
  methods: {
    ...mapActions(["updateCurrentLocation"]),
    getLocation: function() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
          this.position = position;
          let lat = parseFloat(position.coords.latitude);
          let lng = parseFloat(position.coords.longitude);
          this.$store.dispatch("updateCurrentLocation", { lat: lat, lng: lng });
        });
      } else {
        console.log("not support fuq");
      }
    }
  }
};
</script>
