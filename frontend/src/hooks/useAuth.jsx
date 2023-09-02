import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {request} from "../config/helpers/axios_helper.jsx";

const useAuth = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);
    const [token, setToken] = useState("");
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (username && password) {
            try {
                const response = await request("POST", `/login`,  JSON.stringify({username, password}), { headers: { "Content-Type": "application/json" } });
                if (response.status === 200) {
                    localStorage.setItem("token", response.data.token);
                    localStorage.setItem("username", username);
                    navigate("/dashboard");
                } else {
                    setErrors(["Verifique sus datos"]);
                }
            } catch (error) {
                setErrors(["Ocurrió un error al iniciar sesión"]);
            }
        } else {
            setErrors(["Complete los campos"]);
        }
        setTimeout(() => {
            setErrors([]);
        }, 3000);
    };

    return {
        username,
        setUsername,
        password,
        setPassword,
        errors,
        token,
        handleSubmit
    };
};

export default useAuth;