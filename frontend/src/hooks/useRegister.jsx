import { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import {request} from "../config/helpers/axios_helper.jsx";

const useRegister = () => {
  const [fullName, setFullName] = useState('');
  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [phone, setPhone] = useState('');
  const [businessName, setBusinessName] = useState('');
  const navigate  = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validaciones
    if(!fullName || !username || !password || !confirmPassword || !phone || !businessName) return alert('Por favor, completa todos los campos');
    if(password.length < 8) return alert('La contraseña debe tener al menos 8 caracteres');
    if(phone.length < 10) return alert('El teléfono debe tener al menos 10 caracteres');
    if(businessName.length < 3) return alert('El nombre de la empresa debe tener al menos 3 caracteres');
    if(username.length < 3) return alert('El nombre de usuario debe tener al menos 3 caracteres');
    // regex para validar que el teléfono sea un número
    const regex = /^[0-9]+$/;
    if(!regex.test(phone)) return alert('El teléfono debe ser un número');
    const regexUsername = /^\S+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!regexUsername.test(username)) return alert('El nombre de usuario no puede tener espacios o debe ser un email válido');
    // regex para validar que password tenga al menos una mayúscula, una minúscula y un número
    const regex4 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if(!regex4.test(password)) return alert('La contraseña debe tener al menos una mayúscula, una minúscula y un número');
    if (password !== confirmPassword) return alert('Las contraseñas no coinciden');

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
      // Realiza la solicitud POST a tu endpoint
      // const response = await axios.post('http://localhost:8080/api/v1/users/register', userData);
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
    fullName,
    setFullName,
    username,
    setUserName,
    password,
    setPassword,
    confirmPassword,
    setConfirmPassword,
    phone,
    setPhone,
    businessName,
    setBusinessName,
    handleSubmit,
  };
};

export default useRegister;