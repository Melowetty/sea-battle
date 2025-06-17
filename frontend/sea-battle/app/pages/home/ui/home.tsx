import {MainContainer} from "~/widgets/mainContainer";
import {IconButton} from "~/shared/iconButton";
import {TelegramLoginButton} from "~/features/telegramAuth";
import "./home.css";

export function HomePage() {
  return (
    <MainContainer>
        <h1 className={'container-title'}>АВТ0РИЗАЦИЯ</h1>
        <div className={"description-container"}>
            <img className={"pointer"} src={"../../../assets/images/pointer.gif"} />
            <span>Вступить в команду!</span>
            <img className={"pointer"} src={"../../../assets/images/pointer.gif"} />
        </div>
        <TelegramLoginButton />
    </MainContainer>
  );
}