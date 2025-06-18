import {MainContainer} from "~/widgets/mainContainer";
import styles from "./lose.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {AboutContainer} from "~/widgets/aboutContainer";
import {Header} from "~/widgets/header";

export function LosePage() {

    const navigate = useNavigate();

  return (
      <>
        <MainContainer>
            <h1 className={`${styles.containerTitle}`}>Вы проиграли!</h1>
            <img className={styles.gif} src={"../../../../assets/images/lose.gif"} />
            <Button onClick={() => navigate("/menu")} label={"На главную"}/>
        </MainContainer>
      </>
  )};
