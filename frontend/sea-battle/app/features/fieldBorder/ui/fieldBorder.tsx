import styles from "./fieldBorder.module.css";
import type {ReactNode} from "react";

export function FieldBorder({children}: {children: ReactNode}) {

  return (
      <div className={styles.fieldContainer}>
        <table className={styles.fieldBorder}>
          <tbody>
            <tr className={styles.fieldRow}>
              <th>&nbsp;</th>
              <th>A</th>
              <th>B</th>
              <th>C</th>
              <th>D</th>
              <th>E</th>
              <th>F</th>
              <th>G</th>
              <th>H</th>
              <th>I</th>
              <th>J</th>
            </tr>
            {children}
          </tbody>
        </table>
      </div>
  );
}