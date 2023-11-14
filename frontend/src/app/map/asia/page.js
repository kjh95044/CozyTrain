'use client'

import { useEffect, useState } from 'react';
import MapAllButton from '../component/MapAllButton';
import MapCloseButton from '../component/MapCloseButton';
import Marker from '../component/Marker';
import styles from './page.module.css'
import MapModal from '../component/MapModal';

import getFetch from "@/services/getFetch"


export default function Asia() {
    const [asiaCountry, setAsiaCountry] = useState([]);
    const [countries, setCountries] = useState([]);

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedCountry, setSelectedCountry] = useState('');
    const [selectedCountryEng, setSelectedCountryEng] = useState('');

    // 아시아 나라 정보 
    const getAsiaCountry = async () => {
        const data = await getFetch("train/visit/country?continent=asia")
        setAsiaCountry(data.response);
    }

    useEffect(() => {
        getAsiaCountry();
    }, []);

    useEffect(() => {
        if (asiaCountry.length !== 0) {
            const newCountries = [
                {
                    name: '대한민국',
                    engName: 'korea',
                    visited: asiaCountry[0].visit,
                    position: { x: 300, y: 475 }
                },
                {
                    name: '일본',
                    engName: 'japan',
                    visited: asiaCountry[1].visit,
                    position: { x: 370, y: 480 }
                },
                {
                    name: '태국',
                    engName: 'thailand',
                    visited: asiaCountry[2].visit,
                    position: { x: 60, y: 710 }
                },
                {
                    name: '중국',
                    engName: 'china',
                    visited: asiaCountry[3].visit,
                    position: { x: 140, y: 420 }
                },
            ]

            setCountries(newCountries);
        }
    }, [asiaCountry])

    // 모달 관련 함수 
    const openModal = (country) => {
        setIsModalOpen(true);
        setSelectedCountry(country.name);
        setSelectedCountryEng(country.engName);
    }

    const closeModal = () => {
        setIsModalOpen(false);
    }



    return (
        <div className={styles.container}>
            <MapCloseButton />
            {countries.map((country, index) => (
                <Marker
                    key={index}
                    country={country}
                    visited={country.visited}
                    position={country.position}
                    openModal={openModal}
                />
            ))}
            {isModalOpen &&
                <MapModal
                    text={`${selectedCountry}(으)로 이동하시겠습니까?`}
                    move={selectedCountryEng}
                    onCloseModal={closeModal}>
                </MapModal>
            }
            <div className={styles.mapAllButton}>
                <MapAllButton>전체 보기</MapAllButton>
            </div>
        </div>
    );
}
