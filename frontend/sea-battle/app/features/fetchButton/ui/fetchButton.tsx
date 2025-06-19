import styles from "./fetchButton.module.css";
import {Button} from "~/shared/button";
import React from "react";
import {Loading} from "~/shared/loading";

type buttonProps = {
    onClick?: () => void;
    label?: string;
}

export function FetchButton({label = "кнопка", ...props}: buttonProps) {
    const [isLoading, setIsLoading] = React.useState(false);

    const handleClick = () => {
        setIsLoading(true);
        props.onClick && props.onClick();
    }

  return (
      <>
          {!isLoading && (
              <Button onClick={handleClick} label={label} />
          )}
          {isLoading && (
                <Loading />
          )}
      </>
  );
}