import {MainContainer} from "~/widgets/mainContainer";
import {TelegramLoginButton} from "~/features/telegramAuth";
import styles from "./home.module.css";
import {OverlayComponent} from "~/features/overlayComponent";

export function HomePage() {
  return (
      <>
        <OverlayComponent classOverlay={styles.overlay} classLogo={styles.logo}/>
        <MainContainer classMain={styles.mainContainer} classContent={styles.contentContainer}>
            <h1 className={styles.containerTitle}>АВТ0РИЗАЦИЯ</h1>
            <div className={styles.descriptionContainer}>
                <img className={styles.pointer} src={"../../../assets/images/pointer.gif"} />
                <span>Вступить в команду!</span>
                <img className={styles.pointer} src={"../../../assets/images/pointer.gif"} />
            </div>
            <TelegramLoginButton />
        </MainContainer>
  </>
  );
}