import { SpellcheckResult } from '../../../../models/search/spellcheck/SpellcheckResult';
import { LanguagePreferenceSelector } from '../LanguagePreferenceSelector';
import { Stack } from 'react-bootstrap';
import { When } from 'react-if';
import { useHomeQueryParams } from '../../../../lib/hooks/useHomeQueryParams';
import { useSpellcheckResultWrapper } from '../../../../lib/hooks/spellcheck/useSpellcheckResultWrapper';

export interface SpellcheckerPanelProps {
  spellcheckResult?: SpellcheckResult;
}

export const SpellcheckerPanel = ({
  spellcheckResult,
}: SpellcheckerPanelProps) => {
  const { setSearchQuery } = useHomeQueryParams();
  const { hasSpellcheckResult, getBestSuggestions } =
    useSpellcheckResultWrapper(spellcheckResult);

  const onReplaceForSuggestion = (suggestion: string) => {
    setSearchQuery(suggestion);
  };

  if (!spellcheckResult) {
    return <LanguagePreferenceSelector />;
  }

  const bestSuggestions = getBestSuggestions();

  return (
    <>
      <LanguagePreferenceSelector />
      {hasSpellcheckResult() && (
        <div className="fs-5">
          <div>Did you mean?</div>
          <Stack direction="horizontal" gap={3}>
            {bestSuggestions.map((suggestion, index) => (
              <>
                <p
                  role="button"
                  className="fst-italic link-primary"
                  key={index}
                  onClick={() => onReplaceForSuggestion(suggestion)}>
                  {suggestion}
                </p>
                {/* Don't show separator when last element */}
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
