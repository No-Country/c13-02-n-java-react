import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";

// Pages
import Landing from "./components/Landing/Landing";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";

import ProtectedRoute from "./hooks/ProtectedRoute";
import Setting from "./components/Profile/Setting.jsx";
import Layout from "./components/Dashboard/Layout";
import Dashboard from "./components/Dashboard/items/pages/Dashboard";
import Ingresos from "./components/Dashboard/items/pages/Ingresos";
import Egresos from "./components/Dashboard/items/pages/Egresos";
import Inventario from "./components/Dashboard/items/pages/Inventario";
import Productos from "./components/Dashboard/items/pages/Productos";
import Pedidos from "./components/Dashboard/items/pages/Pedidos";
import Settings from "./components/Dashboard/items/pages/Settings";

function App() {
  const [auth, setAuth] = useState(true);

  useEffect(() => {
    console.log(auth);
  }, [auth]);

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* Rutas sin proteger  */}
          <Route path="/" element={<Landing />} />
          <Route
            path="/login"
            element={<Login auth={auth} setAuth={setAuth} />}
          />
          <Route path="/register" element={<Register />} />

          {/* Rutas protegidas */}
          <Route element={<ProtectedRoute auth={auth} />}>
            <Route
              path="/dashboard"
              element={<Layout />}

              children={[
                <Route index={true} element={<Dashboard />} />,
                <Route path={"/dashboard/ingresos"} element={<Ingresos />} />,
                <Route path={"/dashboard/egresos"} element={<Egresos />} />,
                <Route
                  path={"/dashboard/inventario"}
                  element={<Inventario />}
                />,
                <Route path={"/dashboard/productos"} element={<Productos />} />,
                <Route path={"/dashboard/pedidos"} element={<Pedidos />} />,
                <Route path={"/dashboard/settings"} element={<Setting />} />,
              ]}
            />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
