<template>
  <div>
    <v-row align="center" justify="center">
      <v-img
        :src="imageUrl"
        aspect-ratio="1"
        class="grey lighten-2"
        max-width="1250"
        max-height="300"
      ></v-img>
    </v-row>

    <v-btn color="#341646" class="mb-2 white--text" @click="onPickFile">Upload image</v-btn>
    <input
      type="file"
      style="display: none"
      ref="fileInput"
      accept="image/*"
      @change="onFilePicked"
    />

    <v-row>
      <v-col cols="12" sm="6" class="d-flex align-center">
        <v-overflow-btn
          class="pr-2"
          :items="dropdown_icon"
          label="Segmented Btn"
          segmented
          target="#dropdown-example"
        ></v-overflow-btn>
      </v-col>

      <v-col cols="12" sm="6" class="d-flex align-center">
        <v-overflow-btn
          class="pr-2"
          :items="dropdown_icon"
          label="Segmented Btn"
          segmented
          target="#dropdown-example"
        ></v-overflow-btn>
      </v-col>
    </v-row>

    <v-card justify="center">
      <form>
        <v-text-field v-model="name" :counter="200" label="Write something ..." required></v-text-field>
      </form>HIIII
    </v-card>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";

export default {
  name: "communityForm",
  data() {
    return {
      imageUrl: "",
      image: null,
      items: ["Unfollow", "Leave group"],
      name: "",
      rules: [
        value =>
          !value ||
          value.size < 2000000 ||
          "Avatar size should be less than 2 MB!"
      ]
    };
  },

  methods: {
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onPickFile() {
      this.$refs.fileInput.click();
    },
    onFilePicked(event) {
      const files = event.target.files;
      let filename = files[0].name;
      if (filename.lastIndexOf(".") <= 0) {
        return alert("Please add a valid file!");
      }
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.image = files[0];
    }
  }
};
</script> 
 
 
