import "./Footer.css";

function Footer() {
  return (
    <div className="footer">
      <div className="logo w-25 mt-5 ">Logo nombre</div>

      <div className="d-flex flex-column mt-5  gap-4">
        <h3 className="text-white mt-5 mx-5  ">
          Conocé <span className="fw-bold ">FIMA</span>{" "}
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
