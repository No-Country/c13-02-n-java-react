import { useState, useEffect } from "react";

import { Form } from "react-bootstrap";

function BrandsSelect() {
  const [brands, setBrands] = useState([]);

  const getBrands = async () => {
    try {
      const response = await axios.get(

        "/brands",
        setBrands(),

        
      );

      setBrands(response.data.content);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getBrands();
  }, []);

  return (
    <Form.Select aria-label="Default select example">
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
