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
    <!-- Upload Cover picture Do not do any thing now -->
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
    <!-- Upload Cover picture  not do any thing now -->

    <!-- Edit Description Admin only -->
    <v-layout row wrap>
      <v-flex class="text-xs-left">
        <h2>Event Name</h2>
      </v-flex>
      <v-flex class="text-xs-right">
        <v-dialog v-model="dialogOfEdit" persistent max-width="600px">
          <template v-slot:activator="{ on }"></template>
          <v-card>
            <v-card-title></v-card-title>
            <v-card-text>
              <v-container grid-list-md>
                Community Name
                <v-text-field
                  v-model="communityForm.communityName"
                  label="* Community Name"
                  required
                ></v-text-field>
                <br />Community Description
                <br />
                <br />
                <v-textarea
                  outline
                  name="description"
                  label="Description"
                  color="pink"
                  rows="6"
                  required
                  hide-details
                  v-model="communityForm.communityDetail"
                ></v-textarea>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="#341646" flat @click="dialogOfEdit = false">Close</v-btn>
              <v-btn color="#341646" flat @click="dialogOfEdit = false">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-flex>
    </v-layout>
    <p size="1px">User Comment</p>
    <div v-for="(review,postIndex ) in reviewList " :key="postIndex">
      <v-card>
        <v-layout row wrap align-start justify-center fill-height>
          <v-layout row pt-2>
            <v-flex xs1 md1 lg1 mt-3 ml-3>
              <v-icon>today</v-icon>
              <!-- <v-img  max-width="40" max-height="40" left></v-img> -->
            </v-flex>
            <v-flex xs11 md11 lg11 mt-1 ml-3 mr-1>
              <div>{{review.displayName}}</div>
           
              <p
                class="date"
              >{{formatDateForReadable(review.reviewDate)}} {{formatAMPM(review.reviewDate)}}</p>
            </v-flex>
          </v-layout>

          <v-card-text class="px-3 pt-3 pb-0">{{review.reviewDetail}} <br>
          
          <v-layout row>
            <v-flex  >
          Given rating :
            </v-flex>
            <v-flex xs7>
       
      <v-rating
      readonly
        v-model="review.rating"
        color="yellow accent-4"
        dense
        half-increments
        hover
        size="18"
      ></v-rating>
            </v-flex>
      <v-flex xs1>
            ({{review.rating}})
      </v-flex>

          </v-layout>
          </v-card-text>

        </v-layout>
        <br />
      </v-card>
      <br />
    </div>

    <br />
  </div>
</template> 
  
<script>
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
      defaultImage:
        "https://www.elegantthemes.com/blog/wp-content/uploads/2017/03/Facebook-Groups-for-Bloggers-shutterstock_555845587-ProStockStudio-FT.png",
      remove: ["remove"],
      post: "",
      newPost: "",
      reviewList: [],
      commentInPost: "",
      reviewDate: "",
      comment: "",
      postIndex: 0,
      dialogOfComment: false,
      name: "",
      show: false,
      marker: true,
      event: {},
      communityForm: {
        communityName: "",
        communityDetail: ""
      }
    };
  },
  mounted() {
    this.loadAllReviewOfEvent();
  },
  computed: {
    ...mapGetters(["getUser"]),

    icon() {
      return this.icons[this.iconIndex];
    }
  },
  methods: {
    loadAllReviewOfEvent() {
      let loader = this.$loading.show();
      axios
        .get(
          `${process.env.EVENT_SERVICE}/event/reviews/${this.$route.params.elasticEventId}`
        )
        .then(reviewResponse => {
          console.log(reviewResponse.data.reviewList);
          this.event = reviewResponse.data;
          this.reviewList = reviewResponse.data.reviewList;
          loader.hide();
        })
        .catch(err => {
          loader.hide();
        });
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
      this.dialogOfComment = true;
      this.postIndex = postIndex;
      console.log(
        "--------------------postIndex get comment from post ----------------------"
      );
      console.log(postIndex);
      console.log(this.reviewList[postIndex].commentList);
    },
    // onPictureListUpload(event){
    //    console.log("uplaod din");
    //   this.postPictureLists = event.target.files;
    //   this.postPictureListsUrl = []
    //   for (let i = 0; i < this.postPictureLists.length; i++) {
    //     const fileReader = new FileReader();
    //     fileReader.addEventListener("load", () => {
    //       this.postPictureListsUrl.push({
    //         url: fileReader.result,
    //         name: event.target.files[i].name
    //       });
    //     });
    //     fileReader.readAsDataURL(this.postPictureLists[i]);
    //   }
    // },

    // removePost(todo) {
    //   const postIndex = this.reviewList.indexOf(todo);
    //   this.reviewList.splice(postIndex, 1);
    // },

    addComment(postIndex) {
      var value = this.comment && this.comment.trim();
      if (!value) {
        return;
      }
      console.log("------ ADD Comment ----");
      console.log(postIndex);
      console.log("-------- Post List ------");
      console.log(this.reviewList);
      console.log("------------------");
      this.postIndex = postIndex;
      let isCommentList = this.reviewList[postIndex].commentList;
      console.log(isCommentList);

      if (isCommentList === undefined) {
        console.log("It's first comment");
        this.reviewList[postIndex].commentList = [];
      }

      this.reviewList[postIndex].commentList.push(this.comment);
      console.log(this.reviewList[postIndex].commentList);
      this.comment = "";
      this.dialogOfComment = false;
      console.log(postIndex);
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
    handleUploaded(resp) {
      this.userAvatar = resp.relative_url;
    },
    formatDateForReadable: function(formatDate) {
      let date = new Date(formatDate);
      formatDate =
        date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear();
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
.date {
  font-size: 14px;
}
</style>
