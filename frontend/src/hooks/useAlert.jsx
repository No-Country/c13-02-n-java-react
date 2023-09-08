import Imagenes from "../assets/imagenes"


function useAlert({type,title,text,info}) {
  
// Tipos de alerta [error,success,warning,info,question]
   return Swal.fire({
        /* icon: `${type}`, */
        imageUrl : Imagenes.avatar,
        imageWidth :'100px',
        title: `${title }`,
        text: `${text ? text :''}`,
      
        confirmButtonColor : '#594fff',
        position :'top'
    
      }  )


}

export default useAlert