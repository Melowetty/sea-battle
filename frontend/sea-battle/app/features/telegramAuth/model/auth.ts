import { authApi } from '../api/telegramAuth.api';
import type {LoginRequest, LoginResponse} from "~/features/telegramAuth/api/types";
import type ITelegramUser from "~/types/telegram/api-telegram-user";
import {useAuthStore} from "~/features/auth/model/authStore";
import {useNavigate} from "react-router";

export const loginUser = async (credentials: ITelegramUser) => {
    const navigate = useNavigate();
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
    navigate("/menu");
    // localStorage.setItem('token', data.accessToken);
    // localStorage.setItem('expiresAt', String(data.accessTokenExpiresIn));
    return data;
};