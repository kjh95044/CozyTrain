"use client";
import { useRouter } from "next/navigation";
import styles from "./PrimaryButton.module.css";

export default function BackButton(props) {
  const router = useRouter();

  const handleRedirect = () => {
    router.back();
  };

  return (
    <button className={styles.button} onClick={handleRedirect}>
      {props.children}
    </button>
  );
}
