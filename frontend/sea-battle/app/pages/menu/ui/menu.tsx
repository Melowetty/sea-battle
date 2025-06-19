import {MainContainer} from "~/widgets/mainContainer";
import styles from "./menu.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {OverlayComponent} from "~/features/overlayComponent";
import {Header} from "~/widgets/header";

export function MenuPage() {

    const navigate = useNavigate();

  return (
      <>
        {/*<Header />*/}
        <MainContainer>
            <h1 className={styles.containerTitle}>МЕНЮ</h1>
            <Button onClick={() => {navigate("/play")}} label={"Начать игру"} />
            <Button onClick={() => {navigate("/multiplayer")}} label={"В сети"} />
            <Button onClick={() => {navigate("/about")}} label={"0 нас"} />
            <img className={styles.animation} src={"../../../assets/images/pirateFlag.gif"} />
        </MainContainer>
      </>
  )};
