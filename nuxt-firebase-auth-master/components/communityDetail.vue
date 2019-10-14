<template>
  <div>
    <v-flex align="center">
      <v-carousel hide-delimiters hide-controls xs6 sm12 height="200px;">
        <v-img
          :src="imageUrl"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        ></v-img>
      </v-carousel>
    </v-flex>

    <v-btn color="#341646" class="mb-2 white--text" @click="onPickFile">
      Upload image
      <v-icon>add_a_photo</v-icon>
    </v-btn>
    <input
      type="file"
      style="display: none"
      ref="fileInput"
      accept="image/*"
      @change="onFilePicked"
    />

    <br />
    <br />

    <v-card rounded outlined class="mx-auto">
      <div class="px-3">
        <form>
          <v-layout>
            <v-flex xs12>
              <v-text-field v-model="newPost" name="newPost" id="newPost" value></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex xs12 class="text-xs-left">
              <v-btn style="margin-right: 0px" icon>
                <v-icon>photo_camera</v-icon>
              </v-btn>
              <v-btn style="margin: 0px" icon>
                <v-icon>room</v-icon>
              </v-btn>
              <v-btn style="margin: 0px" icon>
                <v-icon>assessment</v-icon>
              </v-btn>
              <v-btn style="margin: 0px" icon>
                <v-icon>event</v-icon>
              </v-btn>
            </v-flex>
            <v-flex xs class="text-xs-right">
              <v-btn text color="#341646" class="mb-2 white--text" @click="addPost()">Post</v-btn>
            </v-flex>
          </v-layout>
        </form>
      </div>
    </v-card>

    <br />
    <h3>{{post}}</h3>

    <v-card v-for="(todo,index ) in postList " :key="index">
      <span :class="{ done: todo.done }">{{todo.post}}</span>
      <br />
      <br />
      <v-btn
        @click="removePost(todo)"
        color="#341646"
        class="mb-2 white--text"
        type="button"
        name="button"
      >Remove</v-btn>
    </v-card>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";

export default {
  name: "communityDetail",
  data() {
    return {
      imageUrl: "",
      image: null,
      items: ["Unfollow", "Leave group"],
      post: "",
      newPost: "",
      postList: [],
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
    },
    addPost() {
      this.postList.push({
        post: this.newPost,
        done: false
      });
      this.newPost = "";
    },
    removePost(todo) {
      const postIndex = this.postList.indexOf(todo);
      this.postList.splice(postIndex, 1);
    
    }
  }
};
</script> 
 
 
