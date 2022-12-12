import { UrlListing } from '../../components/indexing-urls/UrlListing';
import { IndexingOptions } from '../../components/indexing-urls/IndexingOptions';
import {
  IndexingStatus,
  useIndexingUrls,
} from '../../lib/hooks/useIndexingUrls';
import { Link, useSearchParams } from 'react-router-dom';
import { IndexingResultStatusPanel } from '../../components/indexing-urls/IndexingResultStatusPanel';
import { Container, Stack } from 'react-bootstrap';
import { appendQueryParams } from '../../utils/query-params';

export const IndexingUrls = () => {
  const [searchParams] = useSearchParams();
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

  return (
    <Container className="m-auto text-center">
      <Stack className="w-75 mx-auto" gap={3}>
        <Link to={appendQueryParams('/', searchParams.toString())}>
          Go back
        </Link>
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