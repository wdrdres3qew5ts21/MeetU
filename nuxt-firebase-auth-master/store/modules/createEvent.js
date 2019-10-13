const state = () => ({
    eventTemplate: {}
});

const getters = {
    getEventTemplate: function (state) {
        return state.eventTemplate;
    }
};

const mutations = {
    setEventTemplate: function (state, eventTemplate) {
        state.eventTemplate = eventTemplate;
    }
};

const actions = {
    setEventTemplate: function ({ commit }, eventTemplate) {
        console.log("action work for eventTemplate");
        commit("setEventTemplate", eventTemplate);
    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
