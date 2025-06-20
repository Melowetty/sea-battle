import {httpClient, httpClientToken} from "~/shared/api/client";
import {API_ENDPOINTS} from "~/shared/api/endpoints";
import type { GameDto, Coordinate } from '~/processes/game/model/types';

export const gameApi = {
    getGameState: (id:string) =>
        httpClientToken.get<GameDto>(API_ENDPOINTS.GAME.GET_GAME_STATE(id), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    leaveGame: (id:string) =>
        httpClientToken.post<GameDto>(API_ENDPOINTS.GAME.LEAVE_GAME(id), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    shot: (id:string, coords: Coordinate) =>
        httpClientToken.post<GameDto>(API_ENDPOINTS.GAME.SHOT(id), coords, {
            headers: {
                'Content-Type': 'application/json',
            }
        }),
};