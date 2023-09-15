import { useState, useEffect } from "react";

import { Form } from "react-bootstrap";
import createCategory from "../../services/products.js";

function BrandsSelect({onBrandChange}) {
  const [brands, setBrands] = useState([]);

  const getBrands = async () => {
    try {
      const bransData = await createCategory.getAll('brands?size=150');
      setBrands(bransData.content);
      console.log(bransData);
    } catch (error) {
      console.log(error);
    }
  };

  const handleBrandChange = (e) => {
    const selectedCategory = e.target.value;
    onBrandChange(selectedCategory);
  };

  useEffect(() => {
    getBrands();
  }, []);

  return (
      <Form.Select aria-label="Default select example" onChange={handleBrandChange}>
      <option>--Seleccione marca--</option>

      {brands
        ? brands.map((brand, key) => {
            return (
              <option key={key} value={brand.name}>
                {brand.name}{" "}
              </option>
            );
          })
        : ""}
    </Form.Select>
  );
}

export default BrandsSelect;
