'use client'

import { Canvas } from "@react-three/fiber";
import { Suspense, useRef, useEffect, useState } from "react";
import { OrbitControls, Clone } from "@react-three/drei";
import { useGLTF } from "@react-three/drei";

import styles from "./page.module.css"
import MapCloseButton from "../component/MapCloseButton";
import MapAllButton from "../component/MapAllButton";
import GlobeButton from "../component/GlobeButton";
import TrainButton from "../component/TrainButton";
import Modal from "@/components/Modal";
import ExplainModal from "../component/ExplainModal"

import getFetch from "@/services/getFetch"
import positionData from "public/json/position.json"

export default function Korea() {

    const [curPosition, setCurPosition] = useState([]);
    const [curRotation, setCurRotation] = useState([]);
    const [curCountry, setCurCountry] = useState("");
    const [curRegion, setCurRegion] = useState(""); 

    // 모델 클릭 여부 상태 
    const [showModal, setShowModal] = useState(false);
    const [modalTitle, setModalTitle] = useState("");
    const [modalText, setModalText] = useState("");

    const [loading, setLoading] = useState(true);

    const getTrainLocation = async () => {
        const data = await getFetch("train/cur-location-info")
        const curRegionNum = data.response.regionNum;
        const curArea = data.response.area;
        setCurCountry(data.response.countryKor);
        setCurRegion(data.response.regionKor);

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
        { name: "ground", url: "/models/japan-ground1.glb", position: [0, 0, 0], rotation: [0, 0, 0] },
        { name: "train", url: "/models/red-train.glb", position: curPosition, rotation: curRotation },
        { name: "flag", url: "/models/japan-flag.glb", position: [0, 0.1, 0], rotation: [0, 0, 0]},
        { name: "sapporo", url: "/models/sapporo.glb", position: [-0.098,-0.001,-0.03], rotation: [0,3.1,0]},
        { name: "tokyo", url: "/models/sapporo.glb", position: [-0.098,-0.001,-0.03], rotation: [0,3.1,0]}

    ]

    const Model = ({ url, scale, position, rotation, title, text }) => {
        const { scene } = useGLTF(url);
        scene.scale.set(scale, scale, scale);
        scene.position.set(position[0], position[1], position[2])
        scene.rotation.set(rotation[0], rotation[1], rotation[2])

        const handleClick = () => {
            if(url !== Models[0].url) {
                setShowModal(true);
                setModalTitle(title);
                setModalText(text);
            }
        }
        return <Clone object={scene} onClick={handleClick}/>;
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
                    {curCountry === '일본' && (
                        <group ref={group}>
                            <Model
                                url={Models[1].url}
                                scale={0.0003}
                                position={[Models[1].position[0], Models[1].position[1], Models[1].position[2]]}
                                rotation={[Models[1].rotation[0], Models[1].rotation[1], Models[1].rotation[2]]}
                                title="칙칙 포근포근 🚂"
                                text={`칙칙 ... 💤
                                포근포근 ... 💤
                                
                                열차는 지금 ${curCountry} ${curRegion}에서 달리고 있습니다!
                                (｡･∀･)ﾉﾞ
                                `} />
                        </group>
                    )
                    }
                    <Model
                        url={Models[2].url}
                        scale={0.02}
                        position={[Models[2].position[0], Models[2].position[1], Models[2].position[2]]}
                        rotation={[Models[2].rotation[0], Models[2].rotation[1], Models[2].rotation[2]]}
                        title="🍣일본🍡"
                        text={`일본은 아시아의 섬나라로, 
                        독특하고 다양한 문화를 자랑합니다. 
                        
                        일본은 세계적인 경제 강국으로서, 
                        첨단 기술, 자동차 산업, 그리고 만화와 애니메이션 분야에서 
                        글로벌 영향력을 지속적으로 확장하고 있습니다. 

                        또한, 일본은 매력적인 음식 문화로 유명하며, 
                        회, 라멘, 초밥 등이 세계적으로 사랑받고 있습니다.`}
                    />
                    <Model
                        url={Models[3].url}
                        scale={0.004}
                        position={[Models[3].position[0], Models[3].position[1], Models[3].position[2]]}
                        rotation={[Models[3].rotation[0], Models[3].rotation[1], Models[3].rotation[2]]}
                        title="🍣삿포로🍡 - 눈"
                        text={`삿포로는 일본의 홋카이도 지방에 위치한 도시로, 
                        자연의 아름다움과 도시의 현대성이 조화롭게 어우러져 있습니다. 
                        
                        주변에는 푸른 바다와 높은 산봉우리가 
                        인상적으로 펼쳐져 있어 자연 감각을 느낄 수 있습니다. 
                        
                        특히 겨울에는 눈으로 덮인 풍경이 아름다움을 더해주며, 
                        삿포로 눈축제는 세계적으로 유명한 축제 중 하나로 손꼽힙니다.`}
                    />
                    <Model
                        url={Models[3].url}
                        scale={0.004}
                        position={[Models[3].position[0], Models[3].position[1], Models[3].position[2]]}
                        rotation={[Models[3].rotation[0], Models[3].rotation[1], Models[3].rotation[2]]}
                        title="🍣삿포로🍡 - 눈"
                        text={`삿포로는 일본의 홋카이도 지방에 위치한 도시로, 
                        자연의 아름다움과 도시의 현대성이 조화롭게 어우러져 있습니다. 
                        
                        주변에는 푸른 바다와 높은 산봉우리가 
                        인상적으로 펼쳐져 있어 자연 감각을 느낄 수 있습니다. 
                        
                        특히 겨울에는 눈으로 덮인 풍경이 아름다움을 더해주며, 
                        삿포로 눈축제는 세계적으로 유명한 축제 중 하나로 손꼽힙니다.`}
                    />
                    <Model
                        url={Models[0].url}
                        scale={0.175}
                        position={[Models[0].position[0], Models[0].position[1], Models[0].position[2]]}
                        rotation={[Models[0].rotation[0], Models[0].rotation[1], Models[0].rotation[2]]}
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
                <Modal onClick={() => setShowModal(false)}>
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