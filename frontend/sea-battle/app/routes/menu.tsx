import type { Route } from "../../.react-router/types/app/routes/+types";
import {MenuPage} from "~/pages/menu";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Главная страница" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Menu() {

  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <MenuPage />;
}
