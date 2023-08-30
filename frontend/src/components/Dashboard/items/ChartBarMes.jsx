
import {
  BarChart,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Bar,
  Legend,
  LabelList,
  Label,
} from "recharts";


function ChartBarMes() {
  
  const data = [
    {
      name: 'Enero',
      Ganancias: 1500
    },
    {
      name: 'Febrero',
      Ganancias: 4620
    },
    {
      name: 'Marzo',
      Ganancias: 7800
    },
    {
      name: 'Abril',
      Ganancias: 2500
    },
    {
      name: 'Mayo',
      Ganancias: 3650
    },
    {
      name: 'Junio',
      Ganancias: 6900
    },
    {
      name: 'Julio',
      Ganancias: 6300
    },
    {
      name: 'Agosto',
      Ganancias: 1000
    },
    {
      name: 'Setiembre',
      Ganancias: 7800
    },
    {
      name: 'Octubre',
      Ganancias: 5600
    },
    {
      name: 'Noviembre',
      Ganancias: 8500
    },
    {
      name: 'Diciembre',
      Ganancias: 1000
    },
    
  ];

  


  return (

    <>

    <h2 className="text fs-5 ">Gráfica de ganancias anuales</h2>
    <ResponsiveContainer width="100%" height={450} className={'bg-white'}>
    <BarChart
      data={data}
      margin={{ top: 2, bottom: 2, left: 4, right: 10 }}
      >
      <Legend  verticalAlign="top" height={36} />
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name">
       
      </XAxis>
      <YAxis
        label={{
          value: "Ganancias",
          angle: -90,
          position: "insideLeft",
          textAnchor: "middle",
        }}
        />
     
      <Bar dataKey="Ganancias" fill="#577AE1"  maxBarSize={50} >
        <LabelList dataKey="Ganancias" position="top" />
      </Bar>
      <Tooltip />
    </BarChart>
  </ResponsiveContainer>
   
        </>
    
   
  );
}

export default ChartBarMes;






