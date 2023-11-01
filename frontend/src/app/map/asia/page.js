import MapAllButton from '../component/MapAllButton';
import MapCloseButton from '../component/MapCloseButton';
import Marker from '../component/Marker';
import styles from './page.module.css'


export default function Asia() {

    const countries = [
        {
            name: '대한민국',
            visited: true,
            position: {x:300, y:475}
        },
        {
            name: '일본',
            visited: true,
            position: {x:370, y:480}
        },
        {
            name: '태국',
            visited: false,
            position: {x:60, y:710}
        },
        {
            name: '중국',
            visited: false,
            position: {x:140, y:420}
        },
    ]

    return (
        <div className={styles.container}>
            <MapCloseButton />
            {countries.map((country, index) => (
                <Marker
                    key={index}
                    country={country.name}
                    visited={country.visited}
                    position={country.position}
                />
            ))}
            <MapAllButton />
        </div>
    );
}
