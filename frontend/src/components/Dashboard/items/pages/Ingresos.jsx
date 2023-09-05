import React from 'react'
import CardValues from '../CardValues'
import { currentDate,months } from '../../../../config/Data/ArraysItems'
import ViewMonth from '../../../../hooks/ViewMonth'
import TableI from '../../../Tables/TableI'

function Ingresos() {


const month = currentDate.getMonth() +1








  return (
    <div>  
     
     <h1 className='fs-4 mb-2 text-gray-500'>Ingresos</h1>
     { ViewMonth(month,months)}
     

     <TableI/>
 
    
    


 
    
    
    </div>
  )
}

export default Ingresos