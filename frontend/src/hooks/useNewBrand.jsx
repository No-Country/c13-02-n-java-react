import React, { useState, useEffect } from "react";
import axios from "axios";
import useAlert from "./useAlert";
import { useContext } from "react";

import createCategory from "../services/products.js";
function useNewBrand() {
  const [brand, setBrand] = useState("");
  const [brands, setBrands] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (brand !== "") {
      try {
        if (brand) {
          const categoryData = await createCategory.create('brands', {name: brand});
          setBrand("");
        }

        useAlert({
          type: "success",
          title: "Cargado con éxito",
        });
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

  return {handleSubmit, setBrand, brand};
}

export default useNewBrand;
