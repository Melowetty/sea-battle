import styles from "./button.module.css";

type buttonProps = {
    label?: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
    variant?: "primary" | "secondary" | "tertiary";
}

export function Button({label = "кнопка", variant = "primary", ...props}: buttonProps) {

  return (
        <button className={`${styles.btn} ${styles[variant]}`} type={props.type} onClick={props.onClick}>
            <span className={`${styles.btnLabel}`}>{label}</span>
        </button>
  );
}