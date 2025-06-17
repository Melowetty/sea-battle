import {MainContainer} from "~/widgets/mainContainer";
import {IconButton} from "~/shared/iconButton";
import "./home.css";

export function HomePage() {
  return (
    <MainContainer>
        <h1 className={'container-title'}>АВТ0РИЗАЦИЯ</h1>
        <div className={"description-container"}>
            <img className={"pointer"} src={"../../../assets/images/pointer.gif"} />
            <span>Взять на абордаж!</span>
            <img className={"pointer"} src={"../../../assets/images/pointer.gif"} />
        </div>
        <IconButton style={"telegram"} icon={"../../../assets/images/telegram.PNG"} label={"Войти через Telegram"} />
    </MainContainer>
  );
}