import { Link, useNavigate } from "react-router-dom";
import "../css/Login.css";
import useAuth from "../../hooks/useAuth.jsx";
import SpinnerLoad from "../../components/spinner/SpinnerLoad.jsx";
import Imagenes from "../../assets/imagenes.jsx";

function Login() {
  const {
    username,
    setUsername,
    password,
    setPassword,

    handleSubmit,
    isLoading,
  } = useAuth();

  

  return (
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
       
        <div className="login_image_logo">
          <img src={Imagenes.avatar} alt="Logo" /> 
     
        </div>
        <div className="text-center ">
          <h1>FIMA</h1>
          <p>Finance Manager</p>
          {isLoading ? <SpinnerLoad /> : ""}
            
        </div>

        <h2>Iniciar sesión con correo electrónico</h2>

        {/* formulario de login  */}
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
          {/* input de usuario  */}
         

          <div className="login_input">
            {/* input de usuario  */}
            <div className="register_input --min ">
              <label className="bg-select">Usuario</label>
              <input
                type="text"
                value={username}
                placeholder="Correo eléctronico"
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            {/* input de empresa  */}
            <div className="register_input --min">
              <label className="bg-select">Contraseña</label>
              {/* input password */}

              <input
                type="password"
                placeholder="Contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          <Link className="recover_link_login" to={"/recoverPassword"}>
            ¿Olvidó su contraseña?
          </Link>

          <div className="option_login">
            <input type="submit" value={"Login"} />
            <button className="google_login">
              {" "}
              <img src={Imagenes.google} /> Iniciar sesión con Google
            </button>
          </div>
        </form>

        <p className="--mt">
          ¿No tiene una cuenta?{" "}
          <Link className="recover_link_login" to={"/register"}>
            Registrarse ahora
          </Link>
        </p>
      </div>
    </section>
  );
}

export default Login;
