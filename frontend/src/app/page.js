import Letter from "./Letter";
import AriveTrain from "@/components/Lottie/AriveTrain";
import styles from "./page.module.css";

export default function Home() {
  return (
    <>
      <div id="overlay"></div>
      <Letter />
      <AriveTrain />
    </>
  );
}
