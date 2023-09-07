import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { request } from "../config/helpers/axios_helper.jsx";

const useRecovery = () => {
  const [username, setUsername] = useState("");
  const [tokenRecovery,setTokenRecovery]=useState('')
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log(username);

    try {
      // Realiza la solicitud POST a tu endpoint
      const response = await request("POST", "/password/recovery", {
      email:  `${username}`
      });

      // Maneja la respuesta aquí, por ejemplo, puedes mostrar un mensaje de éxito o redireccionar al usuario.
      console.log("Recupero de contraseña ", response.data.token);
      setTokenRecovery(response.data.token)
      navigate('/recoverPassword/messageEmailconfirm')
    } catch (error) {
      // Maneja los errores aquí, por ejemplo, muestra un mensaje de error al usuario.
      console.error("Usuario no válido", error);
    }
  };

  return {
    handleSubmit,
    setUsername,
    username,
  };
};

export default useRecovery;
