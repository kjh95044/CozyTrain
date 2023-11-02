'use client'

import { Canvas } from "@react-three/fiber";
import { Suspense } from "react";
import { OrbitControls, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

import styles from "./page.module.css"
import MapCloseButton from "../component/MapCloseButton";
import MapAllButton from "../component/MapAllButton";
import GlobeButton from "../component/GlobeButton";


const Models = [
    { name: "ground", url: "/models/korea-ground.glb" }
]

const Model = ({ url, scale }) => {
    const { scene } = useGLTF(url);
    scene.scale.set(scale, scale, scale);
    return <Clone object={scene} />;
}

export default function Korea() {
    return (
        <div className={styles.container}>
            <GlobeButton />
            <MapCloseButton />
            <div className={styles.mapAllButton}>
                <MapAllButton>대한민국</MapAllButton>
            </div>
            <Canvas camera={{ position: [0, 0.1, -0.2], near: 0.03 }}>
                <Suspense fallback={null}>
                    <Model url={Models[0].url} scale={0.16} />
                    <ambientLight intensity={3} />
                </Suspense>
                <OrbitControls />
            </Canvas>
        </div >
    )
}