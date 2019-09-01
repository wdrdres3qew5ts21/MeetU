export default function ({ store, redirect, route }) {
  store.state.user != null && route.name == 'login' ? redirect('/userProfile') : ''
  store.state.user == null && isAdminRoute(route) ? redirect('/login') : ''

}

function isUserProfileRoute (route) {
  if (route.matched.some(record => record.path == '/userProfile')) {
    console.log("fuq user profile")
    return true
  }
}


function isAdminRoute (route) {
  if (route.matched.some(record => record.path == '/admin')) {
    return true
  }
}
