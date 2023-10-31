'use client';

import { useEffect, useRef } from "react";
import styles from "./page.module.css";

//import * as THREE from 'three';
import Globe from 'globe.gl';

import pretendard from '../../../public/assets/fonts/IBM Plex Sans KR Medium_Regular.json'

export default function map() {

    const continentArray = [
        {
            lat: 42.13407,
            lng: 120,
            label: "아시아"
        },
        {
            lat: 50.13407,
            lng: 43.62981,
            label: "유럽"
        },
        {
            lat: 7.13407,
            lng: 21.62981,
            label: "아프리카"
        },
        {
            lat: -27,
            lng: 135,
            label: "오세아니아"
        },
        {
            lat: -18,
            lng: -55,
            label: "남아메리카"
        },
        {
            lat: 38.13407,
            lng: -100,
            label: "북아메리카"
        },
    ];

    const globalRef = useRef(null);

    useEffect(() => {
        const globe = Globe()(globalRef.current);
        globe.globeImageUrl('assets/image/earth.jpg')
            .backgroundImageUrl('//unpkg.com/three-globe/example/img/night-sky.png')
            .labelText('label')
            .labelSize(1.5)
            .labelDotRadius(2.2)
            .labelTypeFace(pretendard)
            .labelColor(() => 'rgba(220,86,95,1)')
            .labelsData(continentArray, { lat: 'lat', lng: 'lng', label: 'label' });

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