import { IndexingResultStatus } from '../../../services/urls/urls';

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
