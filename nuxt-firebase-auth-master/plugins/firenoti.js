// import { messaging } from '@/plugins/fireinit.js'
// import axios from "axios"

// export default (context) => {
//     messaging.usePublicVapidKey("BBE3arRFCbWg0FTbJ-xh0R__ngAdYnjdPtv3PFTyCJRy1ceuztLgcDrPVkVpNzSwn7xsOL5tFwW6dQzhcwoBEqM");
//     // Request Permission of Notifications
//     messaging.requestPermission().then(() => {
//         console.log('Notification permission granted.');
//         // Get Token
//         messaging.getToken().then((notificationToken) => {
//             let notificationBody = {
//                 notificationToken: notificationToken,
//                 userId: "sss",
//                 username: "username"
//             }
//             axios.post(`${process.env.USER_SERVICE}/notification/token`, notificationBody)
//             console.log(token)
//         }).catch(err => {
//             console.log(err)
//         })
//     }).catch((err) => {
//         console.log('Unable to get permission to notify.', err);
//     });
// }


