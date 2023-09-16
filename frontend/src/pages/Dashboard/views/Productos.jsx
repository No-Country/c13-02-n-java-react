import {useState} from "react";
import TableP from "../../../components/Tables/TableP.jsx";
import ModalCategory from "../../../components/Modals/ModalCategory.jsx";
import ModalProduct from "../../../components/Modals/ModalProduct.jsx";
import ModalBrand from "../../../components/Modals/ModalBrand.jsx";
import {Card, Container} from "react-bootstrap";

function Productos() {
    const [showCategory, setShowCategory] = useState(false);
    const [showProduct, setShowProduct] = useState(false);
    const [showBrand, setShowBrand] = useState(false);

    const handleCloseCategory = () => setShowCategory(false);
    const handleShowCategory = () => setShowCategory(true);

    const handleShowProduct = () => setShowProduct(true);
    const handleCloseProduct = () => setShowProduct(false);

    const handleShowBrand = () => setShowBrand(true);
    const handleCloseBrand = () => setShowBrand(false);


    return (
        <Container className="conainer-products">
            <Card className="card-products">

                <Card.Header className="card-header__products">
                    <Card.Title className="h3 mb-0 text-gray-800">Productos</Card.Title>
                    <div className="my-2">
                        <p>Cargue Productos, Categorías y Marcas </p>
                        <div className="w-100 d-flex  justify-content-end align-items-end gap-3  ">
                            <button
                                className="btn btn-warning text-white"
                                onClick={handleShowCategory}
                            >
                                <i className="fa-solid fa-list"></i> Categoría
                            </button>
                            <button
                                className="btn btn-primary text-white"
                                onClick={handleShowProduct}
                            >
                                <i className="fa-solid fa-plus"></i> Producto
                            </button>
                            <button
                                className="btn btn-info text-white  "
                                onClick={handleShowBrand}
                            >
                                <i className="fa-solid fa-copyright"></i> Marcas
                            </button>

                            {showCategory ? (<ModalCategory
                                show={showCategory}
                                setShow={setShowCategory}
                                handleClose={handleCloseCategory}
                                handleShow={handleShowCategory}
                            />) : ("")}
                            {showProduct ? (<ModalProduct
                                show={showProduct}
                                setShow={setShowProduct}
                                handleClose={handleCloseProduct}
                                handleShow={handleShowProduct}

                                handleShowCategory={handleShowCategory}
                                handleShowBrand={handleShowBrand}
                            />) : ("")}
                            {showBrand ? (<ModalBrand
                                show={showBrand}
                                setShow={setShowBrand}
                                handleClose={handleCloseBrand}
                                handleShow={handleShowBrand}
                            />) : ("")}
                        </div>
                    </div>
                </Card.Header>
                <hr/>
                <Card.Body className="card-body__products">
                    <TableP/>
                </Card.Body>
            </Card>
        </Container>
    );


}

export default Productos;
