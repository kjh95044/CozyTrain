"use client";

import CloseButton from "./button/CloseButton";
import styles from "./Modal.module.css";

const BackDrop = ({ onClose }) => {
  return <div className={styles.backdrop} onClick={onClose}></div>;
};

const Overlay = (props) => {
  return (
    <div className={styles.overlay}>
      <div className={styles.title}>
        {props.title}
        <div className={styles.closeBtn}>
          <CloseButton onClick={props.onClick} />
        </div>
      </div>
      {props.children}
    </div>
  );
};

export default function Modal(props) {
  return (
    <>
      <Overlay onClick={props.onClick} title={props.title}>
        {props.children}
      </Overlay>
      <BackDrop onClose={props.onClick} />
    </>
  );
}
