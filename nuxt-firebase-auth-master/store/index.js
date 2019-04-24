import Vue from "vue";
import Vuex from "vuex";
import authentication from "./modules/authentication";
import location from "./modules/location";

Vue.use(Vuex);

const store = () => {
  return new Vuex.Store({
    state: {},
    modules: {
      authentication,
      location
    }
  });
};

export default store;
