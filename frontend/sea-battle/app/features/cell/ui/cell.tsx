import  "./cell.css";
import type {ReactNode} from "react";

type CellProps = {
  variant?: "empty" | "ship" | "miss" | "checked" | "space" | "enemy";
  x: number;
  y: number;
}

export function Cell({variant = "empty", ...props}: CellProps): ReactNode {

  const handleEnemy = (x:number, y:number) => {
    console.log(x, y, variant);
    const cell = document.getElementById(`${x}${y}enemy`);
    if (cell) {
      cell.className = cell.className.replace("enemy", "miss");
      cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
      // cell.className = cell.className.replace("enemy", "checked");
      // cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
    }
  }

  return (
    <th
        data-x={props.x}
        data-y={props.y}
        id={`${props.x}${props.y}${variant}`}
        className={`cell ${variant}`}
        onClick={variant === "enemy" ? () => handleEnemy(props.x, props.y) : () => {}}>
    </th>
  );
}