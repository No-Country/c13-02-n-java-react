import React from 'react'

function useControlStock() {
  



    function controlStock (stock)  {

        if(stock >= 50 && stock < 80){
      
          return <p className="bg-warning  text-white  rounded-2 " >Stock medio</p>
      
        }else if(stock < 50) {
      
          return <p className="bg-danger text-white  rounded-2 " >Stock bajo</p>
      
      
          
        }else if(stock > 80) {
          return <p className="bg-success text-white  rounded-2 " >Stock Alto</p>
      
          
        }
      };

/* formatea a moneda el string */

      function formatoMoneda(numero) {
        // Formatear el número como una cadena con dos decimales y comas separando miles
        const formato = new Intl.NumberFormat("es-ES", {
          style: "currency",
          currency: "USD",
          minimumFractionDigits: 2,
        });
      
        // Devolver la cadena formateada
        return formato.format(numero);
      
       
      }





      /* filtross */

      /* filtro por categoria  */


 

      return{
        controlStock,
        formatoMoneda,
       
      }
}

export default useControlStock