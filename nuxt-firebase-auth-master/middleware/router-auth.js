export default function ({ store, redirect, route }) {
 // isHaveJwtToken() === true && route.name == 'login' ? redirect('/userProfile') : ''
  store.getters.getUser.uid != null && route.name == 'login' ? redirect('/userProfile') : ''
  store.getters.getUser.uid == null && isAdminRoute(route) ? redirect('/login') : ''

}

function isUserProfileRoute(route) {
  if (route.matched.some(record => record.path == '/userProfile')) {
    return true
  }
}


function isAdminRoute(route) {
  if (route.matched.some(record => record.path == '/admin')) {
    return true
  }
}

function isJwtTokenExpired() {
  let jwtToken = jwtDecode(localStorage.getItem("jwtToken"))
  let tokenExpireDate = jwtToken.exp

  if (Date.now() >= tokenExpireDate * 1000) {
    console.log("Invalid Expired !")
    localStorage.clear("jwtToken")
    store.dispatch('logoutFromVuex')
    return false
  } else {
    console.log("Valid")
    return true
  }
}

function isHaveJwtToken() {
  if (localStorage.getItem("jwtToken") == null || localStorage.getItem("jwtToken") == undefined) {
    return {
      status: false,
      errorMessage: "Please Login before use application !"
    }
  } else {
    return isJwtTokenExpired()
  }
}

