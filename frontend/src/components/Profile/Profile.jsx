import React, { useState } from 'react'

const Profile = () => {
    const [editingUsername, setEditingUsername] = useState(false)
    const [newUsername, setNewUsername] = useState('Nombre de Usuario')

    const handleUsernameEdit = () => {
        setEditingUsername(true)
    }

    const handleUsernameSave = () => {
        setEditingUsername(false)
    }

    const handleUsernameChange = (event) => {
        setNewUsername(event.target.value)
    }

    return (
        <div className='bg-white p-8 shadow-md rounded-md mt-4'>
            <h2 className='text-xl font-semibold mb-4'>Perfil de Usuario</h2>
            <div className='flex items-center space-x-4'>
                <img
                    src='https://via.placeholder.com/100'
                    alt='Profile'
                    className='w-20 h-20 rounded-full'
                />
                <div>
                    {editingUsername ? (
                        <div className='flex items-center'>
                            <input
                                type='text'
                                value={newUsername}
                                onChange={handleUsernameChange}
                                className='border rounded-md px-2 py-1 mr-2'
                            />
                            <button
                                onClick={handleUsernameSave}
                                className='bg-blue-500 text-white px-2 py-1 rounded-md'
                            >
                                Guardar
                            </button>
                        </div>
                    ) : (
                        <div className='flex items-center'>
                            <h3 className='text-lg font-medium'>{newUsername}</h3>
                            <button onClick={handleUsernameEdit} className='text-blue-500 ml-2'>
                                Editar
                            </button>
                        </div>
                    )}
                    <p className='text-gray-600'>correo@example.com</p>
                </div>
            </div>
            <p className='mt-4'>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod justo vitae
                volutpat.
            </p>
            <div className='mt-4'>
                <button className='text-blue-500'>Cambiar Foto de Perfil</button>
                <button className='text-blue-500 ml-4'>Cambiar Contraseña</button>
            </div>
        </div>
    )
}

export default Profile
