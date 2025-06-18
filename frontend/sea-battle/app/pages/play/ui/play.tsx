import {MainContainer} from "~/widgets/mainContainer";
import "./play.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {FieldContainer} from "~/widgets/fieldContainer";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";

export function PlayPage() {

  return (
    <MainContainer>
        <div className="play-container">
            <FieldContainer>
                <h1 className={'container-title'}>Ваше поле</h1>
                <PlayerField />
            </FieldContainer>

            <FieldContainer>
                <h1 className={'container-title'}>Поле соперника</h1>
                <EnemyField />
            </FieldContainer>
        </div>
    </MainContainer>
  )};
