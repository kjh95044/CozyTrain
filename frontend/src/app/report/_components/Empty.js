import Sleep404 from "@/components/Lottie/Sleep404";

export default function Empty() {
  return (
    <div
      style={{
        display: "flex",
        marginTop: "40px",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        position: "absolute",
        top: "16%",
        gap: "12px",
      }}
    >
      <Sleep404 />
      <div style={{ textAlign: "center" }}>
        <p>해당 리포트가 없어요</p>
        <p>수면 데이터를 연동해 리포트를 받아보세요 :)</p>
      </div>
    </div>
  );
}
