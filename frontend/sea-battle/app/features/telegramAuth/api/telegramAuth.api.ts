import { httpClient } from '~/shared/api/client';
import { API_ENDPOINTS } from '~/shared/api/endpoints';
import type { LoginRequest, LoginResponse } from './types';
import type ITelegramUser from "~/types/telegram/api-telegram-user";

export const authApi = {
    login: (data: LoginRequest) =>
        httpClient.post<LoginResponse>(API_ENDPOINTS.AUTH.LOGIN, data, {
            headers: {
                'Content-Type': 'application/json',
            }
        }),

    // logout: () => httpClient.post(API_ENDPOINTS.AUTH.LOGOUT)
};