import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import CategoriesSelect from "../Selects/CategoriesSelect";
import { Link } from "react-router-dom";

function ModalProduct({ setShow, show, handleClose, handleShow,handleShowBrand,handleShowCategory }) {


const showControl = (modal)=> {

  handleClose()

  modal()
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
              <Form.Label className= "mt-2">Categoría</Form.Label>
             
             <CategoriesSelect/>
             <div className="mt-2 d-flex justify-content-end ">
              <button onClick={()=> showControl(handleShowCategory)} className="text-decoration-none  bg-warning   text-white py-1 px-2 rounded-2 " ><i className="fa-regular fa-pen-to-square"></i></button>
              </div>
              <Form.Label className= "mt-2">Marca</Form.Label>
              <Form.Select aria-label="Default select example">
                <option>--Seleccione marca--</option>
                <option value="1">Bebidas</option>
                <option value="2">Snacks</option>
                <option value="3">Cereales</option>
              </Form.Select>
              
              <div className="mt-2 d-flex justify-content-end ">
              <button onClick={()=> showControl(handleShowBrand)} className="text-decoration-none  bg-info   text-white py-1 px-2 rounded-2 " ><i className="fa-regular fa-pen-to-square"></i></button>
              </div>

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
