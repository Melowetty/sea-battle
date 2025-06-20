export const API_ENDPOINTS = {
    AUTH: {
        LOGIN: '/auth/telegram',
    },
    ROOM: {
        CREATE_ROOM: '/rooms/create',
        JOIN_RANDOM: '/rooms/join',
        JOIN_BY_CODE: (code:string) => `/rooms/join/${code}`,
        START_GAME: (code:string) => `/rooms/${code}/start`,
        START_WITH_BOTS: (code:string) => `/rooms/${code}/start/bots`,
        LEAVE_ROOM: (code:string) => `/rooms/${code}/leave`,
    },
    GAME: {
        GET_GAME_STATE: (id:string) => `/games/${id}`,
        LEAVE_GAME: (id:string) => `/games/${id}/leave`,
        SHOT: (id: string) => `/games/${id}/shot`,
    }
};