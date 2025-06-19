import {MainContainer} from "~/widgets/mainContainer";
import styles from "./multiplayer.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {Input} from "~/shared/input";
import {Separator} from "~/shared/separator";

export function MultiplayerPage() {

    const navigate = useNavigate();

  return (
      <>
        <MainContainer>
            <h1 className={styles.containerTitle}>В СЕТИ</h1>
            {/*<img className={styles.earth} src={'../../../../assets/images/earth.gif'} alt={"globe"} />*/}
            <form className={styles.form}>
                <Input placeholder={"Код"} label={"Код комнаты"}/>
                <Button variant={"secondary"} onClick={() => {navigate("/multiplayer")}} label={"Присоединиться по коду"} />
            </form>

            <Button onClick={() => {navigate("/play")}} label={"Создать комнату"} />
            <Separator />
            <Button onClick={() => {navigate(-1)}} label={"Назад"} />
        </MainContainer>
      </>
  )};
