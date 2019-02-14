import { auth } from '@/plugins/fireinit.js'
console.log('eiei')


export default (context) => {
  const { store } = context

  return new Promise((resolve, reject) => {
    auth.onAuthStateChanged(user => {
      // auth.currentUser.getIdToken(/* forceRefresh */ true)
      //   .then((idToken) => {
      //   // Send token to your backend via HTTPS
      //   // ...
      //     console.log(idToken)
      //   }).catch((error) => {
      //     console.log(error)
      //   })
      console.log('state change login fireauth.js')
      store.commit('setUser', user)
      // console.log(user)
      resolve()
    })
  })
}
