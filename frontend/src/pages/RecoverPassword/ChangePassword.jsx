import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import useChangePassword from "../../hooks/useChangePassword";
import Imagenes from "../../assets/imagenes";

function ChangePassword() {
  const token = useParams();

  const [isLoading,setIsLoading]= useState('')

  const {
    handleSubmit,
    password,
    passwordConfirm,
    setPassword,
    setPasswordConfirm,
    setTokenRecovery,
  } = useChangePassword();

  const handleToken = async (token) => {
    await setTokenRecovery(token.token);
  };

  useEffect(() => {
    handleToken(token);
  }, []);

  return (
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
        <div className="login_image_logo">
          <img src={Imagenes.logo} alt="Logo" />
        </div>
        <div className="text-center ">
          <h1>FIMA</h1>
          <p style={{width:'150px'}}>finance & inventory
 manager</p>
        </div>

        <h2>Recuperar contraseña </h2>

        <p className="text-center ">Ingresa tu nueva contraseña</p>

        {isLoading ? <SpinnerLoad /> : ""} 
        {/* formulario de recuperar password  */}
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}

          <div className="login_input">
            {/* input de usuario  */}
            <div className="register_input --min ">
              <label className="bg-select">Contraseña</label>
              <input
                type="password"
                value={password}
                placeholder="Contraseña"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          <div className="login_input">
            {/* input de usuario  */}
            <div className="register_input --min ">
              <label className="bg-select">Repetir contraseña</label>
              <input
                type="password"
                value={passwordConfirm}
                placeholder="Repetir contraseña"
                onChange={(e) => setPasswordConfirm(e.target.value)}
              />
            </div>
          </div>

          <div className="option_login">
            <input type="submit" value={"Restablecer"} />
          </div>
        </form>
      </div>
    </section>
  );
}

export default ChangePassword;
