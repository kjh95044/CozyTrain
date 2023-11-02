"use client";

import styles from "./Modal.module.css";

const BackDrop = (props) => {
  return <div></div>;
};

const ModalOverlay = (props) => {
  return <div className={styles.modal_overlay}>{props.children}</div>;
};

export default function Modal(props) {
  return (
    <>
      <ModalOverlay>{props.children}</ModalOverlay>
    </>
  );
}
