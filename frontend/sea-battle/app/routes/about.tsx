import type { Route } from "../../.react-router/types/app/routes/+types";
import {AboutPage} from "~/pages/about";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Кто мы?" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  return <AboutPage />;
}
