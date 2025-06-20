import {MainContainer} from "~/widgets/mainContainer";
import styles from "./play.module.css";
import {FieldContainer} from "~/widgets/fieldContainer";
import {FieldBorder} from "~/features/fieldBorder";
import {PlayerField} from "~/features/playerField";
import {EnemyField} from "~/features/enemyField";
import {DialogWindow} from "~/shared/dialog";
import {Header} from "~/widgets/header";
import {Cell} from "~/features/cell";
import {createContext, useCallback, useEffect, useState, useRef} from "react";
import {useNavigate} from "react-router";
import {getGame, leaveGame} from "~/features/game/model/game";
import { type CellHandle } from "~/features/cell/ui/cell";
import { shot } from "~/features/game/model/game";



type WaitContextType = {
    waiting: boolean;
    toggleWaitState: () => void;
    setWaitState: (value: boolean) => void;
};

export const WaitContext = createContext<WaitContextType>({
    waiting: false,
    toggleWaitState: () => {},
    setWaitState: () => {}
});

export function PlayPage(id:string) {

    // const [position, setPosition] = useState<Position>({ x: 0, y: 0, tag: '' });
    //
    // const updatePosition = (newPosition: Partial<Position>) => {
    //     setPosition(prev => ({ ...prev, ...newPosition }));
    // };

    const items = Array.from({ length: 10 }, (_, index) => ({
        value: index + 1,
    }));

    const navigate = useNavigate();
    const intervalIdRef = useRef(null);
    const [waiting, setWaiting] = useState<boolean>(false);
    const [hits, setHits] = useState([]);


    const [playerOne, setPlayerOne] = useState();
    const [playerTwo, setPlayerTwo] = useState();

    useEffect(() => {
        const handleBeforeUnload = (e) => {
            e.preventDefault();
            return e.returnValue = 'Вы уверены, что хотите обновить страницу?';
        };

        window.addEventListener('beforeunload', handleBeforeUnload);

        return () => {
            window.removeEventListener('beforeunload', handleBeforeUnload);
        };
    }, []);

    const getDataGame = () => {
        const data = getGame(id.id);
        data.then((game) => {
            console.log(game);
            console.log(game.data.currentPlayer);
            if (game.data.currentPlayer !== "00000000-0000-0000-0000-000000000001"){
                const combined = [
                    ...game.data.playerState.hits.map(item => [item, 'hits'] as const),
                    ...game.data.playerState.misses.map(item => [item, 'misses'] as const),
                    ...game.data.playerState.destructions.map(item => [item, 'destructions'] as const)
                ];
                console.log(combined);
                const result = combined.filter(x => !hits.includes(x));
                result.map((item) => {
                    console.log(item[0].x, item[0].y);
                    handleAlly(item[0].x, item[0].y, item[1]);
                });
                setHits(combined);
            };
        });
    }



    useEffect(() => {
        intervalIdRef.current = setInterval(getDataGame, 10000);

        return () => {
            clearInterval(intervalIdRef.current);
        };
    }, []);

    const handleSurrender = () => {
        const data = leaveGame(id.id);
        data.then((game) => {
            console.log(game);
            navigate("/lose");
        });
        // navigate('/lose')
    };


    const [ships, setShips] = useState();
    const [coordsArray, setCoords] = useState([]);


    useEffect(() => {
        const data = getGame(id.id);
        data.then((game) => {
            const ships = game.data.playerState.ships;
            // console.log(ships);
            // setPlayerOne(game.data.players);
            // setPlayerTwo(game.data.players)
            setShips(ships);
            ships.map((ship) => {
                const coords = ship.coordinates;
                coords.map((coord) => {
                    coordsArray.push(coord);
                })
            })
        })
    }, [])


    useEffect(() => {
        // console.log(coordsArray);
    }, [ships]);


    const [status, setStatus] = useState("waiting");

    const handleEnemy = (x: number, y: number) => {
        console.log(x, y);
        const xNew = x-1;
        const yNew = y-1;
        const data = shot(id.id, { xNew, yNew });
        data.then((data) => {
            const result = data.data.event;
            const cell = document.getElementById(`${x}${y}enemy`);
            if (cell) {
                if (result === "HIT") {
                    cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
                } else if (result === "MISS") {
                    cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
                    setWaiting(true);
                }
            }
        });
    };

    const handleAlly = (x: number, y: number, tag: string) => {
        const cell = document.getElementById(`${x+1}${y+1}ally`);
        console.log("pdasdada");
        if (cell) {
            if (tag === "hits") {
                setStatus("checked");
                cell.innerHTML = `<img src="../../../../assets/images/red_cross.png" alt="❌"/>`;
            } else if (tag === "destructions") {
                setStatus("miss");
            } else {
                setStatus("miss");
                cell.innerHTML = `<img src="../../../../assets/images/circle.png" alt="⚫"/>`;
                setWaiting(false);
            }
        }
    };



  return (
      <>
        <MainContainer>
            <div className={`${styles.playContainer}`}>
                <FieldContainer>
                    <h1
                        className={`${styles.containerTitle}`}>
                        Игрок <img className={styles.avatar} src={"../../../../assets/images/stepan.png"} />
                    </h1>
                    <FieldBorder>
                        {items.map((row) => (
                            <tr>
                                <th>{row.value}</th>
                                {items.map((item) => {

                                    if (coordsArray.some(coords => coords.y === (row.value - 1) && coords.x === (item.value - 1))) {
                                        return (
                                            <th
                                                data-x={item.value}
                                                data-y={row.value}
                                                status={""}
                                                id={`${item.value}${row.value}${"ally"}`}
                                                className={
                                                    waiting === false
                                                        ? `${styles.cell} ${styles.ship}`
                                                        : `${styles.cell} ${styles.ship} ${styles.disabled}`
                                                }
                                                onClick={
                                                    () => handleEnemy(item.value, row.value)
                                                }
                                            ></th>
                                        );
                                    }
                                    else{
                                        return (
                                                <th
                                                    data-x={item.value}
                                                    data-y={row.value}
                                                    status={""}
                                                    id={`${item.value}${row.value}${"ally"}`}
                                                    className={
                                                        waiting === false
                                                            ? `${styles.cell} ${styles.empty}`
                                                            : `${styles.cell} ${styles.empty} ${styles.disabled}`
                                                    }
                                                    onClick={
                                                        () => handleEnemy(item.value, row.value)
                                                    }
                                                ></th>
                                            )
                                    }
                                })}
                            </tr>
                        ))}
                    </FieldBorder>
                </FieldContainer>
                <div className={`${styles.statsContainer}`}>
                    {!waiting ? (
                        <h1 className={styles.turnActive}>
                            ВАШ Х0Д!
                        </h1>
                    ):(
                        <h1 className={styles.turnWait}>
                            Х0Д ПР0ТИВНИКА!
                        </h1>
                    )}

                    <DialogWindow
                        confirm={"Завершить"}
                        title={"Завершение игры"}
                        description={"Вы уверены, что хотите завершить игру?"}
                        label="Завершить игру"
                        onClick={handleSurrender} />
                </div>
                <FieldContainer>
                    <h1 className={`${styles.containerTitle}`}>Ботик</h1>
                    <FieldBorder>
                        {items.map((row) => (
                            <tr>
                                <th>{row.value}</th>
                                {items.map((item) => (
                                    <th
                                        data-x={item.value}
                                        data-y={row.value}
                                        id={`${item.value}${row.value}${"enemy"}`}
                                        className={
                                            waiting === false
                                                ? `${styles.cell} ${styles.enemy}`
                                                : `${styles.cell} ${styles.enemy} ${styles.disabled}`
                                        }
                                        onClick={
                                            () => handleEnemy(item.value, row.value)
                                        }
                                    ></th>
                                ))}
                            </tr>
                        ))}
                    </FieldBorder>
                </FieldContainer>
            </div>
        </MainContainer>
      </>
  )};
