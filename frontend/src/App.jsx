
import {
  createBrowserRouter,
  BrowserRouter,
  Routes,
  Route,
  RouterProvider,
  Router,
  HashRouter,
} from "react-router-dom";

import Landing from "./components/Landing/Landing";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import Dashboard from "./components/Dashboard/Dashboard";
import ProtectedRoute from "./hooks/ProtectedRoute";
import { useState,useEffect } from "react";

/* const router = createBrowserRouter([
  {
    path: "/",
    element: <Landing />,
  },

  {
    path: "/login",
    element: <Login />,
  },

  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/dashboard",
    element: <Dashboard />,
  },
]); */

function App() {

const [auth,setAuth] = useState(false)


useEffect(() => {
 

  console.log(auth);
}, [auth])


  return (
    <>
   
   <BrowserRouter>
      <Routes>
        {/* Rutas sin proteger  */}
        <Route path="/" element={<Landing/>}  />
        <Route path="/login" element={<Login auth={auth} setAuth={setAuth}  />}  />
        <Route path="/register" element={<Register/>} />
        {/* Rutas protegidas */}

        <Route element={<ProtectedRoute auth={auth} />} >

        <Route path="/dashboard" element={<Dashboard/>} />

        </Route>
      </Routes>

   </BrowserRouter>
    {/*   <RouterProvider router={router} /> */}

    </>
  );
}

export default App;
