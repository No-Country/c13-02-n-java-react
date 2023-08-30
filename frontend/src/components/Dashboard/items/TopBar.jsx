import { Link } from "react-router-dom";

import Imagenes from "../../../assets/imagenes";

function TopBar() {
  const nickName = localStorage.getItem("email");

  return (
    <>
      <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item ">
            <Link class="nav-link " to={"/dashboard/profile"}>
              <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                {nickName}
              </span>
              <img class="img-profile rounded-circle" src={Imagenes.avatar} />
            </Link>
          </li>
        </ul>
      </nav>
    </>
  );
}

export default TopBar;
