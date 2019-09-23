import Vue from "vue";
import Vuex from "vuex";
import location from "./modules/location";
import fetchEvent from './modules/fetchEvent'
import mockLogin from './modules/mockLogin'
import category from './modules/category'
import user from './modules/user'

Vue.use(Vuex);

const store = () => {
  return new Vuex.Store({
    state: {},
    modules: {
      location,
      fetchEvent,
      user,
      category
    }
  });
};

export default store;
