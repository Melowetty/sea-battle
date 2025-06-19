import styles from "./separator.module.css";

export function Separator() {

  return (
      <div className={styles.separatorContainer}>
          <img className={styles.separator} alt={"separator"} src={"../../../../assets/images/separator.PNG"} />
      </div>
  );
}