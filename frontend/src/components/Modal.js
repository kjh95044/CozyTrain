"use client";

import styles from "./Modal.module.css";

const BackDrop = ({ onClose }) => {
  return <div className={styles.backdrop} onClick={onClose}></div>;
};

const Overlay = (props) => {
  return <div className={styles.overlay}>{props.children}</div>;
};

export default function Modal(props) {
  return (
    <>
      <Overlay>{props.children}</Overlay>
      <BackDrop onClose={props.onClick} />
    </>
  );
}
