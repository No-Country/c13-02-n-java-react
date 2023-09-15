import { useState, useEffect } from "react";

import { Form } from "react-bootstrap";
import createCategory from "../../services/products.js";

function BrandsSelect() {
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

  useEffect(() => {
    getBrands();
  }, []);

  return (
    <Form.Select  aria-label="Default select example">
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
