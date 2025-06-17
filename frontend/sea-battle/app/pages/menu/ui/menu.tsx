import {MainContainer} from "~/widgets/mainContainer";
import {IconButton} from "~/shared/iconButton";
import "./menu.css";

export function MenuPage() {
  return (
    <MainContainer>
        <h1 className={'container-title'}>АВТ0РИЗАЦИЯ</h1>
        <img className={"pointer"} src={"../../../assets/images/pointer.gif"} />
        <IconButton style={"telegram"} icon={"../../../assets/images/telegram.PNG"} label={"Войти через Telegram"} />
    </MainContainer>
  );
}