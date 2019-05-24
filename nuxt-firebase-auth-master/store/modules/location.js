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
