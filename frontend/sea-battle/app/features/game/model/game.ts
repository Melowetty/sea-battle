import { gameApi } from '../api/gameApi';
import type {Coordinate} from "~/processes/game/model/types";

export const getGame = async (id:string) => {
    const data = await gameApi.getGameState(id);
    return data;
};

export const leaveGame = async (id:string) => {
    const data = await gameApi.leaveGame(id);
    return data;
}

export const shot = async (id:string, coords: Coordinate) => {
    const data = await gameApi.shot(id, coords);
    return data;
}