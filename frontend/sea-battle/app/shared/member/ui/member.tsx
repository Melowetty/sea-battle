import styles from "./member.module.css";

type memberProps = {
    name: string;
    role: string;
    image: string;
}

export function Member({...props}: memberProps) {

  return (
        <div className={`${styles.memberContainer}`}>
            <img className={`${styles.memberImage}`} src={props.image} alt="image" />
            <span className={`${styles.memberName}`}>{props.name}</span>
            <span className={`${styles.memberRole}`}>{props.role}</span>
        </div>
  );
}