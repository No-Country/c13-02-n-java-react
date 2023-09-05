import React from 'react'
import ViewMonth from '../../../components/dashboard/ViewMonth.jsx'
import {currentDate, months} from '../../../config/models/ArraysItems.js'
import TableE from '../../../components/Tables/TableE.jsx'

function Egresos() {
    const month = currentDate.getMonth() + 1
    return (
        <div><h1 className="fs-4 mb-2 text-gray-500">Egresos</h1>
            {ViewMonth(month, months)}
            <TableE/>
        </div>
    )
}

export default Egresos