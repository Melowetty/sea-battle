import type { Route } from "../../.react-router/types/app/routes/+types";
import React, {useEffect} from "react";
import {HomePage} from "~/pages/home";
import {useAuthStore} from "~/features/auth/model/authStore";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "В бой!" },
    { name: "description", content: "Home to React Router!" },
  ];
}

export default function Logout() {

    const logout = useAuthStore();
    useEffect(() => {
        logout.clearAuthData();
    }, []);

    return <HomePage />;
}
