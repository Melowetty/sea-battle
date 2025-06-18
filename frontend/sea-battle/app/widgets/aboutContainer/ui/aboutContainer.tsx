import styles from "./aboutContainer.module.css";
import {Member} from "~/shared/member";

export function AboutContainer() {

  return (
    <div className={`${styles.aboutContainer}`}>
        <Member name={"Денис Малинин"} role={"Backend"} image={"../../../assets/images/denis.png"}/>
        <Member name={"Гуцол Степан"} role={"Frontend"} image={"../../../assets/images/stepan.png"}/>
        <Member name={"Кунакбаев Данил"} role={"Backend"} image={"../../../assets/images/danil.png"}/>
    </div>
  );
}