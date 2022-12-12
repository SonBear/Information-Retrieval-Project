import { api } from '../http';
import { SearchResult } from '../../models/search/documents/SearchResult';
import { appendQueryParams } from '../../utils/query-params';

export const search = (params: string) => {
  return api.get<SearchResult>(appendQueryParams(`/facade/search`, params));
};
