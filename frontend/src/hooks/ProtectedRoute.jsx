import { Navigate,Outlet, Route } from "react-router-dom"

function ProtectedRoute({auth}) {


    console.log(auth,'desde protected');
    if(auth) {
        return <Outlet/>
    }else{
        return <Navigate to={'/login'} />
    }
}

export default ProtectedRoute