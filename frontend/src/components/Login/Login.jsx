import "./Login.css";
import { useState } from "react";

function Login() {
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
        
      if (email == JSON.parse(user.email) && password == JSON.parse(user.password)) {
        console.log("Usuario permitido");
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
          <h1>Nombre app</h1>


          <h2>Iniciar sesión con correo electrónico</h2>
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
          {/* input de usuario  */}
          <div className="login_input">
          
            <input type="email" placeholder="Dirección de correo electrónico" onChange={(e) => setEmail(e.target.value)} />
  {/* input password */}
            <input
              type="password"
              placeholder="Contraseña"
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
      
         


          <a className="recover_link_login" href="#">¿Olvidó su contraseña?</a>
        
          <input type="submit" value={"Login"} />
        </form>

        <p className="--mt">¿No tiene una cuenta? <a href="#" className="recover_link_login ">Registrarse ahora</a></p>
      </div>



      {errores?.length > 0 ? <p>{errores} </p> : ""}
    </section>
  );
}

export default Login;
