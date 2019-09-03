import firebase, {
  auth,
  GoogleProvider,
  FacebookProvider,
  TwitterProvider
} from "@/plugins/fireinit.js";
import axios from "axios"

const state = () => ({
  user: {
    photoURL: "https://image.flaticon.com/icons/png/512/64/64572.png",
    uid: null,
    username: "linjingyun12",
    firstName: "Supakorn",
    lastName: "Trakulmaiphol",
    persona: {
      probationDuration: 2,
      absentInRow: 5,
      interestIdea: [],
      //interestIdea: ["art", "game", "technology"],
      interestBehaviorList: [
        { genre: "art", totalView: 0, totalParticipate: 1, absent: 0 },
        { genre: "business", totalView: 5, totalParticipate: 0, absent: 0 },
        { genre: "book", totalView: 2, totalParticipate: 1, absent: 0 },
        { genre: "beauty", totalView: 8, totalParticipate: 0, absent: 0 },
        { genre: "family", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "flim", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "game", totalView: 20, totalParticipate: 1, absent: 0 },
        { genre: "photography", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "music", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "technology", totalView: 13, totalParticipate: 1, absent: 0 },
        { genre: "social", totalView: 2, totalParticipate: 1, absent: 0 }
      ],
      badgeList: [
        {
          badgeName: "# Shadowy Red Hat",
          level: 0,
          totalExp: 125,
          nextLevelExp: 150
        }
      ]
    },
    ticketHistory: [
      {
        eventId: "aTgfdg12nd",
        eventName: "Openshift By Redhat 2018",
        eventStartDate: new Date(),
        qrcode: "",
        exp: 75
      },
      {
        eventId: "5acmy02ffh",
        eventName: "AWSomeday By AWS 2019",
        eventStartDate: new Date(),
        qrcode: "",
        exp: 50
      }
    ]
  }
});

const getters = {
  getUser: function (state) {
    return state.user;
  }
};

const mutations = {
  setUser: function (state, user) {
    state.user = user;
  },
  addInterestIdea: function (state, interestIdea) {
    let interestIdeaList = state.user.persona.interestIdea;
    if (interestIdeaList.length >= 3) {
      interestIdeaList.shift();
    }
    interestIdeaList.push(interestIdea);
    //state.User.persona.interestIdea.push(interestIdea)
  },
  setInterestIdea: function (state, interestIdea) {
    state.User.persona.interestIdea = interestIdea;
  }
};

const actions = {
  testContext: function (context) {
    this.$router.push({ path: '/' })
  },
  login: function ({ commit }, user) {
    console.log("action work for  login");
    commit("SetLogin", user);
  },
  setInterestIdea: function ({ commit }, idea) {
    commit("setInterestIdea", idea);
  },
  autoSignIn: function ({ commit }, payload) {
    commit("setUser", payload);
  },
  signUpWithEmail: function ({ commit }, credential) {
    console.log("email!!!!!!!!!!!!!!!")
    console.log(credential)
    auth.createUserWithEmailAndPassword(credential.email, credential.password).then(result => {
      credential.uid =result.user.uid
      credential.displayName = result.user.displayName
      credential.photoURL = result.user.photoURL
      axios.post(`${process.env.USER_SERVICE}/user`,credential)
      console.log(result);
    })
      .catch(err => {
        console.log(err);
      });
  },
  signInWithEmail: function ({ commit }, credential) {
    auth.signInWithEmailAndPassword(credential.email, credential.password).then(result => {
      console.log(result);
    })
      .catch(err => {
        console.log(err);
      });
  },
  signInWithGoogle: function ({ commit }) {
    auth.signInWithRedirect(GoogleProvider).then(result => {
      console.log(result);
    })
      .catch(err => {
        console.log(err);
      });
  },
  signInWithFacebook: function ({ commit }) {
    console.log(FacebookProvider);
    auth
      .signInWithRedirect(FacebookProvider)
      .then(user => {
        commit('setUser', {
          dispalyName: user.displayName,
          email: user.email,
          emailVerified: user.emailVerified,
          isAnonymous: user.isAnonymous,
          photoURL: user.photoURL,
          uid: user.uid
        })
      })
      .catch(err => {
        console.log(err);
      });
  },
  signOut: function ({ commit }) {
    auth
      .signOut()
      .then(() => {
        commit("setUser", {});
      })
      .catch(err => console.log(error));
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
