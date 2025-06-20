import { forwardRef, useImperativeHandle, useContext, useState } from "react";
import styles from "./cell.module.css";
import { shot } from "~/features/game/model/game";
import { WaitContext } from "~/pages/play/ui/play";

type CellProps = {
  variant?: "empty" | "ship" | "miss" | "checked" | "space" | "enemy";
  x: number;
  y: number;
  type: "ally" | "enemy";
  gameId: string;
};

export type CellHandle = {
  handleAlly: (x: number, y: number, tag: string) => void;
};

export const Cell = forwardRef<CellHandle, CellProps>(
    ({ variant = "empty", gameId, ...props }, ref) => {
      const { waiting, setWaitState } = useContext(WaitContext);
      const [status, setStatus] = useState("waiting");

      const handleEnemy = (x: number, y: number) => {
        const data = shot(gameId, { x, y });
        data.then((data) => {
          const result = data.data.event;
          const cell = document.getElementById(`${x}${y}enemy`);
          if (cell) {
            if (result === "HIT") {
              setStatus("checked");
              cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
            } else if (result === "MISS") {
              setStatus("miss");
              cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
              setWaitState(true);
            }
          }
        });
      };

      const handleAlly = (x: number, y: number, tag: string) => {
        const cell = document.getElementById(`${x}${y}ally`);
        console.log("pdasdada");
        if (cell) {
          if (tag === "hits") {
            setStatus("checked");
            cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
          } else if (tag === "destructions") {
            setStatus("miss");
          } else {
            setStatus("miss");
            cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
          }
        }
      };

      useImperativeHandle(ref, () => ({
        handleAlly,
      }));

      return (
          <>
          </>
      );
    }
);

Cell.displayName = "Cell";