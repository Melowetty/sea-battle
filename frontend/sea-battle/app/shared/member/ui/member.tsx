import "./member.css";

type memberProps = {
    name: string;
    role: string;
    image: string;
}

export function Member({...props}: memberProps) {

  return (
        <div className="member-container">
            <img className="member-image" src={props.image} alt="image" />
            <span className="member-name">{props.name}</span>
            <span className="member-role">{props.role}</span>
        </div>
  );
}