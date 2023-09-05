import "./Register.css";
import { useState } from "react";
import axios from "axios";
import useRegister from "../../hooks/useRegister";

function Register() {

  const  {
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
  } = useRegister()

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
              
              <input type="text" onChange={(e) => setFullName(e.target.value)}

                value={fullName}
              />
            </div>
            {/* input de empresa  */}
            <div className="register_input">
              <label>Empresa</label>
              <input type="text" onChange={(e) => setBusisnessName(e.target.value)} 
              value={businessName}/>
            </div>
          </div>
          <div className="div_container_input">
            {/* input de teléfono  */}
            <div className="register_input">
              <label>Teléfono</label>
              <input
                type="number"
                autoComplete="false"
                onChange={(e) => setPhone(e.target.value)
                }
                value={phone}
              />
            </div>
            {/* input de email  */}
            <div className="register_input">
              <label>Email</label>

              <input
                type="email"
                autoComplete="false"
                onChange={(e) => setUserName(e.target.value)}
                value={username}
              />
            </div>
          </div>
          <div className="div_container_input">
            {/* input password */}
            <div className="register_input">
              <label>Contraseña</label>
              <input
                autoComplete="false"
                type="password"
                onChange={(e) => setPassword(e.target.value)}
                value={password}
              />
            </div>
            {/* input password */}
            <div className="register_input">
              <label>Confirmar contraseña</label>
              <input
                type="password"
                onChange={(e) => setConfirmPassword(e.target.value)}
                value={confirmPassword}
              />
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
