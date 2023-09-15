import { currentDate } from "../../../config/models/ArraysItems.js";
import CardValues from "../../../components/dashboard/CardValues.jsx";
import ChartBarMes from "../../../components/dashboard/ChartBarMes.jsx";
import ChartPie from "./ChartPie.jsx";

import getReport from "../../../services/products.js";
import { useEffect, useState } from "react";

function Dashboard() {
  const year = currentDate.getFullYear();

  const [data, setData] = useState();
  const [dataMonths, setDataMonths] = useState([["Task", "Hours per Day"]]);

  const getChart = async () => {
    const monthsResponse = await getReport.getAll("reports/months");

    const arrayValor = Object.values(monthsResponse);
    const arrayKeys = Object.keys(monthsResponse);

    const dataNew = arrayKeys.concat(arrayValor);

    setDataMonths([...dataMonths, dataNew]);

    console.log(dataMonths);
  };

  const getCards = async () => {
    try {
      const response = await getReport.getAll("reports");

      setData(response);

      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getCards();
    getChart();
  }, []);


  /* data para las cards de prueba  */
  const dataCardsDashboard = [
    {
      type: "Ganancias (Mes)",
      mount: `$ ${data?.totalProfitMonth}`,
      icon: "fas fa-calendar",
      color: "primary",
    },
    {
      type: "Ventas anuales",
      mount: `$ ${data?.totalSaleYear}`,
      icon: "fas fa-calendar",
      color: "warning",
    },
    {
      type: "Ventas Diarias",
      mount: `$ ${data?.totalSaleDay}`,
      icon: "fa-solid fa-truck",
      color: "success",
    },
    {
      type: "Costos anuales",
      mount: `$ ${data?.totalCostYear}`,
      icon: "fa-regular fa-clock",
      color: "danger",
    },
  ];

  const Balance = [
    ["Ganancias Anuales", "Gastos Anuales"],

    ["Ganancias", data?.totalSaleYear],
    ["Costos Anuales", data?.totalCostYear],
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
            data={data}
          />
        ))}
      </div>

      <div className="  container-fluid   d-flex flex-wrap   w-100  ">
        <div className=" col-xs-12 col-sm-12 col-md-6    col-lg-6   ">
          <ChartBarMes
            data={dataMonths}
            titulo={`Ganancias mensuales Año (${year})`}
          />
        </div>
        <div className="col-xs-12 col-sm-12 col-md-6   col-lg-6 ">
          <ChartBarMes data={Balance} titulo={"Balance Anual"} />
        </div>
      </div>
    </>
  );
}

export default Dashboard;
