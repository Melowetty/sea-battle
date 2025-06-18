import  "./overlayComponent.css";

export function OverlayComponent({classOverlay, classLogo}) {

  return (
      <div className={`overlay-component ${classOverlay}`}>
        <img className={`overlay-logo ${classLogo}`} src={"../../../../assets/images/logo.png"} alt="seabattle" />
      </div>
  );
}