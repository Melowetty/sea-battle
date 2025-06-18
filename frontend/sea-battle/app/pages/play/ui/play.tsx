import {MainContainer} from "~/widgets/mainContainer";
import styles from "./play.module.css";
import {FieldContainer} from "~/widgets/fieldContainer";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";
import {DialogWindow} from "~/shared/dialog";
import {Header} from "~/widgets/header";
import {useEffect} from "react";

export function PlayPage() {

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

  return (
      <>
        {/*<Header />*/}
        <MainContainer>
            <div className={`${styles.playContainer}`}>
                <FieldContainer>
                    <h1 className={`${styles.containerTitle}`}>Ваше поле</h1>
                    <PlayerField />
                </FieldContainer>
                <div className={`${styles.statsContainer}`}>
                    <h1 className={styles.turnActive}>
                        ВАШ Х0Д!
                    </h1>
                    <DialogWindow
                        confirm={"Завершить"}
                        title={"Завершение игры"}
                        description={"Вы уверены, что хотите завершить игру?"}
                        label="Завершить игру" />
                </div>
                <FieldContainer>
                    <h1 className={`${styles.containerTitle}`}>Поле соперника</h1>
                    <EnemyField />
                </FieldContainer>
            </div>
        </MainContainer>
      </>
  )};
