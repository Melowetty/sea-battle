import type { Route } from "../../.react-router/types/app/routes/+types";
import {PlayPage} from "~/pages/play";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";
import {GamePage} from "~/features/game/ui/game";
import {useParams} from "react-router-dom";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Морской бой" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {

  const { isAuthenticated } = useAuthStore();
  const {id} = useParams<{ id: string }>();

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <PlayPage id={id} />;
}
