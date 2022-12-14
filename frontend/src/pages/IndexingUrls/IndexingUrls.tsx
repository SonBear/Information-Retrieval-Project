import { UrlListing } from '../../components/indexing/urls/UrlListing';
import { IndexingOptions } from '../../components/indexing/urls/IndexingOptions';
import {
  IndexingStatus,
  useIndexingUrls,
} from '../../lib/hooks/indexing/useIndexingUrls';
import { useNavigate } from 'react-router-dom';
import { IndexingResultStatusPanel } from '../../components/indexing/urls/IndexingResultStatusPanel';
import { Container, Stack } from 'react-bootstrap';

export const IndexingUrls = () => {
  const navigate = useNavigate();
  const {
    urls,
    lastIndexingResult,
    setUrls,
    handleIndexUrls,
    handleReindexUrls,
    handleDeleteUrls,
    loading,
    status,
  } = useIndexingUrls();

  if (loading) {
    return <div>Loading...</div>;
  }

  const onGoBackClicked = () => {
    navigate(-1);
  };

  return (
    <Container className="m-auto text-center">
      <Stack className="w-75 mx-auto" gap={3}>
        <div
          role="button"
          className="link-primary text-decoration-underline"
          onClick={onGoBackClicked}>
          Go back
        </div>
        <h1 className="text-capitalize">Insert urls</h1>
        <UrlListing urls={urls} onUrlListChanged={setUrls} />
        <IndexingOptions
          showLoadingIndicator={status !== IndexingStatus.NONE}
          loadingIndicatorLabel={status}
          onIndexUrls={handleIndexUrls}
          onReindexUrls={handleReindexUrls}
          onDeleteUrls={handleDeleteUrls}
        />
      </Stack>
      <IndexingResultStatusPanel indexingResult={lastIndexingResult} />
    </Container>
  );
};
