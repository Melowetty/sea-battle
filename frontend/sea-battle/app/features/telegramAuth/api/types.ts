export interface LoginRequest {
    id: number,
    firstName: string,
    lastName: string | undefined,
    username: string | undefined,
    photoUrl: string | undefined,
    authDate: number,
    hash: string
}

export interface LoginResponse {
    accessToken: string,
    accessTokenExpiresIn: number,
    refreshToken: string,
    refreshTokenExpiresIn: number
}