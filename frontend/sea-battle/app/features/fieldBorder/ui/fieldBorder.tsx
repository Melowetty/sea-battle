import  "./fieldBorder.css";
import type {ReactNode} from "react";

export function FieldBorder({children}: {children: ReactNode}) {

  return (
      <div className={"field-container"}>
        <table className={"field-border"}>
          <tr className={"field-row"}>
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
        </table>
      </div>
  );
}