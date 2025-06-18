import {MainContainer} from "~/widgets/mainContainer";
import styles from "./win.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {AboutContainer} from "~/widgets/aboutContainer";
import {Header} from "~/widgets/header";

export function WinPage() {

    const navigate = useNavigate();

  return (
      <>
        <MainContainer>
            <h1 className={`${styles.containerTitle}`}>Вы победили!</h1>
            <img className={styles.gif} src={"../../../../assets/images/win.gif"} />
            <Button onClick={() => navigate("/menu")} label={"На главную"}/>
        </MainContainer>
      </>
  )};
