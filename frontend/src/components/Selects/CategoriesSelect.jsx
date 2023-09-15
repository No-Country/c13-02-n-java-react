import { useState, useEffect } from "react";
import { Form } from "react-bootstrap";
import createCategory from "../../services/products.js";

function CategoriesSelect({onCategoryChange}) {
  const [categories, setCategories] = useState([]);

  const getCategories = async () => {
    try {
        const categoryData = await createCategory.getAll('categories');
        setCategories(categoryData.content);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getCategories();
  }, []);

    const handleCategoryChange = (e) => {
        const selectedCategory = e.target.value;
        onCategoryChange(selectedCategory);
    };

  return (
      <Form.Select aria-label="Default select example" onChange={handleCategoryChange}>
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
