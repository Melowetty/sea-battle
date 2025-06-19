import {MainContainer} from "~/widgets/mainContainer";
import {TelegramLoginButton} from "~/features/telegramAuth";
import styles from "./home.module.css";
import {OverlayComponent} from "~/features/overlayComponent";
import {Button} from "~/shared/button";
import {loginUser} from "~/features/telegramAuth/model/auth";
import {FetchButton} from "~/features/fetchButton";
import {useNavigate} from "react-router";
import {useAuthStore} from "~/features/auth/model/authStore";
import {authApi} from "~/features/telegramAuth/api/telegramAuth.api";
import type ITelegramUser from "~/types/telegram/api-telegram-user";
import type {LoginRequest} from "~/features/telegramAuth/api/types";

export function HomePage() {

    const navigate = useNavigate();

    const setAuthData = useAuthStore(state => state.setAuthData);

    const user:LoginRequest = {
        "authDate": 17251857,
        "firstName": "Стёп",
        "hash": "вфвфапфаф",
        "id":932326,
        "lastName":"Гуцол",
        "photoUrl":"Гуцол",
        "username": "Gucol",
    }

    const login = async(user:LoginRequest) =>{
        const { data } = await authApi.login(user);
        const expiresAt = Date.now() + data.accessTokenExpiresIn * 1000;
        console.log(data);
        setAuthData(data.accessToken, expiresAt);
        navigate("/menu");
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
            <TelegramLoginButton onClick={() => login(user)} />
            <FetchButton onClick={() => login(user)} label="Резерв" />
        </MainContainer>
    </>
  );
}