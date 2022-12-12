import { SearchPanel } from '../../components/search/documents/SearchPanel';
import { DocumentResultList } from '../../components/search/documents/DocumentResultList';
import { Link } from 'react-router-dom';
import { useSearch } from '../../lib/hooks/useSearch';
import { FacetPanel } from '../../components/search/facet/FacetPanel';
import { SpellcheckerPanel } from '../../components/search/spellcheck/SpellcheckPanel';

export const DocumentSearch = () => {
  const { searchResult, loading } = useSearch();

  return (
    <div>
      <Link to="/index">Index web pages</Link>
      <FacetPanel />
      <SearchPanel />
      <SpellcheckerPanel spellcheckResult={searchResult?.spellcheckResult} />
      <DocumentResultList
        loading={loading}
        documents={searchResult?.documents}
      />
    </div>
  );
};
