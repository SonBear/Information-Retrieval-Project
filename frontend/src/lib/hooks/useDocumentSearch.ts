import { useEffect, useState } from 'react';
import { search } from '../../services/search/search';
import { SearchResult } from '../../models/search/SearchResult';
import { useSearchParams } from 'react-router-dom';

export const useDocumentSearch = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [loading, setLoading] = useState(false);
  const [searchResult, setSearchResult] = useState<SearchResult>();
  const query = searchParams.get('query');

  useEffect(() => {
    if (query) {
      handleSearch(query);
    } else {
      searchParams.delete('query');
      setSearchParams(searchParams);
      setSearchResult(undefined);
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
