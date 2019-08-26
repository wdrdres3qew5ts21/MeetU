// global.XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest
import axios from "axios"
import * as firebase from "firebase";
import 'firebase/messaging';
// import 'firebase/auth'
// import 'firebase/database'

const firebaseConfig = {
  apiKey: "AIzaSyDj6tin4BDkUrwgtaKyjEMncf60p7GkLn0",
  authDomain: "meetu-69b29.firebaseapp.com",
  databaseURL: "https://meetu-69b29.firebaseio.com",
  projectId: "meetu-69b29",
  storageBucket: "meetu-69b29.appspot.com",
  messagingSenderId: "1058128161659",
  appId: "1:1058128161659:web:6e143e16242ba4c2"
};

if (!firebase.apps.length){
  firebase.initializeApp(firebaseConfig)

  const messaging = firebase.messaging();
  messaging.usePublicVapidKey("BBE3arRFCbWg0FTbJ-xh0R__ngAdYnjdPtv3PFTyCJRy1ceuztLgcDrPVkVpNzSwn7xsOL5tFwW6dQzhcwoBEqM");
  // Request Permission of Notifications
  messaging.requestPermission().then(() => {
    console.log('Notification permission granted.');
    // Get Token
    messaging.getToken().then((token) => {
      let notificationBody = {
        token: token,
        userId: "userId",
        username: "username"
      }
      axios.post(`${process.env.USER_SERVICE}/notification/token`, notificationBody)
      console.log(token)
    }).catch(err =>{
      console.log(err)
    })

  }).catch((err) => {
    console.log('Unable to get permission to notify.', err);
  });

} 

export const FacebookProvider = new firebase.auth.FacebookAuthProvider()
export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const TwitterProvider = new firebase.auth.TwitterAuthProvider()
export const auth = firebase.auth()
export const DB = firebase.database()
export default firebase
