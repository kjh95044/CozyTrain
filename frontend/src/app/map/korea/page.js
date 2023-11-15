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
import ExplainModal from "../component/ExplainModal"

import getFetch from "@/services/getFetch"
import positionData from "public/json/position.json"

export default function Korea() {

    const [curPosition, setCurPosition] = useState([]);
    const [curRotation, setCurRotation] = useState([]);
    const [curCountry, setCurCountry] = useState("");

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
        { name: "ground", url: "/models/korea-ground.glb", position: [0, 0, 0], rotation: [0, 0, 0] },
        { name: "train", url: "/models/red-train.glb", position: curPosition, rotation: curRotation },
        { name: "flag", url: "/models/korea-flag.glb", position: [0, 0.1, 0], rotation: [0, 0, 0]},
        { name: "seoul", url: "/models/seoul.glb", position: [-0.111, 0.03, -0.07], rotation: [0, 0, 0]},
        { name: "busan", url: "/models/busan.glb", position: [-0.02, -0.003, 0.108], rotation: [0, 1.8, 0]},
        { name: "jeju", url: "/models/jeju.glb", position: [0.065, 0, -0.098], rotation: [0, 0.3, 0]}
    ]

    const Model = ({ url, scale, position, rotation, onClick, title, text }) => {
        const { scene } = useGLTF(url);
        scene.scale.set(scale, scale, scale);
        scene.position.set(position[0], position[1], position[2])
        scene.rotation.set(rotation[0], rotation[1], rotation[2])

        useEffect(() => {
            setTimeout(() => {
                setLoading(false);
            }, 1500)
        }, [scene])

        
        const handleClick = () => {
            if(url !== Models[0].url) {
                setShowModal(true);
                setModalTitle(title);
                setModalText(text);
            }
        }


        return <Clone object={scene} onClick={handleClick} />;
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
                    <Model
                        url={Models[3].url}
                        scale={0.0004}
                        position={[Models[3].position[0], Models[3].position[1], Models[3].position[2]]}
                        rotation={[Models[3].rotation[0], Models[3].rotation[1], Models[3].rotation[2]]}
                        onClick={() => setShowModal(true)}
                        title="🔴서울🔵 - 롯데타워"
                        text={`
                        대한민국의 수도로, 한반도 중앙에 위치해 있습니다. 

                        현대적인 농경지와 전통적인 한옥이 공존하며, 
                        한강이 시내를 가로지르고 있습니다. 

                        서울은 국제적인 비즈니스와 문화 중심지로서 번화하고 다양한 역사적 명소, 현대적 건축물, 예술과 음악의 장소를 제공합니다. `}
                    />
                    <Model
                        url={Models[4].url}
                        scale={0.005}
                        position={[Models[4].position[0], Models[4].position[1], Models[4].position[2]]}
                        rotation={[Models[4].rotation[0], Models[4].rotation[1], Models[4].rotation[2]]}
                        onClick={() => setShowModal(true)}
                        title="🔴부산🔵 - 광안대교"
                        text={`
                        부산은 대한민국 동해안에 위치한 해안 도시입니다. 

                        주요 항구인 부산항은 국제 무역의 중심지이며, 
                        매년 열리는 부산국제영화제는 세계적으로 유명합니다. 

                        부산은 다양한 해안 경치와, 맛있는 해산물로 유명하며, 
                        대표적인 관광지로는 해운대, 광안리 등이 있습니다. `}
                    />
                    <Model
                        url={Models[5].url}
                        scale={0.000012}
                        position={[Models[5].position[0], Models[5].position[1], Models[5].position[2]]}
                        rotation={[Models[5].rotation[0], Models[5].rotation[1], Models[5].rotation[2]]}
                        onClick={() => setShowModal(true)}
                        title="🔴제주도🔵 - 바람, 귤"
                        text={`
                        제주도는 대한민국 남쪽 해상에 위치한 섬으로, 
                        아름다운 자연 경관과 독특한 문화로 알려져 있습니다. 
                        
                        화산 활동으로 형성된 한라산은 대표적인 자연 명소이며, 
                        용두암과 함께 제주의 아름다운 풍경을 만들어냅니다. 
                        
                        제주는 특유의 흑돼지와 맛있는 감귤로 유명하며, 
                        다양한 휴양지가 방문객들에게 휴식을 제공합니다. `}
                    />
                    <ambientLight intensity={3} />
                    <Model
                        url={Models[0].url}
                        scale={0.175}
                        position={[Models[0].position[0], Models[0].position[1], Models[0].position[2]]}
                        rotation={[Models[0].rotation[0], Models[0].rotation[1], Models[0].rotation[2]]}
                    />
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
            {loading && (
                <>
                <ExplainModal>
                </ExplainModal>
                </>
            )

            }
        </div >
    )
}