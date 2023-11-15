'use client'

import { Canvas } from "@react-three/fiber";
import { Suspense, useRef, useEffect, useState } from "react";
import { OrbitControls, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

import styles from "./page.module.css"
import MapCloseButton from "../component/MapCloseButton";
import MapAllButton from "../component/MapAllButton";
import GlobeButton from "../component/GlobeButton";
import Modal from "@/components/Modal";

import getFetch from "@/services/getFetch"
import positionData from "public/json/position.json"
import MapModal from "../component/MapModal";

export default function Korea() {

    const [curPosition, setCurPosition] = useState([]);
    const [curRotation, setCurRotation] = useState([]);
    const [curCountry, setCurCountry] = useState("");

    // 모델 클릭 여부 상태
    const [showModal, setShowModal] = useState(false);
    
    const [modalTitle, setModalTitle] = useState("");
    const [modalText, setModalText] = useState("");

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

    useEffect(() => {
        handleClick();
    }, [showModal])

    const Models = [
        { name: "ground", url: "/models/korea-ground.glb", position: [0, 0, 0], rotation: [0, 0, 0] },
        { name: "train", url: "/models/red-train.glb", position: curPosition, rotation: curRotation },
        { name: "flag", url: "/models/korea-flag.glb", position: [0, 0.1, 0], rotation: [0, 0, 0]}
    ]

    const Model = ({ url, scale, position, rotation, onClick, title, text }) => {
        const { scene } = useGLTF(url);
        scene.scale.set(scale, scale, scale);
        scene.position.set(position[0], position[1], position[2])
        scene.rotation.set(rotation[0], rotation[1], rotation[2])

        setModalTitle(title);
        setModalText(text);
        return <Clone object={scene} onClick={onClick} />;
    }

    const handleClick = () => {
        console.log("모델 클릭 되었어용");
    }

    return (
        <div className={styles.container}>
            <GlobeButton />
            <MapCloseButton />
            <div className={styles.mapAllButton}>
                <MapAllButton>대한민국</MapAllButton>
            </div>
            <Canvas camera={{ position: [0, 0.03, -0.2], near: 0.038 }}>
                <Suspense fallback={null}>
                    <Model
                        url={Models[0].url}
                        scale={0.175}
                        position={[Models[0].position[0], Models[0].position[1], Models[0].position[2]]}
                        rotation={[Models[0].rotation[0], Models[0].rotation[1], Models[0].rotation[2]]}
                    />
                    {curCountry === '한국' && (
                        <group ref={group}>
                            <Model
                                url={Models[1].url}
                                scale={0.0003}
                                position={[Models[1].position[0], Models[1].position[1], Models[1].position[2]]}
                                rotation={[Models[1].rotation[0], Models[1].rotation[1], Models[1].rotation[2]]}
                            />
                        </group>
                    )}
                    <Model
                        url={Models[2].url}
                        scale={0.02}
                        position={[Models[2].position[0], Models[2].position[1], Models[2].position[2]]}
                        rotation={[Models[2].rotation[0], Models[2].rotation[1], Models[2].rotation[2]]}
                        onClick={() => setShowModal(true)}
                        title="🔴대한민국🔵"
                        text={`대한민국은 동아시아에 위치한 
                        한반도의 남쪽에 위치한 국가로, 수도는 서울입니다.

                        대한민국은 1948년 8월 15일에 국제사회에서 공식적으로 독립을 선언하면서 탄생했습니다.

                        문화적으로는 한류 현상으로 세계적으로 유명한 대한민국의 드라마, 음악, 영화 등이 있습니다. 


                        또한, 한국의 전통문화인 한복, 불교, 향토음식 등도 많은 사람들에게 인기를 끌고 있습니다.`}
                    />
                    <ambientLight intensity={3} />
                </Suspense>
                <OrbitControls
                    enableRotate={true}
                    enableZoom={false}
                    maxPolarAngle={Math.PI / 2.21}
                // target={group.current?.position || [0, 0, 0]}
                />
            </Canvas>
            {showModal && (
                <>
                <Modal onClick={() => setShowModal(false)} >
                    <div>
                        <div className={styles.modalTitle}>
                            {modalTitle}
                        </div>
                            <div className={styles.modalText}>
                            {modalText}
                            </div>
                    </div>
                </Modal>
                </>
            )}
        </div >
    )
}