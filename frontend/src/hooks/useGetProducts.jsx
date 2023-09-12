import React, {useEffect, useState} from 'react'
import createCategory from "../services/products.js";
import useAlert from "./useAlert.jsx";

function useGetProducts() {
  const [products, setProducts] = useState([]);

  const handleGetProducts = async () => {
    try {
      const ProductsData = await createCategory.getAll('products');
      setProducts(ProductsData.content);
      return ProductsData.content;
    } catch (error) {
      console.log(error);
      useAlert({
        type: "error",
        title: "Error de carga",
        text: error.message,
      });
      throw error;
    }
  };

  return {handleGetProducts, products};

}

export default useGetProducts