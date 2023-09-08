import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function Sidebar() {
  const navigate = useNavigate();

  const tokenDestroy = () => {
    sessionStorage.setItem("token", '');

    navigate("/");
  };

  const Links = [
    { label: "Inicio", icon: `fa-solid fa-house`, route: "/dashboard" },

    {
      label: "Ingresos",
      icon: `fa-solid fa-coins`,
      route: "/dashboard/ingresos",
    },
    {
      label: "Egresos",
      icon: `fa-solid fa-money-bill-transfer`,
      route: "/dashboard/egresos",
    },
    {
      label: "Inventario",
      icon: `fa-solid fa-box`,
      route: "/dashboard/inventario",
    },
    {
      label: "Productos",
      icon: `fa-solid fa-list-check`,
      route: "/dashboard/productos",
    },
    
  ];

  return (
    <>
      {/* <!-- Sidebar --> */}
      <ul
        className="navbar-nav bg-gradient-primary  sidebar sidebar-dark "
        id="accordionSidebar"
      >
        {/*    <!-- Sidebar - Brand --> */}
        <Link
          to={"/dashboard"}
          className="sidebar-brand d-flex align-items-center justify-content-center"
        >
          <div className="sidebar-brand-icon rotate-n-15">
            <i className="fas fa-laugh-wink"></i>
          </div>
          <div className="sidebar-brand-text mx-3">FIMA</div>
        </Link>

        {/*  <!-- Divider --> */}
        <hr className="sidebar-divider my-0" />

        {/* <!-- Nav Item - Dashboard --> */}
        <li className="nav-item active">
          <Link className="nav-link" to={"/dashboard"}>
            <i className="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </Link>
        </li>

        {/*  <!-- Divider --> */}
        <hr className="sidebar-divider" />

        {/*  <!-- Heading --> */}
        <div className="sidebar-heading">Reportes</div>

        {Links.map((link, key) => {
          return (
            <li key={key} className="nav-item">
              <Link className="nav-link collapsed" to={link.route}>
                <i className={link.icon}></i>
                <span>{link.label} </span>
              </Link>
            </li>
          );
        })}

        {/*  <!-- Divider --> */}
        <hr className="sidebar-divider" />
        {/*
            <!-- Heading --> */}
        <div className="sidebar-heading">Acciones</div>

        {/* <!-- Nav Item - Charts --> */}
        <li className="nav-item">
          <Link className="nav-link" to={"/dashboard/settings"}>
            <i className="fa-solid fa-user-pen"></i>
            <span>Ajustes</span>
          </Link>
        </li>

        {/*    <!-- Nav Item - Tables --> */}
        <li className="nav-item">
          <button className="nav-link" onClick={() => tokenDestroy()}>
            <i className="fa-solid fa-arrow-right-from-bracket"></i>
            <span>Salir</span>
          </button>
        </li>

        {/* <!-- Divider --> */}
        <hr className="sidebar-divider d-none d-md-block" />
      </ul>
      {/* <!-- End of Sidebar --> */}
    </>
  );
}

export default Sidebar;
