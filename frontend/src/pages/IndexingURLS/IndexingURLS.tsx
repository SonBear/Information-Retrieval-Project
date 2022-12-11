import { URLListing } from '../../components/indexing-urls/URLListing';
import { IndexingOptions } from '../../components/indexing-urls/IndexingOptions';
import { useIndexingURLS } from '../../lib/hooks/useIndexingURLS';
import { Then, When } from 'react-if';
import { Link } from 'react-router-dom';
import { IndexingResultStatusPanel } from '../../components/indexing-urls/IndexingResultStatusPanel';

export const IndexingURLS = () => {
  const {
    urls,
    lastIndexingResult,
    setUrls,
    handleIndexUrls,
    handleReindexUrls,
    handleDeleteUrls,
    loading,
    indexing,
  } = useIndexingURLS();

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <div>
        <Link to="/">Go back</Link>
        <p>Insert urls to index</p>
      </div>
      <URLListing urls={urls} onUrlListChanged={setUrls} />
      <IndexingOptions
        onIndexUrls={handleIndexUrls}
        onReindexUrls={handleReindexUrls}
        onDeleteUrls={handleDeleteUrls}
      />
      <When condition={indexing}>
        <Then>
          <p>Indexing URLS...</p>
        </Then>
      </When>
      <IndexingResultStatusPanel indexingResult={lastIndexingResult} />
    </div>
  );
};
