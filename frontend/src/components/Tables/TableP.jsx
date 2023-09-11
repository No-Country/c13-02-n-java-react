import Table from "react-bootstrap/Table";

import { useState } from "react";

import { categorias, opciones, productosPrueba } from "../../config/models/ArraysItems";
import useControlStock from "../../hooks/useControlStock";

function TableP() {
  const { formatoMoneda, controlStock,  } =
    useControlStock();

  const [tablaProductos, setTablaProductos] = useState(...[productosPrueba]);
  const [query, setQuery] = useState();

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
        var producto = productosPrueba.filter((product) => {
          
         if( product.categoria === query ) {
    
          return product
          
         }else if(query == 'todos') {
    
          return product
          
         }
        });
        setTablaProductos(producto);
      };
    
    
    
       /* filtro  Stock */
       const filtrarStock = (query) => {
        var producto = productosPrueba.filter((product) => {
          if (query == "alto") {
            return product.cantidad > 90;
          } else if (query == "medio") {
            return product.cantidad >= 50 && product.cantidad < 80;
          } else if (query == "bajo") {
            return product.cantidad < 49;
          }else if(query == 'todos') {
    
            return product
            
          }
        });
        setTablaProductos(producto);
      };

  return (
    <>
     <div className="d-flex gap-2  w-100 ">
     <select
        className=" w-25  shadow-lg dropdown form-select"
        aria-label="Default select example"
        onChange={handlechangeStock}
      >
        <optgroup>
          <option selected disabled>
            Stock
          </option>

          <option value={"todos"}>Todos</option>
         {opciones.map((op,key)=> {

          return(
            <option value={op}>{op.label} </option>
          )
         })}
        </optgroup>
      </select>
      <select
        className=" w-25  shadow-lg dropdown form-select"
        aria-label="Default select example"
        onChange={handlechangeCategory}
      >
        <optgroup>
        
          <option selected disabled value="todos">Todas las categorías</option>

          {categorias.map((cat, key) => {
            return <option value={cat}>{cat} </option>;
          })}
        </optgroup>
      </select>
     </div>

      <Table size="sm" className="mt-5 text-center    ">
        <thead className="rounded-5">
          <tr>
            <th className="bg-primary text-white py-2   ">Estado</th>
            <th className="bg-primary text-white py-2   ">ID</th>
            <th className="bg-primary text-white py-2   ">Producto</th>
            <th className="bg-primary text-white py-2   ">Fecha</th>
            <th className="bg-primary text-white py-2   ">Cantidad</th>
            <th className="bg-primary text-white py-2    ">Precio unitario</th>
            <th className="bg-primary text-white py-2    ">Total</th>
            <th className="bg-primary text-white py-2    ">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {tablaProductos.map((pr, key) => {
            return (
              <tr key={key}>
                <td>{controlStock(pr.cantidad)}</td>
                <td>{pr.id} </td>
                <td>{pr.producto} </td>
                <td>{pr.fecha} </td>
                <td>{pr.cantidad} </td>
                <td>{formatoMoneda(pr.precioUnitario)} </td>
                <td>{formatoMoneda(pr.cantidad * pr.precioUnitario)} </td>
                <td> </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </>
  );
}

export default TableP;
