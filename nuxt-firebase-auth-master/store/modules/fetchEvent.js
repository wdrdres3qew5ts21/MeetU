import axios from 'axios'
const state = () => ({

});

const getters = {
  getTemplate: function(state) {
      return null
  }
};

const mutations = {
    setTemplate: function(state, updatedLocation) {
        
  }
};

const actions = {
  getEventByTags: async function({ commit }, eventTags) {
      let concentPerPage = 3;
      let eventList = await axios.get(`${process.env.EVENT_SERVICE}/events?isRecently=true
      &contentPerPage=${concentPerPage}
      &eventTags=${eventTags}`);
      eventList = eventList.data
      return eventList
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
