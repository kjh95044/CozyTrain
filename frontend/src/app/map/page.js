'use client';

import { useEffect, useRef, useState } from "react";
import styles from "./page.module.css";
import MapModal from "@/app/map/component/MapModal";

import getFetch from "@/services/getFetch";

import Globe from 'globe.gl';

import IBMRegular from '../../../public/fonts/IBM_Regular.json'
import MapCloseButton from "@/app/map/component/MapCloseButton";

export default function Map() {
    const [continents, setContinents] = useState([]);

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [clickContinent, setClickContinent] = useState("");
    const [clcikContinentEng, setClickContinentEng] = useState("");

    const getVisitContinents = async () => {
        const data = await getFetch("train/visit/continent");
        setContinents(data.response);
    }


    const globalRef = useRef(null);

    useEffect(() => {
        getVisitContinents();
    }, []);

    useEffect(() => {
        if (continents.length !== 0) {
            console.log(continents);

            const continentArray = [
                {
                    lat: 42.13407,
                    lng: 120,
                    label: "아시아",
                    visit: continents[0].visit,
                    onLabelClick: () => {
                        if (continents[0].visit) {
                            setIsModalOpen(true);
                            setClickContinent("아시아");
                            setClickContinentEng("asia");
                        }
                    }
                },
                {
                    lat: 50.13407,
                    lng: 43.62981,
                    label: "유럽",
                    visit: continents[1].visit,
                    onLabelClick: () => {
                        if (continents[1].visit) {
                            setIsModalOpen(true);
                            setClickContinent("유럽");
                            setClickContinentEng("europe");
                        }
                    }
                },
                {
                    lat: 7.13407,
                    lng: 21.62981,
                    label: "아프리카",
                    visit: continents[2].visit,
                    onLabelClick: () => {
                        if (continents[2].visit) {
                            setIsModalOpen(true);
                            setClickContinent("아프리카");
                            setClickContinentEng("africa");
                        }
                    }
                },
                {
                    lat: -27,
                    lng: 135,
                    label: "오세아니아",
                    visit: continents[3].visit,
                    onLabelClick: () => {
                        if (continents[3].visit) {
                            setIsModalOpen(true);
                            setClickContinent("오세아니아");
                            setClickContinentEng("oceania");
                        }
                    }
                },
                {
                    lat: -18,
                    lng: -55,
                    label: "남아메리카",
                    visit: continents[5].visit,
                    onLabelClick: () => {
                        if (continents[5].visit) {
                            setIsModalOpen(true);
                            setClickContinent("남아메리카");
                            setClickContinentEng("south_america");
                        }
                    }
                },
                {
                    lat: 38.13407,
                    lng: -100,
                    label: "북아메리카",
                    visit: continents[4].visit,
                    onLabelClick: () => {
                        if (continents[4].visit) {
                            setIsModalOpen(true);
                            setClickContinent("북아메리카")
                            setClickContinentEng("north_america")
                        }
                    }
                },
            ];


            if (typeof window !== 'undefined') {
                const globe = Globe()(globalRef.current);
                globe.globeImageUrl('images/map/earth.jpg')
                    .backgroundImageUrl('//unpkg.com/three-globe/example/img/night-sky.png')
                    .labelText('label')
                    .labelSize(1.5)
                    .labelDotRadius(2.2)
                    .labelTypeFace(IBMRegular)
                    .labelColor((point) => {
                        const continent = continentArray.find(c => c.label === point.label);
                        return continent.visit ? 'rgba(220, 86, 95, 1)' : 'rgba(142,142,142,1)';
                    })
                    .onLabelClick(point => point.onLabelClick())
                    .labelsData(continentArray, { lat: 'lat', lng: 'lng', label: 'label' });
                return () => {
                    globe;
                };
            }
        }
    }, [continents]);

    const closeModal = () => {
        setIsModalOpen(false);
    }


    return (
        <div className={styles.container}>
            <MapCloseButton />
            {isModalOpen &&
                <MapModal
                    text={`${clickContinent}로  이동하시겠습니까?`}
                    move={clcikContinentEng}
                    onCloseModal={closeModal}
                ></MapModal>
            }
            <div ref={globalRef} style={{ width: '100%', zIndex: '1' }} />
        </div>
    );
}