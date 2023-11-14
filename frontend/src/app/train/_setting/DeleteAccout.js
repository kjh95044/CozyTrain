import styles from "./DeleteAccout.module.css";

import deleteFetch from "@/services/deleteFetch";
import PriBtn from "@/components/button/PrimaryButton";
import SecBtn from "@/components/button/SecondaryButton";

export default function DeleteAccout(props) {
  const deleteMember = async () => {
    const resp = await deleteFetch("member");

    router.push("/login");
  };

  return (
    <div className={styles.modal}>
      <div>정말로 탈퇴하시겠습니까?</div>
      <div className={styles.btn}>
        <PriBtn onClick={props.handleDeleteAcountFalse}>아니요</PriBtn>
        <SecBtn onClick={deleteMember}>네</SecBtn>
      </div>
    </div>
  );
}
