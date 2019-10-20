const state = () => ({
    eventTemplate: {
        eventName: '',
        eventDetail: '',
        eventTags: [],
        exp: 0.0,
        badge: {
        },
        eventPictureCover: "",
        eventPictureLists: [],
        numberOfTicket: 10,
        createEventDate: "",
        endRegisterDate: "",
        eventStartDate: "",
        eventEndDate: "",
        eventPictureCoverBase: "",
        eventPictureListsBase: [],
        location: {
            place: '',
            detail: '',
            subDistrict: '',
            distrct: '',
            province: '',
            country: '',
            geopoint: {
                lat: 0,
                lon: 0
            }
        }
    }
});

const getters = {
    getEventTemplate: function (state) {
        return state.eventTemplate;
    }
};

const mutations = {
    setEventTemplate: function (state, eventTemplate) {
        state.eventTemplate.eventName = eventTemplate.eventName;
        state.eventTemplate.eventDetail = eventTemplate.eventDetail;
        state.eventTemplate.eventTags = eventTemplate.eventTags;
        state.eventTemplate.badge = eventTemplate.badge;
        state.eventTemplate.exp = parseFloat(eventTemplate.exp);
        state.eventTemplate.eventStartDate = eventTemplate.eventStartDate;
        state.eventTemplate.eventEndDate = eventTemplate.eventEndDate;
    },
    setPictureDetail(state, pictureDetail) {
        state.eventTemplate.eventPictureCoverBase = pictureDetail.eventPictureCoverBase
        state.eventTemplate.eventPictureListsBase = pictureDetail.eventPictureListsBase
        console.log(state)
    },
    setEventLocation(state, location) {
        // state.eventTemplate.location.place = location.place
        // state.eventTemplate.location = location.addresscomponents
        // state.eventTemplate.location = location.addressDetail
        // state.eventTemplate.location = location.streetNumber
        // state.eventTemplate.location = location.road
        // state.eventTemplate.location = location.subDistrict
        // state.eventTemplate.location = location.distrct
        // state.eventTemplate.location = location.province
        // state.eventTemplate.location = location.country
        state.eventTemplate.location = location
    },
    setGeopoint(state, geopoint){
        state.eventTemplate.location.geopoint = geopoint
    }
};

const actions = {
    setEventTemplate: function ({ commit }, eventTemplate) {
        console.log("action work for eventTemplate");
        commit("setEventTemplate", eventTemplate);
    },
    setPictureDetail({ commit }, pictureDetail) {
        commit('setPictureDetail', pictureDetail)
    },
    setEventLocation({commit}, location) {
        commit('setEventLocation', location)
    },
    setGeopoint({commit}, geopoint){
        commit('setGeopoint', geopoint)
    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
