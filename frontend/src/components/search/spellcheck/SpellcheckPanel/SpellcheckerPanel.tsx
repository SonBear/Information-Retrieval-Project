import { SpellcheckResult } from '../../../../models/search/spellcheck/SpellcheckResult';
import { appendQueryParams } from '../../../../utils/query-params';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { LanguagePreferenceSelector } from '../LanguagePreferenceSelector';
import { Col, Row } from 'react-bootstrap';

export interface SpellcheckerPanelProps {
  spellcheckResult?: SpellcheckResult;
}

const hasSpellcheckResult = (spellcheckResult: SpellcheckResult) => {
  return (
    spellcheckResult.suggestions.length > 0 ||
    spellcheckResult.collations.length > 0
  );
};

export const SpellcheckerPanel = ({
  spellcheckResult,
}: SpellcheckerPanelProps) => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const onReplaceForSuggestion = (suggestion: string) => {
    searchParams.set('query', suggestion);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  if (!spellcheckResult) {
    return <LanguagePreferenceSelector />;
  }

  let bestSuggestions = spellcheckResult.suggestions;
  if (spellcheckResult.collations.length > 0) {
    bestSuggestions = spellcheckResult.collations;
  }

  return (
    <div className="mt-2">
      <LanguagePreferenceSelector />
      {hasSpellcheckResult(spellcheckResult) && (
        <>
          <div>Did you mean?</div>
          {bestSuggestions.map((bestSuggestion, index) => {
            return (
              <p
                className="text-primary"
                style={{ cursor: 'pointer' }}
                key={index}
                onClick={() => onReplaceForSuggestion(bestSuggestion)}>
                {bestSuggestion}
              </p>
            );
          })}
        </>
      )}
    </div>
  );
};
