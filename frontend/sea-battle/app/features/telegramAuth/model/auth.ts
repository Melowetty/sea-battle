import { authApi } from '../api/telegramAuth.api';
import type {LoginRequest} from "~/features/telegramAuth/api/types";
import type ITelegramUser from "~/types/telegram/api-telegram-user";

export const loginUser = async (credentials: ITelegramUser) => {
    console.log(credentials);
    const { data } = await authApi.login(credentials);
    // localStorage.setItem('token', data.token);
    console.log(data);
    return data;
};