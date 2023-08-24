import { Navigate,Outlet, Route } from "react-router-dom"

function ProtectedRoute({auth}) {
 


   

    if(auth) {

        return <Outlet/>
        
    }else{

      
        return <Navigate to={'/login'} />
    }


}

export default ProtectedRoute