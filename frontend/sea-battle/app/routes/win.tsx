import type { Route } from "../../.react-router/types/app/routes/+types";
import {WinPage} from "~/pages/win";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Победа!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {

  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <WinPage />;
}
