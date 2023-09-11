import { Navigate,Outlet, Route } from "react-router-dom"
import {useState} from "react";

function ProtectedRoute({auth}) {
    const token = localStorage.getItem('token')
    const [isAuth, setIsAuth] = useState(auth)
    if (isAuth) {
        return <Outlet/>
    }else{
        return <Navigate to={'/login'} />
    }
}

export default ProtectedRoute