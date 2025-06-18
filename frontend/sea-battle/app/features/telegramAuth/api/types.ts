export interface LoginRequest {
    id: bigint,
    firstName: string,
    lastName: string,
    username: string,
    photoUrl: string,
    authDate: bigint,
    hash: string
}

export interface LoginResponse {
    accessToken: string,
    accessTokenExpiresIn: bigint,
    refreshToken: string,
    refreshTokenExpiresIn: bigint
}