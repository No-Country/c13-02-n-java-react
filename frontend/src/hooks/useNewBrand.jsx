import React, { useState, useEffect } from "react";
import axios from "axios";
import useAlert from "./useAlert";
import { useContext } from "react";
import { UserContext } from "../context/UserProvider";
function useNewBrand() {
  const { token,Url_API } = useContext(UserContext);

  console.log(token, "desde brand");

  const [brand, setBrand] = useState("");
 

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (brand != "") {
      try {
        const response = await axios.post(
          
          "/brands",
          {
            name: `${brand}`,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`, // Utiliza Bearer Token
            },
          }
        );

        useAlert({
          type: "success",
          title: "Cargado con éxito",
        });

        console.log(response);
      } catch (error) {
        console.log(error);
        useAlert({
          type: "error",
          title: "Error de carga",
          text: error.message,
        });
      }
    } else {
      useAlert({
        type: "error",
        title: "Error de carga",
        text: "Debe completar el campo",
      });
    }
  };

  return { handleSubmit, setBrand, brand, brands, setBrands };
}

export default useNewBrand;
