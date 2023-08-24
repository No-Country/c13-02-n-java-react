import { Link } from "react-router-dom";
import "./Landing.css";
import Footer from "../Footer/Footer";

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
          Prueba <b>Fima - Finance Manager</b> y comienza a gestionar tu emprendimiento y tus finanzas de una manera mas organizada. 
          </p>

          <button>Comenzar ahora</button>
        </div>
      </div>

      <div className="separator"></div>

      <div className="info_landing">
        <p>
        Si recien comenzas con tu emprendimiento o si solo buscas cómo organizar mejor tu negocio, <b>FIMA</b> es lo que necesitas
        </p>

        <h3>Funcionalidades ideales para vos</h3>
      </div>

      <div className="info_icons_landing">
        <div className="container_icon ">
          <img src="./icons/negocio.svg" alt="icons" />
          <p>Carga el perfil de tu negocio</p>
        </div>
        <div className="container_icon">
          <img src="./icons/Group 6.svg" alt="icons" />
          <p>Registra tus ventas y calcula tus ganancias</p>
        </div>
        <div className="container_icon">
          <img src="./icons/dinero.svg" alt="icons" />
          <p>Registra tus compras para controlar tus gastos</p>
        </div>
        <div className="container_icon">
          <img src="./icons/perfil.svg" alt="icons" />
          <p>Carga tus productos y separarlos por categorías</p>
        </div>
        
       
        <div className="container_icon">
          <img src="./icons/Group 2.svg" alt="icons" />
          <p>Registra tus compras para controlar tus gastos</p>
        </div>
        <div className="container_icon">
          <img src="./icons/cards.svg" alt="icons" />
          <p>Carga tus productos y sepáralos por categorías</p>
        </div>


       
      </div>


      <div className="barras_landing">
        <img src="./icons/barras.svg" alt="imagen" />
      </div>

      <div className="banner_landing">
        <img src="./img/alcancia.svg" alt="img" />
        <img  className="--absolute"  src="./img/Rectangle.svg" alt="img" />
      <p>Digitalízate con FIMA
y optimiza el rendimiento de tu negocio</p>
      </div>



      <div className="bubbles_landing">
  <img src="./icons/Group9.svg" alt="bubbles" />
</div>



<Footer/>
    </section>



  );
}

export default Landing;
