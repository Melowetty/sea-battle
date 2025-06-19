export interface Coordinate {
    x: number;
    y: number;
}

export interface Ship {
    coordinates: Coordinate[];
    hits: Coordinate[];
    healthPoints: number;
    status: 'ALIVE' | 'DEAD';
}

export interface PlayerState {
    player: string;
    ships: Ship[];
    destructions: Coordinate[];
    hits: Coordinate[];
    misses: Coordinate[];
    aliveShips: number;
}

export interface GameDto {
    id: string;
    players: UserDto[];
    currentPlayer: string;
    playerState: Record<string, PlayerState>;
    gameStartDate: string;
}

export interface RoomDto {
    code: string;
    host: UserDto;
    players: UserDto[];
    maxSize: number;
    isPublic: boolean;
}

export interface UserDto {
    id: string;
    name: string;
    avatar: string | null;
    isBot: boolean;
}