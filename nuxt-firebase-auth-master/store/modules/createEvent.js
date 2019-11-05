import * as firebase from "firebase/app";
import "firebase/storage";
import axios from "axios"
const state = () => ({
    eventTemplate: {
        eventName: '',
        eventDetail: '',
        eventTags: [],
        badge: {
            badgeId: "",
            exp: 0.0,
        },
        organize: {
            organizeId: ""
        },
        eventPictureCover: "",
        eventPictureLists: [],
        numberOfTicket: 10,
        createEventDate: "",
        endRegisterDate: "",
        eventStartDate: "",
        eventEndDate: "",
        eventStartDateTempt: "",
        eventEndDateTempt: "",
        eventStartTimeTempt: "",
        eventEndTimeTempt: "",
        eventPictureCoverBase: "",
        eventPictureListsBase: [],
        location: {
            place: '',
            detail: '',
            streetNumber: '',
            road: '',
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
        state.eventTemplate.organize = eventTemplate.organize;
        state.eventTemplate.eventName = eventTemplate.eventName;
        state.eventTemplate.eventDetail = eventTemplate.eventDetail;
        state.eventTemplate.eventTags = eventTemplate.eventTags;
        console.log('--- iso date --')
        state.eventTemplate.eventStartDate = eventTemplate.eventStartDate;
        state.eventTemplate.eventEndDate = eventTemplate.eventEndDate;
        console.log('---tempt date ---')
        state.eventTemplate.eventStartDateTempt = eventTemplate.eventStartDateTempt;
        state.eventTemplate.eventEndDateTempt = eventTemplate.eventEndDateTempt;
        state.eventTemplate.eventStartTimeTempt = eventTemplate.eventStartTimeTempt;
        state.eventTemplate.eventEndTimeTempt = eventTemplate.eventEndTimeTempt;
        state.eventTemplate.numberOfTicket = eventTemplate.numberOfTicket;
    },
    setPictureDetail(state, pictureDetail) {
        state.eventTemplate.eventPictureCoverBase = pictureDetail.eventPictureCoverBase
        state.eventTemplate.eventPictureListsBase = pictureDetail.eventPictureListsBase
        console.log(state)
    },
    setBadgeDetail(state, badge) {
        state.eventTemplate.badge.badgeId = badge.badgeId;
        state.eventTemplate.badge.exp = parseFloat(badge.exp);
    },
    setEventLocation(state, location) {
        state.eventTemplate.location.place = location.place
        state.eventTemplate.location.detail = location.detail
        state.eventTemplate.location.streetNumber = location.streetNumber
        state.eventTemplate.location.road = location.road
      //  state.eventTemplate.location.subDistrict = location.subDistrict
        state.eventTemplate.location.distrct = location.distrct
        state.eventTemplate.location.province = location.province
        state.eventTemplate.location.country = location.country
        //state.eventTemplate.location = location
    },
    setGeopoint(state, geopoint) {
        console.log('--Geopoint last-')
        console.log(geopoint)
        state.eventTemplate.location.geopoint = geopoint
    }
};

const actions = {
    setEventTemplate: function ({ commit }, eventTemplate) {
        console.log("action work for eventTemplate");
        console.log(eventTemplate)
        commit("setEventTemplate", eventTemplate);
    },
    setBadgeDetail({ commit }, badge) {
        commit('setBadgeDetail', badge)
    },
    setPictureDetail({ commit }, pictureDetail) {
        commit('setPictureDetail', pictureDetail)
    },
    setEventLocation({ commit }, location) {
        commit('setEventLocation', location)
    },
    setGeopoint({ commit }, geopoint) {
        commit('setGeopoint', geopoint)
    },
    saveEventAndUpload({ commit, getters, vm }) {
        let loader = this._vm.$loading.show()
        console.log("Vuex Upload Event Template")
        let eventTemplate = getters.getEventTemplate
        eventTemplate.eventPictureCover = eventTemplate.eventPictureCoverBase
        eventTemplate.eventPictureLists = eventTemplate.eventPictureListsBase
        console.log(eventTemplate)
        // upload cover


        try {
            let pictureFile = eventTemplate.eventPictureCoverBase;
            let pictureFiles = eventTemplate.eventPictureListsBase;
            let dateobj = new Date();
            let fileName = pictureFile.name + "_" + dateobj.toISOString();
            let storage = firebase.storage();
            let storageRef = storage.ref("/eventPicture");
            let setupFile = storageRef.child(fileName);
            setupFile.putString(pictureFile.url, 'data_url', { contentType: 'image/jpeg' }).then(snapshot => {
                snapshot.ref.getDownloadURL().then(eventPictureCover => {
                    console.log(`Picture : `, eventPictureCover);
                    try {
                        let eventPictureList = [] 
                        for (let i = 0; i < pictureFiles.length; i++) {
                            pictureFile = pictureFiles[i];
                            dateobj = new Date();
                            fileName = pictureFile.name + "_" + dateobj.toISOString();
                            storage = firebase.storage();
                            storageRef = storage.ref();
                            setupFile = storageRef.child(fileName);
                            setupFile.putString(pictureFile.url, 'data_url', { contentType: 'image/jpeg' }).then(snapshot => {
                                snapshot.ref.getDownloadURL().then(eventPicture => {
                                    eventPictureList.push(eventPicture)
                                    if(eventPictureList.length === pictureFiles.length){
                                        console.log("-------- Event Picture List -----------")
                                        console.log(`Picture list : `, eventPictureList);
                                        eventTemplate.eventPictureCover = eventPictureCover
                                        eventTemplate.eventPictureLists = eventPictureList
                                        
                                        axios.post(`${process.env.EVENT_SERVICE}/event`, eventTemplate, {
                                            headers: {
                                                'Authorization': `Bearer ${localStorage.getItem('jwtToken') || ''}`
                                            }
                                        }).then(eventTemplate => {
                                            console.log(eventTemplate.data)
                                            this._vm.$swal({
                                                type: "success",
                                                title: "Upload Event success!!",
                                                text: `Upload Event success!!`
                                            });
                                           
                                        }).catch(error => {
                                            this._vm.$swal({
                                                type: "error",
                                                title: "Fail to Create Event!!!",
                                                text: `Fail to Create Event!!!`
                                            });

                                        }).finally(()=>{
                                            loader.hide()
                                        });
                                    }

                                });
                            });
                        }
                      

                    } catch (err) {
                        loader.hide()
                        console.log(err);
                    }

                });
            });
        } catch (err) {
            loader.hide()
            console.log(err);
        }






    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
