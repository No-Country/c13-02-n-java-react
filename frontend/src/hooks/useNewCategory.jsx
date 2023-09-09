import React, { useState, useEffect } from "react";
import { request } from "../config/helpers/axios_helper";
import useAlert from "./useAlert";

function useNewCategory() {
    const [category, setCategory] = useState("");

    const [token, setToken] = useState("");

    useEffect(() => {
        const tokenRecovery = sessionStorage.getItem("token")

        setToken(tokenRecovery);
    }, []);


    const tokenFijo = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVsdTE0OTVAZ21haWwuY29tIiwiaWF0IjoxNjk0MzAxODI5LCJleHAiOjE2OTQzMDU0Mjl9.mmTPjU1R_3MToMe_OM6gMWGZiq-m6d_hFUJfTnSJi4g'


    console.log(token);
    console.log(tokenFijo);

    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log("este es el token ", token);
        if (category != "") {
            try {
                const response = await request("POST", "/categories", {
                    name: `${category}`,


                }, { headers: {
                    'Authorization': `Bearer ${tokenFijo}` // Utiliza Bearer Token
                  }});

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

    return { handleSubmit, category, setCategory };
}

export default useNewCategory;
