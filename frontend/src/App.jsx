import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { useState, useEffect } from 'react'

// Pages
import Landing from './components/Landing/Landing'
import Login from './components/Login/Login'
import Register from './components/Register/Register'
import Dashboard from './components/Dashboard/Dashboard'
import ProtectedRoute from './hooks/ProtectedRoute'
import Profile from './components/Profile/Profile'

function App() {
    const [auth, setAuth] = useState(false)

    useEffect(() => {
        console.log(auth)
    }, [auth])

    return (
        <>
            <BrowserRouter>
                <Routes>
                    {/* Rutas sin proteger  */}
                    <Route path='/' element={<Landing />} />
                    <Route path='/login' element={<Login auth={auth} setAuth={setAuth} />} />
                    <Route path='/register' element={<Register />} />
                    <Route path='/profile' element={<Profile />} />

                    {/* Rutas protegidas */}
                    <Route element={<ProtectedRoute auth={auth} />}>
                        <Route path='/dashboard' element={<Dashboard />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </>
    )
}

export default App
