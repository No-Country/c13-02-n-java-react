import React from 'react'
import { Link } from 'react-router-dom'
import './ErrorPage.css'
function ErrorPage() {
  return (
    
    <section className="page_404">
	<div className="container-fluid w-100   justify-content-center  align-items-center ">
		<div className="row">	
		<div className="col-md-12 col-xl-12  ">
		<div className="col-sm-12 col-sm-offset-1  text-center">
		<div className="four_zero_four_bg">
			<h1 className="text-center ">404</h1>
		
		
		</div>
		
		<div className="contant_box_404">
		<h3 className="h2">
		Veo que te perdiste 
		</h3>
		
		<p>Página no disponible </p>
		
		<Link to={'/'} className="link_404">Volver a inicio </Link>
	</div>
		</div>
		</div>
		</div>
	</div>
</section>
  )
}

export default ErrorPage