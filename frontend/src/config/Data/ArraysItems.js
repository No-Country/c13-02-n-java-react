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

const formGroups = [
  [
    { label: "Username", type: "text", placeholder: "Username" },
    { label: "Email", type: "email", placeholder: "Enter email" },
  ],
  [
    { label: "Business Name", type: "text", placeholder: "Business Name" },
    { label: "Password", type: "password", placeholder: "Password" },
  ],
  [{ label: "Phone", type: "number", placeholder: "Phone" }],
];

/* data para las cards de prueba  */
const dataCardsDashboard = [
  {
    type: "Ganancias netas (Mes)",
    mount: `$ ${12000}`,
    icon: "fas fa-calendar",
    color: "primary",
  },
  {
    type: "Ganancias Diarias",
    mount: `$ ${5000}`,
    icon: "fas fa-calendar",
    color: "warning",
  },
  {
    type: "Pedidos Entregados",
    mount: 75,
    icon: "fa-solid fa-truck",
    color: "success",
  },
  {
    type: "Costos",
    mount: `$ ${5900}`,
    icon: "fa-regular fa-clock",
    color: "danger",
  },
];

const dataMeses = [
  ["Meses", "Ganancias"],
  ["Enero", 1150],
  ["Febrero", 12200],
  ["Marzo", 7851],
  ["Abril", 5230],
  ["Mayo ", 7845],
  ["Junio ", 9632],
  ["Julio", 4123],
  ["Agosto", 12008],
  ["Setiembre", 7895],
  ["Octubre", 7451],
  ["Noviembre", 12365],
  ["Diciembre", 4578],
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
    producto: "papel",
    fecha: 2323,
    cantidad: 245,
    precioUnitario: 14,
    total: 4512,
  },
  {
    id: 1,
    producto: "papel",
    fecha: 2323,
    cantidad: 245,
    precioUnitario: 14,
    total: 4512,
  },
  {
    id: 1,
    producto: "papel",
    fecha: 2323,
    cantidad: 245,
    precioUnitario: 14,
    total: 4512,
  },
  {
    id: 1,
    producto: "papel",
    fecha: 2323,
    cantidad: 245,
    precioUnitario: 14,
    total: 4512,
  },
];

const currentDate = new Date();

export {
  menuItems,
  formGroups,
  dataCardsDashboard,
  dataMeses,
  currentDate,
  months,
  productosPrueba
};
