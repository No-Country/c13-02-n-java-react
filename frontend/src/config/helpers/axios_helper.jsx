import axios from 'axios';

// axios.defaults.baseURL = "https://vps-3471374-x.dattaweb.com/api/v1"
axios.defaults.baseURL = "http://localhost:8080/api/v1"
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`
axios.defaults.headers.post["Content-type"] = "application/json"

export const request = (method, url, data) => {
    return axios({
        method,
        url,
        data
    })
}