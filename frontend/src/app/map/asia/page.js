import MapAllButton from '../component/MapAllButton';
import MapCloseButton from '../component/MapCloseButton';
import styles from './page.module.css'


export default function Asia() {
    return (
        <div className={styles.container}>
            <MapCloseButton />
            <MapAllButton />
        </div>
    );
}
