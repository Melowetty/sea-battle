import { roomApi } from '../api/roomApi';
import type {RoomDto} from "~/processes/game/model/types";

export const createRoom = async () => {
    const data = await roomApi.createRoom();
    return data;
};

export const startGameWithBot = async (code: string) => {
    const game = await roomApi.startWithBots(code);
    return game;
}