const state = () => ({
    communityTemplate: {}
});

const getters = {
    getCommunityTemplate: function (state) {
        return state.communityTemplate;
    }
};

const mutations = {
    setCommunityTemplate: function (state, communityTemplate) {
        state.communityTemplate = communityTemplate;
    }
};

const actions = {
    setCommunityTemplate: function ({ commit }, communityTemplate) {
        console.log("action work for communityTemplate");
        commit("setcommunityTemplate", communityTemplate);
    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
