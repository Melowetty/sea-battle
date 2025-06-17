import type { Route } from "../../.react-router/types/app/routes/+types";
import {HomePage} from "~/pages/home";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "В бой!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Home() {
  return <HomePage />;
}
