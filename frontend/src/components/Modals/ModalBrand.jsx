import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";

function ModalBrand({ setShow, show, handleClose, handleShow }) {
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
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleClose}>
            Agregar Marca
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalBrand;
