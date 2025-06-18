import {MainContainer} from "~/widgets/mainContainer";
import styles from "./play.module.css";
import {FieldContainer} from "~/widgets/fieldContainer";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";
import {DialogWindow} from "~/shared/dialog";
import {Header} from "~/widgets/header";

export function PlayPage() {

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
                    <h1>
                        Ваш ход
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
