import { type RouteConfig, index, route } from "@react-router/dev/routes";

export default [
    index("routes/home.tsx"),
    route("/menu", "routes/menu.tsx"),
    route("/about", "routes/about.tsx"),
    route("/play/:id", "routes/play.tsx"),
    route("/win", "routes/win.tsx"),
    route("/lose", "routes/lose.tsx"),
    route("/multiplayer", "routes/multiplayer.tsx"),
    route("/logout", "routes/logout.tsx"),
    route("/game/:id", "routes/game.tsx"),
] satisfies RouteConfig;