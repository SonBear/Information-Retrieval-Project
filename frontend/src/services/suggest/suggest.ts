import { api } from '../http';
import { SuggestionResult } from '../../models/search/suggestion/SuggestionResult';

export const getSuggestions = (query: string) => {
  return api.get<SuggestionResult>(`/facade/suggest/?query=${escape(query)}`);
};
