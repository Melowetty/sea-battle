import {MainContainer} from "~/widgets/mainContainer";
import styles from "./about.module.css";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";
import {AboutContainer} from "~/widgets/aboutContainer";
import {Header} from "~/widgets/header";

export function AboutPage() {

    const navigate = useNavigate();

  return (
      <>
        {/*<Header />*/}
        <MainContainer>
            <h1 className={`${styles.containerTitle}`}>0 НАС</h1>
            <AboutContainer />
            <Button onClick={() => navigate(-1)} label={"Назад"}/>
        </MainContainer>
      </>
  )};
