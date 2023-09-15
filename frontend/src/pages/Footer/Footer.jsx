import Imagenes from "../../assets/imagenes";
import "../css/Footer.css";

function Footer() {
  return (
    <div className="footer">
      <div className="logo  d-flex flex-column mt-5  gap-4 ">

       <div className="logo_data">
        <img src={Imagenes.logo} alt="" />
      
      <div className="d-flex flex-column text-logo ">
      <h3 className="fs-4 mt-5 text-white ">FIMA</h3>
      <p className="fs-6 ">finance & inventory manager</p>
      </div>
       </div>

        <p className="w-100 ">Simplificamos la gestión de tu negocio desde un solo lugar, fácil e intuitivo, conocenos!</p>


      <div  className="d-flex gap-4 ">

        <div className="icon_red_footer"><i className="fa-brands fa-instagram"></i></div>
        <div className="icon_red_footer"><i className="fa-brands fa-facebook"></i></div>
        <div className="icon_red_footer"><i className="fa-brands fa-x-twitter"></i></div>
      </div>
   
    

      </div>


      <div className="d-flex flex-column mt-5  gap-4">
        <h3 className="text-white fs-5  mt-5 mx-5  ">
          Conocé <span className="fw-bold fs-4   ">FIMA</span>{" "}
        </h3>

        <div className="border-bottom border-white text-white  ">
          <p>¿Cómo funciona?</p>
        </div>
        <div className="border-bottom border-white text-white  ">
          <p>Registrarse</p>
        </div>
        <div className="border-bottom border-white text-white  ">
          <p>Políticas de privacidad</p>
        </div>
        <div className="border-bottom border-white text-white  ">
          <p>Términos y condiciones</p>
        </div>
      </div>
    </div>
  );
}

export default Footer;
