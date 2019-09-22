export function isLogin() {
    let isLogin = false;
    if (localStorage.getItem("jwtToken")) {
        isLogin = true;
    }
    return isLogin
}