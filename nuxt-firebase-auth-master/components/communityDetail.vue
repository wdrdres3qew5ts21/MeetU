<template>
  <div>
    <!-- ห้าม format หน้านี้เด็ดขาดเพราะ text field จะหาย
            <v-text-field
            :append-outer-icon="comment ? 'send' : 'send'"
            box
            value
            v-model="comment"
            clear-icon="close"
            clearable
            label="Write comment..."
            type="text"
            @click:append="toggleMarker"
            @click:append-outer="addComment(postIndex)"
            @click:clear="clearMessage"
          ></v-text-field>
    -->
    <v-flex align="center">
      <div v-if="imageUrl == ''">
        <v-img
          :src="defaultImage"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        >
          <v-btn class="button" @click="onPickFile">
            Upload image
            &nbsp;
            &nbsp;
            <v-icon>add_a_photo</v-icon>
            <br />
          </v-btn>
        </v-img>
      </div>
      <div v-else>
        <v-img
          :src="imageUrl"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        ></v-img>
      </div>
    </v-flex>
    <input
      type="file"
      style="display: none"
      ref="fileInput"
      accept="image/*"
      @change="onFilePicked"
    />
    <br />
    <br />

<h2> Community Name</h2>
<p size="1px">description about community</p>
<br>
   <v-flex  class="text-xs-left">
              <v-btn text color="grey lighten-3" class="joinButton
              " @click="join = !join" >             
                   {{ join ? 'follow ' : 'Unfollow' }}              
                   </v-btn>
            </v-flex>
           
     <div v-show="!join" > 
       
    <v-card rounded outlined class="mx-auto">
      <div class="px-3">
        <form>
          <v-layout>
            <v-flex xs12>
              <br />
              <v-text-field
                v-model="newPost"
                name="newPost"
                placeholder="Write Something..."
                id="newPost"
              ></v-text-field>
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

     </div>

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

        <v-container grid-list-xs fluid style="padding:5px">
          <br />
          <v-list>
            <v-list-tile-content>
              <div class="textarea" contenteditable="false">                
                {{todo.post}}
              </div>
            </v-list-tile-content>
          </v-list>
        </v-container>

        <v-card-text rounded outlined class="mx-auto">
          <v-divider></v-divider>
          <v-flex class="text-right">
            <v-btn text block flat @click="getCommentFromPost(postIndex)">
              <v-icon>comment</v-icon>Comment
            </v-btn>
            <!-- <v-btn
                  @click="removePost(todo)"
                  color="#341646"
                  class="mb-2 white--text"
                  type="button"
                  name="button"
            >Remove</v-btn>-->
          </v-flex>
        </v-card-text>
        <v-list>
          <v-card
            rounded
            outlined
            v-for="(comment,commentIndex ) in postList[postIndex].commentList "
            :key="commentIndex"
            max-width="500px"
          >
            <v-container grid-list-xs xs4 fluid style="padding:5px">
              <v-list-tile xs4>
                <v-list-tile-avatar>
                  <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
                </v-list-tile-avatar>
                <v-list-tile>
                  <div>
                    <v-list-tile-content>
                      <v-card color="grey lighten-3" class="rounded-card" max-width="240px">
                        <v-list-tile-title class="margin-name">
                          <font size="2">{{ getUser.displayName}}</font>
                        </v-list-tile-title>

                        <v-list-tile-sub-title class="margin-comment">
                          <font size="2">{{comment}}</font>
                        </v-list-tile-sub-title>
                      </v-card>
                    </v-list-tile-content>
                  </div>
                </v-list-tile>
              </v-list-tile>
            </v-container>
          </v-card>
        </v-list>
      </v-card>
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
            max-width="auto"
          >
            <!-- grid-list-xs -->
            <v-container grid-list-xs xs4 fluid style="padding:5px">
              <v-list-tile xs4>
                <v-list-tile-avatar>
                  <v-img :aspect-ratio="1/1" :src="getUser.photoURL"></v-img>
                </v-list-tile-avatar>
                <v-list-tile>
                  <div>
                    <v-list-tile-content>
                      <v-card color="grey lighten-3" class="rounded-card" max-width="270px">
                        <v-list-tile-title class="margin-name">
                          <font size="2">{{ getUser.displayName}}</font>
                        </v-list-tile-title>
                        <v-list-tile-sub-title class="margin-comment">
                          <font size="2">{{comment}}</font>
                        </v-list-tile-sub-title>
                      </v-card>
                    </v-list-tile-content>
                  </div>
                </v-list-tile>
              </v-list-tile>
            </v-container>
          </v-card>
          <form>
            <v-layout ma-3>
              <v-layout row wrap>
                <v-flex xs12>
                  <!-- :append-icon="marker ? 'map-marker' : 'map-marker-off'" -->
                  <v-text-field
            :append-outer-icon="comment ? 'send' : 'send'"
            box
            value
            v-model="comment"
            clear-icon="close"
            clearable
            label="Write comment..."
            type="text"
            @click:append="toggleMarker"
            @click:append-outer="addComment(postIndex)"
            @click:clear="clearMessage"
          ></v-text-field>
                </v-flex>
              </v-layout>
              <!-- <v-flex xs12>
                <v-flex lass="text-xs-left">
                  <v-text-field
                    v-model="comment"
                    box
                    clearable
                    name="newComment"
                    id="newComment"
                    value
                    label="write something..."+
                  ></v-text-field>
                </v-flex>
              </v-flex>-->
              <!-- <v-flex class="text-xs-right" pa-2>
                <v-btn icon text large @click="addComment(postIndex)"><v-icon large>send</v-icon></v-btn>
              </v-flex>-->
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
      join: false,
      imageUrl: "",
      image: null,
      defaultImage:
        "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png",
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
      show: false,
      message: "Hey!",
      marker: true,
      iconIndex: 0
    };
  },
  components: {
    postCommunity
  },
  mounted() {
    this.initUserProfile();
  },
  computed: {
    ...mapGetters(["getUser"]),

    icon() {
      return this.icons[this.iconIndex];
    }
  },
  methods: {
    initUserProfile: function() {
      axios
        .get(`${process.env.USER_SERVICEg}/user/${this.getUser.uid}`)
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
      this.postIndex = postIndex;
      console.log(
        "--------------------postIndex get comment from post ----------------------"
      );
      console.log(postIndex);
      console.log(this.postList[postIndex].commentList);
    },

    addPost() {
      var value = this.newPost && this.newPost.trim();
      if (!value) {
        return;
      }
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
      var value = this.comment && this.comment.trim();
      if (!value) {
        return;
      }
      console.log("------ ADD Comment ----");
      console.log(postIndex);
      console.log("-------- Post List ------");
      console.log(this.postList);
      console.log("------------------");
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
    },
    toggleMarker() {
      this.marker = !this.marker;
    },
    sendMessage() {
      this.resetIcon();
      this.clearMessage();
    },
    clearMessage() {
      this.message = "";
    },
    resetIcon() {
      this.iconIndex = 0;
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
 
 <style >
.rounded-card {
  border-radius: 12px;
  padding: 6px;
  margin: 1px;
}

.margin-name {
  margin-right: 9px;
  margin-left: 3px;
}
.margin-comment {
  margin: -5px;
  margin-left: 3px;
}

.textarea {
  width: 330px;
  min-height: 50px;
  height: auto;
}
.button {
  height: 40px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  background-color: #341646;
  color: #341646;
  font-size: 16px;
  padding: 12px 24px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  text-align: center;

}
.joinButton {
  height: 40px;
  font-size: 16px;
  padding: 12px ;
  border: none;
  cursor: pointer;
  border-radius: 0px;
  text-align: center;
}


h2 {
  color: #341646;
}

</style>
