import { useEffect } from 'react';
import styles from "./telegramAuth.module.css";
import {loginUser} from "~/features/telegramAuth/model/auth";

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


export const TelegramLoginButton = () => {
    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://telegram.org/js/telegram-widget.js?22';
        script.async = true;
        script.setAttribute('data-telegram-login', 'FloppaByGucol_bot');
        script.setAttribute('data-size', 'large');
        // script.setAttribute('data-auth-url', 'https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net/auth/telegram');
        // script.setAttribute('data-request-access', 'write');
        // script.setAttribute('data-request-url', 'https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net/auth/telegram');
        script.setAttribute('data-onauth', 'onTelegramAuth(user)')

        const container = document.getElementById('telegram-btn-container');
        container?.appendChild(script);

        window.onTelegramAuth = function (user: ITelegramUser) {
            loginUser(user);
        }


        return () => {
            container?.removeChild(script);
        };
    }, []);

    return <div id="telegram-btn-container" className={`${styles.telegramBtnWrapper}`}>
    </div>;
};