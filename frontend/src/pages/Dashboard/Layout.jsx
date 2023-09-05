import Sidebar from '../../components/dashboard/Sidebar.jsx'
import TopBar from '../../components/dashboard/TopBar.jsx'
import './dashboardmin.css'
import {Outlet} from 'react-router-dom'

function Layout() {
    return (
        <div id="page-top">
            <div id="wrapper">
                <Sidebar/>
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <TopBar/>
                        <div className="container-fluid">
                            <Outlet/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Layout