import { useState, useEffect } from 'react';
import { gameApi } from '../api/gameApi';
import type { GameDto, Coordinate } from '~/processes/game/model/types';

export const useGame = (gameId: string) => {
    const [game, setGame] = useState<GameDto | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    const fetchGame = async () => {
        try {
            const { data } = await gameApi.getGameState(gameId);
            setGame(data);
        } catch (err) {
            setError('Ошибка загрузки игры');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchGame();

        const interval = setInterval(fetchGame, 5000);
        return () => clearInterval(interval);
    }, [gameId]);

    const fire = async (coords: Coordinate) => {
        try {
            await gameApi.fire(gameId, coords);
            await fetchGame();
        } catch (err) {
            setError('Ошибка при выстреле');
        }
    };

    return { game, loading, error, fire };
};