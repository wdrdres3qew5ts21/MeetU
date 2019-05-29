const state = () => ({
  mockUser: {
    userId: "fdsfs531ffsr",
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
  mockGetUser: function(state) {
    return state.mockUser;
  }
};

const mutations = {
  mockSetUser: function(state, mockUser) {
    state.mockUser = mockUser;
  },
  addInterestIdea: function(state, interestIdea) {
    let interestIdeaList = state.mockUser.persona.interestIdea;
    if (interestIdeaList.length >= 3) {
      interestIdeaList.shift();
    }
    interestIdeaList.push(interestIdea);
    //state.mockUser.persona.interestIdea.push(interestIdea)
  },
  setInterestIdea: function(state, interestIdea) {
    state.mockUser.persona.interestIdea = interestIdea;
  }
};

const actions = {
  mockLogin: function({ commit }, mockUser) {
    console.log("action work for mock login");
    commit("mockSetLogin", mockUser);
  },
  setInterestIdea: function({ commit }, idea) {
    commit("setInterestIdea", idea);
  }
};
export default {
  state,
  mutations,
  actions,
  getters
};
