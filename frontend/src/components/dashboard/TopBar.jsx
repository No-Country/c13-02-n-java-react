import { Link } from "react-router-dom";
import Imagenes from "../../assets/imagenes.jsx";
import {getLocalStorageIdUtil} from "../../config/utils/getLocalStorageIdUtil.js";
import userProfile from "../../services/products.js";
import {useEffect, useState} from "react";

function TopBar() {
  const [userData, setUserData] = useState({});
  const AxiosUserDataDos = async () => {
    try {
      // Todo: consumir la API para obtener los datos del usuario
      const id = getLocalStorageIdUtil("id")
      const userData = await userProfile.getUser(`users/${id}`);
      setUserData(userData);
    } catch (error) {
      console.log(error);
    }
  }
  useEffect(() => {
    AxiosUserDataDos().then(r => console.log(r));
  }, []);

  return (
    <>
      <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item  d-flex justify-content-center  align-items-center ">

            <p className="text-capitalize m-0 ">{userData.fullName}</p>
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
