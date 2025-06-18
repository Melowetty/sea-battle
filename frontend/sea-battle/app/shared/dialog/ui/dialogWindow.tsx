import "./dialogWindow.css";
import {Dialog} from "radix-ui";
import {Button} from "~/shared/button";
import {useNavigate} from "react-router";

type dialogProps = {
    label?: string;
    title?: string;
    confirm?: string;
    description?: string;
    onClick?: () => void;
    type?: "submit" | "button" | undefined;
}

export function DialogWindow({label = "кнопка", title = "заголовок", description = "Описание", confirm = "Принять", ...props}: dialogProps) {

    const navigate = useNavigate();

  return (
      <Dialog.Root>
          <Dialog.Trigger asChild={true}>
              <Button label={label} onClick={props.onClick} />
          </Dialog.Trigger>
          <Dialog.Portal>
              <Dialog.Overlay className={"dialog-overlay"} />
              <Dialog.Content className="dialog-content">
                  <Dialog.Title className={"dialog-title"}>
                      {title}
                  </Dialog.Title>
                  <Dialog.Description className={"dialog-description"}>
                      {description}
                  </Dialog.Description>
                  <div className="dialog-buttons">
                      <Dialog.Close asChild={true}>
                          <Button variant={"secondary"} label={"0тмена"} onClick={() => {}} />
                      </Dialog.Close>
                      <Dialog.Close asChild={true}>
                          <Button label={confirm} onClick={() => {navigate("/menu")}} />
                      </Dialog.Close>
                  </div>
              </Dialog.Content>
          </Dialog.Portal>
      </Dialog.Root>
  );
}