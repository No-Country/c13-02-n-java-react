import React from 'react'
import CardValues from '../../../components/dashboard/CardValues.jsx'
import {currentDate, months} from '../../../config/models/ArraysItems.js'
import ViewMonth from '../../../components/dashboard/ViewMonth.jsx'
import TableI from '../../../components/Tables/TableI.jsx'

function Ingresos() {

    const month = currentDate.getMonth() + 1

    return (
        <div>
            <h1 className='fs-4 mb-2 text-gray-500'>Ingresos</h1>
            {ViewMonth(month, months)}
            <TableI/>
        </div>
    )
}

export default Ingresos