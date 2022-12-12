import { SpellcheckResult } from '../../../../models/search/spellcheck/SpellcheckResult';
import { appendQueryParams } from '../../../../utils/query-params';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { LanguagePreferenceSelector } from '../LanguagePreferenceSelector';
import { Stack } from 'react-bootstrap';
import { When } from 'react-if';

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
    <>
      <LanguagePreferenceSelector />
      {hasSpellcheckResult(spellcheckResult) && (
        <div className="fs-5">
          <div>Did you mean?</div>
          <Stack direction="horizontal" gap={3}>
            {bestSuggestions.map((bestSuggestion, index) => (
              <>
                <p
                  role="button"
                  className="fst-italic link-primary"
                  key={index}
                  onClick={() => onReplaceForSuggestion(bestSuggestion)}>
                  {bestSuggestion}
                </p>
                {/* Don't show separator if its last element */}
                <When condition={index !== bestSuggestions.length - 1}>
                  <p>/</p>
                </When>
              </>
            ))}
          </Stack>
        </div>
      )}
    </>
  );
};
