import Table from 'react-bootstrap/Table';
import {productosPrueba} from '../../config/models/ArraysItems';

function TableE() {
  return (
    <Table   hover size="sm" className='mt-5 text-center  ' >
      <thead  >
        <tr >
          <th className='bg-primary text-white py-2  rounded-1  '>ID</th>
          <th className='bg-primary text-white py-2  rounded-1  '>Producto</th>
          <th className='bg-primary text-white py-2  rounded-1  '>Fecha</th>
          <th className='bg-primary text-white py-2  rounded-1  '>Cantidad</th>
          <th className='bg-primary text-white py-2  rounded-1  '>Precio unitario</th>
          <th className='bg-primary text-white py-2  rounded-1  '>Total</th>
        </tr>
      </thead>
      <tbody>
       
       {productosPrueba.map((pr)=> {

        return  <tr>
        <td>{pr.id} </td>
        <td>{pr.producto}  </td>
        <td>{pr.fecha} </td>
        <td>{pr.cantidad} </td>
        <td>$ {pr.precioUnitario} </td>
        <td>$ {pr.total} </td>
      </tr>
       })}
      
       
      </tbody>
    </Table>
  );
}

export default TableE;