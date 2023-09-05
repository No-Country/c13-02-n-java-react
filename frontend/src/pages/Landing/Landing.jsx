import { Link } from "react-router-dom";
import "./Landing.css";
import Footer from "../Footer/Footer.jsx";
import Imagenes from "../../assets/imagenes.jsx";

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
          <Link className="notBg" to={"/login"}>
            Iniciar sesión
          </Link>
          <Link className="btnCustom" to={"/register"}>
            Registrarse
          </Link>
        </nav>
      </header>

      <div className="hero_landing">
        <div className="img_hero_landing">
          <img src={Imagenes.pieCharts} alt="chart" />
        </div>
        <div className="action_hero_landing">
          <h1>Organiza tu negocio en un solo lugar</h1>
          <p>
            Prueba <b>Fima - Finance Manager</b> y comienza a gestionar tu
            emprendimiento y tus finanzas de una manera mas organizada.
          </p>

          <Link className="btnCustom btn-start" to={"/login"}>
            {" "}
          Comenzar ahora
          </Link>
        </div>
      </div>

      <div className="separator"></div>

      <div className="cards_container_landing">
        <div className="info_landing">
          <p>
            Si recien comenzas con tu emprendimiento o si solo buscas cómo
            organizar mejor tu negocio, <b>FIMA</b> es lo que necesitas
          </p>

          <h3>Funcionalidades ideales para vos</h3>
        </div>

        <div className="info_icons_landing">
          <div className="container_icon ">
            <img src={Imagenes.negocio} alt="icons" />
            <p>Crea tu cuenta y carga el perfil de tu negocio</p>
          </div>
          <div className="container_icon">
            <img src={Imagenes.group6} alt="icons" />
            <p>Lleva un control tus ventas y calcula tus ganancias</p>
          </div>
          <div className="container_icon">
            <img src={Imagenes.dinero} alt="icons" />
            <p>Registra tus compras para controlar tu dinero invertido</p>
          </div>
          <div className="container_icon">
            <img src={Imagenes.perfil} alt="icons" />
            <p>Separa tu gastos personales de los de tu negocio </p>
          </div>

          <div className="container_icon">
            <img src={Imagenes.group2} alt="icons" />
            <p>Carga tus productos y separalos por categorías</p>
          </div>
          <div className="container_icon">
            <img src={Imagenes.cards} alt="icons" />
            <p>Toma pedidos y reserva productos para tus clientes</p>
          </div>
        </div>
      </div>
      <div className="barras_landing">
        <img src={Imagenes.barras} alt="imagen" />
      </div>

      <div className="banner_landing">
        <img src={Imagenes.alcancia} alt="img" />
        <img className="--absolute" src={Imagenes.rectangle} alt="img" />
        <p>Digitalízate con FIMA y optimiza el rendimiento de tu negocio</p>
      </div>

      {/* <div className='bubbles_landing'>
				<img src={Imagenes.group9} alt='bubbles' />
			</div> */}

      <Footer />
    </section>
  );
}

export default Landing;
