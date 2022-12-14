import { When } from 'react-if';
import { Alert, AlertProps, Stack } from 'react-bootstrap';
import { IndexingResultStatus } from '../../../../models/indexing/urls/IndexingResultStatus';
import { UrlList } from '../../../../models/indexing/urls/UrlList';

export interface IndexingResultStatusPanelProps {
  indexingResult?: IndexingResultStatus;
}

export const IndexingResultStatusPanel = ({
  indexingResult,
}: IndexingResultStatusPanelProps) => {
  if (!indexingResult) {
    return null;
  }

  return (
    <Stack gap={3} className="mt-3">
      <IndexingUrlsListResultAlert
        variant="success"
        alertHeading="Successfully indexed"
        urlList={indexingResult.successfullyIndexedUrlSet}
      />
      <IndexingUrlsListResultAlert
        variant="danger"
        alertHeading="Failed indexing"
        urlList={indexingResult.failedToIndexUrlSet}
      />
      <IndexingUrlsListResultAlert
        variant="secondary"
        alertHeading="Already indexed"
        urlList={indexingResult.alreadyIndexedUrlSet}
      />
    </Stack>
  );
};

interface IndexingUrlsListResultAlertProps extends AlertProps {
  alertHeading: string;
  urlList: UrlList;
}

const IndexingUrlsListResultAlert = ({
  alertHeading,
  urlList,
  ...rest
}: IndexingUrlsListResultAlertProps) => {
  if (!urlList) {
    return null;
  }

  return (
    <When condition={urlList.urls.length}>
      <Alert {...rest}>
        <Alert.Heading className="text-capitalize">
          {alertHeading}
        </Alert.Heading>
        <ul className="text-start">
          {urlList.urls.map(url => (
            <li>{url}</li>
          ))}
        </ul>
      </Alert>
    </When>
  );
};
