import styles from "./header.module.css";
import {useNavigate} from "react-router";

type headerProps = {
    classHeader?: string;
}

export function Header({classHeader}: headerProps) {

  const navigate = useNavigate();

  const handleCLick = ()=> {
      navigate("/menu");
    }

  return (
    <header className={`${styles.headerContainer} ${classHeader}`}>
      <img onClick={handleCLick} className={`${styles.headerLogo}`} src={"../../../../assets/images/logo.png"} alt="seabattle" />
    </header>
  );
}