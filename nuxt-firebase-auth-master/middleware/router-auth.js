export default function ({ store, redirect, route }) {
  store.getters.getUser.uid != null && route.name == 'login' ? redirect('/userProfile') : ''
  store.getters.getUser.uid == null && isAdminRoute(route) ? redirect('/login') : ''

}

function isUserProfileRoute (route) {
  if (route.matched.some(record => record.path == '/userProfile')) {
    return true
  }
}


function isAdminRoute (route) {
  if (route.matched.some(record => record.path == '/admin')) {
    return true
  }
}
