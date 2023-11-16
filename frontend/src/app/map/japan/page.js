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
import MapModal from "../component/MapModal";

import getFetch from "@/services/getFetch"
import positionData from "public/json/position.json"

export default function Korea() {

    const [curPosition, setCurPosition] = useState([]);
    const [curRotation, setCurRotation] = useState([]);
    const [curCountry, setCurCountry] = useState("");
    const [curCountryEng, setCurCountryEng] = useState("");
    const [curRegion, setCurRegion] = useState(""); 

    // 모델 클릭 여부 상태 
    const [showModal, setShowModal] = useState(false);
    const [modalTitle, setModalTitle] = useState("");
    const [modalText, setModalText] = useState("");

    const [loading, setLoading] = useState(true);

    // 기차 위치한 나라와 현재 나라가 같은 지 비교
    const [isTrain, setIsTrain] = useState(false);
    const [showTrainModal, setShowTrainModal] = useState(false);

    const getTrainLocation = async () => {
        const data = await getFetch("train/cur-location-info")
        const curRegionNum = data.response.regionNum;
        const curArea = data.response.area;
        setCurCountry(data.response.countryKor);
        setCurCountryEng(data.response.country);
        setCurRegion(data.response.regionKor);

        if(data.response.countryKor === '일본') {
            setIsTrain(true)
        }

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
        { name: "train", url: "/models/red-train.glb", position: curPosition, rotation: curRotation },
        { name: "flag", url: "/models/japan-flag.glb", position: [0, 0.1, 0], rotation: [0, 0, 0]},
        { name: "sapporo", url: "/models/sapporo.glb", position: [-0.098,-0.001,-0.03], rotation: [0,3.1,0]},
        { name: "tokyo", url: "/models/tokyo.glb", position: [0.01,-0.003,0.095], rotation: [0,-5.4,0]},
        { name: "osaka", url: "/models/osaka.glb", position: [0.068, -0.002, -0.107], rotation: [0, 1.16, 0] },
    ]

    const Model = ({ url, scale, position, rotation, title, text }) => {
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
        return <Clone object={scene} onClick={handleClick}/>;
    }

    return (
        <div className={styles.container}>
            <GlobeButton />
            <TrainButton onClick={() => setShowTrainModal(true)}/>
            <MapCloseButton />
            <div className={styles.mapAllButton}>
                <MapAllButton>일본</MapAllButton>
            </div>
            <Canvas camera={{ position: [0, 0.03, -0.2], near: 0.038 }}>
                <Suspense fallback={null}>
                    {isTrain && (
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
                        url={Models[4].url}
                        scale={0.0003}
                        position={[Models[4].position[0], Models[4].position[1], Models[4].position[2]]}
                        rotation={[Models[4].rotation[0], Models[4].rotation[1], Models[4].rotation[2]]}
                        title="🍣도쿄🍡 - 도쿄 타워"
                        text={`도쿄는 일본의 수도이자 최대 도시로, 
                        현대성과 전통이 조화롭게 어우러진 도시입니다. 
                        
                        고츠쿠지 신사와 아사쿠사 신사 같은 전통적인 명소부터, 
                        긴 은하수대로 유명한 신주쿠, 히브야 신주쿠 등 
                        현대적이고 번화한 상업 지역까지 다양한 얼굴을 갖추고 있습니다. 
                        
                        높은 건물들과 현대적인 도로망은 도쿄가 세계적인 도시로서의 역할을 두드러지게 합니다. `}
                    />
                    <Model
                        url={Models[5].url}
                        scale={0.01}
                        position={[Models[5].position[0], Models[5].position[1], Models[5].position[2]]}
                        rotation={[Models[5].rotation[0], Models[5].rotation[1], Models[5].rotation[2]]}
                        title="🍣오사카🍡 - 애니메이션 캐릭터"
                        text={`오사카는 일본의 중부에 위치한 경제와 문화의 중심지로, 
                        다양한 역사적 명소와 현대적인 도시 풍경을 자랑합니다. 
                        
                        도톤보리 같은 관광명소는 전통과 현대의 만남을 보여주며, 
                        오사카의 길거리 음식 문화는 국내외에서 많은 이들에게 사랑받고 있습니다. 
                        
                        또한, 많은 애니메이션 매장과 카페가 모여 있어 
                        애니메이션 팬들에게 인기를 끌고 있습니다.`}
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
            {showTrainModal && (
                <>
                {!isTrain ? (
                    <MapModal
                        onCloseModal={() => setShowTrainModal(false)}
                        text={`기차는 현재 ${curCountry} ${curRegion}에 있습니다. 
                        이동하시겠습니까?`}
                        move={curCountryEng}
                    >       
                    </MapModal>
                ) :
                (
                    <Modal onClick={() => setShowTrainModal(false)} >
                        <div>
                            <div className={styles.modalTitle}>
                                칙칙 포근포근 🚂
                            </div>
                                <div className={styles.modalText}>
                                    칙칙 ... 💤 <br/>
                                    포근포근 ... 💤 <br/>
                                    <br/>
                                    열차는 지금 {curCountry} {curRegion}에서 달리고 있습니다! <br/>
                                    (｡･∀･)ﾉﾞ <br/>
                                </div>
                        </div>
                    </Modal>
                )}
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