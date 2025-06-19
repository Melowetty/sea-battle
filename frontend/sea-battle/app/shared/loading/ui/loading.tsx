import styles from "./loading.module.css";

export function Loading() {

  return (
        <div className={styles.loading}>
            <img className={styles.loadingImg} alt={"loading"} src={"../../../../assets/images/chest.gif"} />
        </div>
  );
}