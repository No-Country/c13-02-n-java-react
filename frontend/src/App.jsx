import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import ProtectedRoute from "./hooks/ProtectedRoute.jsx";
// Pages
import * as Pages from "./pages";
import ErrorPage from "./pages/404/ErrorPage.jsx";
import useGetScreen from "./hooks/useGetScreen.jsx";

import LayoutOnboarding from "./components/Onboarding/LayoutOnboarding.jsx";
import OnboardingOne from "./components/Onboarding/OnboardingOne.jsx";
import Onboardingtwo from "./components/Onboarding/Onboardingtwo.jsx";
import Onboardingthree from "./components/Onboarding/Onboardingthree.jsx";

function App() {

  const {setIsDesktop,isdesktop,getScreen} = useGetScreen()
  const [auth, setAuth] = useState(localStorage.getItem('auth') || false );

  useEffect(() => {
    auth
      ? localStorage.setItem("auth", "true")
      :''
  }, [auth]);


  

  useEffect(() => {


    getScreen()

   
  }, [])
  



  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* Rutas sin proteger  */}
          {

            isdesktop ?<Route path="/" element={<Pages.Landing />} />   :<Route path="/" element={ <LayoutOnboarding   />} children={[



              <Route index={true}  element={<OnboardingOne/>}  />,
              <Route path="/onboardingtwo" element={<Onboardingtwo/>}  />,
              <Route path="/onboardingthree" element={<Onboardingthree/>}  />

            ]} /> 
          }

          <Route path="/login" element={<Pages.Login auth={auth} setAuth={setAuth} />} />
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
                /*     <Route
                  key="inventario"
                  path={"/dashboard/inventario"}
                  element={<Pages.Inventario />}
                  />, */
                <Route
                  key="productos"
                  path={"/dashboard/productos"}
                  element={<Pages.Productos />}
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
