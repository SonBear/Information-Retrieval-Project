import { SearchPanel } from '../../components/search/SearchPanel';
import { DocumentResultList } from '../../components/search/DocumentResultList';
import { Link } from 'react-router-dom';
import { useDocumentSearch } from '../../lib/hooks/useDocumentSearch';

export const DocumentSearch = () => {
  const { searchResult, loading } = useDocumentSearch();

  return (
    <div>
      <Link to="/index">Index web pages</Link>
      <FacetPanel />
      <SearchPanel />
      <DocumentResultList
        loading={loading}
        documents={searchResult?.documents}
      />
    </div>
  );
};
