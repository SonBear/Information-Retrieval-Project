import { api } from '../http';
import { SearchResult } from '../../models/search/SearchResult';

export const search = (query: string) => {
  return api.get<SearchResult>(`/facade/search?query=${query}`);
};
