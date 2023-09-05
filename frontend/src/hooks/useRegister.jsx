import { useState, useEffect } from "react";

import { useNavigate } from "react-router-dom";
import { request } from "../config/helpers/axios_helper";
function useRegister() {
  const navigate = useNavigate();

  const [fullName, setFullName] = useState("");
  const [businessName, setBusisnessName] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  /* array de errores */
  const [errores, setErrores] = useState([]);
  /* controla el submit */

  /* test de validación de email  */

  let regex = new RegExp(
    "([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|\"([]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|[[\t -Z^-~]*])"
  );

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      console.log("Las contraseñas no coinciden ");

      return;
    }

    if (![fullName, businessName, phone, username, password, confirmPassword].includes("")) {
      if (
        regex.test(email) &&
        email !== user.email &&
        password !== user.password
      ) {
        try {
          const response = await request("POST", "/users/register", {
            fullName,
            password,
            username,
            businessName,
            phone,
            confirmPassword


          });
console.log(response)
          if (response.status === 200) {
            console.log(response.data);
          } else {
            console.log(response.status);
          }
        } catch (error) {
          console.log(error);
        }
      } else {
        setErrores(["Este usuario ya existe"]);
      }
    } else {
      setErrores(["Todos los campos son obligatorios"]);
    }
  };

  return {
    setConfirmPassword,
    confirmPassword,
    setPhone,
    setUserName,
    setFullName,
    setBusisnessName,
    setPassword,
    username,
    password,
    fullName,
    businessName,
    phone,
    setErrores,
    errores,
    handleSubmit,
  };
}

export default useRegister;
