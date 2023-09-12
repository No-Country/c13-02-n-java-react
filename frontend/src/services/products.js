import axios from "axios";

const baseUrl = "https://vps-3471374-x.dattaweb.com/api/v1";

let token = null;

const setToken = (newToken) => {
    token = `Bearer ${newToken}`;
    console.log(token);
};

// Todo: Todo lo relacionado con el login
const getAllUsers = () => {
    const config = {
        headers: {Authorization: token},
    }

    const request = axios.get(`${baseUrl}/users`, config);
    return request.then((response) => {
        const data = response.data;
        const usersId = data.content.map((user) => user.id);
        return usersId;
    }).catch((error) => {
        console.log(error);
        throw error;
    });
};
const getUser = (endpoint) => {
    const config = {
        headers: {Authorization: token},
    }

    const request = axios.get(`${baseUrl}/${endpoint}`, config);
    return request.then((response) => response.data);
};


// Todo: Controladoes para los endpoints de Productos Categorias y más...
const getAll = (endpoint) => {
    const config = {
        headers: {Authorization: token},
    }
    const request = axios.get(`${baseUrl}/${endpoint}`, config);
    return request.then((response) => response.data);
}


const getId = (endpoint, id) => {
    const config = {
        headers: {Authorization: token},
    }
    const request = axios.get(`${baseUrl}/${endpoint}/${id}`, config);
    return request.then((response) => response.data);
}

const create = (endpoint, newObject) => {
    const config = {
        headers: {Authorization: token},
    }
    const request = axios.post(`${baseUrl}/${endpoint}`, newObject, config);
    return request.then((response) => response.data);
}

const update = (id, newObject) => {
    const config = {
        headers: {Authorization: token},
    }
    const request = axios.put(`${baseUrl}/${id}`, newObject, config);
    return request.then((response) => response.data);
}

const remove = (id) => {
    const request = axios.delete(`${baseUrl}/${id}`);
    return request.then((response) => response.data);
}

export default {getAllUsers, getUser, getAll, getId, create, update, remove, setToken};
