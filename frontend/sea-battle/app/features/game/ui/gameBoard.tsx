import type { Coordinate, Ship } from '~/processes/game/model/types';

interface GameBoardProps {
    ships: Ship[];
    hits: Coordinate[];
    misses: Coordinate[];
    onCellClick?: (coords: Coordinate) => void;
    isInteractive?: boolean;
}

export const GameBoard = ({
                              ships,
                              hits,
                              misses,
                              onCellClick,
                              isInteractive = false,
                          }: GameBoardProps) => {
    const handleClick = (x: number, y: number) => {
        if (!onCellClick || !isInteractive) return;
        onCellClick({ x, y });
    };

    return (
        <div className="grid grid-cols-10 gap-1">
            {Array.from({ length: 10 }).map((_, y) => (
                Array.from({ length: 10 }).map((_, x) => {
                    const isShip = ships.some(ship =>
                        ship.coordinates.some(c => c.x === x && c.y === y)
                    );
                    const isHit = hits.some(c => c.x === x && c.y === y);
                    const isMiss = misses.some(c => c.x === x && c.y === y);

                    return (
                        <div
                            key={`${x}-${y}`}
                            onClick={() => handleClick(x, y)}
                            className={`
                w-8 h-8 border border-gray-300
                ${isShip ? 'bg-gray-500' : ''}
                ${isHit ? 'bg-red-500' : ''}
                ${isMiss ? 'bg-blue-500' : ''}
                ${isInteractive ? 'cursor-pointer hover:bg-gray-200' : ''}
              `}
                        />
                    );
                })
            ))}
        </div>
    );
};