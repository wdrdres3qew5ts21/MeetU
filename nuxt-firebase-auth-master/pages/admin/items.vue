<template>
  <div>
    <v-btn @click="isCameraOpen = !isCameraOpen">Open camera</v-btn>
    <div v-if="isCameraOpen">
   
    </div>
  </div>
</template>
<script>
export default {};
</script>

<style>
#adminNav {
  display: flex;
  flex-flow: row wrap;
  /*justify-content: space-around;*/
  margin-bottom: 1em;
}
.adminNavRoute {
  margin: 0.25em 0.5em;
  padding: 0.25em;
}
#adminChild {
  margin-top: 1em;
}
</style>
