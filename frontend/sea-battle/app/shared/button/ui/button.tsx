import "./button.css";

type buttonProps = {
    label?: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
    variant?: "primary" | "secondary" | "tertiary";
}

export function Button({label = "кнопка", variant = "primary", ...props}: buttonProps) {

  return (
        <button className={`btn ${variant}`} type={props.type} onClick={props.onClick}>
            <span className={"btn-label"}>{label}</span>
        </button>
  );
}