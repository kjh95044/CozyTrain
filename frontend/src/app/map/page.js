'use client';

import { useEffect, useRef } from "react";
import styles from "./page.module.css";

import Globe from 'globe.gl';



export default function map() {

    const globalRef = useRef(null);

    useEffect(() => {
        const globe = Globe()(globalRef.current);
        globe.globeImageUrl('assets/image/earth.jpg')
        return () => {
            globe;
        };
    }, []);

    return (
        <div className={styles.container}>
            <div ref={globalRef} style={{ width: '100%', height: '500px' }}></div>
        </div>
    );
}
