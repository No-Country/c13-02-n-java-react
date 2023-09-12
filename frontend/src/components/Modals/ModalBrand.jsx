import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import useNewBrand from "../../hooks/useNewBrand";


function ModalBrand({ setShow, show, handleClose, handleShow }) {


const {handleSubmit,setBrand,brand ,brands, setBrands}=  useNewBrand()


  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Cargar Marca</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Marca</Form.Label>
              <Form.Control
                type="text"
                placeholder="Coca Cola, Pepsi, Arcor..."
                autoFocus
                value={brand}
                onChange={(e)=>setBrand(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleSubmit}>
            Agregar Marca
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalBrand;
