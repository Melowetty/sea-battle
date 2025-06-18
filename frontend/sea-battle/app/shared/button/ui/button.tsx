import "./button.css";

type buttonProps = {
    label?: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
}

export function Button({label = "кнопка", ...props}: buttonProps) {

  return (
        <button className={`btn`} type={props.type} onClick={props.onClick}>
            <span className={"btn-label"}>{label}</span>
        </button>
  );
}