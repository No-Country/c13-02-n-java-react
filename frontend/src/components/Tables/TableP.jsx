import Table from "react-bootstrap/Table";
import {useEffect, useState} from "react";
import { categorias, opciones, productosPrueba } from "../../config/models/ArraysItems";
import useControlStock from "../../hooks/useControlStock";
import useGetProducts from "../../hooks/useGetProducts";

function TableP() {
    // const { formatoMoneda, controlStock,  } =
    //   useControlStock();
    //
    const {handleGetProducts, products} = useGetProducts();


    useEffect(() => {
        handleGetProducts().then((data) => {
            console.log(data.content);
        }).catch((error) => {
            console.error(error);
        });
    }, []);


    // const [query, setQuery] = useState();
    //
    // const handlechangeStock = (e) => {
    //   setQuery(e.target.value);
    //
    //   filtrarStock(e.target.value);
    // };
    // const handlechangeCategory = (e) => {
    //   setQuery(e.target.value);
    //
    //   filtrarCategory(e.target.value);
    // };
    //
    //      /* filtro  Stock */
    //      const filtrarCategory = (query) => {
    //       var producto = productosPrueba.filter((product) => {
    //
    //        if( product.categoria === query ) {
    //
    //         return product
    //
    //        }else if(query == 'todos') {
    //
    //         return product
    //
    //        }
    //       });
    //       setTablaProductos(producto);
    //     };
    //
    //
    //
    //      /* filtro  Stock */
    //      const filtrarStock = (query) => {
    //       var producto = productosPrueba.filter((product) => {
    //         if (query == "alto") {
    //           return product.cantidad > 90;
    //         } else if (query == "medio") {
    //           return product.cantidad >= 50 && product.cantidad < 80;
    //         } else if (query == "bajo") {
    //           return product.cantidad < 49;
    //         }else if(query == 'todos') {
    //
    //           return product
    //
    //         }
    //       });
    //       setTablaProductos(producto);
    //     };

  return (
    <>
     <div className="d-flex gap-2  w-100 ">
     <select
        className=" w-25  shadow-lg dropdown form-select"
        aria-label="Default select example"
         // onChange={handlechangeStock}
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
          //  onChange={handlechangeCategory}
      >
        <optgroup>
        
          <option selected disabled value="todos">Todas las categorías</option>

          {categorias.map((cat, key) => {
            return <option value={cat}>{cat} </option>;
          })}
        </optgroup>
      </select>
     </div>

        <Table size="sm" className="mt-5 text-center">
        <thead className="rounded-5">
          <tr>
            <th className="bg-primary text-white py-2   ">Estado</th>
            <th className="bg-primary text-white py-2   ">ID</th>
              <th className="bg-primary text-white py-2   ">Name</th>
              <th className="bg-primary text-white py-2   ">Price</th>
              <th className="bg-primary text-white py-2   ">Stock</th>
              <th className="bg-primary text-white py-2    ">Brand</th>
              <th className="bg-primary text-white py-2    ">Category</th>
          </tr>
        </thead>
            <tbody>
            {products.map((product) => (
                    <tr key={product.id}>
                        <td></td>
                        <td>{product.id} </td>
                        <td>{product.name} </td>
                        <td>{product.price} </td>
                        <td>{product.stock} </td>
                        <td>{product.brand.name}</td>
                        <td>{product.category.name}</td>
                        <td></td>
                    </tr>
                )
            )}
            </tbody>
      </Table>
    </>
  );
}

export default TableP;
