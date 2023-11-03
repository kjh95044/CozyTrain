'use client'

import { Canvas } from "@react-three/fiber";
import { Suspense, useRef, useEffect } from "react";
import { OrbitControls, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

import * as THREE from 'three';

import styles from "./page.module.css"
import MapCloseButton from "../component/MapCloseButton";
import MapAllButton from "../component/MapAllButton";
import GlobeButton from "../component/GlobeButton";


const Models = [
    { name: "ground", url: "/models/korea-ground.glb", position: [0, 0, 0], rotation: [0, 0, 0] },
    { name: "train", url: "/models/red-train.glb", position: [-0.133, -0.003, -0.017], rotation: [0, 0, 0] }
]

const Model = ({ url, scale, position, rotation }) => {
    const { scene } = useGLTF(url);
    scene.scale.set(scale, scale, scale);
    scene.position.set(position[0], position[1], position[2])
    scene.rotation.set(rotation[0], rotation[1], rotation[2])
    return <Clone object={scene} />;
}

export default function Korea() {

    const group = useRef();

    useEffect(() => {
        if (group.current) {
            controls.current.target.copy(group.current.position);
        }
    }, []);

    return (
        <div className={styles.container}>
            <GlobeButton />
            <MapCloseButton />
            <div className={styles.mapAllButton}>
                <MapAllButton>대한민국</MapAllButton>
            </div>
            <Canvas camera={{ position: [0, 0.03, -0.2], near: 0.038, lookAt: [-0.133, -0.003, -0.017] }}>
                <Suspense fallback={null}>
                    <Model
                        url={Models[0].url}
                        scale={0.175}
                        position={[Models[0].position[0], Models[0].position[1], Models[0].position[2]]}
                        rotation={[Models[0].rotation[0], Models[0].rotation[1], Models[0].rotation[2]]}
                    />
                    <group ref={group}>
                        <Model
                            url={Models[1].url}
                            scale={0.0003}
                            position={[Models[1].position[0], Models[1].position[1], Models[1].position[2]]}
                            rotation={[Models[1].rotation[0], Models[1].rotation[1], Models[1].rotation[2]]} />
                    </group>
                    <ambientLight intensity={3} />
                </Suspense>
                <OrbitControls
                    enableRotate={true}
                    enableZoom={false}
                    maxPolarAngle={Math.PI / 2.21}
                // target={group.current?.position || [0, 0, 0]}
                />
            </Canvas>
        </div >
    )
}