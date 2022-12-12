import { useEffect, useState } from 'react';
import { search } from '../../services/search/search';
import { SearchResult } from '../../models/search/documents/SearchResult';
import { useSearchParams } from 'react-router-dom';

export const useSearch = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [loading, setLoading] = useState(false);
  const [searchResult, setSearchResult] = useState<SearchResult>();
  const query = searchParams.get('query');

  useEffect(() => {
    if (query) {
      handleSearch(searchParams.toString());
    } else {
      searchParams.delete('query');
      setSearchParams(searchParams);
      setSearchResult(undefined);
    }
  }, [searchParams]);

  const handleSearch = (params: string) => {
    setLoading(true);
    search(params).then(result => {
      setSearchResult(result);
      setLoading(false);
    });
  };

  return { searchResult, handleSearch, loading };
};
