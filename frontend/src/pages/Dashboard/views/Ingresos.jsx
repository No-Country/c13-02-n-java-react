import React from 'react'
import CardValues from '../../../components/dashboard/CardValues.jsx'
import {currentDate, months} from '../../../config/models/ArraysItems.js'
import ViewMonth from '../../../components/dashboard/ViewMonth.jsx'
import TableI from '../../../components/Tables/TableI.jsx'
import {useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import useAlert from '../../../hooks/useAlert.jsx'
import {Card, Container} from "react-bootstrap";

function Ingresos() {

    const month = currentDate.getMonth() + 1

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const falsaCarga = () => {

        setTimeout(() => {


            useAlert({
                type: "success", title: "Cargado con exito", text: '',
            })
            handleClose()

        }, 1500);

    }


    const provedores = ["Proveedor 1", "Proveedor 2", "Proveedor 3", "Proveedor 4", "Proveedor 5", "Proveedor 6", "Proveedor 7", "Proveedor 8", "Proveedor 9"]

    return (<>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Comprar Producto</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">

                            <Form.Select aria-label="Default select example">
                                <option>--Seleccione proveedor--</option>

                                {provedores ? provedores.map((prov, key) => {
                                    return (<option key={key} value={prov}>
                                        {prov}{" "}
                                    </option>);
                                }) : ""}
                            </Form.Select>
                            <Form.Label className='mt-4'>Nombre Producto</Form.Label>
                            <Form.Control
                                type="text"

                                autoFocus
                            />

                            <Form.Label className='mt-4'>Cantidad</Form.Label>
                            <Form.Control
                                type="number"

                                autoFocus
                            />
                            <Form.Label className='mt-4'>Marca</Form.Label>
                            <Form.Control
                                type="text"

                                autoFocus
                            />
                        </Form.Group>

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
                        <Card.Title className='fs-4 mb-2 text-gray-500'>Ingresos</Card.Title>
                        {ViewMonth(month, months)}
                        <Button variant="primary" className='mt-3 ' onClick={handleShow}>
                            Comprar Productos
                        </Button>
                    </Card.Header>
                    <hr/>
                    <Card.Body className="card-body__products">
                        <TableI/>
                    </Card.Body>
                </Card>
            </Container>
        </>
    )
}

export default Ingresos