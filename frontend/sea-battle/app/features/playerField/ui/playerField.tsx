import  "./playerField.css";
import type {ReactNode} from "react";
import {FieldBorder} from "~/features/fieldBorder";
import {Cell} from "~/features/cell";

export function PlayerField() {


    const items = Array.from({ length: 10 }, (_, index) => ({
        value: index + 1,
    }));

    return (
      <FieldBorder>
          {items.map((row) => (
              <tr>
                  <th>{row.value}</th>
                  {items.map((item) => (
                      <Cell x={row.value} y={item.value} />
                  ))}
              </tr>
          ))}
      </FieldBorder>
    );
}