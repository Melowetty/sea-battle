import type { Route } from "../../.react-router/types/app/routes/+types";
import {WinPage} from "~/pages/win";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Победа!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  return <WinPage />;
}
