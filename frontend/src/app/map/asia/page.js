'use client'

import { useState } from 'react';
import MapAllButton from '../component/MapAllButton';
import MapCloseButton from '../component/MapCloseButton';
import Marker from '../component/Marker';
import styles from './page.module.css'
import MapModal from '../component/MapModal';


export default function Asia() {

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedCountry, setSelectedCountry] = useState('');
    const [selectedCountryEng, setSelectedCountryEng] = useState('');

    // 모달 관련 함수 
    const openModal = (country) => {
        setIsModalOpen(true);
        setSelectedCountry(country.name);
        setSelectedCountryEng(country.engName);
    }

    const closeModal = () => {
        setIsModalOpen(false);
    }

    const countries = [
        {
            name: '대한민국',
            engName: 'korea',
            visited: true,
            position: { x: 300, y: 475 }
        },
        {
            name: '일본',
            engName: 'japan',
            visited: true,
            position: { x: 370, y: 480 }
        },
        {
            name: '태국',
            engName: 'thailand',
            visited: false,
            position: { x: 60, y: 710 }
        },
        {
            name: '중국',
            engName: 'china',
            visited: false,
            position: { x: 140, y: 420 }
        },
    ]

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
