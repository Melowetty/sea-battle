import {MainContainer} from "~/widgets/mainContainer";
import styles from "./play.module.css";
import {FieldContainer} from "~/widgets/fieldContainer";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";
import {DialogWindow} from "~/shared/dialog";
import {Header} from "~/widgets/header";
import {createContext, useCallback, useEffect, useState} from "react";
import {useNavigate} from "react-router";
import {getGame, leaveGame} from "~/features/game/model/game";
import {family} from "detect-libc";

type WaitContextType = {
    waiting: boolean;
    toggleWaitState: () => void;
    setWaitState: (value: boolean) => void;
};

export const WaitContext = createContext<WaitContextType>({
    waiting: false,
    toggleWaitState: () => {},
    setWaitState: () => {}
});

export function PlayPage(id:string) {

    const navigate = useNavigate();

    const [waiting, setWaiting] = useState<boolean>(false);

    const toggleWaitState = useCallback(() => {
        setWaiting(prev => !prev);
    }, []);

    const handleSetWaitState = useCallback((value: boolean) => {
        setWaiting(value);
    }, []);

    useEffect(() => {
        const handleBeforeUnload = (e) => {
            e.preventDefault();
            return e.returnValue = 'Вы уверены, что хотите обновить страницу?';
        };

        window.addEventListener('beforeunload', handleBeforeUnload);

        return () => {
            window.removeEventListener('beforeunload', handleBeforeUnload);
        };
    }, []);

    useEffect(() => {
        const data = getGame(id.id);
        data.then((game) => {
            console.log(game);
        })

    }, []);

    const handleSurrender = () => {
        const data = leaveGame(id.id);
        data.then((game) => {
            console.log(game);
            navigate("/lose");
        })
        // navigate('/lose')
    }

  return (
      <>
        {/*<Header />*/}
          <WaitContext.Provider      value={{waiting,toggleWaitState,setWaitState: handleSetWaitState}}>
                <MainContainer>
                    <div className={`${styles.playContainer}`}>
                        <FieldContainer>
                            <h1
                                className={`${styles.containerTitle}`}>
                                Ваше поле <img className={styles.avatar} src={"../../../../assets/images/stepan.png"} />
                            </h1>
                            <PlayerField id={id}/>
                        </FieldContainer>
                        <div className={`${styles.statsContainer}`}>
                            {!waiting ? (
                                <h1 className={styles.turnActive}>
                                    ВАШ Х0Д!
                                </h1>
                            ):(
                                <h1 className={styles.turnWait}>
                                    Х0Д ПР0ТИВНИКА!
                                </h1>
                            )}

                            <DialogWindow
                                confirm={"Завершить"}
                                title={"Завершение игры"}
                                description={"Вы уверены, что хотите завершить игру?"}
                                label="Завершить игру"
                                onClick={handleSurrender} />
                        </div>
                        <FieldContainer>
                            <h1 className={`${styles.containerTitle}`}>Ботик</h1>
                            <EnemyField id={id} />
                        </FieldContainer>
                    </div>
                </MainContainer>
          </WaitContext.Provider>
      </>
  )};
