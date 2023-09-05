import axios from 'axios';

axios.defaults.baseURL = "http://vps-3471374-x.dattaweb.com:8080/api/v1"
axios.defaults.headers.post["Content-type"] = "application/json"

export const request = (method, url, data) => {
    return axios({
        method,
        url,
        data
    })
}