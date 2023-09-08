import { useState, useEffect } from "react";
import { request } from "../config/helpers/axios_helper";
import { Form } from "react-bootstrap";

function CategoriesSelect() {
  const [categories, setCategories] = useState([]);

  const getCategories = async () => {
    try {
      const response = await request(
        "GET",
        "/categories",
        setCategories(),

        
      );

      setCategories(response.data.content);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getCategories();
  }, []);

  return (
    <Form.Select aria-label="Default select example">
      <option>--Seleccione categoria--</option>

      {categories
        ? categories.map((cat, key) => {
            return (
              <option key={key} value={cat.name}>
                {cat.name}{" "}
              </option>
            );
          })
        : ""}
    </Form.Select>
  );
}

export default CategoriesSelect;
