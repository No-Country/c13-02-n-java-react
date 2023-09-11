import axios from "axios";

// axios.defaults.baseURL = "http://localhost:8080/api/v1"
axios.defaults.baseURL = "https://vps-3471374-x.dattaweb.com/api/v1";
/*  axios.defaults.headers.common["Authorization"] = `Bearer ${sessionStorage.getItem("token")   }`  */
const config = {
  headers: {
    Authorization: `Bearer ${sessionStorage.getItem("token")}`,
  }
}

axios.defaults.headers.post["Content-type"] = "application/json";

export const request = async (method, url, data) => {
  try {
    return await axios({
      method,
      url,
      data,
    });
  } catch (error) {
    console.error("Error", error);
    throw new Error(error.response.data.message || "Error desconocido");
  }
};
