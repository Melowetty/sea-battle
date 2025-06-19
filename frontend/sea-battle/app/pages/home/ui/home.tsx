import {MainContainer} from "~/widgets/mainContainer";
import {TelegramLoginButton} from "~/features/telegramAuth";
import styles from "./home.module.css";
import {OverlayComponent} from "~/features/overlayComponent";
import {Button} from "~/shared/button";
import {loginUser} from "~/features/telegramAuth/model/auth";
import {FetchButton} from "~/features/fetchButton";
import {useNavigate} from "react-router";

export function HomePage() {

    const navigate = useNavigate();

    const handleClick = () => {
        const user = {
            "auth_date": 17251857,
            "first_name": "Стёп",
            "hash": "вфвфапфаф",
            "id":932326,
            "last_name":"Гуцол",
            "photo_url":"Гуцол",
            "username": "Gucol",
        }
        const data = loginUser(user);
        data.then((data) => {
            console.log(data);
            navigate("/menu");
        })
    }

    return (
      <>
        <OverlayComponent classOverlay={styles.overlay} classLogo={styles.logo}/>
        <MainContainer classMain={styles.mainContainer} classContent={styles.contentContainer}>
            <h1 className={styles.containerTitle}>АВТ0РИЗАЦИЯ</h1>
            <div className={styles.descriptionContainer}>
                <img className={styles.pointer} src={"../../../assets/images/pointer.gif"} />
                <span>Вступить в команду!</span>
                <img className={styles.pointer} src={"../../../assets/images/pointer.gif"} />
            </div>
            <TelegramLoginButton />
            <FetchButton onClick={handleClick} label="Резерв" />
        </MainContainer>
    </>
  );
}