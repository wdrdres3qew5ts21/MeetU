const state = () => ({
  mockUser: {
      username: 'linjingyun12',
      firstname: 'Supakorn',
      lastname: 'Trakulmaiphol',
      interest: ['game', 'technology', 'art']
  }
});

const getters = {
    mockGetUser: function(state) {
    return state.mockUser;
  }
};

const mutations = {
    mockSetUser: function(state, mockUser) {
    state.mockUser = mockUser;
  }
};

const actions = {
  mockLogin: function({ commit }, mockUser) {
    console.log("action work for mock login");
    commit("mockSetLogin", mockUser);
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
