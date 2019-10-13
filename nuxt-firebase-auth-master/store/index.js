import Vue from "vue";
import Vuex from "vuex";
import location from "./modules/location";
import fetchEvent from './modules/fetchEvent'
import category from './modules/category'
import user from './modules/user'
import createEvent from './modules/createEvent'
import createCommunity from './modules/createCommunity'

Vue.use(Vuex);

const store = () => {
  return new Vuex.Store({
    state: {},
    modules: {
      user,
      location,
      category,
      fetchEvent,
      createEvent,
      createCommunity
    }
  });
};

export default store;
