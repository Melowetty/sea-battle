import type { Route } from "../../.react-router/types/app/routes/+types";
import {HomePage} from "~/pages/home";
import {useAuthStore} from "~/features/auth/model/authStore";
import {Navigate} from "react-router";
import React from "react";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "В бой!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Home() {
    return <HomePage />;
}
