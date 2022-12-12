import { Button, ButtonProps, Spinner } from 'react-bootstrap';
import { Else, If, Then } from 'react-if';
import React from 'react';

export interface IndexingOptionButtonProps extends ButtonProps {
  showLoadingIndicator?: boolean;
  children: JSX.Element | JSX.Element[] | string;
}

export const IndexingOptionButton = ({
  showLoadingIndicator,
  children,
  ...rest
}: IndexingOptionButtonProps) => {
  return (
    <Button disabled={showLoadingIndicator} {...rest}>
      <If condition={showLoadingIndicator}>
        <Then>
          <Spinner
            as="span"
            animation="border"
            size="sm"
            role="status"
            aria-hidden="true"
            className="me-1"
          />
          <span className="visually-hidden">Loading...</span>
          {children}
        </Then>
        <Else>{children}</Else>
      </If>
    </Button>
  );
};
