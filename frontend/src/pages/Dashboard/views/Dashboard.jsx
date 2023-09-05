import "../../../components/css/customcolors.css";
import {
  dataMeses,
  dataCardsDashboard,
  currentDate,
} from "../../../config/models/ArraysItems.js";
import CardValues from "../../../components/dashboard/CardValues.jsx";
import ChartBarMes from "../../../components/dashboard/ChartBarMes.jsx";
import ChartPie from "./ChartPie.jsx";

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

      <div className="  container-fluid   d-flex flex-wrap   w-100  ">
        <div className=" col-xs-12 col-sm-12 col-md-6    col-lg-6   ">
          <ChartBarMes
            data={dataMeses}
            titulo={`Ganancias Anuales (${year})`}
          />
        </div>
        <div className="col-xs-12 col-sm-12 col-md-6   col-lg-6 ">
          <ChartBarMes data={Balance} titulo={"Balance Diario"} />
        </div>
      </div>
    </>
  );
}

export default Dashboard;
