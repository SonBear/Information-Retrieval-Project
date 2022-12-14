import { SpellcheckResult } from '../../../models/search/spellcheck/SpellcheckResult';

export const useSpellcheckResultWrapper = (
  spellcheckResult?: SpellcheckResult,
) => {
  const hasSuggestionsOrCollations = (spellcheckResult: SpellcheckResult) => {
    return (
      spellcheckResult.suggestions.length > 0 ||
      spellcheckResult.collations.length > 0
    );
  };

  const hasSpellcheckResult = () => {
    return spellcheckResult && hasSuggestionsOrCollations(spellcheckResult);
  };

  const getBestSuggestions = () => {
    let bestSuggestions: string[] = [];
    if (!spellcheckResult) {
      return bestSuggestions;
    }
    bestSuggestions = spellcheckResult.suggestions;
    if (spellcheckResult.collations.length > 0) {
      bestSuggestions = spellcheckResult.collations;
    }
    return bestSuggestions;
  };

  return {
    hasSpellcheckResult,
    getBestSuggestions,
  };
};
