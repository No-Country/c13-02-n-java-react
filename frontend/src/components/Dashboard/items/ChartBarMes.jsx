import { Chart } from "react-google-charts";

function ChartBarMes() {
  const data = [
    ["Mes", "Ventas", "Gastos", "Ganancias"],
    ["Enero", 1000, 400, 200],
    ["Febrero", 1170, 460, 250],
    ["Marzo", 660, 1120, 300],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
    ["Abril", 1030, 540, 350],
  ];

  const options = {
    chart: {
      title: "Ganancias",
      subtitle: "Sales, Expenses, and Profit: 2014-2017",
    },
  };
  return (
    <Chart
      chartType="Bar"
      width="100%"
      height="400px"
      data={data}
      options={options}

    />
  );
}

export default ChartBarMes;
