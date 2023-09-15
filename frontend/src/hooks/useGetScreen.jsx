import { useState } from "react";

function useGetScreen() {
  const [isdesktop, setIsDesktop] = useState(true);

  const getScreen = () => {
    // Obtiene la resolución de pantalla del usuario
    var anchoPantalla =
      window.innerWidth ||
      document.documentElement.clientWidth ||
      document.body.clientWidth;
    var altoPantalla =
      window.innerHeight ||
      document.documentElement.clientHeight ||
      document.body.clientHeight;

    // Comprueba la resolución y toma una decisión en función de ella
    if (anchoPantalla >= 1043 && altoPantalla >= 715) {
      // Código para pantallas de alta resolución (Full HD o superior)

      setIsDesktop(true);

      console.log("Pantalla de alta resolución");
    } else {
      // Código para pantallas de baja resolución
      setIsDesktop(false);
    
      console.log("Pantalla de baja resolución");
      // Puedes cambiar la vista o aplicar estilos específicos aquí
    }
  };
  return {
    getScreen,
    setIsDesktop,
    isdesktop,
  };
}

export default useGetScreen;
