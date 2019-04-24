// export const location = {
//   state: {
//     currentLocation: {}
//   },
//   getters: {
//     getCurrentLocation: function(state) {
//       return state.currentLocation;
//     }
//   },
//   mutations: {
//     setCurrentLocation: function(state, updatedLocation) {
//       state.currentLocation = updatedLocation;
//       console.log("location work" + state.currentLocation)
//     }
//   },
//   actions: {
//     setCurrentLocation: function({ commit }, updatedLocation) {
//       commit("setCurrentLocation", updatedLocation);
//     }
//   }
// };

const state = () => ({
  currentLocation: {}
});

const getters = {
  getCurrentLocation: function(state) {
    return state.currentLocation;
  }
};

const mutations = {
  setCurrentLocation: function(state, updatedLocation) {
    state.currentLocation = updatedLocation;
  }
};

const actions = {
  updateCurrentLocation: function({ commit }, updatedLocation) {
    console.log("action work for setlocation");
    commit("setCurrentLocation", updatedLocation);
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
