import {MainContainer} from "~/widgets/mainContainer";
import "./about.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {AboutContainer} from "~/widgets/aboutContainer";

export function PlayPage() {

    const navigate = useNavigate();

  return (
    <MainContainer>
        <h1 className={'container-title'}>0 НАС</h1>
        <AboutContainer />
        <Button onClick={() => navigate(-1)} label={"Назад"}/>
    </MainContainer>
  )};
