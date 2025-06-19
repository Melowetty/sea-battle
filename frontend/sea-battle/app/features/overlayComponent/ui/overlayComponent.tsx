import styles from "./overlayComponent.module.css";

type overlayProps = {
    classOverlay?:string,
    classLogo?: string,
}

export function OverlayComponent({classOverlay, classLogo}:overlayProps) {

  return (
      <div className={`${styles.overlayComponent} ${classOverlay}`}>
        <img className={`${styles.overlayLogo} ${classLogo}`} src={"../../../../assets/images/logo.png"} alt="seabattle" />
      </div>
  );
}