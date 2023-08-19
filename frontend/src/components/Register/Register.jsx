import "./Register.css";
import { useState } from "react";



function Register() {
  /* Usuario registrado */

  const user = {
    email: localStorage.getItem("email") || undefined,
    password: localStorage.getItem("password") || undefined,
  };

  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [telefono, setTelefono] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  /* array de errores */

  const [errores, setErrores] = useState([]);
  /* controla el submit */

  /* test de validación de email  */

 let regex = new RegExp(
    "([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|\"([]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(.[!#-'*+/-9=?A-Z^-~-]+)*|[[\t -Z^-~]*])"
  ); 

  const handleSubmit = (e) => {
    e.preventDefault();

    if (![email,password,nombre,apellido,telefono].includes('') ) {
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
    <section className="login">
      {/* cuadro de login */}
      <div className="login_panel">
        {/* formulario de login  */}
        <form onSubmit={(e) => handleSubmit(e)}>
          {/* div del logo  */}
          <div className="login_image_logo">
            <img src="#" alt="Logo" />
          </div>
          {/* input de nombre  */}
          <div className="login_input">
            <label>Nombre</label>
            <input type="text" onChange={(e) => setNombre(e.target.value)} />
          </div>
          {/* input de apellido  */}
          <div className="login_input">
            <label>Apellido</label>
            <input type="text" onChange={(e) => setApellido(e.target.value)} />
          </div>
          {/* input de teléfono  */}
          <div className="login_input">
            <label>Teléfono</label>
            <input type="tel" onChange={(e) => setTelefono(e.target.value)} />
          </div>
          {/* input de email  */}
          <div className="login_input">
            <label>Email</label>
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

          <input type="submit" value={"Registro"} />
        </form>
      </div>

      {errores?.length > 0 ? <p>{errores} </p> : ""}
    </section>
  );
}

export default Register;

/*   if (email  && ) {
      console.log("email válido");
    } else {
      console.log("error");
    } */


