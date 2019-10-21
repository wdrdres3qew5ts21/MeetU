console.log('Authen Service Worker !')

importScripts('https://www.gstatic.com/firebasejs/7.2.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.2.1/firebase-auth.js');

const firebaseConfig = {
  apiKey: "AIzaSyDj6tin4BDkUrwgtaKyjEMncf60p7GkLn0",
  authDomain: "meetu-69b29.firebaseapp.com",
  databaseURL: "https://meetu-69b29.firebaseio.com",
  projectId: "meetu-69b29",
  storageBucket: "meetu-69b29.appspot.com",
  messagingSenderId: "1058128161659",
  appId: "1:1058128161659:web:6e143e16242ba4c2"
};


if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig)
}

var authen = firebase.auth();


authen.onAuthStateChanged(user => {
  authen.getRedirectResult().then((result) => {
    console.log("service worker authen")
    console.log("Auth status change")
    if (result.credential) {
      // This gives you a Facebook Access Token. You can use it to access the Facebook API.
      var token = result.credential.accessToken;
      var isNewUser = result.additionalUserInfo.isNewUser
      console.log(result)
      if (isNewUser) {
        console.log("---------- First Time sign up ----------")
       // axios.post(`${process.env.USER_SERVICE}/user`, result.user)
      }

    }
  }).catch(function (error) {

  });
  console.log("state change")
  authen.currentUser.getIdToken(/* forceRefresh */ true)
    .then((jwtToken) => {
      console.log("authen via service worker")
      // Send token to your backend via HTTPS
      // window.localStorage.setItem("jwtToken", jwtToken)
      console.log("current user !")
      // Set Vuex State After Success login
      // Request Permission Will work only user login Success

    }).catch((error) => {
      // login failed
      console.log(error)
    })
})