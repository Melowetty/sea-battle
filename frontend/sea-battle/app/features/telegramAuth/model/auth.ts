import { authApi } from '../api/telegramAuth.api';
import type {LoginRequest, LoginResponse} from "~/features/telegramAuth/api/types";
import type ITelegramUser from "~/types/telegram/api-telegram-user";

export const loginUser = async (credentials: ITelegramUser) => {
    const cleanCredentials: LoginRequest = {
        "authDate": credentials.auth_date,
        "firstName": credentials.first_name,
        "hash": credentials.hash,
        "id":credentials.id,
        "lastName":credentials.last_name,
        "photoUrl":credentials.photo_url,
        "username":credentials.username,
    }
    console.log(cleanCredentials);
    const { data } = await authApi.login(cleanCredentials);
    // localStorage.setItem('token', data.token);
    console.log(data);
    return data;
};