import "./Register.css";
import { useState } from "react";



function Register() {
  /* Usuario registrado */

  const user = {
    email: localStorage.getItem("email") || undefined,
    password: localStorage.getItem("password") || undefined,
  };

  const [usuario, setUsuario] = useState("");
  const [empresa, setEmpresa] = useState("");
  const [telefono, setTelefono] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordrepeat, setPasswordrepeat] = useState("");

  /* array de errores */

  const [errores, setErrores] = useState([]);
  /* controla el submit */

  /* test de validación de email  */

 let regex = new RegExp(
    "([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|\"([]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|[[\t -Z^-~]*])"
  ); 

  const handleSubmit = (e) => {
    e.preventDefault();

    if (![email,password,usuario,empresa,telefono].includes('') ) {
      if (email != user.email && regex.test(email)  && password != user.password) {
        
        localStorage.setItem('email',JSON.stringify(email))
        localStorage.setItem('password',JSON.stringify(password))


      } else {
        setErrores(["Este Usuario ya esta registrado"]);
      }
    } else {
      setErrores(["Todos los campos son obligatorios"]);
      console.log(errores);
    }

    setTimeout(() => {
      setErrores("");
    }, 1500);
  };

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
            <input type="text" onChange={(e) => setUsuario(e.target.value)} />
          </div>
          {/* input de empresa  */}
          <div className="register_input">
            <label>Empresa</label>
            <input type="text" onChange={(e) => setEmpresa(e.target.value)} />
          </div>
       </div>
         <div className="div_container_input">
           {/* input de teléfono  */}
           <div className="register_input">
            <label>Teléfono</label>
            <input type="tel"  autoComplete="false"  onChange={(e) => setTelefono(e.target.value)} />
          </div>
          {/* input de email  */}
          <div className="register_input">
            <label>Email</label>

            <input type="email"  autoComplete="false" onChange={(e) => setEmail(e.target.value)} />
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
            />
          </div>
          {/* input password */}
          <div className="register_input">
            <label>Confirmar contraseña</label>
            <input
              type="password"
              onChange={(e) => setPasswordrepeat(e.target.value)}
            />
          </div>
        </div>

          <input type="submit" className="--50"  value={"Crear cuenta"} />
        </form>
      </div>

      {errores?.length > 0 ? <p>{errores} </p> : ""}
    </section>
  );
}

export default Register;
