import './Onboarding.css'
import Imagenes from '../../assets/Imagenes.jsx'
import {Link, Outlet} from 'react-router-dom'
import {useLocation, useNavigate} from 'react-router-dom'


function LayoutOnboarding() {

  const navigate = useNavigate()
  const location = useLocation()


  const nextPage = (location) => {

    if (location.pathname == '/') {

      navigate('/onboardingtwo')

    } else if (location.pathname == '/onboardingtwo') {

      navigate('/onboardingthree')

    } else if (location.pathname == '/onboardingthree') {


      navigate('/login')


    }


  }


  return (
      <div
          className='bg-onboarding min-vh-100  min-vw-100 justify-content-center align-content-center  text-center pt-5 px-3    '>

        <h1 className='text-white '>Fima</h1>

        <Outlet/>


        <ul className='w-25  m-auto list-unstyled d-flex justify-content-center  align-content-center gap-2 '>
          <li><Link className={`${location.pathname === '/' ? 'text-blue' : 'text-gray-300'}`} to={'/'}> <i
              className="fa-solid fa-circle"></i> </Link></li>
          <li><Link className={`${location.pathname === '/onboardingtwo' ? 'text-blue' : 'text-gray-300'}`}
                    to={'/onboardingtwo'}> <i className="fa-solid fa-circle"></i> </Link></li>
          <li><Link className={`${location.pathname === '/onboardingthree' ? 'text-blue' : 'text-gray-300'}`}
                    to={'/onboardingthree'}> <i className="fa-solid fa-circle"></i> </Link></li>
        </ul>


        <div className='m-auto  d-flex flex-column mt-4  justify-content-center  align-content-center gap-4 '>
          <button className='btn-boarding text-white  m-auto ' onClick={() => nextPage(location)}>Continuar
          </button>

          <Link className='' to={'/login'}>Omitir</Link>
        </div>

      </div>
  )
}

export default LayoutOnboarding