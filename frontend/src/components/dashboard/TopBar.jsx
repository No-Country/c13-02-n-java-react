import { Link } from "react-router-dom";
import Imagenes from "../../assets/imagenes.jsx";

function TopBar() {
  const nickName = sessionStorage.getItem("username");

  return (
    <>
      <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item  d-flex justify-content-center  align-items-center ">

          {nickName}
            <Link className="nav-link " to={"/dashboard/settings"}>
              
              <img className="img-profile rounded-circle" src={Imagenes.avatar} alt="avatar"/>
            </Link>
          </li>
        </ul>
      </nav> 
    </>
  );
}

export default TopBar;
