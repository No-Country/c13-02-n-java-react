import { Link } from "react-router-dom";
import "./Landing.css";

function Landing() {
  return (
    <section className="landing">
      <header className="header_landing">
        <div className="logo_landing">
          {/*  <picture>
                    <img src="#" alt="logo" />
                </picture> */}
          <h1>Logo/nombre app</h1>
        </div>

        <nav className="navigation_landing">
          <ul>
          <Link to={'/login'}>  <li>Iniciar sesión </li></Link>
           <Link to={'/register'}> <li className="--bg-button">Registrarse</li></Link>
          </ul>
        </nav>
      </header>

      <div className="hero_landing">
        <div className="img_hero_landing">
          <img src="./img/PieCharts.svg" alt="chart" />
        </div>
        <div className="action_hero_landing">
          <h1>Organiza tu negocio en un solo lugar</h1>
          <p>
            Prueba appsinnombre y comienza a gestionar tu emprendimiento y tus
            finanzas de una manera más organizada.{" "}
          </p>

          <button>Comenzar ahora</button>
        </div>
      </div>

      <div className="separator"></div>

      <div className="info_landing">
        <p>
          Si recién comienzas con tu emprendimiento o si solo buscas cómo
          organizar mejor tu negocio, dklajdfkja es lo que necesitas
        </p>

        <h3>Funcionalidades ideales para vos</h3>
      </div>

      <div className="info_icons_landing">
        <div className="container_icon --min">
          <img src="./icons/Vector.svg" alt="icons" />
          <p>Carga el perfil de tu negocio</p>
        </div>
        <div className="container_icon">
          <img src="./icons/group3.svg" alt="icons" />
          <p>Registra tus compras para controlar tus gastos</p>
        </div>
        <div className="container_icon">
          <img src="./icons/Group 2.svg" alt="icons" />
          <p>Carga tus productos y separarlos por categorías</p>
        </div>
        <div className="container_icon">
          <img src="./icons/Group 6.svg" alt="icons" />
          <p>Registra tus ventas y calcula tus ganancias</p>
        </div>
        <div className="container_icon --min">
          <img src="./icons/Vector.svg" alt="icons" />
          <p>Carga el perfil de tu negocio</p>
        </div>
        <div className="container_icon">
          <img src="./icons/group3.svg" alt="icons" />
          <p>Registra tus compras para controlar tus gastos</p>
        </div>
        <div className="container_icon">
          <img src="./icons/Group 2.svg" alt="icons" />
          <p>Carga tus productos y sepáralos por categorías</p>
        </div>
        <div className="container_icon">
          <img src="./icons/Group 6.svg" alt="icons" />
          <p>Registra tus ventas y calcula tus ganancias</p>
        </div>
      </div>
    </section>
  );
}

export default Landing;
