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
    auth ? localStorage.setItem("auth", "true") : localStorage.removeItem("auth");
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
                <Route key="dashboard" index={true} element={<Dashboard />} />,
                <Route key="ingresos" path={"/dashboard/ingresos"} element={<Ingresos />} />,
                <Route key="egresos" path={"/dashboard/egresos"} element={<Egresos />} />,
                <Route key="inventario" path={"/dashboard/inventario"} element={<Inventario />} />,
                <Route key="productos" path={"/dashboard/productos"} element={<Productos />} />,
                <Route key="pedidos" path={"/dashboard/pedidos"} element={<Pedidos />} />,
                <Route key="settings" path={"/dashboard/settings"} element={<Setting />} />,
              ]}
            />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
