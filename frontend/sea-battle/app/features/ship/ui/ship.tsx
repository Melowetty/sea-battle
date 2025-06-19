import { useState } from 'react';
import { Button } from '~/shared/button';
import type { Ship, Coordinate } from '~/processes/game/model/types';
import {GameBoard} from "~/features/game/ui/gameBoard";

export const ShipPlacement = ({
                                  onComplete
                              }: {
    onComplete: (ships: Ship[]) => void
}) => {
    const [ships, setShips] = useState<Ship[]>([]);

    const addShip = (coordinates: Coordinate[]) => {
        setShips([...ships, {
            coordinates,
            hits: [],
            healthPoints: coordinates.length,
            status: 'ALIVE'
        }]);
    };

    return (
        <div>
            <h2>Расставьте корабли</h2>
    <GameBoard
    ships={ships}
    hits={[]}
    misses={[]}
    onCellClick={(coords) => {
        // Логика добавления корабля
    }}
    isInteractive
    />
    <button
        onClick={() => onComplete(ships)}
    disabled={ships.length < 5} // Минимум 5 кораблей
        >
        Подтвердить
    </button>
    </div>
);
};