import Imagenes from "../../assets/Imagenes.jsx"

function OnboardingOne() {
    return (
        <>
            <img className='z-2 mt-5  ' src={Imagenes.chartOnb1} alt=""/>
            <h2 className='fs-4 mt-4   '>¡Te damos la bienvenida a tu nuevo gestor de finanzas e inventario!</h2>
            <p className='w-100  mt-4 mb-4  '>Con Fima podrás gestionar tu negocio y tus finanzas desde tu celular o
                computadora.</p>
        </>
    )
}

export default OnboardingOne