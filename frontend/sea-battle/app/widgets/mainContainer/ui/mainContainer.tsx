import styles from "./mainContainer.module.css";

type props={
    children: React.ReactNode,
    classMain?: string,
    classContent?: string,
}

export function MainContainer({ children, classMain, classContent}: props) {

  return (
        <div className={`${styles.mainContainer} ${classMain}`} >
            <div className={`${styles.contentContainer} ${classContent}`}>
                {children}
            </div>
        </div>
  );
}