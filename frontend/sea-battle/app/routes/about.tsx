import type { Route } from "../../.react-router/types/app/routes/+types";
import {AboutPage} from "~/pages/about";
import {HomePage} from "~/pages/home";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "Кто мы?" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function About() {
  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <AboutPage />;
}
