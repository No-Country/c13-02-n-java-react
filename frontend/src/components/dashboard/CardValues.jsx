import React from 'react'

function CardValues({icon, mount, color, type}) {
    return (
        <>
            {/*   <!-- Earnings (Monthly) Card Example --> */}
            <div className="col-xl-3 col-md-6 mb-4">
                <div className={`card border-left-${color}  shadow h-100 py-2`}>
                    <div className="card-body">
                        <div className="row no-gutters align-items-center">
                            <div className="col mr-2 px-2   ">
                                <div className={`text-xs font-weight-bold  text-${color} text-uppercase mb-1`}>
                                    {type} </div>
                                <div className="h5 mb-0 font-weight-bold text-gray-800">{mount}  </div>
                            </div>
                            <div className="col-auto mr-2">
                                <i className={`${icon} fa-2x text-gray-300 `}></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default CardValues