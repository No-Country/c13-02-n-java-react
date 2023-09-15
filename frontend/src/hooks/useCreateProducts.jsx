import React, {useEffect, useState} from 'react'
import {getLocalStorageIdUtil} from "../config/utils/getLocalStorageIdUtil.js";
import userProfile from "../services/products.js";
import createProducts from "../services/products.js";

function useCreateProducts() {
    const [product, setProduct] = useState([]);

    const CreateProducts = async (e) => {
        e.preventDefault();
        try {
            if (product) {
                const productData = {
                    name: product.name,
                    price: product.price,
                    stock: product.stock,
                    category: product.category,
                    brand: product.brand,
                };
                const categoryData = await createProducts.create('/products', {productData});
                setProduct(categoryData.content);
                console.log(categoryData.content);
            }

        } catch (error) {
            console.log(error);
        }
    }

    const handleChange = (fieldName, value) => {
        setProduct({
            ...product,
            [fieldName]: value,
        });
    };

    return {product, setProduct, handleChange, CreateProducts}
 
}

export default useCreateProducts