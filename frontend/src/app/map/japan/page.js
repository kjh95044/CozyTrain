'use client'

import { Canvas } from "@react-three/fiber";
import { Suspense, useRef, useEffect, useState } from "react";
import { OrbitControls, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

import styles from "./page.module.css"
import MapCloseButton from "../component/MapCloseButton";
import MapAllButton from "../component/MapAllButton";
import GlobeButton from "../component/GlobeButton";

import getFetch from "@/services/getFetch"
import positionData from "public/json/position.json"

export default function Korea() {

    const [curPosition, setCurPosition] = useState([]);
    const [curRotation, setCurRotation] = useState([]);
    const [curCountry, setCurCountry] = useState("");

    const getTrainLocation = async () => {
        const data = await getFetch("train/cur-location-info")
        const curRegionNum = data.response.regionNum;
        const curArea = data.response.area;
        setCurCountry(data.response.countryKor);

        const foundPositionData = findPosition(curRegionNum, curArea);
        if (foundPositionData) {
            setCurPosition(foundPositionData.position);
            setCurRotation(foundPositionData.rotation);
        }
    }

    const findPosition = (regionNum, area) => {
        return positionData.find((data) =>
            data.regionNum === regionNum && data.area === area
        );
    }

    const group = useRef();

    useEffect(() => {
        if (group.current) {
            controls.current.target.copy(group.current.position);
        }

        getTrainLocation();
    }, []);

    const Models = [
        { name: "ground", url: "/models/japan-ground.glb", position: [0, 0, 0], rotation: [0, 0, 0] },
        { name: "train", url: "/models/red-train.glb", position: curPosition, rotation: curRotation }
    ]

    const Model = ({ url, scale, position, rotation }) => {
        const { scene } = useGLTF(url);
        scene.scale.set(scale, scale, scale);
        scene.position.set(position[0], position[1], position[2])
        scene.rotation.set(rotation[0], rotation[1], rotation[2])
        return <Clone object={scene} />;
    }

    return (
        <div className={styles.container}>
            <GlobeButton />
            <MapCloseButton />
            <div className={styles.mapAllButton}>
                <MapAllButton>일본</MapAllButton>
            </div>
            <Canvas camera={{ position: [0, 0.03, -0.2], near: 0.038 }}>
                <Suspense fallback={null}>
                    <Model
                        url={Models[0].url}
                        scale={0.175}
                        position={[Models[0].position[0], Models[0].position[1], Models[0].position[2]]}
                        rotation={[Models[0].rotation[0], Models[0].rotation[1], Models[0].rotation[2]]}
                    />
                    {curCountry === '일본' && (
                        <group ref={group}>
                            <Model
                                url={Models[1].url}
                                scale={0.0003}
                                position={[Models[1].position[0], Models[1].position[1], Models[1].position[2]]}
                                rotation={[Models[1].rotation[0], Models[1].rotation[1], Models[1].rotation[2]]} />
                        </group>
                    )
                    }
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