import styles from "./dialogWindow.module.css";
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
              <Button label={label} />
          </Dialog.Trigger>
          <Dialog.Portal>
              <Dialog.Overlay className={`${styles.dialogOverlay}`} />
              <Dialog.Content className={`${styles.dialogContent}`}>
                  <Dialog.Title className={`${styles.dialogTitle}`}>
                      {title}
                  </Dialog.Title>
                  <Dialog.Description className={`${styles.dialogDescription}`}>
                      {description}
                  </Dialog.Description>
                  <div className={`${styles.dialogButtons}`}>
                      <Dialog.Close asChild={true}>
                          <Button variant={"secondary"} label={"0тмена"} onClick={() => {}} />
                      </Dialog.Close>
                      <Dialog.Close asChild={true}>
                          <Button label={confirm} onClick={props.onClick} />
                      </Dialog.Close>
                  </div>
              </Dialog.Content>
          </Dialog.Portal>
      </Dialog.Root>
  );
}