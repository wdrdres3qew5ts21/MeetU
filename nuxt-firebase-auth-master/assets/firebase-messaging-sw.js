// [START initialize_firebase_in_sw]
// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/5.5.6/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.5.6/firebase-messaging.js');
// Initialize the Firebase app in the service worker by passing in the messagingSenderId.
firebase.initializeApp({
  'messagingSenderId': '1058128161659' // 4. Get Firebase Configuration
});

// Retrieve an instance of Firebase Messaging so that it can handle background messages.
const messaging = firebase.messaging();
// [END initialize_firebase_in_sw]