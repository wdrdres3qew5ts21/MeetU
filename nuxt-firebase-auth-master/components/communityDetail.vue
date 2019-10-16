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

    <div v-for="(todo,postIndex ) in postList " :key="postIndex">
      <v-card rounded outlined>
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
            </v-flex>
          </v-layout>
        </div>
        <v-container grid-list-xs fluid style="padding:10px">
          <br />
          <v-layout wrap>
            <span :class="{ done: todo.done }">{{todo.post}}</span>
            <h1>{{postIndex}}</h1>
          </v-layout>
        </v-container>
        <v-card-text rounded outlined class="mx-auto">
          <v-divider></v-divider>
          <v-flex class="text-right">
            <v-btn text block flat @click="getCommentFromPost(postIndex)">
              <v-icon>comment</v-icon>Comment
            </v-btn>
            <v-layout wrap justify-end>
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

        <v-card
          rounded
          outlined
          v-for="(comment,commentIndex ) in postList[postIndex].commentList "
          :key="commentIndex"
        >
          <v-container grid-list-xs fluid style="padding:10px">
            <v-flex xs12 class="text-xs-left">
              <v-avatar size="30">
                <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
              </v-avatar>
              {{ getUser.displayName}}
              <br />
              <span></span>
              <span :class="{ done: todo.done }">{{comment}}</span>
            </v-flex>
          </v-container>

          <br />
        </v-card>
        <br />
        <br />
      </v-card>
      <br />
      <br />
    </div>

    <div v-if="postList.length != 0">
      <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
        <!-- <template v-slot:activator="{ on }">
                <v-btn v-on="on" text block flat @click="getCommentFromPost(postIndex)">
                  <v-icon>comment</v-icon>Comment
                </v-btn>
        </template>-->
        <v-card>
          <v-toolbar dark color="primary">
            <v-btn icon dark @click="dialog = false">
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>comment</v-toolbar-title>
          </v-toolbar>

          <v-card
            rounded
            outlined
            v-for="(comment,commentIndex ) in  postList[postIndex].commentList "
            :key="commentIndex"
          >
            <v-container grid-list-xs fluid style="padding:10px">
              <v-flex xs12 class="text-xs-left">
                <v-avatar size="30">
                  <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
                </v-avatar>
                {{ getUser.displayName}}
                <br />
                <span></span>
                <span>{{comment}}</span>
                <span>{{postList}}</span>
              </v-flex>
            </v-container>
          </v-card>
          <form>
            {{postIndex}}
            <v-layout ma-3>
              <v-flex xs12>
                <v-flex lass="text-xs-left">
                  <v-text-field
                    v-model="comment"
                    name="newComment"
                    id="newComment"
                    value
                    label="write something..."
                  ></v-text-field>
                </v-flex>
              </v-flex>
              <v-flex class="text-xs-right" pa-2>
                <v-btn text small @click="addComment(postIndex)">Comment</v-btn>
              </v-flex>
            </v-layout>
          </form>
        </v-card>
      </v-dialog>
    </div>
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
      //postList: [{post: '' , commentList:[]}],
      commentInPost: "",
      comment: "",
      // newcomment: [],
      postIndex: 0,
      dialog: false,
      name: "",
      rules: [
        value =>
          !value ||
          value.size < 2000000 ||
          "Avatar size should be less than 2 MB!"
      ],
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
    getCommentFromPost(postIndex) {
      this.dialog = true;
      this.postIndex = postIndex
      console.log(
        "--------------------postIndex get comment from post ----------------------"
      );
      console.log(postIndex);
      console.log(this.postList[postIndex].commentList);
    },

    addPost() {
      this.postList.push({
        post: this.newPost,
        done: false
      });
      this.newPost = "";
      console.log(this.postList);
    },
    // removePost(todo) {
    //   const postIndex = this.postList.indexOf(todo);
    //   this.postList.splice(postIndex, 1);
    // },

    addComment(postIndex) {
      console.log("------ ADD Comment ----");
      console.log(postIndex);
      console.log("-------- Post List ------")
      console.log(this.postList)
      console.log("------------------")
      this.postIndex = postIndex;
      let isCommentList = this.postList[postIndex].commentList;
      console.log(isCommentList);

      if (isCommentList === undefined) {
        console.log("It's first comment");
        this.postList[postIndex].commentList = [];
      }

      this.postList[postIndex].commentList.push(this.comment);
      console.log(this.postList[postIndex].commentList);
      this.comment = "";
      this.dialog = false;
      console.log(postIndex);
    }
    // addComment() {
    //     this.postList.commentList.push({
    //     // commentList: this.commentList.push({
    //     comment:  this.newComment,
    //     done: false
    //     // }),

    //   });
    //   this.newComment = "";
    //   this.dialog = false;
    // },
  }
};
</script> 
 
 
