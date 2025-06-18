import  "./fieldContainer.css";
import type {ReactNode} from "react";

export function FieldContainer({children}: {children: ReactNode}) {

  return (
    <div className="field-container">
        {children}
    </div>
  );
}