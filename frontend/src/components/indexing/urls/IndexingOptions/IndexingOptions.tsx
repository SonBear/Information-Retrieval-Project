import { Stack } from 'react-bootstrap';
import { When } from 'react-if';
import React from 'react';
import { IndexingOptionButton } from '../IndexingOptionButton';

export interface IndexURLOptionsProps {
  onIndexUrls?: () => void;
  onReindexUrls?: () => void;
  onDeleteUrls?: () => void;
  loadingIndicatorLabel: string;
  showLoadingIndicator?: boolean;
}

export const IndexingOptions = ({
  loadingIndicatorLabel,
  showLoadingIndicator = false,
  onIndexUrls = () => {},
  onReindexUrls = () => {},
  onDeleteUrls = () => {},
}: IndexURLOptionsProps) => {
  return (
    <Stack direction="horizontal" gap={3} className="justify-content-end">
      <When condition={showLoadingIndicator}>
        <div>{loadingIndicatorLabel}</div>
      </When>
      <IndexingOptionButton
        variant="outline-primary"
        onClick={onIndexUrls}
        showLoadingIndicator={showLoadingIndicator}>
        Index
      </IndexingOptionButton>
      <IndexingOptionButton
        variant="outline-secondary"
        onClick={onReindexUrls}
        showLoadingIndicator={showLoadingIndicator}>
        Reindex
      </IndexingOptionButton>
      <IndexingOptionButton
        variant="outline-danger"
        onClick={onDeleteUrls}
        showLoadingIndicator={showLoadingIndicator}>
        Delete
      </IndexingOptionButton>
    </Stack>
  );
};
