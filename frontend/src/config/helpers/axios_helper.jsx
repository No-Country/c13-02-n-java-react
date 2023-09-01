import axios from 'axios';

axios.defaults.baseURL = "http://localhost:8080/api/v1"
axios.defaults.headers.post["Content-type"] = "application/json"

export const request = (method, url, data) => {
    return axios({
        method,
        url,
        data
    })
}