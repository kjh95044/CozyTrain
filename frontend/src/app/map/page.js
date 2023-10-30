'use client';

import Image from "next/image";
import MapAll from "public/assets/image/map-all.png";
import styles from "./page.module.css";

import * as THREE from 'three';
import { Canvas, useFrame } from "@react-three/fiber";
import { Suspense, useEffect, useRef } from "react";
import { OrbitControls, Environment, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

const Models = [
    { name: "earth", url: "/assets/models/earth.glb" },
];

const Model = ({ url }) => {
    const { scene } = useGLTF(url);
    return <Clone object={scene} />;
};

export default function map() {
    return (
        <div className={styles.container}>
            <Canvas camera={{ position: [0, 0, -0.2], near: 0.025 }}>
                <Environment preset="city" />
                <Suspense fallback={null}>
                    <Model url={Models[0].url} />
                    <ambientLight intensity={0.5} />
                    <pointLight position={[10, 10, 10]} />
                </Suspense>
                <OrbitControls />
            </Canvas>
        </div>
    );
}
