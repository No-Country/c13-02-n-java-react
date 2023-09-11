import "../css/Login.css";
import { Link, useNavigate } from "react-router-dom";

import SpinnerLoad from "../../components/spinner/SpinnerLoad.jsx";
import Imagenes from "../../assets/imagenes.jsx";
//pruebaassss
import { useState, useEffect } from "react";

import Swal from "sweetalert2";

import axios from "axios";
import useAlert from "../../hooks/useAlert";
import { request } from "../../config/helpers/axios_helper";

//fin

function Login({ auth, setAuth }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);
  const [token, setToken] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

 /*  const useAuth = () => { */
    const handleSubmit = async (e) => {
      e.preventDefault();

      if (username && password) {
        setIsLoading(true);
        try {
          const response = await request(
            "POST",
            `/login`,
            JSON.stringify({ username, password }),
            { headers: { "Content-Type": "application/json" } }
          );
          console.log(response.status);
          if (response.status === 200) {
            sessionStorage.setItem("token", response.data.token);
            sessionStorage.setItem("username", username);
            setAuth(true)
            setIsLoading(false);
            navigate("/dashboard");
          } else {
            setIsLoading(false);
            //use alert
            useAlert({
              type: "error",
              title: errors,
              text: "Verifique sus datos",
            });
          }
        } catch (error) {
          setIsLoading(false);

          //use alert
          useAlert({
            type: "error",
            title: "Error de ingreso",
            text: "Usuario o Contraseña inválidos ",
          });
        }
      } else {
        setErrors(["Complete los campos"]);
        //use alert
        useAlert({
          type: "warning",
          title: "Campos obligatorios",
          text: "Complete los campos ",
        });
      }
    };

    /*     return{
  
        username,
        setUsername,
        password,
        setPassword,
        errors,
        token,
        handleSubmit,
        isLoading,
  
  
      } */
 /*  }; */

  /*   const {handleSubmit,
    
    username,
    setUsername,
    password,
    setPassword,
   
    token,
  
    isLoading,
    navigate} = useAuth() */

  return (
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
        <div className="login_image_logo">
          <img src={Imagenes.logo} alt="Logo" />
        </div>
        <div className="text-center ">
          <h1>FIMA</h1>
          <p style={{ width: "150px" }}>finance & inventory manager</p>
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
