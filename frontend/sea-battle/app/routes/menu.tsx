import type { Route } from "../../.react-router/types/app/routes/+types";
import {MenuPage} from "~/pages/menu";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Главная страница" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Menu() {
  return <MenuPage />;
}
