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
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
          <div className="login_image_logo">
            <img src="#" alt="Logo" />
          </div>
          {/* input de usuario  */}
          <div className="login_input">
            <label>Usuario</label>
            <input type="email" onChange={(e) => setEmail(e.target.value)} />
          </div>
          {/* input password */}
          <div className="login_input">
            <label>Contraseña</label>
            <input
              type="password"
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <input type="submit" value={"Login"} />
        </form>

        <a href="#">Olvidé mi contraseña</a>
      </div>

      {errores?.length > 0 ? <p>{errores} </p> : ""}
    </section>
  );
}

export default Login;
