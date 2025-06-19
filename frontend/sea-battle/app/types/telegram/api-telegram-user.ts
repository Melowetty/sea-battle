// types/telegram/api-telegram-user.ts

export default interface ITelegramUser {
    /**
     * Уникальный идентификатор пользователя Telegram
     * @example 123456789
     */
    id: number;

    /**
     * Имя пользователя
     * @example "John"
     */
    first_name: string;

    /**
     * Фамилия пользователя (может отсутствовать)
     * @example "Doe"
     */
    last_name?: string;

    /**
     * Юзернейм в Telegram (может отсутствовать)
     * @example "johndoe"
     */
    username?: string;

    /**
     * URL фото профиля (может отсутствовать)
     * @example "https://t.me/i/userpic/123/abc.jpg"
     */
    photo_url?: string;

    /**
     * Время авторизации (Unix timestamp)
     * @example 1678901234
     */
    auth_date: number;

    /**
     * Хэш для проверки данных
     * @example "abc123..."
     */
    hash: string;
}