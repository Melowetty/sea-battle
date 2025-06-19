import styles from "./playerField.module.css";
import cellStyles from "../../cell/ui/cell.module.css";
import {FieldBorder} from "~/features/fieldBorder";
import {Cell} from "~/features/cell";
import {useEffect, useState} from "react";
import {getGame} from "~/features/game/model/game";
import type {Coordinate, Ship} from "~/processes/game/model/types";

export function PlayerField(id) {

    const [ships, setShips] = useState<Ship[]>();
    const [coordsArray, setCoords] = useState<Coordinate[]>([]);

    const items = Array.from({ length: 10 }, (_, index) => ({
        value: index + 1,
    }));

    useEffect(() => {
        const data = getGame(id.id.id);
        data.then((game) => {
            const ships = game.data.playerState.ships;
            console.log(ships);
            setShips(ships);
            ships.map((ship) => {
                const coords = ship.coordinates;
                coords.map((coord) => {
                    coordsArray.push(coord);
                })
            })
        })
    }, [])

    useEffect(() => {
        console.log(coordsArray);
    }, [ships]);

    return (
      <FieldBorder>
          {items.map((row) => (
              <tr>
                  <th>{row.value}</th>
                  {items.map((item) => {

                      if (coordsArray.some(coords => coords.y === (row.value - 1) && coords.x === (item.value - 1))) {
                          return (
                              <Cell type={"ally"} variant={"ship"} y={row.value} x={item.value}/>
                          );
                      }
                      else{
                          return <Cell type={"ally"} variant={"empty"} y={row.value} x={item.value}/>
                      }
                  })}
              </tr>
          ))}
      </FieldBorder>
    );
}