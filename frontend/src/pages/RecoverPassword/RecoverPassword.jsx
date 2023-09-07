
import { useState } from "react";
import { Link } from "react-router-dom";
import useRecovery from "../../hooks/useRecovery";
import Imagenes from "../../assets/imagenes";

function RecoverPassword() {

 

  const {handleSubmit,setUsername,username} = useRecovery()

  return (
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
        {/* formulario de login  */}
        <div className="login_image_logo">
          <img src={Imagenes.avatar} alt="Logo" /> 
        </div>
        <div className="text-center ">
          <h1>FIMA</h1>
          <p>Finance Manager</p>
        </div>

        <h2>Recuperar contraseña </h2>

        <p className="text-center ">Ingresa tu correo electrónico y te enviaremos un link para restablecerla.</p>

        {/*  {isLoading ? <SpinnerLoad/> : ''} */}
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
        

          <div className="login_input">
            {/* input de usuario  */}
            <div className="register_input --min ">
              <label className="bg-select">Email</label>
              <input
                type="email"
                value={username}
              
                placeholder="Correo eléctronico"
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
          </div>

          <div className="option_login">
            <input type="submit" value={"Enviar"} />
          </div>

          <Link to={'/login'}>Cancelar</Link>
        </form>
      </div>
    </section>
  );
}

export default RecoverPassword;
