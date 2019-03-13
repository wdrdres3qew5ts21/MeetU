// global.XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest
import firebase from 'firebase'
import 'firebase/auth'
import 'firebase/database'

var config = {
  apiKey: 'AIzaSyDj6tin4BDkUrwgtaKyjEMncf60p7GkLn0',
  authDomain: 'meetu-69b29.firebaseapp.com',
  databaseURL: 'https://meetu-69b29.firebaseio.com',
  projectId: 'meetu-69b29',
  storageBucket: 'meetu-69b29.appspot.com',
  messagingSenderId: '1058128161659'
}

if (!firebase.apps.length) firebase.initializeApp(config)

export const FacebookProvider = new firebase.auth.FacebookAuthProvider()
export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const TwitterProvider = new firebase.auth.TwitterAuthProvider()
export const auth = firebase.auth()
export const DB = firebase.database()
export default firebase
