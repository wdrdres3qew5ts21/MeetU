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

if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig)
}

export default (context) => {
  if (process.client) {
    console.log("Fuq client Side !!!")
    messaging = firebase.messaging()
    messaging.usePublicVapidKey("BBE3arRFCbWg0FTbJ-xh0R__ngAdYnjdPtv3PFTyCJRy1ceuztLgcDrPVkVpNzSwn7xsOL5tFwW6dQzhcwoBEqM");
    // Request Permission of Notifications
    messaging.requestPermission().then(() => {
      console.log('Notification permission granted.');
      // Get Token
      messaging.getToken().then((notificationToken) => {
        let notificationBody = {
          notificationToken: notificationToken,
          userId: "sss",
          username: "username"
        }
        axios.post(`${process.env.USER_SERVICE}/notification/token`, notificationBody)
        console.log(token)
      }).catch(err => {
        console.log(err)
      })
    }).catch((err) => {
      console.log('Unable to get permission to notify.', err);
    });
  }
}




export const FacebookProvider = new firebase.auth.FacebookAuthProvider()
export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const auth = firebase.auth()
// export default firebase
