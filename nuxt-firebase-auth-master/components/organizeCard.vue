<template>
  <v-card class="mx-auto" max-width="400">
       
    <v-img class="#341646--text" height="200px" :src="defaultImage">
      <!-- <v-card-title class="align-end fill-height">Top 10 Australian beaches</v-card-title> -->
    <v-btn class="button" depressed flat  >
                 <v-avatar color="#341646" size="40">
                <v-icon  color="white" size="25"> edit </v-icon>
                 </v-avatar>
    </v-btn>
    </v-img>
  
     

    <v-card-text>
      <span class="text--primary">
        <h3>{{organizeName}}</h3>
        <!-- <br />
        <span>Members :</span> -->
      </span>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: {
    organizePictureCover: String,
    organizeName: String,
    memberLists: Array
  },
  data() {
    return{
    defaultImage: "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png"
  }}
};
</script>
<style >
.button {
  height: 40px;
  position: absolute;
  top: 10%;
  left: 90%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);

  color: #341646;
  font-size: 16px;
  padding: 12px 24px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  text-align: center;
}
</style>