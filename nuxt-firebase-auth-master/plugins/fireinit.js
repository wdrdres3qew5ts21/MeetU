import * as firebase from "firebase/app";
import 'firebase/messaging';
import 'firebase/auth';
import 'firebase/storage'
import axios from "axios"
import jwtDecode from "jwt-decode"

const firebaseConfig = {
  apiKey: "AIzaSyDj6tin4BDkUrwgtaKyjEMncf60p7GkLn0",
  authDomain: "meetu-69b29.firebaseapp.com",
  databaseURL: "https://meetu-69b29.firebaseio.com",
  projectId: "meetu-69b29",
  storageBucket: "meetu-69b29.appspot.com",
  messagingSenderId: "1058128161659",
  appId: "1:1058128161659:web:6e143e16242ba4c2"
};

let messaging = null;
let authen = null;

if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig)
}

export default (context) => {
  const { store } = context
  if (process.client) {
    console.log("Client Side !!!")
    authen = firebase.auth()
    if (firebase.messaging.isSupported()){
      messaging = firebase.messaging()
    }
  }

  try {
    let userJwt = jwtDecode(localStorage.getItem("jwtToken"))
    console.log(userJwt)
    if (userJwt) {
      store.commit('setUser', {
        displayName: userJwt.name,
        email: userJwt.email,
        emailVerified: userJwt.email_verified,
        photoURL: userJwt.picture,
        uid: userJwt.user_id,
        jwtToken: localStorage.getItem("jwtToken")
      })
    }
  } catch (err) {
    console.log(err)
  }



  authen.onAuthStateChanged(user => {
    authen.getRedirectResult().then((result) => {
      console.log("Auth status change")
      if (result.credential) {
        // This gives you a Facebook Access Token. You can use it to access the Facebook API.
        let token = result.credential.accessToken;
        let isNewUser = result.additionalUserInfo.isNewUser
        console.log(result)
        if (isNewUser) {
          console.log("---------- First Time sign up ----------")
          axios.post(`${process.env.USER_SERVICE}/user`, result.user)
        }

      }
    }).catch(function (error) {

    });
    console.log("state change")
    authen.currentUser.getIdToken(/* forceRefresh */ true)
      .then((jwtToken) => {
        // Send token to your backend via HTTPS
        localStorage.setItem("jwtToken", jwtToken)
        console.log("current user !")
        // Set Vuex State After Success login
        store.commit('setUser', {
          displayName: user.displayName,
          email: user.email,
          emailVerified: user.emailVerified,
          isAnonymous: user.isAnonymous,
          photoURL: user.photoURL,
          uid: user.uid,
          jwtToken: jwtToken
        })
        // Request Permission Will work only user login Success

        messaging.usePublicVapidKey("BJsK20EGvD7TRip8YI_DP-sxYxeNK65jwuK6v_Mek8birN_doChyesgPAmAuXMiu81TJOkejoypqvz9Pl7TWhe0");
        // Request Permission of Notifications
        messaging.requestPermission().then(() => {
          console.log('Notification permission granted.');
          // Get Token
          messaging.getToken().then((notificationToken) => {
            let notificationBody = {
              notificationToken,
              uid: store.getters.getUser.uid,
            }
            axios.post(`${process.env.EVENT_SERVICE}/notification/token`, notificationBody)
            console.log(notificationToken)
          }).catch(err => {
            console.log(err)
          })
        }).catch((err) => {
          console.log('Unable to get permission to notify.', err);
        });

      }).catch((error) => {
        // login failed
        console.log(error)
      })
  })
}


export const FacebookProvider = new firebase.auth.FacebookAuthProvider()
export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const auth = firebase.auth()
// export default firebase
