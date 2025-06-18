import  "./header.css";
import {useNavigate} from "react-router";

export function Header({classHeader}) {

  const navigate = useNavigate();

  const handleCLick = ()=> {
      navigate("/menu");
    }

  return (
    <header className={`header-container ${classHeader}`}>
      <img onClick={handleCLick} className={"header-logo"} src={"../../../../assets/images/logo.png"} alt="seabattle" />
    </header>
  );
}