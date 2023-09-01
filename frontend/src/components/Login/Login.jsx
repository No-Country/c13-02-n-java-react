import { Link, useNavigate } from "react-router-dom";
import "./Login.css";
import { useState } from "react";
import Imagenes from "../../assets/imagenes";

function Login({ setAuth }) {
  const navigate = useNavigate();

  localStorage.setItem("email", "correo@correo.com");
  localStorage.setItem("password", "123");
  /* Usuario registrado */

  const user = {
    email: localStorage.getItem("email"),
    password: localStorage.getItem("password"),
  };

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  /* array de errores */

  const [errores, setErrores] = useState([]);
  /* controla el submit */

  const handleSubmit = (e) => {
    e.preventDefault();

    if (email && password) {
      if (email == user.email && password == user.password) {
        setAuth(true);
        navigate("/dashboard");
      } else {
        setErrores(["Verifique sus datos, usuario no válido"]);
      }
    } else {
      setErrores(["Complete sus datos"]);
      console.log(errores);
    }

    setTimeout(() => {
      setErrores("");
    }, 1500);
  };

  return (
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
        {/* formulario de login  */}
        <div className="login_image_logo">
          {/*  <img src="#" alt="Logo" /> */}
        </div>
        <div className="text-center ">
          <h1>FIMA</h1>
          <p>Finance Manager</p>
        </div>

        <h2>Iniciar sesión con correo electrónico</h2>
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
          {/* input de usuario  */}
          {errores?.length > 0 ? <p className="errores">{errores} </p> : ""}

          <div className="login_input">
            {/* input de usuario  */}
            <div className="register_input --min ">
              <label className="bg-select">Usuario</label>
              <input
                type="text"
                placeholder="Correo eléctronico"
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>
            {/* input de empresa  */}
            <div className="register_input --min">
              <label className="bg-select">Contraseña</label>
              {/* input password */}

              <input
                type="password"
                placeholder="Contraseña"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          <a className="recover_link_login" href="#">
            ¿Olvidó su contraseña?
          </a>

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
