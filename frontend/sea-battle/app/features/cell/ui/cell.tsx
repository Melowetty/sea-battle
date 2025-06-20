import styles from "./cell.module.css";
import {type ReactNode, useContext, useState} from "react";
import {shot} from "~/features/game/model/game";
import {gameApi} from "~/features/game/api/gameApi";
import {WaitContext} from "~/pages/play/ui/play";



type CellProps = {
  variant?: "empty" | "ship" | "miss" | "checked" | "space" | "enemy";
  x: number;
  y: number;
  type: "ally" | "enemy";
  gameId: string;
}

export function Cell({variant = "empty", gameId,  ...props}: CellProps): ReactNode {
  const { waiting,setWaitState,toggleWaitState } = useContext(WaitContext);
  const [status, setStatus] = useState("waiting");
  const handleEnemy = (x:number, y:number) => {
    const data = shot(gameId, {x: x, y: y});
    data.then((data) => {
      const result = data.data.event;
      const cell = document.getElementById(`${x}${y}enemy`);
      if (cell) {
        if (result === "HIT") {
          setStatus("checked");
          cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
        }
        else if (result === "MISS") {
          setStatus("miss");
          cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
          setWaitState(true);
        }
      }
      // console.log(data);
      // console.log(waiting);
    });
  }


  return (
    <th
        data-x={props.x}
        data-y={props.y}
        id={`${props.x}${props.y}${props.type}`}
        className={waiting === false ? `${styles.cell} ${styles[variant]} ${styles[status]}` :  `${styles.cell} ${styles[variant]} ${styles[status]} ${styles.disabled}`}
        onClick={variant === "enemy" ? () => handleEnemy(props.x, props.y) : () => {}}>
    </th>
  );
}