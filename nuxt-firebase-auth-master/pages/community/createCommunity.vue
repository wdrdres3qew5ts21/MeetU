<template>
  <v-container>
    <div>
      <br />
      <h2>Create New Community</h2>
      <v-form ref="form" v-model="valid" :lazy-validation="false">
        <v-text-field
          v-model="communityForm.communityName"
          label="* Community Name"
          required
          :rules="[v => !!v || 'Community name is required']"
        ></v-text-field>
        <v-layout class="mb-4">
          <v-layout class="mb-4">
            <v-autocomplete
              :items="categoryList"
              item-text="categoryLabel"
              item-value="categoryName"
              label="Category (Limit to 3 tags only)"
              chips
              clearable
              v-model="communityForm.categorySelected"
              multiple
              sm6
              xs2
            >
              <template v-slot:selection="data">
                <v-chip :selected="data.selected">
                  <strong>{{ data.item.categoryName}}</strong>&nbsp;
                </v-chip>
              </template>
            </v-autocomplete>
          </v-layout>
          <!-- {{communityForm.categorySelected}} -->
          <!-- <v-autocomplete label="Categorys" :items="categoryList" block></v-autocomplete> -->
        </v-layout>

        <v-layout class="mb-4">
          <v-textarea
            outline
            name="description"
            label="Description"
            rows="6"
            required
            hide-details
            v-model="communityForm.communityDetail"
            :rules="[v => !!v || 'Description  is required']"
          ></v-textarea>
        </v-layout>
        <!-- <nuxt-link :to="`/community/communityId/?`" style="text-decoration-line:none;"> -->
        <br />
        <v-btn
          block
          v-if="!valid | communityForm.categorySelected==0"
          color="grey"
          disabled="true"
          class="mb-2 white--text"
        >Create Community</v-btn>
        <v-btn block
          v-else
          color="#341646"
          class="mb-2 white--text"
          @click="createCommunity">Create Community</v-btn>
        <!-- </nuxt-link> -->
      </v-form>
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";
import { mockCategoryList } from "@/utils/categoryJson";
export default {
  name: "communityForm",
  data() {
    return {
      selectedFile: null,
      valid: true,
      categoryList: [],
      selectedCategoryList: [],
      communityForm: {
        categorySelected: [],
        communityName: "",
        communityDetail: ""
      },

      dialog1: false,
      dialog2: false
    };
  },
  computed: {
    ...mapGetters(["getCategory"])
  },
  mounted() {
    this.loadCategoryList();
  },
  watch: {
    "communityForm.categorySelected"(categorySelected) {
      if (categorySelected.length > 3) {
        this.communityForm.categorySelected.shift();
      }
    }
  },
  methods: {
    ...mapActions(["autoSignIn", "setCategory"]),
    loadCategoryList() {
      axios
        .get(`${process.env.EVENT_SERVICE}/category`)
        .then(categoryList => {
          this.categoryList = categoryList.data;
          this.setCategory(this.categoryList);
        })
        .catch(error => {
          this.categoryList = mockCategoryList;
        });
    },
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload: function(e) {
      // upload file, get it from this.selectedFile
    },
    createCommunity() {
      let loader = this.$loading.show();
      axios
        .post(
          `${process.env.COMMUNITY_SERVICE}/community`,
          this.communityForm,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwtToken")}`
            }
          }
        )
        .then(communityResponse => {
          loader.hide();
          this.$swal({
            type: "success",
            title: "Create community success!!",
            text: `Enjoy  your community`
          });
          this.$router.push(`/community/${communityResponse.data.communityId}`);
        })
        .catch(err => {
          loader.hide();
        });
    }
  }
};
</script> 
 
 
<style>
.v-content {
  max-width: 100%;
  background-color: #eeeeee;
  font-family: Roboto;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}

.accountButton {
  width: 500px;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}

.upgradeSuccessTextPopup {
  font-weight: bold;
  font-size: 18px;
}

.popupUpgrade:hover {
  background-color: white;
}

.h3 {
  font-family: "Roboto";
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 5%;
}

.p {
  font-size: 16px;
}
</style> 
