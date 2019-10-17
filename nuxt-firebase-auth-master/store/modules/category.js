const state = () => ({
    category: [{
        categoryId: '',
        categoryName: '',
        categoryLabel: ''
    }]
});

const getters = {
    getCategory: function (state) {
        return state.category;
    }
};

const mutations = {
    setCategory: function (state, updatedCategory) {
        state.category = updatedCategory;
    }
};

const actions = {
    setCategory: function ({ commit }, updatedCategory) {
        console.log("action work for setlocation");
        commit("setCategory", updatedCategory);
    }
};

export default {
    state,
    mutations,
    actions,
    getters
};
