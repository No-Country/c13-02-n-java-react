import React, { useState, useEffect } from "react";
import { request } from "../config/helpers/axios_helper";
import useAlert from "./useAlert";

function useNewBrand() {
  const [brand, setBrand] = useState("");
  const [brands, setBrands] = useState([]);
  

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (brand != "") {
      try {
        const response = await request("POST", "/brands", {
          name: `${brand}`,
        });

        useAlert({
            type: "success",
            title: "Cargado con éxito",
            
          });

        console.log(response);
      } catch (error) {
        console.log(error);
        useAlert({
            type :'error',
            title:'Error de carga',
            text :error.message
        })
      }
    }else{
        
        useAlert({
            type :'error',
            title:'Error de carga',
            text : 'Debe completar el campo'
        })
    }
  }

return {handleSubmit,setBrand,brand ,brands, setBrands}

}

export default useNewBrand;



