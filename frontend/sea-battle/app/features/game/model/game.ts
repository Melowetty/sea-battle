import { gameApi } from '../api/gameApi';

export const getGame = async (id:string) => {
    const data = await gameApi.getGameState(id);
    return data;
};

export const leaveGame = async (id:string) => {
    const data = await gameApi.leaveGame(id);
    return data;
}