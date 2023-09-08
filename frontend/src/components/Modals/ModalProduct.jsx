import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import CategoriesSelect from "../../hooks/CategoriesSelect";

function ModalProduct({ setShow, show, handleClose, handleShow }) {
  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Cargar Producto</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form className="d-flex flex-column  gap-3 ">
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label className= "mt-2">Categoria</Form.Label>
             
             <CategoriesSelect/>
              <Form.Label className= "mt-2">Marca</Form.Label>
              <Form.Select aria-label="Default select example">
                <option>--Seleccione marca--</option>
                <option value="1">Bebidas</option>
                <option value="2">Snacks</option>
                <option value="3">Cereales</option>
              </Form.Select>

              <Form.Label className= "mt-2">Nombre</Form.Label>

              <Form.Control
                type="text"
                placeholder="Bebidas, Comestibles, Golosinas..."
                autoFocus
              />

              <Form.Group className="d-flex justify-content-center  align-items-center  gap-2  mt-3 ">
                <div className="d-flex flex-column ">
                  <Form.Label className= "mt-2">Stock</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Ingrese Stock"
                    autoFocus
                  />
                </div>

                <div className="d-flex flex-column ">
                  <Form.Label className= "mt-2">Precio</Form.Label>

                  <Form.Control
                    type="Number"
                    placeholder="Precio Producto"
                    autoFocus
                  />
                </div>

                 
              </Form.Group>
             <div className="mt-3 ">
             <Form.Label>Imagen Producto</Form.Label>
              <Form.Control
                    type="File"
                    placeholder=""
                    autoFocus
                  />
             </div>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleClose}>
            Cargar Producto
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalProduct;
