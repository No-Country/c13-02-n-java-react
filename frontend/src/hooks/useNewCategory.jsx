import React, { useState, useEffect } from "react";
import { request } from "../config/helpers/axios_helper";
import useAlert from "./useAlert";

function useNewCategory() {
  const [category, setCategory] = useState("");
  const [categories, setCategories] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (category != "") {
      try {
        const response = await request("POST", "/categories", {
          name: `${category}`,
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

return {handleSubmit,category,setCategory,categories, setCategories}

}

export default useNewCategory;



