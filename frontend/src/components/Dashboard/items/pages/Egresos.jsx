import React from 'react'
import ViewMonth from '../../../../hooks/ViewMonth'
import { currentDate,months } from '../../../../config/Data/ArraysItems'
import TableE from '../../../Tables/TableE'



function Egresos() {

  const month = currentDate.getMonth() +1


  
  return (
    <div>  <h1 className="fs-4 mb-2 text-gray-500">Egresos</h1>
    
    {ViewMonth(month,months)}


    <TableE />


    

    
    </div>
  )
}

export default Egresos