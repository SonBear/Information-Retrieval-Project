import { useState } from 'react';
import { SuggestionResult } from '../../models/search/suggestion/SuggestionResult';
import { getSuggestions } from '../../services/suggest/suggest';

export const useSuggestions = () => {
  const [suggestionResult, setSuggestionResult] = useState<SuggestionResult>();

  const handleSearchQueryChanged = (query: string) => {
    getSuggestions(query).then(result => {
      setSuggestionResult(result);
    });
  };

  const clearSuggestions = () => {
    setSuggestionResult(undefined);
  };

  return {
    suggestionResult,
    handleSearchQueryChanged,
    clearSuggestions,
  };
};
