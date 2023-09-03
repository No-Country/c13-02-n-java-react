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

    const [isLoading,setIsLoading]= useState(false)

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (username && password) {
            setIsLoading(true)
            try {
                const response = await request("POST", `/login`,  JSON.stringify({username, password}), { headers: { "Content-Type": "application/json" } });
              
                if (response.status === 200) {
                    localStorage.setItem("token", response.data.token);
                    localStorage.setItem("username", username);
                    setIsLoading(false)
                    navigate("/dashboard");
                } else {
                    setIsLoading(false)
                    setErrors(["Verifique sus datos"]);
                }
            } catch (error) {
                setIsLoading(false)
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
        handleSubmit,
        isLoading
    };
};

export default useAuth;