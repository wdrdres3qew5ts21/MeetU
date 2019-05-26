const state = () => ({
  mockUser: {
    username: "linjingyun12",
    firstname: "Supakorn",
    lastname: "Trakulmaiphol",
    persona: {
      probationDuration: 0,
      absentInRow: 0,
      interestIdea: ['art', 'game', 'technology'],
      interestBahavior: [
        { genre: "art", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "business", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "book", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "beauty", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "family", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "flim", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "game", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "photography", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "music", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "technology", totalView: 0, totalParticipate: 0, absent: 0 },
        { genre: "social", totalView: 0, totalParticipate: 0, absent: 0 }
      ],
      badgeList: [
        { badgeName: '# Shadowy Red Hat', level: 0, totalExp: 125, nextLevelExp: 150}
      ]
    },
    ticketHistory: [
      {eventId: 'aTgfdg12nd', eventName: 'Openshift By Redhat 2018', eventStartDate: new Date(), qrcode: '', exp:75},
      {eventId: '5acmy02ffh', eventName: 'AWSomeday By AWS 2019', eventStartDate: new Date(), qrcode: '', exp:50}
    ]
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
