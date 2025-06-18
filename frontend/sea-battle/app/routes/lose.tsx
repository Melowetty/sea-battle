import type { Route } from "../../.react-router/types/app/routes/+types";
import {LosePage} from "~/pages/lose";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Поражение!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  return <LosePage />;
}
