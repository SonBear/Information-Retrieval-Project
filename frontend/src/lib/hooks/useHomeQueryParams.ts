import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../utils/query-params';

export const useHomeQueryParams = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const setSearchQuery = (query: string) => {
    // Reset to page 1 whenever a new search is made.
    searchParams.delete('page');
    setQueryParam('query', query);
  };

  const setQueryParam = (queryParam: string, value: string) => {
    searchParams.set(queryParam, value);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  return {
    setSearchQuery,
    setQueryParam,
  };
};
