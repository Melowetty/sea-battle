import {MainContainer} from "~/widgets/mainContainer";
import styles from "./menu.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {createRoom, startGameWithBot} from "~/features/room/model/room";
import type {RoomDto} from "~/processes/game/model/types";
import {LoadingContainer} from "~/widgets/loadingContainer";
import {useState} from "react";

export function MenuPage() {

    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState<boolean>(false);


    const handleBotGame = () => {
        setIsLoading(true);
        const data = createRoom();
        data.then((room) => {
            console.log(room);
            const game = startGameWithBot(room.data.code);
            game.then((session) => {
                console.log(session);
                navigate(`/play/${session.data.id}`);
            })
        })
    }

  return (
      <>
        {isLoading && (<LoadingContainer />)}
        <MainContainer>
            <h1 className={styles.containerTitle}>МЕНЮ</h1>
            <Button onClick={handleBotGame} label={"Начать игру"} />
            {/*<Button onClick={() => {navigate("/play")}} label={"Начать игру"} />*/}
            <Button onClick={() => {navigate("/multiplayer")}} label={"В сети"} />
            <Button onClick={() => {navigate("/about")}} label={"0 нас"} />
            <img className={styles.animation} src={"../../../assets/images/pirateFlag.gif"} />
        </MainContainer>
      </>
  )};
