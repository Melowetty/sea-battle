import { roomApi } from '../api/roomApi';
import { Button } from "~/shared/button";
import type {RoomDto} from "~/processes/game/model/types";
import { useParams } from 'react-router-dom';
import {useState, useEffect} from "react";
import {useAuthStore} from "~/features/auth/model/authStore";

export const RoomPage = () => {
    const { code } = useParams();
    const [room, setRoom] = useState<RoomDto | null>(null);

    const currentUser = useAuthStore().token;

    const fetchRoom = async () => {
        if (!code) return;
        const { data } = await roomApi.joinByCode(code);
        setRoom(data);
    };

    const startGame = async () => {
        if (!code) return;
        await roomApi.startGame(code);
        // Перенаправление на игровой экран
    };

    useEffect(() => {
        fetchRoom();
    }, [code]);

    return (
        <div>
            <h1>Комната: {code}</h1>
            <div>
                {room?.players.map(player => (
                    <div key={player.id}>{player.name}</div>
                ))}
            </div>
            <Button label={"Начать игру"} onClick={startGame} />
        </div>
    );
};