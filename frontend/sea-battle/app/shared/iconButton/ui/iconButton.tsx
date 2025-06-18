import styles from "./iconButton.module.css";

type buttonProps = {
    label?: string;
    icon: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
    style?: "primary";
}

export function IconButton({label = "кнопка", style = "primary", ...props}: buttonProps) {

  return (
        <button className={`${styles.iconBtn} ${styles[style]}`} type={props.type} onClick={props.onClick}>
            <img className={`${styles.iconBtnIcon}`} src={props.icon} alt={"icon"} />
            <span className={`${styles.iconBtnLabel}`}>{label}</span>
        </button>
  );
}