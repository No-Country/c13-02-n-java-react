import React, {useEffect, useState} from 'react'
import {getLocalStorageIdUtil} from "../config/utils/getLocalStorageIdUtil.js";
import userProfile from "../services/products.js";

function useCreateProducts() {
    const [userData, setUserData] = useState([]);
    const [
        {idBrans, idCategories, name, description, price, stock, image,}
    ] = useState([{}]);
    const CreateProducts = async () => {
        try {


        } catch (error) {
            console.log(error);
        }
    }
    useEffect(() => {
        CreateProducts().then(r => console.log(r));
    }, []);
 
}

export default useCreateProducts