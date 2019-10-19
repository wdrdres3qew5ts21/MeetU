const state = () => ({
    eventTemplate: {
        eventName: '',
        eventDetail: '',
        eventTags: [],
        eventPictureCover: "",
        eventPictureLists: [],
        numberOfTicket: 0,
        createEventDate: "",
        endRegisterDate: "",
        eventStartDate: "",
        eventEndDate: "",
        eventPictureCoverBase: "",
        eventPictureListsBase: [],
    }
});

const getters = {
    getEventTemplate: function (state) {
        return state.eventTemplate;
    }
};

const mutations = {
    setEventTemplate: function (state, eventTemplate) {
        state.eventTemplate = eventTemplate;
    },
    setPictureDetail(state, pictureDetail) {
        state.eventTemplate.eventPictureCoverBase = pictureDetail.eventPictureCoverBase
        state.eventTemplate.eventPictureListsBase = pictureDetail.eventPictureListsBase
        console.log(state)
    }
};

const actions = {
    setEventTemplate: function ({ commit }, eventTemplate) {
        console.log("action work for eventTemplate");
        commit("setEventTemplate", eventTemplate);
    },
    setPictureDetail({ commit }, pictureDetail) {
        commit('setPictureDetail', pictureDetail)
    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
