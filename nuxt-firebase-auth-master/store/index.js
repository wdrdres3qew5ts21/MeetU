import Vue from "vue";
import Vuex from "vuex";
import authentication from "./modules/authentication";
import location from "./modules/location";
import fetchEvent from './modules/fetchEvent'

Vue.use(Vuex);

const store = () => {
  return new Vuex.Store({
    state: {},
    modules: {
      authentication,
      location,
      fetchEvent
    }
  });
};

export default store;
