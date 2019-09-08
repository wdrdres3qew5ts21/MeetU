import * as firebase from "firebase/app";
import 'firebase/messaging';
import 'firebase/auth';
import axios from "axios"

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
    console.log("Fuq client Side !!!")
    authen = firebase.auth()
    messaging = firebase.messaging()

    authen.onAuthStateChanged(user => {
      console.log("state change")
      console.log(user)
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
            uid: user.uid
          })
          // Request Permission Will work only user login Success

          messaging.usePublicVapidKey("BBE3arRFCbWg0FTbJ-xh0R__ngAdYnjdPtv3PFTyCJRy1ceuztLgcDrPVkVpNzSwn7xsOL5tFwW6dQzhcwoBEqM");
          // Request Permission of Notifications
          messaging.requestPermission().then(() => {
            console.log('Notification permission granted.');
            // Get Token
            messaging.getToken().then((notificationToken) => {
              let notificationBody = {
                notificationToken,
                uid: store.getters.getUser.uid,
              }
              axios.post(`${process.env.USER_SERVICE}/notification/token`, notificationBody)
              console.log(notificationToken)
            }).catch(err => {
              console.log(err)
            })
          }).catch((err) => {
            console.log('Unable to get permission to notify.', err);
          });

          // Do something after OAuth Redirect login success
          authen.getRedirectResult().then((result) => {
            if (result.credential) {
              console.log("fuq finallyyyyyyyyyyyyyyyy")
              // This gives you a Facebook Access Token. You can use it to access the Facebook API.
              let token = result.credential.accessToken;
              console.log(result)
              
            }
            // The signed-in user info.
            let user = result.user;
          }).catch(function (error) {
  
          });

        }).catch((error) => {
          // login failed
          console.log(error)
        })
    })
  }
}




export const FacebookProvider = new firebase.auth.FacebookAuthProvider()
export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const auth = firebase.auth()
// export default firebase
