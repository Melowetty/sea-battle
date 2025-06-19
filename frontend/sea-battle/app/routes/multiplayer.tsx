import type { Route } from "../../.react-router/types/app/routes/+types";
import {MultiplayerPage} from "~/pages/multiplayer";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "В сети" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Menu() {
  return <MultiplayerPage />;
}
