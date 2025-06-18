import type { Route } from "../../.react-router/types/app/routes/+types";
import {PlayPage} from "~/pages/play";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Морской бой" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  return <PlayPage />;
}
