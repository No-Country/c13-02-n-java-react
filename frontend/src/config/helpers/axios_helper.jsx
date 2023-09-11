import axios from 'axios';

// axios.defaults.baseURL = "http://localhost:8080/api/v1"
 axios.defaults.baseURL = "https://vps-3471374-x.dattaweb.com/api/v1"
axios.defaults.headers.get["Authorization"] = `Bearer ${localStorage.getItem("token")  }`
axios.defaults.headers.post["Content-type"] = "application/json"

export const request = async (method, url, data) => {

  

    try {
        return await axios({
            method,
            url,
            data,
        });

    } catch (error) {
        console.error('Error', error);
        throw new Error(error.response.data.message || 'Error desconocido')
    }
}