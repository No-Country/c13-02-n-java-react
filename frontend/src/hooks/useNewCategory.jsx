import React, { useState, useEffect } from "react";
import useAlert from "./useAlert";
import createCategory from "../services/products.js";

function useNewCategory() {
    const [category, setCategory] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (category !== "") {
            try {
                if (category) {
                    const categoryData = await createCategory.create('categories', {name: category});
                    setCategory(categoryData.content);
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

    return { handleSubmit, category, setCategory };
}

export default useNewCategory;
