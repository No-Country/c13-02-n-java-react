import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import useNewCategory from "../../hooks/useNewCategory";

function ModalCategory({ setShow, show, handleClose, handleShow, showControl}) {


const {handleSubmit,category,setCategory,categories, setCategories} = useNewCategory()


  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Cargar Categoria</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Categoria</Form.Label>
              <Form.Control
                type="text"
                placeholder="Bebidas, Comestibles, Golosinas..."
                autoFocus
                value={category}
                onChange={(e)=> setCategory(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleSubmit}  onAuxClick={handleClose}>
            Agregar categoria
          </Button>
        </Modal.Footer>
      </Modal>
    </>

    
  );

  return {
    
  }
}

export default ModalCategory;
