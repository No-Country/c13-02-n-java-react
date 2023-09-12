import { useState, useEffect } from "react";
import { Form } from "react-bootstrap";
import createCategory from "../../services/products.js";

function CategoriesSelect() {
  const [categories, setCategories] = useState([]);

  const getCategories = async () => {
    try {
        const categoryData = await createCategory.getAll('categories');
        setCategories(categoryData.content);
        console.log(categoryData);

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
