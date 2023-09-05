import "./Register.css";
import { useState } from "react";
import axios from "axios";
import useRegister from "../../hooks/useRegister";
import {request} from "../../config/helpers/axios_helper.jsx";
import {useFormData} from "../../config/models/formData.js";

function Register() {
  const {
    formData,
    handleChange,
    handleSubmit,
  } = useRegister();

  return (
    <section className="register">
      {/* cuadro de registro */}

      <div className="register_panel">
        <h1>Regístrate ahora</h1>

        {/* formulario de registro  */}
        <form onSubmit={(e) => handleSubmit(e)}>
          <div className="div_container_input">
            {/* input de usuario  */}
            <div className="register_input">
              <label>Usuario</label>
              <input type="text" name="fullName" onChange={handleChange} value={formData.fullName}/>
            </div>
            {/* input de empresa  */}
            <div className="register_input">
              <label>Empresa</label>
              <input type="text" name="businessName" onChange={handleChange} value={formData.businessName}/>
            </div>
          </div>
          <div className="div_container_input">
            {/* input de teléfono  */}
            <div className="register_input">
              <label>Teléfono</label>
              <input
                  type="tel" name="phone" autoComplete="false" onChange={handleChange} value={formData.phone}
              />
            </div>
            {/* input de email  */}
            <div className="register_input">
              <label>Email</label>
              <input type="email" name="username" autoComplete="false" onChange={handleChange}
                     value={formData.username}/>
            </div>
          </div>
          <div className="div_container_input">
            {/* input password */}
            <div className="register_input">
              <label>Contraseña</label>
              <input autoComplete="false" name="password" type="password" onChange={handleChange}
                     value={formData.password}/>
            </div>
            {/* input password */}
            <div className="register_input">
              <label>Confirmar contraseña</label>
              <input type="password" name="confirmPassword" onChange={handleChange} value={formData.confirmPassword}/>
            </div>
          </div>

          <div className="d-flex align-items-center ">
            <input type="checkbox" />
            <p className="w-50 ">
              Acepto los Términos y condiciones y autorizo el uso de mis datos
              de acuerdo a la Declaración de Privacidad.
            </p>
          </div>
          <input type="submit" className="--50" value={"Crear cuenta"} />
        </form>
      </div>
    </section>
  );
}

export default Register;
