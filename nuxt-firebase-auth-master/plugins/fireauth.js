import { auth } from '@/plugins/fireinit.js'

export default (context) => {
  const { store } = context

  // return new Promise((resolve, reject) => {
  //   auth.onAuthStateChanged(user => {
  //     // auth.currentUser.getIdToken(/* forceRefresh */ true)
  //     //   .then((idToken) => {
  //     //   // Send token to your backend via HTTPS
  //     //   // ...
  //     //     console.log(idToken)
  //     //   }).catch((error) => {
  //     //     console.log(error)
  //     //   })
  //     console.log('state change login fireauth.js')
  //     store.commit('setUser', user)
  //     // store.commit('setUser', {
  //     //   dispalyName: user.displayName,
  //     //   email: user.email,
  //     //   emailVerified: user.emailVerified,
  //     //   isAnonymous: user.isAnonymous,
  //     //   photoURL: user.photoURL,
  //     //   uid: user.uid
  //     // })
  //     console.log(user)
  //     resolve()
  //   })
  // })
}
