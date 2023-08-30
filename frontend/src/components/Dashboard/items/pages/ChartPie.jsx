import React from "react";
import { Pie, PieChart, ResponsiveContainer, Tooltip, Cell, Label } from "recharts";

function ChartPie({ title, nameOne,nameTwo,valueOne,valueTwo,colorOne,colorTwo } ) {
  const data = [
    { name: nameOne , value: valueOne },
    { name: nameTwo, value: valueTwo },
   
  ];

  const COLORS = [ colorOne, colorTwo ];

  const RADIAN = Math.PI / 180;
const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
  const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
  const x = cx + radius * Math.cos(-midAngle * RADIAN);
  const y = cy + radius * Math.sin(-midAngle * RADIAN);

  return (
    <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
      {`${ (percent * 100).toFixed(0)}%`}
    </text>)

}
  

  
  
  return (

    
    <div className="w-25 h-auto  mt-5  d-flex  flex-column bg-white p-4 shadow-sm  rounded-4   justify-content-center  align-items-center  ">
     
        <h3 className="fs-5 ">{title} </h3>
      <ResponsiveContainer width="100%" height={300}>
        
        <PieChart   >
          <Pie style={{color:'red'}}
            dataKey={"value"}
            data={data}
            innerRadius={0}
            outerRadius={105}
            fill="#334"
            cx="50%"
            cy="50%"
          
            label={renderCustomizedLabel}
            labelLine={false}
        
            
          >
            {data.map((entry, index) => (
              <Cell
              
                key={`cell-${index}`}
                fill={COLORS[index % COLORS.length]}
              />
            ))}
            
          </Pie>
        
          <Tooltip />
         
        </PieChart>

        
      </ResponsiveContainer>
    </div>
  );
}

export default ChartPie;
