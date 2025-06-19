import styles from "./fieldContainer.module.css";
import type {ReactNode} from "react";

export function FieldContainer({children}: {children: ReactNode}) {

  return (
    <div>
        {children}
    </div>
  );
}