import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import ProtectedRoute from "./hooks/ProtectedRoute.jsx";
// Pages
import * as Pages from "./pages";
import ErrorPage from "./pages/404/ErrorPage.jsx";

function App() {
  const [auth, setAuth] = useState(true);

  useEffect(() => {
    auth
      ? localStorage.setItem("auth", "true")
      : localStorage.removeItem("auth");
  }, [auth]);

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* Rutas sin proteger  */}
          <Route path="/" element={<Pages.Landing />} />
          <Route
            path="/login"
            element={<Pages.Login auth={auth} setAuth={setAuth} />}
          />
          <Route path="/register" element={<Pages.Register />} />
          <Route path="/recoverPassword" element={<Pages.RecoverPassword />} />
          <Route
            path="/recoverPassword/messageEmailconfirm"
            element={<Pages.MessageEmail />}
          />
          <Route
            path="/api/v1/password/:token"
            element={<Pages.ChangePassword />}
          />

          {/* Rutas protegidas */}
          <Route element={<ProtectedRoute auth={auth} />}>
            <Route
              path="/dashboard"
              element={<Pages.Layout />}
              children={[
                <Route
                  key="dashboard"
                  index={true}
                  element={<Pages.Dashboard />}
                />,
                <Route
                  key="ingresos"
                  path={"/dashboard/ingresos"}
                  element={<Pages.Ingresos />}
                />,
                <Route
                  key="egresos"
                  path={"/dashboard/egresos"}
                  element={<Pages.Egresos />}
                />,
                <Route
                  key="inventario"
                  path={"/dashboard/inventario"}
                  element={<Pages.Inventario />}
                />,
                <Route
                  key="productos"
                  path={"/dashboard/productos"}
                  element={<Pages.Productos />}
                />,
                <Route
                  key="pedidos"
                  path={"/dashboard/pedidos"}
                  element={<Pages.Pedidos />}
                />,
                <Route
                  key="settings"
                  path={"/dashboard/settings"}
                  element={<Pages.Setting />}
                />,
              ]}
            />
          </Route>

          <Route path="*" element={<Pages.ErrorPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
