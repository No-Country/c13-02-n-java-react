import Table from 'react-bootstrap/Table';
import {productosPrueba} from '../../config/models/ArraysItems';

function TableP() {
  return (
    <Table   hover size="sm" className='mt-5 text-center     ' >
      <thead className='rounded-5'  >
        <tr >
          <th className='bg-primary text-white py-2   '>ID</th>
          <th className='bg-primary text-white py-2   '>Producto</th>
          <th className='bg-primary text-white py-2   '>Fecha</th>
          <th className='bg-primary text-white py-2   '>Cantidad</th>
          <th className='bg-primary text-white py-2    '>Precio unitario</th>
          <th className='bg-primary text-white py-2    '>Total</th>
        </tr>
      </thead>
      <tbody>
       
       {productosPrueba.map((pr,key)=> {

        return  <tr key={key}>
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

export default TableP;