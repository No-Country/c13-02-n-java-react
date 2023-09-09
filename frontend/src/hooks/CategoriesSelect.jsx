import { useState, useEffect } from "react";
import { request } from "../config/helpers/axios_helper";
import { Form } from "react-bootstrap";
import axios from "axios";

function CategoriesSelect() {
  const [categories, setCategories] = useState([]);

  const [token, setToken] = useState(localStorage.getItem("token"));

  /* const getCategories = async () => {
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
  }; */

  const tokenfijo =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVsdTE0OTVAZ21haWwuY29tIiwiaWF0IjoxNjk0MTk5MzA3LCJleHAiOjE2OTQyMDI5MDd9.d4vFh_hOtpFvrDNUtTGCqezUqriJ05COVtMp6yL7OJw";

  useEffect(() => {
    console.log(typeof token, token, "token variable");
    console.log(typeof tokenfijo, tokenfijo, "token fijo");
  }, []);

  const getCategories = async () => {
    try {
      const response = await axios.get(
        "https://vps-3471374-x.dattaweb.com/api/v1/categories",
        {
          timeout: 10000,
          headers: {
            Authorization: `Bearer ${token}`, // Asegúrate de ajustar el tipo de token si es diferente (por ejemplo, 'Bearer', 'Token', etc.)
            "Content-Type": "application/json",
          },
        }
      );

      console.log(response.data);
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
