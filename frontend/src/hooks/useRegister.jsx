import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useFormData } from "../config/models/formData.js";
import { validateRegisterUtils } from "../config/utils/validationUtils.js";
import useAlert from "./useAlert.jsx";
import createUser from "../services/products.js";

const useRegister = () => {
  const { formData, handleChange } = useFormData();
  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const {
      fullName,
      username,
      password,
      confirmPassword,
      phone,
      businessName,
    } = formData;

    // Validaciones
    const validationErrors = validateRegisterUtils(formData);
    if (validationErrors.length > 0)
      return validationErrors.forEach((error) =>
        useAlert({
          type: "error",
          title: "Error de registro",
          text: error,
        })
      );

    // Crea un objeto con los datos del usuario
    const userData = {
      fullName,
      username,
      password,
      confirmPassword,
      phone,
      businessName,
      role: { id: 1 },
    };

    try {
      setIsLoading(true);
      // Realiza la solicitud POST a tu endpoint
      createUser.create("users/register", {
        ...userData,
      })
      //const response = await request("POST", "/users/register", {
      //  ...userData,
      //});

      // Maneja la respuesta aquí, por ejemplo, puedes mostrar un mensaje de éxito o redireccionar al usuario.
      console.log("Usuario registrado con éxito:", response.data);
      setIsLoading(false);
      useAlert({
        type: "success",
        title: "Registro exitoso",
        text: response.data,
      });
      navigate("/login");
    } catch (error) {
      setIsLoading(false);
      // Maneja los errores aquí, por ejemplo, muestra un mensaje de error al usuario.
      console.error("Error al registrar el usuario:", error);

      useAlert({
        type: "error",
        title: "Error de registro",
        text: error.message,
      });
    }
  };

  return {
    formData,
    handleChange,
    handleSubmit,
    isLoading,
    setIsLoading,
  };
};

export default useRegister;
