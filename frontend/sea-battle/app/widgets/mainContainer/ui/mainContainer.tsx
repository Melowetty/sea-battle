import "./mainContainer.css";

export function MainContainer({ children }: { children: React.ReactNode }) {

  return (
        <main className={"main-container"}>
            <div className={"content-container"}>
                {children}
            </div>
        </main>
  );
}