import { useGame } from '../lib/useGame';
import { GameBoard } from './gameBoard';
import {useParams} from "react-router-dom";

export const GamePage = ({id}) => {
    const { game, fire } = useGame(id || '');

    if (!game) return <div>Загрузка...</div>;

    const currentPlayerState = game.playerState[game.currentPlayer];
    const opponentId = game.players.find(p => p.id !== game.currentPlayer)?.id;
    const opponentState = opponentId ? game.playerState[opponentId] : null;

    return (
        <div>
            <h1>Морской бой</h1>
            <div>
                <h2>Ваше поле:</h2>
                <GameBoard
                    ships={currentPlayerState.ships}
                    hits={currentPlayerState.hits}
                    misses={currentPlayerState.misses}
                />
            </div>
            <div>
                <h2>Поле противника:</h2>
                {opponentState && (
                    <GameBoard
                        ships={[]} // Не показываем корабли противника
                        hits={opponentState.hits}
                        misses={opponentState.misses}
                        onCellClick={fire}
                        isInteractive={true}
                    />
                )}
            </div>
        </div>
    );
};