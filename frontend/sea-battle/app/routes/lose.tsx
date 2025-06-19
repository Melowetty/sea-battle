import type { Route } from "../../.react-router/types/app/routes/+types";
import {LosePage} from "~/pages/lose";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Поражение!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <LosePage />;
}
