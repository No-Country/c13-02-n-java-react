
import '../customcolors.css'
import CardValues from "../CardValues";
import ChartBarMes from "../ChartBarMes";
import ChartPie from "./ChartPie";

function Dashboard() {

  const currentDate = new Date

  const year = currentDate.getFullYear()

  /* data para las cards de prueba  */
  const data = [
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
      mount:  `$ ${5900}`,
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

  const Balance = [["Ganancias Diarias", "Gastos Diarios"],

  ["Ganancias",5000],
  ["Costos",5900],
]

  return (
    <>
      <div className="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 className="h3 mb-0 text-gray-800">Dashboard</h1>
        <a
          href="#"
          className="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
        >
          <i className="fas fa-download fa-sm text-white-50"></i> Generar
          Reporte
        </a>
      </div>

      <div className="row">
        {data.map((card, key) => (
          <CardValues
            key={key}
            color={card.color}
            type={card.type}
            mount={card.mount}
            icon={card.icon}
          />
        ))}
      </div>

      
          <div   className= "  container-fluid   d-flex justify-content-center    ">
<div   className=' col-lg-6  col-xs-12  col-md-12   '>
<ChartBarMes data={dataMeses} titulo={`Ganancias Anuales (${year})`}/>
</div>
<div className=' col-lg-6  col-xs-12  col-md-12  '>
<ChartBarMes data={Balance} titulo={'Balance Diario'}/>
</div>
         
          </div>
           
        

        


    </>
  );
}

export default Dashboard;
