import Table from "react-bootstrap/Table";
import {useEffect, useState} from "react";
import {categorias, opciones, productosPrueba} from "../../config/models/ArraysItems";
import useControlStock from "../../hooks/useControlStock";
import useGetProducts from "../../hooks/useGetProducts";
import {Card, Container, Button} from "react-bootstrap";
import "../../pages/css/Landing.css"
import {BiEdit, BiTrash} from 'react-icons/bi';

function TableP() {
    const {formatoMoneda, controlStock,} = useControlStock();
    const {handleGetProducts, products} = useGetProducts();
    const [tablaProductos, setTablaProductos] = useState(products);
    const [query, setQuery] = useState();
    const [currentPage, setCurrentPage] = useState(1);
    const productsPerPage = 10;

    const indexOfLastProduct = currentPage * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    const currentProducts = products.slice(indexOfFirstProduct, indexOfLastProduct);

    const totalPages = Math.ceil(products.length / productsPerPage);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };


    useEffect(() => {
        handleGetProducts().then((data) => {
            console.log(data.content);
        }).catch((error) => {
            console.error(error);
        });
    }, []);

    const handlechangeStock = (e) => {
        setQuery(e.target.value);

        filtrarStock(e.target.value);
    };
    const handlechangeCategory = (e) => {
        setQuery(e.target.value);

        filtrarCategory(e.target.value);
    };
    /* filtro  Stock */
    const filtrarCategory = (query) => {
        const producto = tablaProductos.filter((product) => {
            if (product.categoria === query) {
                return product
            } else if (query === 'todos') {
                return product
            }
        });
        setTablaProductos(producto);
    };
    /* filtro  Stock */
    const filtrarStock = (query) => {
        const producto = tablaProductos.filter((product) => {
            if (query === "alto") {
                return product.cantidad > 90;
            } else if (query === "medio") {
                return product.cantidad >= 50 && product.cantidad < 80;
            } else if (query === "bajo") {
                return product.cantidad < 49;
            } else if (query === 'todos') {
                return product
            }
        });
        setTablaProductos(producto);
    };

    return (
        <>
            <Table size="sm" className="text-left ">
                <thead className="rounded-5 text-center">
                <tr>
                    <th className="bg-primary text-white py-2   ">Estado</th>
                    <th className="bg-primary text-white py-2   ">ID</th>
                    <th className="bg-primary text-white py-2   ">Nombre</th>
                    <th className="bg-primary text-white py-2   ">Precio</th>
                    <th className="bg-primary text-white py-2   ">Stock</th>
                    <th className="bg-primary text-white py-2    ">Marcas</th>
                    <th className="bg-primary text-white py-2    ">Categorias</th>
                    <th className="bg-primary text-white py-2    ">E&E</th>
                </tr>
                </thead>
                <tbody className="table-text__products">
                {currentProducts.map((product, key) => (
                    <tr key={product.id} className="my-2 ">
                        <td className="text-center">{controlStock(product.stock)}  </td>
                        <td>{product.id} </td>
                        <td>{product.name} </td>
                        <td>{formatoMoneda(product.price)} </td>
                        <td>{product.stock} </td>
                        <td>{product.brand.name}</td>
                        <td>{product.category.name}</td>
                        {/* Darle un gap a los botones*/}
                        <td>
                            <Button variant="outline-primary" className="btn-sm mr-2"><BiEdit/></Button>
                            <Button variant="outline-danger" className="btn-sm"><BiTrash/></Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            <div className='pagination'>
                {
                    Array.from({length: totalPages}, (_, index) => (
                        <button
                            key={index}
                            onClick={() => handlePageChange(index + 1)}
                            className={index + 1 === currentPage ? 'active' : null}
                        >
                            {index + 1}
                        </button>
                    ))
                }
            </div>
        </>
    );
}

export default TableP;
