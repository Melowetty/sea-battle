import styles from "./header.css";
import {useNavigate} from "react-router";



export function Header() {
  const navigate = useNavigate();

  const handleCLick = ()=> {
      navigate("/");
    }

  return (
    <header className={"header-container"}>
      <img onClick={handleCLick} className={"header-logo"} src={"../../../../assets/images/logo.png"} alt="seabattle" />
    </header>
  );
}