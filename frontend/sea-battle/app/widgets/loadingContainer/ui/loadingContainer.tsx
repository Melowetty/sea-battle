import styles from "./loadingContainer.module.css";
import {Loading} from "~/shared/loading";
import {useEffect, useRef, useState} from "react";

export function LoadingContainer() {
    const [dots, setDots] = useState<boolean>(false);
    const intervalRef = useRef(null);

    useEffect(() => {
        const intervalId = setInterval(() => {

            setDots(dots => !dots);
            console.log('Выполняю действие каждую секунду');
        }, 1000)

        return () => clearInterval(intervalId);
    }, []);

  return (
        <div className={`${styles.loadingContainer}`}>
            <div className={styles.innerContainer}>
                <h2 className={styles.loadingLabel}>Загрузка.{dots ? (".") : ("..")}</h2>
                <img className={styles.image} src={"../../../../assets/images/chest.gif"} alt={"loading.."}/>
            </div>
        </div>
  );
}