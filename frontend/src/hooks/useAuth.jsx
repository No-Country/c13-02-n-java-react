import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

import axios from "axios";
import { request } from "../config/helpers/axios_helper.jsx";
import useAlert from "./useAlert.jsx";

const useAuth = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);
  const [token, setToken] = useState("");

  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (username && password) {
      setIsLoading(true);
      try {
        const response = await request(
          "POST",
          `/login`,
          JSON.stringify({ username, password }),
          { headers: { "Content-Type": "application/json" } ,
          'Authorization': `Bearer ${token}`
        }
        );
        console.log(response.status);
        if (response.status === 200) {
          sessionStorage.setItem("token", response.data.token);
          sessionStorage.setItem("username", username);
          setIsLoading(false);
          navigate("/dashboard");
        } else {
          setIsLoading(false);
          //use alert
          useAlert({
            type: "error",
            title: errors,
            text: "Verifique sus datos",
          });
        }
      } catch (error) {
        setIsLoading(false);

        //use alert
        useAlert({
          type: "error",
          title: "Error de ingreso",
          text: "Usuario o Contraseña inválidos ",
        });
      }
    } else {
      setErrors(["Complete los campos"]);
      //use alert
      useAlert({
        type: "warning",
        title: "Campos obligatorios",
        text: "Parece que faltan datos, ¡complétalos para continuar!",
      });
    }
  };

  return {
    username,
    setUsername,
    password,
    setPassword,
    errors,
    token,
    handleSubmit,
    isLoading,
  };
};

export default useAuth;
