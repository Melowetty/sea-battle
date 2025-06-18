import "./mainContainer.css";

type props={
    children: React.ReactNode,
    classMain?: string,
    classContent?: string,
}

export function MainContainer({ children, classMain, classContent}: props) {

  return (
        <div className={`main-container ${classMain}`} >
            <div className={`content-container ${classContent}`}>
                {children}
            </div>
        </div>
  );
}