import ReactApexChart from "react-apexcharts";

export default function Chart({ sleepStages }) {
  const data = [
    { x: "1", y: [] },
    { x: "2", y: [] },
    { x: "3", y: [] },
    { x: "4", y: [] },
    { x: "5", y: [] },
  ];

  sleepStages.forEach((sleepStage) => {
    const startTimeObject = new Date(sleepStage.startTime);
    const endTimeObject = new Date(sleepStage.endTime);

    const startHour = startTimeObject.getHours();
    const startMinute = startTimeObject.getMinutes();
    const endHour = endTimeObject.getHours();
    const endMinute = endTimeObject.getMinutes();

    // TODO: 시간 분으로 바꾸기
    data.push({
      x: sleepStage.stage.toString(),
      y: [`25`, `26`],
    });
  });

  const options = {
    chart: {
      height: 350,
      type: "rangeBar",
    },
    plotOptions: {
      bar: {
        horizontal: true,
      },
    },
    xaxis: {
      type: "datetime",
    },
  };

  const series = [
    {
      data,
    },
  ];

  return (
    <ReactApexChart
      options={options}
      series={series}
      type="rangeBar"
      height={350}
    />
  );
}
