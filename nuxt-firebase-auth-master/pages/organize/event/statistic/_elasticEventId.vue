<template>
  <div>
    <v-flex align="center">
      <div v-if="imageUrl == ''">
        <v-img
          :src="event.eventPictureCover"
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
    <v-tabs color="#341646" dark slider-color="white" centered>
      <!-- Header Tab -->
      <v-tab ripple>Review &amp; Feedback</v-tab>
      <v-tab ripple>Notification</v-tab>

      <!-- Tabs Content-->
      <v-tab-item>
        <br>
        <center><h3>{{event.eventName}}</h3></center>
        <br />
        <p size="1px">User Comment</p>
        <div v-for="(review,postIndex ) in reviewList " :key="postIndex">
          <v-card>
            <v-layout row wrap align-start justify-center fill-height>
              <v-layout row pt-2>
                <v-flex xs1 md1 lg1 mt-3 ml-3>
                  <!-- <v-icon>today</v-icon> -->
                  <v-avatar size="40" color="grey">
                    <v-img :src="review.photoURL"></v-img>
                  </v-avatar>
                </v-flex>
                <v-flex xs11 md11 lg11 mt-1 ml-3 mr-1>
                  <div>{{review.displayName}}</div>

                  <p
                    class="date"
                  >{{formatDateForReadable(review.reviewDate)}} {{formatAMPM(review.reviewDate)}}</p>
                </v-flex>
              </v-layout>

              <v-card-text class="px-3 pt-3 pb-0">
                {{readMoreActivated ? review.reviewDetail : review.reviewDetail.slice(0, 200) }}
                <center>
                  <a
                    class="textViewMore"
                    @click="readMoreActivated = !readMoreActivated"
                  >{{readMoreActivated ? 'Show less' : 'Read more'}}</a>
                </center>
              </v-card-text>

              <v-card-text class="px-3 pt-3 pb-0">
                <v-layout row>
                  <v-flex>Given rating :</v-flex>
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
                  <v-flex xs1>({{review.rating}})</v-flex>
                </v-layout>
              </v-card-text>
            </v-layout>
            <br />
          </v-card>
          <br />
        </div>
        <br />
      </v-tab-item>
      <!-- Notification Tabs -->
      <v-tab-item>
        <v-flex xs12>
          <v-img
            :src="pushMessage"
            aspect-ratio="1"
            class="grey lighten-2"
            max-width="1250"
            max-height="275"
          />
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="name"
            label="Notification Title"
            :placeholder="`หัวข้อของการแจ้งเตือน (Default คือ ${event.eventName})`"
            v-model="messageTitle"
          ></v-text-field>
          <v-select
            :items="notificationModes"
            v-model="selectNotificationMode"
            label="เลือกประเภทของการแจ้งเตือน (Default คือ Notify)"
          ></v-select>
          <v-textarea
            name="description"
            v-model="messageDetail"
            label="Notification Description"
            rows="3"
            placeholder="แจ้งเตือนข่าวสารไปยังผู้ใช้ที่ได้รับตั๋วกิจกรรมไปแล้ว"
          ></v-textarea>
          <center>
            <v-btn color="primary" @click="pushNotificationToEventTopic()">Push Notification</v-btn>
          </center>
        </v-flex>
      </v-tab-item>
    </v-tabs>
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
      notificationModes: ['notifify','review'],
      selectNotificationMode: "notifify",
      dialogOfEdit: false,
      readMoreActivated: false,
      join: false,
      imageUrl: "",
      image: null,
      imageInPost: "",
      imagePost: null,
      postPictureListsUrl: [],
      postPictureLists: null,
      pushMessage: require(`@/assets/default/push-message.png`),
      defaultImage: require(`@/assets/default/community.png`),
      remove: ["remove"],
      post: "",
      newPost: "",
      reviewList: [],
      commentInPost: "",
      reviewDate: "",
      comment: "",
      postIndex: 0,
      event: {
        eventName: ""
      },
      dialogOfComment: false,
      name: "",
      show: false,
      marker: true,
      event: {},
      communityForm: {
        communityName: "",
        communityDetail: ""
      },
      elasticEventId: "",
      messageTitle: "",
      messageDetail: ""
    };
  },
  mounted() {
    this.elasticEventId = this.$route.params.elasticEventId;
    this.loadAllReviewOfEvent();
  },
  computed: {
    ...mapGetters(["getUser"]),
    icon() {
      return this.icons[this.iconIndex];
    }
  },
  methods: {
    pushNotificationToEventTopic() {
      let loader = this.$loading.show();
      let linkUrl= `/event/${this.elasticEventId}`
      if(this.selectNotificationMode === 'review'){
        linkUrl = `/ticket/${this.elasticEventId}`
      }
      let notificationBody = {
        title:
          this.messageTitle === "" ? this.event.eventName : this.messageTitle,
        messageDetail: this.messageDetail,
        linkUrl
      };
      axios
        .post(
          `${process.env.EVENT_SERVICE}/notification/push/event/${this.elasticEventId}`,
          notificationBody
        )
        .then(pushNotificationResponse => {
          this.$swal({
            type: "success",
            title: "Push Notification Success !!!",
            text: "Push Notification Success !!!"
          });
        })
        .catch(err => {
          this.$swal({
            type: "error",
            title: "Fail To push notification !",
            text: "Fail To push notification"
          });
        })
        .finally(() => {
          loader.hide();
        });
    },
    loadEventDetail() {
      axios
        .get(`${process.env.EVENT_SERVICE}/event/${this.elasticEventId}`)
        .then(eventResponse => {
          console.log("------------ Async Data  -----------");
          this.event = eventResponse.data;
        })
        .catch(err => {
          console.log("!!!!!!!!!!!!!!!!! Boom Not found !!!!!!!!!!");
          console.log(err);
        });
    },
    loadAllReviewOfEvent() {
      let loader = this.$loading.show();
      axios
        .get(
          `${process.env.EVENT_SERVICE}/event/reviews/${this.elasticEventId}`
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

.textViewMore {
  font-size: 15px;
  font-weight: 800;
}
</style>
