import "../customcolors.css";
import {
  dataMeses,
  dataCardsDashboard,
  currentDate
} from "../../../../config/Data/ArraysItems";
import CardValues from "../CardValues";
import ChartBarMes from "../ChartBarMes";
import ChartPie from "./ChartPie";

function Dashboard() {
  

  const year = currentDate.getFullYear();

  const Balance = [
    ["Ganancias Diarias", "Gastos Diarios"],

    ["Ganancias", 5000],
    ["Costos", 5900],
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
        {dataCardsDashboard.map((card, key) => (
          <CardValues
            key={key}
            color={card.color}
            type={card.type}
            mount={card.mount}
            icon={card.icon}
          />
        ))}
      </div>

      <div className="  container-fluid   d-flex justify-content-center    ">
        <div className=" col-lg-6  col-xs-12  col-md-12   ">
          <ChartBarMes
            data={dataMeses}
            titulo={`Ganancias Anuales (${year})`}
          />
        </div>
        <div className=" col-lg-6  col-xs-12  col-md-12  ">
          <ChartBarMes data={Balance} titulo={"Balance Diario"} />
        </div>
      </div>
    </>
  );
}

export default Dashboard;
