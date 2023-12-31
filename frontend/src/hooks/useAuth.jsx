import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

import axios from "axios";
import useAlert from "../hooks/useAlert";


const useAuth = () =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);
    const [token, setToken] = useState("");
    const [isLoading, setIsLoading] = useState(false);
  
  const navigate = useNavigate()
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      
        if (username && password) {
          setIsLoading(true);
          try {


            //use alert
              useAlert({
                type: "error",
                title: errors,
                text: "Verifique sus datos",
              });

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
            text: "Complete los campos ",
          });
        }
      };
     
      return{
  
        username,
        setUsername,
        password,
        setPassword,
        errors,
        token,
        handleSubmit,
        isLoading,
  
  
      }
  
  
};


export default useAuth