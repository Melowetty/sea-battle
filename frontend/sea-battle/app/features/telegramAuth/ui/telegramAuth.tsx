import { useEffect } from 'react';
import "./telegramAuth.css";
import {IconButton} from "~/shared/iconButton";

export const TelegramLoginButton = () => {
    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://telegram.org/js/telegram-widget.js?22';
        script.async = true;
        script.setAttribute('data-telegram-login', 'FloppaByGucol_bot');
        script.setAttribute('data-size', 'large');
        script.setAttribute('data-auth-url', 'https://your-backend.com/auth/telegram');
        script.setAttribute('data-request-access', 'write');
        script.setAttribute('data-request-url', 'https://your-backend.com/auth/telegram');

        const container = document.getElementById('telegram-btn-container');
        container?.appendChild(script);

        return () => {
            container?.removeChild(script);
        };
    }, []);

    function onTelegramAuth(user) {
        alert('Logged in as ' + user.first_name + ' ' + user.last_name + ' (' + user.id + (user.username ? ', @' + user.username : '') + ')');
    }

    return <div id="telegram-btn-container" className="telegram-btn-wrapper">
    </div>;
};