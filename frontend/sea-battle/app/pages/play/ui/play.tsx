import {MainContainer} from "~/widgets/mainContainer";
import "./play.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {FieldContainer} from "~/widgets/fieldContainer";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";
import {DialogWindow} from "~/shared/dialog";
import {Header} from "~/widgets/header";

export function PlayPage() {

  return (
      <>
        <Header />
        <MainContainer>
            <div className="play-container">
                <FieldContainer>
                    <h1 className={'container-title'}>Ваше поле</h1>
                    <PlayerField />
                </FieldContainer>
                <div className={'stats-container'}>
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
                    <h1 className={'container-title'}>Поле соперника</h1>
                    <EnemyField />
                </FieldContainer>
            </div>
        </MainContainer>
      </>
  )};
