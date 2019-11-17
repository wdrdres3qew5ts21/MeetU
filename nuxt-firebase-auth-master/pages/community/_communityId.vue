<template>
  <div>
    <!-- Upload Cover picture Do not do any thing now -->
    <v-flex align="center">
      <div v-if="imageUrl == ''">
        <v-img
          :src="communityForm.communityPictureCover==undefined?defaultImage: communityForm.communityPictureCover"
          aspect-ratio="1"
          class="grey lighten-2"
          max-width="1250"
          max-height="200"
        >
          <!-- <v-btn class="button" @click="onPickFile">
            Upload image
            &nbsp;
            &nbsp;
            <v-icon>add_a_photo</v-icon>
            <br />
          </v-btn>-->
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
    <!-- Upload Cover picture  not do any thing now -->

    <!-- ------Button for Joined community and un Joined-------  -->
    <v-btn v-if="isSubscribe" block color="primary" @click="unfollowCommunity()">Unfollow Community</v-btn>
    <v-btn v-else block color="primary" @click="followCommunity()">Follow Community</v-btn>

    <!-- Edit Description Admin only -->
    <v-layout>
      <v-flex class="text-xs-left">
        <h2>{{ communityForm.communityName=='' ? 'Community Name': communityForm.communityName}}</h2>
      </v-flex>
      <v-flex v-if="isOwner" row wrap class="text-xs-right">
        <v-dialog v-model="dialogOfEdit" persistent max-width="600px">
          <template v-slot:activator="{ on }">
            <v-btn depressed flat v-on="on">
              <v-icon color="#341646" medium>edit</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <h2>Edit Community</h2>
            </v-card-title>
            <v-card-text>
              <v-container grid-list-md>
                <div v-if="imageUrl == ''">
                  <v-img
                    :src="communityForm.communityPictureCover==undefined?defaultImage: communityForm.communityPictureCover"
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
                Community Description
                <br />
                <br />
                <v-textarea
                  outline
                  name="description"
                  label="Description"
                  color="pink"
                  rows="10"
                  required
                  hide-details
                  v-model="communityForm.communityDetail"
                ></v-textarea>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="#341646" flat @click="dialogOfEdit = false">Close</v-btn>
              <v-btn color="#341646" flat @click="saveCommunityDetail()">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-flex>
    </v-layout>
    <v-layout v-if="communityForm.comunityName !='' " row wrap>
      <v-chip
        v-for="(categoryChip, index) in communityForm.interestTags"
        @click="$router.push(`/event?category=${categoryChip}`)"
        :key="index"
      >
        <strong>{{ categoryChip}}</strong>&nbsp;
      </v-chip>
    </v-layout>
    <p
      size="1px"
    >{{ communityForm.communityName=='' ? 'Description about community': communityForm.communityDetail}}</p>

    <br />

    <!-- Edit Description Admin only -->

    <!-- ------Button for Joined community and un Joined-------  -->

    <v-card rounded outlined class="mx-auto">
      <div class="px-3">
        <v-form>
          <v-layout>
            <v-flex xs12>
              <br />
              <div v-if="postPictureListsUrl.length>0"></div>
              <v-img
                v-for="(image, index) in postPictureListsUrl "
                :key="index"
                :src="image.url"
                aspect-ratio="1"
                class="grey lighten-2"
                max-width="1250"
                max-height="200"
              ></v-img>
              <input
                v-show="false"
                ref="pictureListUpload"
                multiple
                type="file"
                @change="onPictureListUpload"
                accept="image/*"
              />
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
              <v-btn style="margin-right: 0px" icon @click="$refs.pictureListUpload.click()">
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
        </v-form>
      </div>
    </v-card>

    <div v-for="(post,postIndex ) in newPostList" :key="post.postId">
      <v-card rounded outlined>
        <br />
        <div>
          <v-layout>
            <v-container grid-list-xs fluid style="padding:10px">
              <v-layout row wrap>
                <v-flex xs3>
                  <v-avatar size="60">
                    <v-img :aspect-ratio="1/1" :src="post.photoURL"></v-img>
                  </v-avatar>
                </v-flex>
                <v-flex xs9>
                  <b class="showName">
                    <span
                      v-text="post.displayName.length > 10 ? post.displayName.substr(0,23)+'..' :post.displayName"
                    ></span>
                  </b>
                  <v-layout
                    row
                    wrap
                    class="showDate"
                  >{{formatDateForReadable(post.postOfDate)}} {{formatAMPM(post.postOfDate)}}</v-layout>
                </v-flex>
              </v-layout>
              <v-flex v-if="post.uid=== getUser.uid" xs2 class="text-xs-right">
                <v-btn text icon @click="removeNewPost(postIndex,  post.postId)">
                  <v-icon>clear</v-icon>
                </v-btn>
              </v-flex>
            </v-container>
          </v-layout>
        </div>
        <v-container grid-list-xs fluid style="padding:5px">
          <br />
          <v-list>
            <v-list-tile-content>
              <!-- Show image -->
            </v-list-tile-content>
            <v-list-tile-content>
              <div class="textarea" contenteditable="false">{{post.postDetail}}</div>
            </v-list-tile-content>
          </v-list>
        </v-container>
      </v-card>
      <br />
    </div>

    <!-- ------------posted card + small viewcomment + post picture If have :) 
    No upload in firebase yet container------------>
    <br />
    <div v-if="postList.length>0">
      <div v-for="(post,postIndex ) in postList" :key="post.postId">
        <v-card rounded outlined>
          <br />
          <div>
            <v-layout>
              <v-container grid-list-xs fluid style="padding:10px">
                <v-layout row wrap>
                  <v-flex xs3>
                    <v-avatar size="60">
                      <v-img :aspect-ratio="1/1" :src="post.photoURL"></v-img>
                    </v-avatar>
                  </v-flex>
                  <v-flex xs9>
                    <b class="showName">
                      <span
                        v-text="post.displayName.length > 10 ? post.displayName.substr(0,23)+'..' :post.displayName"
                      ></span>
                    </b>

                    <v-layout
                      row
                      wrap
                      class="showDate"
                    >{{formatDateForReadable(post.postOfDate)}} {{formatAMPM(post.postOfDate)}}</v-layout>
                  </v-flex>
                </v-layout>
              </v-container>
              <v-flex v-if="post.uid=== getUser.uid" xs2 class="text-xs-right">
                <v-btn text icon @click="removePost(postIndex, post.postId)">
                  <v-icon>clear</v-icon>
                </v-btn>
              </v-flex>
            </v-layout>
          </div>
          <v-container grid-list-xs fluid style="padding:5px">
            <br />
            <v-list>
              <v-list-tile-content>
                <div class="textarea" contenteditable="false">{{post.postDetail}}</div>
              </v-list-tile-content>
            </v-list>
          </v-container>

          <v-card-text rounded outlined class="mx-auto">
            <v-divider></v-divider>
            <v-flex class="text-right">
              <v-btn text block flat @click="getCommentFromPost(postIndex, post.postId)">
                <v-icon>comment</v-icon>Comment
              </v-btn>
            </v-flex>
          </v-card-text>
          <v-list>
            <v-card xs6 rounded outlined v-if="postList[postIndex].commentList.length > 0">
              <v-container grid-list-xs xs4 fluid style="padding:5px">
                <v-list-tile xs4>
                  <v-list-tile-avatar>
                    <v-img
                      :aspect-ratio="1/1"
                      :src="postList[postIndex].commentList[postList[postIndex].commentList.length-1].photoURL"
                    ></v-img>
                  </v-list-tile-avatar>
                  <v-list-tile>
                    <div>
                      <v-list-tile-content>
                        <v-card color="#F5F5F5" class="rounded-card" max-width="240px">
                          <v-list-tile-title class="margin-name">
                            <font
                              size="2"
                            >{{ postList[postIndex].commentList[postList[postIndex].commentList.length-1].displayName}}</font>
                          </v-list-tile-title>
                          <v-list-tile-sub-title class="margin-comment">
                            <font
                              size="2"
                            >{{postList[postIndex].commentList[postList[postIndex].commentList.length-1].commentOfPostDetail}}</font>
                          </v-list-tile-sub-title>
                        </v-card>
                      </v-list-tile-content>
                    </div>
                  </v-list-tile>
                </v-list-tile>
              </v-container>
            </v-card>
            <!-- แสดงแค่เม้นสุดท้าย -->
          </v-list>
        </v-card>
        <br />
      </div>
      <client-only>
        <infinite-loading spinner="spiral" @infinite="infiniteScroll">
          <!-- <div slot="no-results">
            <v-btn color="primary" block>Go to Top</v-btn>
          </div>-->
        </infinite-loading>
      </client-only>
    </div>
    <!-- ------------ Close of : post+ small viewcomment + post picture If have :) 
    No upload in firebase yet container------------>

    <!-- -----------------------Show dialog with full comment and comment button----------------------- -->
    <div v-if="postList.length != 0">
      <v-dialog
        v-model="dialogOfComment"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
      >
        <template v-slot:activator="{ on }"></template>
        <v-card height="auto">
          <v-toolbar dark color="primary">
            <v-btn icon dark @click="dialogOfComment = false">
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>comment</v-toolbar-title>
          </v-toolbar>
          <div
            v-for="(comment,commentIndex ) in  postList[postIndex].commentList "
            :key="commentIndex"
          >
            <v-card rounded outlined grid-list-xs>
              <v-container grid-list-xs fluid style="padding:5px">
                <v-layout row wrap justify-start>
                  <v-flex xs2>
                    <v-list-tile-avatar>
                      <v-img :aspect-ratio="1/1" :src="comment.photoURL" size="80"></v-img>
                    </v-list-tile-avatar>
                  </v-flex>
                  <v-flex xs10>
                    <v-list-tile-content>
                      <div class="text-comment-area showNameComment" contenteditable="false">
                        <font class="margin-name">
                          <b>{{ comment.displayName}}</b>
                        </font>
                        <br />

                        <font
                          class="showDateComment"
                        >{{formatDateForReadable(comment.commentOfPostDate)}} {{formatAMPM(comment.commentOfPostDate)}}</font>
                        <br />

                        <font color="grey">{{comment.commentOfPostDetail}}</font>
                      </div>
                    </v-list-tile-content>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card>
          </div>
          <v-form>
            <v-layout ma-3>
              <v-layout row wrap>
                <v-flex xs12>
                  <v-text-field
                    :append-outer-icon="comment ? 'send' : 'send'"
                    box
                    v-model="comment"
                    clear-icon="close"
                    clearable
                    label="Write comment..."
                    type="text"
                    @click:append="toggleMarker"
                    @click:append-outer="addComment(postIndex )"
                    @click:clear="clearComment"
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-layout>
          </v-form>
        </v-card>
      </v-dialog>
      <!-- -----------------------Show dialog with full comment and comment button----------------------- -->
    </div>
  </div>
  <!-- remove Post button -->
  <!-- <v-btn
                  @click="removePost(todo)"
                  color="#341646"
                  class="mb-2 white--text"
                  type="button"
                  name="button"
  >Remove</v-btn>-->
</template> 
  
<script>
import * as firebase from "firebase/app";
import "firebase/storage";
import Swal from "sweetalert2";
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "communityDetail",
  data() {
    return {
      dialogOfEdit: false,
      join: false,
      imageUrl: "",
      image: null,
      imageInPost: "",
      imagePost: null,

      postPictureListsUrl: [],
      postPictureLists: null,

      defaultImage: require(`@/assets/default/community.png`),
      remove: ["remove"],
      post: "",
      newPost: "",
      newPostList: [],
      postList: [],
      commentInPost: "",
      comment: "",
      postIndex: 0,
      postIdOfComment: "",
      dialogOfComment: false,
      name: "",
      show: false,
      marker: true,
      page: 0,
      isOwner: false,
      communityForm: {
        communityName: "",
        communityDetail: ""
      },
      communityId: "",
      isSubscribe: false,
      postOfDate: ""
    };
  },
  mounted() {
    this.communityId = this.$route.params.communityId;
    this.loadCommunityDetail();
    this.loadAllPostInCommunity();
    if (localStorage.getItem("jwtToken")) {
      this.verifyIfUserSubscribeCommunity();
    }
  },
  computed: {
    ...mapGetters(["getUser"]),
    icon() {
      return this.icons[this.iconIndex];
    }
  },
  methods: {
    loadCommunityDetail() {
      axios
        .get(`${process.env.COMMUNITY_SERVICE}/community/${this.communityId}`)
        .then(communityResponse => {
          this.communityForm = communityResponse.data;
          console.log(this.communityForm);
        })
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
      // imageUrl คือตัวแปรที่ใช้อัพเข้า cloud firebase
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.image = files[0];
      console.log(this.image);
    },
    saveCommunityDetail() {
      let loader = this.$loading.show();
      if (this.image != null) {
        let pictureFile = this.imageUrl;
        let dateobj = new Date();
        let fileName = this.image.name + "_" + dateobj.toISOString();
        let storage = firebase.storage();
        let storageRef = storage.ref("/communityPicture");
        let setupFile = storageRef.child(fileName);
        setupFile
          .putString(pictureFile, "data_url", { contentType: "image/jpeg" })
          .then(snapshot => {
            snapshot.ref.getDownloadURL().then(eventPictureCover => {
              this.communityForm.communityPictureCover = eventPictureCover;
              axios
                .post(
                  `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}`,
                  this.communityForm,
                  {
                    headers: {
                      Authorization: `Bearer ${localStorage.getItem(
                        "jwtToken"
                      )}`
                    }
                  }
                )
                .then(updateCommunityResponse => {
                  this.dialogOfEdit = false;
                })
                .catch(err => {
                  this.$swal({
                    type: "error",
                    title: "Failed to edit community!!!",
                    text: `${err.response.data.response}`
                  });
                })
                .finally(() => {
                  loader.hide();
                });
            });
          });
      } else {
        axios
          .post(
            `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}`,
            this.communityForm,
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
              }
            }
          )
          .then(updateCommunityResponse => {
            this.dialogOfEdit = false;
          })
          .catch(err => {
            this.$swal({
              type: "error",
              title: "Failed to edit community!!!",
              text: `${err.response.data.response}`
            });
          })
          .finally(() => {
            loader.hide();
          });
      }
    },
    verifyIfUserSubscribeCommunity() {
      let loader = this.$loading.show();
      axios
        .get(
          `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/privilege/status`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(subscribeCommunityResponse => {
          this.isSubscribe = subscribeCommunityResponse.data.isSubscribe;
          this.isOwner = subscribeCommunityResponse.data.isOwner;
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    followCommunity() {
      let loader = this.$loading.show();
      if (
        localStorage.getItem("jwtToken") == null ||
        localStorage.getItem("jwtToken") === undefined
      ) {
        this.$swal({
          type: "error",
          title: "Please login first !!!",
          text: `Please login first !!!`
        });
        this.$router.push("/login");
        loader.hide();
      } else {
        axios
          .post(
            `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/subscribe`,
            null,
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
              }
            }
          )
          .then(joinCommunityResponse => {
            this.isSubscribe = !this.isSubscribe;
            loader.hide();
          })
          .catch(err => {
            this.$swal({
              type: "error",
              title: "Failed to subscribe community !!!",
              text: `${err.response.data.response}`
            });
            loader.hide();
          });
      }
    },
    unfollowCommunity() {
      let loader = this.$loading.show();
      axios
        .post(
          `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/unsubscribe`,
          null,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(joinCommunityResponse => {
          this.isSubscribe = !this.isSubscribe;
          loader.hide();
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Failed to subscribe community !!!",
            text: `${err.response.data.response}`
          });
          loader.hide();
        });
    },
    getCommentFromPost(postIndex, postId) {
      this.dialogOfComment = true;
      this.postIndex = postIndex;
      this.postIdOfComment = postId;
      console.log(
        "--------------------postIndex get comment from post ----------------------"
      );
      console.log(postIndex);
      console.log(this.postList[postIndex].commentList);
    },
    onPictureListUpload(event) {
      console.log("uplaod din");
      this.postPictureLists = event.target.files;
      this.postPictureListsUrl = [];
      for (let i = 0; i < this.postPictureLists.length; i++) {
        const fileReader = new FileReader();
        fileReader.addEventListener("load", () => {
          this.postPictureListsUrl.push({
            url: fileReader.result,
            name: event.target.files[i].name
          });
        });
        fileReader.readAsDataURL(this.postPictureLists[i]);
      }
      this;
    },
    loadAllPostInCommunity() {
      axios
        .get(
          `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/posts`
        )
        .then(postListResponse => {
          this.postList = postListResponse.data.content;
        })
        .catch(err => {});
    },
    addPost() {
      let loader = this.$loading.show();

      let value =
        (this.newPost && this.newPost.trim()) || this.postPictureLists;
      if (!value) {
        return;
      }
      let postTemplate = {
        postDetail: this.newPost,
        photoURL: this.getUser.photoURL,
        postOfDate: new Date().toISOString,
        done: false
      };
      if (
        localStorage.getItem("jwtToken") == null ||
        localStorage.getItem("jwtToken") === undefined
      ) {
        this.$swal({
          type: "error",
          title: "Please login first !!!",
          text: `Please login first !!!`
        });
        this.$router.push("/login");
        loader.hide();
      } else {
        axios
          .post(
            `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/post`,
            postTemplate,
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
              }
            }
          )
          .then(postResponse => {
            console.log("--------------------------------");
            console.log(postResponse.data);
            this.newPostList.push(postResponse.data);
            this.newPostList.reverse();
            this.newPost = "";
            console.log(this.postList);
            loader.hide();
          })
          .catch(err => {
            this.$swal({
              type: "error",
              title: "Fail to post !!!",
              text: `${err.response.data.response}`
            });
            loader.hide();
          });
      }
    },
    removePost(index, postId) {
      let loader = this.$loading.show();
      let deletePostTemplate = {
        postId: postId
      };
      axios
        .post(
          `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/delete/post`,
          deletePostTemplate,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(deletePostResposne => {
          this.postList.splice(index, 1);
          console.log("remove post work");
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    removeNewPost(index, postId) {
      let loader = this.$loading.show();
      let deletePostTemplate = {
        postId: postId
      };
      axios
        .post(
          `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/delete/post`,
          deletePostTemplate,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(deletePostResposne => {
          this.newPostList.splice(index, 1);

          console.log("remove post work");
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    addComment(postIndex) {
      let loader = this.$loading.show();
      let value = this.comment && this.comment.trim();
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
      console.log("----------- psot id of comment --------");
      console.log(this.postIdOfComment);

      let commentBody = {
        postId: this.postIdOfComment,
        commentOfPostDetail: this.comment
      };

      axios
        .post(
          `${process.env.COMMUNITY_SERVICE}/community/comment/post`,
          commentBody,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(commentResponse => {
          this.postList[postIndex].commentList.push(commentResponse.data);
          console.log(this.postList[postIndex].commentList);
          this.comment = "";
          this.dialogOfComment = false;
          console.log(postIndex);
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
    },
    toggleMarker() {
      this.marker = !this.marker;
    },
    sendMessage() {
      this.resetIcon();
      this.clearMessage();
    },
    clearComment() {
      this.message = "";
    },
    resetIcon() {
      this.iconIndex = 0;
    },
    formatDateForReadable: function(formatDate) {
      const months = [
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC"
      ];
      let date = new Date(formatDate);
      formatDate =
        date.getDate() +
        " " +
        months[date.getMonth()] +
        " " +
        date.getFullYear();
      console.log(formatDate);
      return formatDate;
    },
    formatAMPM: function(AMPM) {
      let date = new Date(AMPM);
      const options = {
        hour: "2-digit",
        minute: "2-digit"
      };
      let time = date.toLocaleTimeString("en-US", options);
      return time;
    },
    handleUploaded(resp) {
      this.userAvatar = resp.relative_url;
    },
    infiniteScroll($state) {
      setTimeout(() => {
        this.page++;
        axios
          .get(
            `${process.env.COMMUNITY_SERVICE}/community/${this.communityId}/posts?page=${this.page}`
          )
          .then(postListResponse => {
            if (postListResponse.data.content.length > 0) {
              postListResponse.data.content.forEach(post =>
                this.postList.push(post)
              );
              $state.loaded();
            } else {
              $state.complete();
            }
          })
          .catch(err => {
            console.log(err);
          });
      }, 500);
    }
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
}

.textarea {
  max-width: 320px;
  min-height: 50px;
  height: auto;
  max-height: auto;
}
.text-comment-area {
  padding: 1%;
  border-radius: 12px;
  max-width: auto;
  min-height: 50px;
  height: auto;
  max-height: auto;
  background-color: #f5f5f5;
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
  padding: 12px;
  border: none;
  cursor: pointer;
  border-radius: 0px;
  text-align: center;
}
h2 {
  color: #341646;
}

.showDate {
  font-size: 14px;
}

.showName {
  color: #341646;
  font-size: 17px;
}
.showNameComment {
  font-size: 15px;
  color: #341646;
}
.showDateComment {
  font-size: 11px;
  color: darkgrey;
}
</style>
