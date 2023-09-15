import React from 'react'
import ViewMonth from '../../../components/dashboard/ViewMonth.jsx'
import {currentDate, months} from '../../../config/models/ArraysItems.js'
import TableE from '../../../components/Tables/TableE.jsx'
import {useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import useAlert from '../../../hooks/useAlert.jsx'
import CategoriesSelect from '../../../components/Selects/CategoriesSelect.jsx';
import BrandsSelect from '../../../components/Selects/BrandsSelect.jsx';
import {Card, Container} from "react-bootstrap";
import TableI from "../../../components/Tables/TableI.jsx";

function Egresos() {
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const falsaCarga = () => {
        setTimeout(() => {
            useAlert({
                type: "success",
                title: "Cargado con exito",
                text: '',
            })
            handleClose()
        }, 1500);

    }
    const month = currentDate.getMonth() + 1
    return (
        <>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Comprar Producto</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">

                            <Form.Label className='mt-4'>Nombre Producto</Form.Label>
                            <Form.Control
                                type="text"
                                className='mb-4'
                                autoFocus
                            />
                            <BrandsSelect/>

                            <Form.Label className='mt-4'>Cantidad</Form.Label>
                            <Form.Control
                                type="number"

                                autoFocus
                            />

                        </Form.Group>


                        <CategoriesSelect/>

                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="danger" onClick={handleClose}>
                        Cancelar
                    </Button>
                    <Button variant="primary" onClick={falsaCarga}>
                        Comprar
                    </Button>
                </Modal.Footer>
            </Modal>

            <Container className="conainer-products">
                <Card className="card-products">
                    <Card.Header className="card-header__products">
                        <Card.Title className='fs-4 mb-2 text-gray-500'>Egresos</Card.Title>
                        {ViewMonth(month, months)}
                        <Button variant="primary" className='mt-3 ' onClick={handleShow}>
                            Realizar Ventas
                        </Button>
                    </Card.Header>
                    <hr/>
                    <Card.Body className="card-body__products">
                        <TableE/>
                    </Card.Body>
                </Card>
            </Container>
        </>
    )
}

export default Egresos