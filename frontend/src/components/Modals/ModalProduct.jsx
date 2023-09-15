import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import CategoriesSelect from "../Selects/CategoriesSelect";
import BrandsSelect from "../Selects/BrandsSelect";
import { Link } from "react-router-dom";
import useCreateProducts from "../../hooks/useCreateProducts.jsx";

import useAlert from "../../hooks/useAlert";
function ModalProduct({
  show,
  handleClose,
  handleShowBrand,
  handleShowCategory,
}) {

  

  const {product, setProduct, handleChange, CreateProducts} = useCreateProducts();
  const showControl = (modal) => {
    handleClose();

    modal();
  };

  const handleCategoryChange = (selectedCategoryId) => {
    console.log(`Categoría seleccionada: ${selectedCategoryId}`);
    setProduct({
      ...product,
      category: selectedCategoryId,
    });
  };



  const FalsaCarga = () => {
   setTimeout(() => {

    useAlert({
      type: "success",
      title: "Cargado con exito",
      text: '',
  }   )


  handleClose()
    
   }, 1500);
  }

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Cargar Producto</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form className="d-flex flex-column  gap-3 ">
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label className="mt-2">Categoría</Form.Label>

              <CategoriesSelect onCategoryChange={handleCategoryChange}/>
              <div className="mt-2 d-flex justify-content-end ">
                <button
                  onClick={() => showControl(handleShowCategory)}
                  className="text-decoration-none  bg-warning   text-white py-1 px-2 rounded-2 "
                >
                  <i className="fa-regular fa-pen-to-square"></i>
                </button>
              </div>
              <Form.Label className="mt-2">Marca</Form.Label>

              <BrandsSelect />
              <div className="mt-2 d-flex justify-content-end ">
                <button
                  onClick={() => showControl(handleShowBrand)}
                  className="text-decoration-none  bg-info   text-white py-1 px-2 rounded-2 "
                >
                  <i className="fa-regular fa-pen-to-square"></i>
                </button>
              </div>

              <Form.Label className="mt-2">Nombre</Form.Label>

              <Form.Control
                type="text"
                placeholder="Nombre producto"
                value={product.name}
                onChange={(e) =>
                  handleChange("name", e.target.value)
                }
                autoFocus
              />

              <Form.Group className="d-flex justify-content-center  align-items-center  gap-2  mt-3 ">
                <div className="d-flex flex-column ">
                  <Form.Label className="mt-2">Stock</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Ingrese Stock"
                    value={product.stock}
                    onChange={(e) => handleChange("stock", e.target.value)}
                    autoFocus
                  />
                </div>

                <div className="d-flex flex-column ">
                  <Form.Label className="mt-2">Precio</Form.Label>

                  <Form.Control
                    type="Number"
                    placeholder="Precio Producto"
                    value={product.price}
                    onChange={(e) => handleChange("price", e.target.value)}
                    autoFocus
                  />
                </div>
              </Form.Group>
              <div className="mt-3 ">
                <Form.Label>Imagen Producto</Form.Label>
                <Form.Control type="File" placeholder="" autoFocus />
              </div>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={ ()=>{  FalsaCarga() }}>
            Cargar Producto
          </Button>
        </Modal.Footer>
      </Modal>

    </>
  );
}

export default ModalProduct;

