import { When } from 'react-if';
import { Alert, AlertProps, Stack } from 'react-bootstrap';
import { IndexingResultStatus } from '../../../models/indexing-urls/IndexingResultStatus';
import { UrlList } from '../../../models/indexing-urls/UrlList';

export interface IndexingResultStatusPanelProps {
  indexingResult?: IndexingResultStatus;
}

export const IndexingResultStatusPanel = ({
  indexingResult,
}: IndexingResultStatusPanelProps) => {
  if (!indexingResult) {
    return null;
  }

  return <pre>{JSON.stringify(indexingResult, null, 2)}</pre>;
};
