import { auth } from '@/plugins/fireinit.js'
import axios from "axios"

export default (context) => {
  const { store } = context

  auth.onAuthStateChanged(user => {
    console.log("state change")
    console.log(user)
    auth.currentUser.getIdToken(/* forceRefresh */ true)
      .then((jwtToken) => {
        // Send token to your backend via HTTPS
        localStorage.setItem("jwtToken", jwtToken)
        console.log("current user !")
        console.log(jwtToken)
        axios.post(`${process.env.USER_SERVICE}/user/jwt`, {
          token: jwtToken
        }).then(res => {
          console.log(res)
        })
      }).catch((error) => {
        console.log(error)
      })

    store.commit('setUser', {
      dispalyName: user.displayName,
      email: user.email,
      emailVerified: user.emailVerified,
      isAnonymous: user.isAnonymous,
      photoURL: user.photoURL,
      uid: user.uid
    })
  })
}