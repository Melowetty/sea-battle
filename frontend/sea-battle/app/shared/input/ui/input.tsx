import styles from "./input.module.css";

type inputProps = {
    label?: string;
    type?: string;
    variant?: "primary" | "secondary" | "tertiary";
    placeholder?: string;
    id?: string;
}

export function Input({label = "Название", variant = "primary", placeholder = "placeholder", type = "", ...props}: inputProps) {

  return (
      <div className={styles.inputContainer}>
        <label className={styles.label} htmlFor={props.id}>{label}</label>
        <input className={styles.input} name={props.id} {...props} id={props.id} placeholder={placeholder} type={type} maxLength={6} />
      </div>
  );
}