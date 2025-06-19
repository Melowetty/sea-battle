import { useEffect } from 'react';
import styles from "./telegramAuth.module.css";
import {loginUser} from "~/features/telegramAuth/model/auth";
import {useAuthStore} from "~/features/auth/model/authStore";
import {useNavigate} from "react-router";
import type {LoginRequest} from "~/features/telegramAuth/api/types";

interface ITelegramUser {
    id: number;
    first_name: string;
    last_name?: string;
    username?: string;
    photo_url?: string;
    auth_date: number;
    hash: string;
}

declare global {
    interface Window {
        onTelegramAuth: (user: ITelegramUser) => void
    }
}

type tgProps = {
    onClick: (user:LoginRequest) => void;
}

export const TelegramLoginButton = ({onClick}: tgProps) => {

    const setAuthData = useAuthStore(state => state.setAuthData);
    const navigate = useNavigate();

    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://telegram.org/js/telegram-widget.js?22';
        script.async = true;
        script.setAttribute('data-telegram-login', 'FloppaByGucol_bot');
        script.setAttribute('data-size', 'large');
        script.setAttribute('data-onauth', 'onTelegramAuth(user)')

        const container = document.getElementById('telegram-btn-container');
        container?.appendChild(script);

        window.onTelegramAuth = function (user: ITelegramUser) {
            const userTg:LoginRequest = {
                "authDate": user.auth_date,
                "firstName": user.first_name,
                "hash": user.hash,
                "id": user.id,
                "lastName": user.last_name,
                "photoUrl": user.photo_url,
                "username": user.username,
            }
            onClick(userTg);
        }


        return () => {
            container?.removeChild(script);
        };
    }, []);

    return <div id="telegram-btn-container" className={`${styles.telegramBtnWrapper}`}>
    </div>;
};