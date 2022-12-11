import { useEffect, useState } from 'react';
import { search } from '../../services/search/search';
import { SearchResult } from '../../models/search/SearchResult';
import { useSearchParams } from 'react-router-dom';

export const useDocumentSearch = () => {
  const [searchParams] = useSearchParams();
  const [loading, setLoading] = useState(false);
  const [searchResult, setSearchResult] = useState<SearchResult>();
  const query = searchParams.get('query');

  useEffect(() => {
    if (query) {
      handleSearch(query);
    }
  }, [query]);

  const handleSearch = (query: string) => {
    setLoading(true);
    search(query).then(result => {
      setSearchResult(result);
      setLoading(false);
    });
  };

  return { searchResult, handleSearch, loading };
};
