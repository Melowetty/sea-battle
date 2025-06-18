import {MainContainer} from "~/widgets/mainContainer";
import "./menu.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {useState} from "react";

export function MenuPage() {

    const navigate = useNavigate();

  return (
    <MainContainer>
        <h1 className={'container-title'}>МЕНЮ</h1>
        <Button onClick={() => {navigate("/play")}} label={"0диночная игра"} />
        <Button onClick={() => {navigate("/multiplayer")}} label={"В сети"} />
        <Button onClick={() => {navigate("/about")}} label={"0 нас"} />
        <img className={"animation"} src={"../../../assets/images/pirateFlag.gif"} />
    </MainContainer>
  )};
