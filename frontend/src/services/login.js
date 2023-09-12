import axios from "axios";

const baseUrl = "https://vps-3471374-x.dattaweb.com/api/v1";

const login = async credentials => {
    const {data} = await axios.post(`${baseUrl}/login`, credentials);
    return data;
}

export default {login};