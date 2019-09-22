import axios from "axios"
export default (context) => {
    if (process.client ===true) {
        axios.interceptors.request.use(config => {
            config.headers.common['Authorization'] = `Bearer ${localStorage.getItem('jwtToken')}`;
            return config
        })
    }
}

