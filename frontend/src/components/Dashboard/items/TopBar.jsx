import { Link } from "react-router-dom";

import Imagenes from "../../../assets/imagenes";

function TopBar() {
  const nickName = localStorage.getItem("email");

  return (
    <>
      <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item ">
            <Link className="nav-link " to={"/dashboard/profile"}>
              <span className="mr-2 d-none d-lg-inline text-gray-600 small">
                {nickName}
              </span>
              <img className="img-profile rounded-circle" src={Imagenes.avatar} />
            </Link>
          </li>
        </ul>
      </nav> 
    </>
  );
}

export default TopBar;
