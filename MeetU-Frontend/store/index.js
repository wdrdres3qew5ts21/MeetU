import Vuex from 'vuex'
import firebase, { auth, GoogleProvider, FacebookProvider, TwitterProvider } from '@/plugins/fireinit.js'

const createStore = () => {
  return new Vuex.Store({
    state: {
      user: null
    },
    getters: {
      activeUser: (state, getters) => {
        return state.user
      },
      getUser(state) {
        return state
      }
    },
    mutations: {
      setUser (state, payload) {
        state.user = payload
      }
    },
    actions: {
      autoSignIn ({ commit }, payload) {
        commit('setUser', payload)
      },
      signInWithGoogle ({ commit }) {
          console.log('fuq')
          auth.signInWithRedirect(GoogleProvider)
          .then((result) => {
            console.log(result)
          })
          .catch((err) => {
            console.log(err)
          })
      },
      signInWithFacebook ({ commit }) {
        console.log(FacebookProvider)
        auth.signInWithRedirect(FacebookProvider)
          .then((result) => {
            console.log(result)
          })
          .catch((err) => {
            console.log(err)
          })
      },
      signInWithTwitter ({ commit }) {
        console.log(TwitterProvider)
        auth.signInWithRedirect(TwitterProvider)
          .then((result) => {
            console.log(result)
          })
          .catch((err) => {
            console.log(err)
          })
      },
      signOut ({ commit }) {
        auth.signOut().then(() => {
          commit('setUser', null)
        }).catch(err => console.log(error))
      }
    }
  })
}

export default createStore
