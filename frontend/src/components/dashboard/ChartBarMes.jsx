import {Chart} from "react-google-charts";

function ChartBarMes({data, titulo}) {


    const options = {
        title: titulo,
        titleTextStyle: {
            color: "#577AE1",
        },

        colors: [
            "#F9CD3E",
            "#E74A3B",
            "#1CC88A",
            "#3D63D2",
            "#D9D9D9",
            "#3730A3",
            "#8F3D6F",
            "#5F54CB",
            "#6610F2",
            "#8C68CD",
            "#FD9843",
            "#867571",
        ],
        is3D: true,
    };
    return (
        <Chart
            chartType="PieChart"
            data={data}
            options={options}
            width={"100%"}
            height={"400px"}
        />
    );
}

export default ChartBarMes;
