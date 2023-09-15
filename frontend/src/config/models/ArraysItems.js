const menuItems = [
  {
    href: "account",
    label: "Account Settings",
  },
  {
    href: "security",
    label: "Login & Security",
  },
  {
    href: "additionalSettings",
    label: "Additional Settings",
  },
  {
    href: "about",
    label: "About",
  },
];






/* meses para mostrar en ingresos y egresos */
const months = {
  1: "Enero",
  2: "Febrero",
  3: "Marzo",
  4: "Abril",
  5: "Mayo",
  6: "Junio",
  7: "Julio",
  8: "Agosto",
  9: "Setiembre",
  10: "Octubre",
  11: "Noviembre",
  12: "Diciembre",
};

const productosPrueba = [
  {
    id: 1,
    producto: "Camiseta",
    fecha: 2323,
    cantidad: 345,
    precioUnitario: 19.99,
    categoria: "Ropa"
  },
  {
    id: 2,
    producto: "Zapatos",
    fecha: 2323,
    cantidad: 120,
    precioUnitario: 59.99,
    categoria: "Calzado"
  },
  {
    id: 3,
    producto: "Teléfono móvil",
    fecha: 2323,
    cantidad: 50,
    precioUnitario: 299.99,
    categoria: "Electrónica"
  },
  {
    id: 4,
    producto: "Libro",
    fecha: 2323,
    cantidad: 200,
    precioUnitario: 12.99,
    categoria: "Libros"
  },
  {
    id: 5,
    producto: "Silla",
    fecha: 2323,
    cantidad: 30,
    precioUnitario: 49.99,
    categoria: "Muebles"
  },
  {
    id: 6,
    producto: "Nevera",
    fecha: 2323,
    cantidad: 10,
    precioUnitario: 599.99,
    categoria: "Electrodomésticos"
  },
  {
    id: 7,
    producto: "Cafetera",
    fecha: 2323,
    cantidad: 60,
    precioUnitario: 29.99,
    categoria: "Electrodomésticos"
  },
  {
    id: 8,
    producto: "Televisor",
    fecha: 2323,
    cantidad: 15,
    precioUnitario: 399.99,
    categoria: "Electrónica"
  },
  {
    id: 9,
    producto: "Bicicleta",
    fecha: 2323,
    cantidad: 5,
    precioUnitario: 199.99,
    categoria: "Deportes"
  },
  {
    id: 10,
    producto: "Sofá",
    fecha: 2323,
    cantidad: 20,
    precioUnitario: 499.99,
    categoria: "Muebles"
  },
  {
    id: 11,
    producto: "Cuchillo",
    fecha: 2323,
    cantidad: 100,
    precioUnitario: 9.99,
    categoria: "Utensilios de Cocina"
  },
  {
    id: 12,
    producto: "Plato",
    fecha: 2323,
    cantidad: 200,
    precioUnitario: 3.99,
    categoria: "Utensilios de Cocina"
  },
  {
    id: 13,
    producto: "Taza",
    fecha: 2323,
    cantidad: 150,
    precioUnitario: 2.99,
    categoria: "Utensilios de Cocina"
  },
  {
    id: 14,
    producto: "Lavadora",
    fecha: 2323,
    cantidad: 8,
    precioUnitario: 399.99,
    categoria: "Electrodomésticos"
  },
  {
    id: 15,
    producto: "Microondas",
    fecha: 2323,
    cantidad: 15,
    precioUnitario: 89.99,
    categoria: "Electrodomésticos"
  },
  {
    id: 16,
    producto: "Botella de agua",
    fecha: 2323,
    cantidad: 500,
    precioUnitario: 1.99,
    categoria: "Artículos de Consumo"
  },
  {
    id: 17,
    producto: "Cama",
    fecha: 2323,
    cantidad: 10,
    precioUnitario: 299.99,
    categoria: "Muebles"
  },
  {
    id: 18,
    producto: "Tostadora",
    fecha: 2323,
    cantidad: 30,
    precioUnitario: 19.99,
    categoria: "Electrodomésticos"
  },
  {
    id: 19,
    producto: "Reloj",
    fecha: 2323,
    cantidad: 50,
    precioUnitario: 79.99,
    categoria: "Accesorios"
  },
  {
    id: 20,
    producto: "Ventilador",
    fecha: 2323,
    cantidad: 25,
    precioUnitario: 39.99,
    categoria: "Electrodomésticos"
  }
];
/* categorias */

const categorias = [
  
  "Ropa",
  "Calzado",
  "Electrónica",
  "Libros",
  "Muebles",
  "Electrodomésticos",
  "Deportes",
  "Utensilios de Cocina",
  "Artículos de Consumo",
  "Accesorios"
];

/* niveles de Stock */

const opciones = [
  { value: "alto", label: "Alto" },
  { value: "medio", label: "Medio" },
  { value: "bajo", label: "Bajo" }
];



const currentDate = new Date();

export {
  menuItems,
  // formGroups,

 
  currentDate,
  months,
  productosPrueba,
  categorias,
  opciones
};
