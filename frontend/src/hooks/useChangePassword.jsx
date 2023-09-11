import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { request } from "../config/helpers/axios_helper.jsx";

const useChangePassword = () => {
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [tokenRecovery, setTokenRecovery] = useState("");
  const navigate = useNavigate();

  

  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log(password);
    console.log(passwordConfirm);
    console.log(tokenRecovery);

    try {
      // Realiza la solicitud POST a tu endpoint
      const response = await request("POST", "/password/change", {
        password: `${password}`,
        confirmPassword: `${passwordConfirm}`,
        /* cortar tres primeros caracteres del token  */
        token: `${tokenRecovery.slice(3, 39)}`,
      });

      // Maneja la respuesta aquí, por ejemplo, puedes mostrar un mensaje de éxito o redireccionar al usuario.
      console.log(response.data);

      navigate("/login");
    } catch (error) {
      // Maneja los errores aquí, por ejemplo, muestra un mensaje de error al usuario.
      console.error(error.message);
    }
  };

  return {
    handleSubmit,
    setPassword,
    password,
    setPasswordConfirm,
    passwordConfirm,
    setTokenRecovery,
  };
};

export default useChangePassword;
