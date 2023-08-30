
import Sidebar from './items/Sidebar'
import TopBar from './items/TopBar'
import './dashboardmin.css'
import { Outlet } from 'react-router-dom'

function Layout() {
  return (
    <div id="page-top">
      <div id="wrapper">
        <Sidebar />

        <div id="content-wrapper" className="d-flex flex-column">
          <div id="content">
            <TopBar />

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