import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
