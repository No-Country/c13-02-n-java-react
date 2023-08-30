import CardValues from "../CardValues";
import ChartBarMes from "../ChartBarMes";
import ChartPie from "./ChartPie";

function Dashboard() {
  /* data para las cards de prueba  */
  const data = [
    {
      type: "Ganancias Mensuales",
      mount: `$ ${12000}`,
      icon: "fas fa-calendar",
      color: "primary",
    },
    {
      type: "Ganancias Diarias",
      mount: `$ ${1000}`,
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
      type: "Pedidos Pendientes",
      mount: 15,
      icon: "fa-regular fa-clock",
      color: "danger",
    },
  ];

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

      <div className="row ">
        <ChartBarMes />
      </div>

      <div className="d-flex gap-5">
        <ChartPie
          title={"Porcentaje de Pedidos"}
          nameOne={"Pedidos Entregados"}
          valueOne={75}
          nameTwo={"Pedidos Pendientes"}
          valueTwo={15}
          colorOne={'#1CC88A'}
          colorTwo={'#E85345'}
        />
        <ChartPie
         title={"Porcentaje de Ganancias"}
          nameOne={"Ganancias Mensuales"}
          valueOne={75}
          nameTwo={"Ganancias Pendientes"}
          valueTwo={15}
          colorOne={'#486DDA'}
          colorTwo={'#F9CD3E'}
        />
      </div>
    </>
  );
}

export default Dashboard;
