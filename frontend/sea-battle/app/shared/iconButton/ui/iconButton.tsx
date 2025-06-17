import styles from "./iconButton.css";
import {useNavigate} from "react-router";

type buttonProps = {
    label?: string;
    icon: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
    style?: "telegram" | "primary";
}

export function IconButton({label = "кнопка", style = "primary", ...props}: buttonProps) {

  return (
    <button className={`icon-btn ${style}`} type={props.type} onClick={props.onClick}>
        <img className={"icon-btn-icon"} src={props.icon} alt={"icon"} />
        <span className={"icon-btn-label"}>{label}</span>
    </button>
  );
}