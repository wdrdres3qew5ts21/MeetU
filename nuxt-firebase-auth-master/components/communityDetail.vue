<template>
  <div>
    <v-flex align="center">
      <v-img
        :src="imageUrl"
        aspect-ratio="1"
        class="grey lighten-2"
        max-width="1250"
        max-height="200"
      ></v-img>
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

    <v-card rounded outlined v-for="(todo,index ) in postList " :key="index">
      <br />
      <div>
        <v-layout>
          <v-container grid-list-xs fluid style="padding:10px">
            <v-flex xs12 class="text-xs-left">
              <v-avatar size="60">
                <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
              </v-avatar>
              {{ getUser.displayName}}
            </v-flex>
          </v-container>
          <v-flex xs12 class="text-xs-right">
            <v-btn text icon>
              <v-icon>expand_more</v-icon>
            </v-btn>
            <!-- <v-overflow-btn icon :items="remove">
              <v-icon>expand_more</v-icon>
                 @click="removePost(todo)"
                  color="#341646"
                  class="mb-2 white--text"
                  type="button"
                  name="button"
            </v-overflow-btn>-->
          </v-flex>
        </v-layout>
      </div>
      <v-container grid-list-xs fluid style="padding:10px">
        <br />
        <v-layout wrap>
          <span :class="{ done: todo.done }">{{todo.post}}</span>
        </v-layout>
      </v-container>
      <v-card-text rounded outlined class="mx-auto">
        <v-divider></v-divider>
        <v-flex class="text-right">
          <v-layout wrap justify-end>
            <v-dialog
              v-model="dialog"
              fullscreen
              hide-overlay
              transition="dialog-bottom-transition"
            >
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" text block flat>
                  <v-icon>comment</v-icon>Comment
                </v-btn>
              </template>
              <v-card>
                <v-toolbar dark color="primary">
                  <v-btn icon dark @click="dialog = false">
                    <v-icon>close</v-icon>
                  </v-btn>
                  <v-toolbar-title>comment</v-toolbar-title>
                </v-toolbar>
                <v-card rounded outlined v-for="(todo,index ) in commentList " :key="index">
                  <v-container grid-list-xs fluid style="padding:10px">
                    <v-flex xs12 class="text-xs-left">
                      <v-avatar size="30">
                        <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
                      </v-avatar>
                      {{ getUser.displayName}}
                      <br />
                      <span></span>
                      <span :class="{ done: todo.done }">{{todo.commentInPost}}</span>
                    </v-flex>
                  </v-container>

                  <br />
                </v-card>
                <form >
                  <v-layout ma-3>
                    <v-flex xs12>
                      <v-flex lass="text-xs-left">
                        <v-text-field
                          v-model="newComment"
                          name="newComment"
                          id="newComment"
                          value
                          label="write something..."
                        ></v-text-field>
                      </v-flex>
                    </v-flex>
                    <v-flex class="text-xs-right" pa-2>
                      <v-btn text small @click="addComment()">post</v-btn>
                    </v-flex>
                  </v-layout>
                </form>
              </v-card>
            </v-dialog>

            <!-- <v-btn
                  @click="removePost(todo)"
                  color="#341646"
                  class="mb-2 white--text"
                  type="button"
                  name="button"
            >Remove</v-btn>-->
          </v-layout>
        </v-flex>
        <v-divider></v-divider>
        <v-layout>
          <br />
        </v-layout>
      </v-card-text>

      <v-card rounded outlined v-for="(todo,index ) in commentList " :key="index">
        <v-container grid-list-xs fluid style="padding:10px">
          <v-flex xs12 class="text-xs-left">
            <v-avatar size="30">
              <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
            </v-avatar>
            {{ getUser.displayName}}
            <br />
            <span></span>
            <span :class="{ done: todo.done }">{{todo.commentInPost}}</span>
          </v-flex>
        </v-container>

        <br />
      </v-card>
    </v-card>
  </div>
</template> 
 
 
<script>
import Swal from "sweetalert2";
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
import postCommunity from "@/components/postCommunity";
export default {
  name: "communityDetail",
  data() {
    return {
      imageUrl: "",
      image: null,
      remove: ["remove"],
      post: "",
      newPost: "",
      postList: [],
      commentInPost: "",
      newComment: "",
      commentList: [],
      dialog: false,
      name: "",
      rules: [
        value =>
          !value ||
          value.size < 2000000 ||
          "Avatar size should be less than 2 MB!"
      ]
    };
  },
  components: {
    postCommunity
  },
  mounted() {
    this.initUserProfile();
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {
    initUserProfile: function() {
      axios
        .get(`${process.env.USER_SERVICE}/user/${this.getUser.uid}`)
        .catch(err => {});
    },
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
    },

    addComment() {
      this.commentList.push({
        commentInPost: this.newComment,
        done: false
      });
      this.newComment = "";
      this.dialog = false;
    },
    removePost(todo) {
      const commentIndex = this.commentList.indexOf(todo);
      this.commentList.splice(commentIndex, 1);
    }
  }
};
</script> 
 
 
