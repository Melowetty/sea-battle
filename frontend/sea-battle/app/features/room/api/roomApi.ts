import type { GameDto, RoomDto } from "~/processes/game/model/types";
import type {LoginRequest, LoginResponse} from "~/features/telegramAuth/api/types";
import {httpClient, httpClientToken} from "~/shared/api/client";
import {API_ENDPOINTS} from "~/shared/api/endpoints";

export const roomApi = {
    createRoom: () =>
        httpClientToken.post(API_ENDPOINTS.ROOM.CREATE_ROOM, {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    joinRandom: () =>
        httpClientToken.post(API_ENDPOINTS.ROOM.JOIN_RANDOM, {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    joinByCode: (code: string) =>
        httpClientToken.post<RoomDto>(API_ENDPOINTS.ROOM.JOIN_BY_CODE(code), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    startGame: (code: string) =>
        httpClientToken.post<GameDto>(API_ENDPOINTS.ROOM.START_GAME(code), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    startWithBots:  (code: string) =>
        httpClientToken.post<GameDto>(API_ENDPOINTS.ROOM.START_WITH_BOTS(code), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    leaveRoom: (code: string) =>
        httpClientToken.post(API_ENDPOINTS.ROOM.LEAVE_ROOM(code), {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

};