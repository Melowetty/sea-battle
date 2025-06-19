import styles from "./auth.css";
import type {ReactNode} from "react";

type CellProps = {
  variant?: "empty" | "ship" | "miss" | "checked" | "space" | "enemy";
  x: number;
  y: number;
  onClick?: () => void;
}

export function Auth({variant = "empty", ...props}: CellProps): ReactNode {

  const handleEnemy = (x:number, y:number) => {
    console.log(x, y, variant);
    const cell = document.getElementById(`${x}${y}enemy`);
    if (cell) {
      cell.className = cell.className.replace(`${styles[variant]}`, `${styles.miss}`);
      cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
      // cell.className = cell.className.replace(`${styles[variant]}`, `${styles.checked}`);
      // cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
    }
  }

  return (
    <th
        data-x={props.x}
        data-y={props.y}
        id={`${props.x}${props.y}${variant}`}
        className={`${styles.cell} ${styles[variant]}`}
        onClick={variant === "enemy" ? () => handleEnemy(props.x, props.y) : () => {}}>
    </th>
  );
}