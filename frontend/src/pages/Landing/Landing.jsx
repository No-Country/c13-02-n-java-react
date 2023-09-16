import {Link} from "react-router-dom";
import "../css/Landing.css";
import Footer from "../Footer/Footer.jsx";
import Imagenes from "../../assets/Imagenes.jsx";

function Landing() {
    return (
        <section className="landing">
            <header className="header_landing">
                <div className="logo_landing">
                    <picture>
                        <img src={Imagenes.logo} alt="logo"/>
                    </picture>
                    <p>Fima</p>
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
                    <img src={Imagenes.pieCharts} alt="chart"/>
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
                        <img src={Imagenes.negocio} alt="icons"/>
                        <p>Crea tu cuenta y carga el perfil de tu negocio</p>
                    </div>
                    <div className="container_icon">
                        <img src={Imagenes.groupSeis} alt="icons"/>
                        <p>Lleva un control tus ventas y calcula tus ganancias</p>
                    </div>
                    <div className="container_icon">
                        <img src={Imagenes.dinero} alt="icons"/>
                        <p>Registra tus compras para controlar tu dinero invertido</p>
                    </div>
                    <div className="container_icon">
                        <img src={Imagenes.perfil} alt="icons"/>
                        <p>Separa tu gastos personales de los de tu negocio </p>
                    </div>

                    <div className="container_icon">
                        <img src={Imagenes.group2} alt="icons"/>
                        <p>Carga tus productos y separalos por categorías</p>
                    </div>
                    <div className="container_icon">
                        <img src={Imagenes.cards} alt="icons"/>
                        <p>Toma pedidos y reserva productos para tus clientes</p>
                    </div>
                </div>
            </div>
            <div className="barras_landing  ">
                <img src={Imagenes.respo} alt="imagen"/>
            </div>
            <div className="banner_landing">
                <p>Digitalízate con FIMA y optimiza el rendimiento de tu negocio</p>
                <img className=" " src={Imagenes.respo2} alt="img"/>
            </div>

            <Footer/>
        </section>
    );
}

export default Landing;
