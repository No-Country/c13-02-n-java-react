import { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import {request} from "../config/helpers/axios_helper.jsx";
import {useFormData} from "../config/models/formData.js";
import {validateRegisterUtils} from "../config/utils/validationUtils.js";

const useRegister = () => {
  const {formData, handleChange} = useFormData();
  const navigate  = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const {fullName, username, password, confirmPassword, phone, businessName} = formData;

    // Validaciones
    const validationErrors = validateRegisterUtils(formData);
    if (validationErrors.length > 0) return validationErrors.forEach((error) => alert(error));

    // Crea un objeto con los datos del usuario
    const userData = {
      fullName,
      username,
      password,
      confirmPassword,
      phone,
      businessName,
      role: {id: 1},
    };

    try {
      // Realiza la solicitud POST a tu endpoint
      const response = await request("POST", "/users/register", {
        ...userData
      })

      // Maneja la respuesta aquí, por ejemplo, puedes mostrar un mensaje de éxito o redireccionar al usuario.
        console.log('Usuario registrado con éxito:', response.data);
        navigate("/login");
    } catch (error) {
      // Maneja los errores aquí, por ejemplo, muestra un mensaje de error al usuario.
      console.error('Error al registrar el usuario:', error);
    }
  };

  return {
    formData,
    handleChange,
    handleSubmit,
  };
};

export default useRegister;