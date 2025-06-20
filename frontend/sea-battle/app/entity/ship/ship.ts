interface Ship {
    coordinates: {x: number; y: number}[];
    size: number;
    horizontal: boolean;
}


export function generateRandomShips(): Ship[] {
    const boardSize = 10;
    const shipSizes = [4, 3, 3, 2, 2, 2, 1, 1, 1, 1]; // Классические правила: 1x4, 2x3, 3x2, 4x1
    const ships: Ship[] = [];
    const board: boolean[][] = Array(boardSize)
        .fill(null)
        .map(() => Array(boardSize).fill(false));


    const canPlaceShip = (x: number, y: number, size: number, horizontal: boolean): boolean => {

        if (horizontal) {
            if (x + size > boardSize) return false;
        } else {
            if (y + size > boardSize) return false;
        }


        for (let i = -1; i <= size; i++) {
            for (let j = -1; j <= 1; j++) {
                let checkX, checkY;

                if (horizontal) {
                    checkX = x + i;
                    checkY = y + j;
                } else {
                    checkX = x + j;
                    checkY = y + i;
                }

                // Если клетка в пределах поля и уже занята
                if (
                    checkX >= 0 &&
                    checkX < boardSize &&
                    checkY >= 0 &&
                    checkY < boardSize &&
                    board[checkY][checkX]
                ) {
                    return false;
                }
            }
        }
        return true;
    };


    const placeShip = (x: number, y: number, size: number, horizontal: boolean): void => {
        const ship: Ship = {
            coordinates: [],
            size,
            horizontal
        };

        for (let i = 0; i < size; i++) {
            const coordX = horizontal ? x + i : x;
            const coordY = horizontal ? y : y + i;
            board[coordY][coordX] = true;
            ship.coordinates.push({ x: coordX, y: coordY });
        }

        ships.push(ship);
    };


    for (const size of shipSizes) {
        let placed = false;
        let attempts = 0;

        while (!placed && attempts < 100) {
            attempts++;
            const horizontal = Math.random() > 0.5;
            const x = Math.floor(Math.random() * (horizontal ? boardSize - size + 1 : boardSize));
            const y = Math.floor(Math.random() * (horizontal ? boardSize : boardSize - size + 1));

            if (canPlaceShip(x, y, size, horizontal)) {
                placeShip(x, y, size, horizontal);
                placed = true;
            }
        }

        if (!placed) {
            return generateRandomShips();
        }
    }

    return ships;
}
