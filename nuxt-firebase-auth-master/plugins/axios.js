import axios from "axios"
axios.interceptors.request.use(config => {
    config.headers.common['Authorization'] = `Bearer ${localStorage.getItem('jwtToken')}`;
    return config
})