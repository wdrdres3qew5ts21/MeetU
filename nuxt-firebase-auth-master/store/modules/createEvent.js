import * as firebase from "firebase/app";
import "firebase/storage";
import axios from "axios"
const state = () => ({
    eventTemplate: {
        eventName: '',
        eventDetail: '',
        eventTags: [],
        exp: 0.0,
        badge: {
            badgeId: "",
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
        state.eventTemplate.organize = eventTemplate.organize;
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
        console.log(eventTemplate)
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
    },
    saveEventAndUpload({commit, getters, vm}){
       // let loader = this._vm.$loading.show()
        console.log("Vuex Upload Event Template")
        let eventTemplate = getters.getEventTemplate
        console.log(eventTemplate)
        // upload cover
        let pictureFile = eventTemplate.eventPictureCoverBase;
        let dateobj = new Date();
        let fileName = pictureFile.name + "_" + dateobj.toISOString();
        let storage = firebase.storage();
        let storageRef = storage.ref();
        let setupFile = storageRef.child(fileName);
        // try {
        //   setupFile.putString(pictureFile.url, 'data_url', { contentType: 'image/jpeg' }).then(snapshot => {
        //     snapshot.ref.getDownloadURL().then(downloadURL => {
        //       console.log(`Picture : `, downloadURL);
        //         axios.post(`${process.env.EVENT_SERVICE}/badge`,badgeRequest,{
        //             headers: {
        //                 'Authorization': `Bearer ${localStorage.getItem('jwtToken') || ''}`
        //             }
        //             }).then(badgeResponse=>{
        //             console.log(badgeResponse.data)
        //             loader.hide()
        //             this._vm.$swal({
        //                 type: "success",
        //                 title: "Upload Event success!!",
        //                 text: `Upload Event success!!`
        //             });
        //             }).catch(error => {
        //             this._vm.$swal({
        //                 type: "error",
        //                 title: "Fail to Create Event!!!",
        //                 text: `Fail to Create Event!!!`
        //             });
        //             loader.hide()
        //             setupFile.delete().then(()=> {
        //                 // File deleted successfully
        //                 console.log("delete file success because upload fail")
        //             }).catch((error)=> {
        //                 // Uh-oh, an error occurred!
        //             });
        //         });
        //     });
        //   });
        // } catch (err) {
        //   console.log(err);
        // }

    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
