import { useState, useEffect } from "react";

import getFetch from "@/services/getFetch";

export default function Day() {
  const [report, setReport] = useState({});

  const getReport = async () => {
    // const resp = await getFetch("report");

    setReport(resp.response);
  };

  useEffect(() => {
    getReport();
  }, []);

  return;
}
