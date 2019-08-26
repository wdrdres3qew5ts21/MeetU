import firebase, {
  auth,
  GoogleProvider,
  FacebookProvider,
  TwitterProvider
} from "@/plugins/fireinit.js";

const state = () => ({
  user: {
    photoURL: "https://image.flaticon.com/icons/png/512/64/64572.png"
  }
});

const getters = {
  activeUser: function(state, getters) {
    return state.user;
  }
};

const mutations = {
  setUser: function(state, payload) {
    state.user = payload;
  }
};

const actions = {
  autoSignIn: function({ commit }, payload) {
    commit("setUser", payload);
  },
  signInWithGoogle: function({ commit }) {
    return new Promise((resolve, reject) => {
      auth.signInWithRedirect(GoogleProvider);
      resolve();
    });
  },
  signInWithFacebook: function({ commit }) {
    console.log(FacebookProvider);
    auth
      .signInWithRedirect(FacebookProvider)
      .then(result => {
        console.log(result);
      })
      .catch(err => {
        console.log(err);
      });
  },
  signOut: function({ commit }) {
    auth
      .signOut()
      .then(() => {
        commit("setUser", null);
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
